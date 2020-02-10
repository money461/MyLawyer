package com.tz.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tz.activemq.Producer;
import com.tz.cache.RedisCache;
import com.tz.id.IDUtils;
import com.tz.mapper.index.vo.ZaSysNoticeMapperVo;
import com.tz.mapper.index.vo.ZaUserAwardRecordMapperVo;
import com.tz.mapper.index.vo.ZaUserIncomeRecordMapperVo;
import com.tz.mapper.index.vo.ZaUserProfitMapperVo;
import com.tz.mapper.index.vo.ZaUserPublishCaseMapperVo;
import com.tz.pojo.ZaSysNotice;
import com.tz.pojo.ZaUserAwardRecord;
import com.tz.pojo.ZaUserIncomeRecord;
import com.tz.pojo.ZaUserProfit;
import com.tz.pojo.ZaUserPublishCase;
import com.tz.res.AppMsgResult;
import com.tz.res.Constant;
import com.tz.service.CaseHandleService;

@Service
public class CaseHandleServiceImpl implements CaseHandleService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RedisCache cache;

	@Autowired
	private ZaUserPublishCaseMapperVo zaUserPublishCaseMapperVo;
	
	@Autowired
	private ZaUserIncomeRecordMapperVo zaUserIncomeRecordMapperVo;
	
	@Autowired
	private ZaUserAwardRecordMapperVo zaUserAwardRecordMapperVo;
	
	@Autowired
	private ZaUserProfitMapperVo zaUserProfitMapperVo;
	
	@Autowired
	private ZaSysNoticeMapperVo zaSysNoticeMapperVo;
	
	@Autowired
	private Producer producer;
	
	//校验useruserToken是否过期存在
	public AppMsgResult checkUserToken(String userId,String userToken){
		AppMsgResult msgResult = null; 
			if(StringUtils.isNotEmpty(userToken)){
				//获取用户的登录 token
				String cache_key=RedisCache.CAHCENAME+"|getUserToken|"+userId;
				String userToken_cache = cache.getCache(cache_key, String.class);
				//是否存在
				if(null != userToken_cache && userToken.equals(userToken_cache)){
					//重置userToken有效时间
					cache.putCacheWithExpireTime(cache_key, userToken_cache, RedisCache.USERCAHCETIME);
					msgResult= AppMsgResult.nodata(true,"success");
				}else{
					msgResult= AppMsgResult.nodata(true, "failure");
				}
			}else{
			msgResult= AppMsgResult.nodata(false, "failure");
		}
		return msgResult;
		
	}

	/**
	 * 律师发出帮助请求
	 * @param id  //案件id
	 * @param uId  //用户id
	 * @param date  //案件日期
	 * @param userId //律师id
	 * @param userToken
	 */
	@Override
	public AppMsgResult lawHandlingCase(String id,String userId, String userToken) {
		AppMsgResult result =null;
		AppMsgResult msgResult = checkUserToken(userId, userToken);
		if((boolean)msgResult.getFlag()) {
			//缓存校验该律师是否已对该案件发出过请求 律师id+案件id
			final String lawHandlingCasecache_key=RedisCache.CAHCENAME+"|lawHandlingCasecache_key|"+userId+id;
			
			String lawHandlcache = cache.getCache(lawHandlingCasecache_key, String.class);
			if(lawHandlcache!=null) {
				return AppMsgResult.result(677, "您今天已经对该案件发出过帮助请求,请耐心等候！", null);
			}
			
			//查询案件信息
			ZaUserPublishCase publishCase = zaUserPublishCaseMapperVo.queryPublishCaseById(id);
			if(publishCase==null) {
				return AppMsgResult.result(543, "该案件未找到！",null);
			}
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String date = format.format(publishCase.getUpdatedTime());
			//发出通知
			String msg = "愿意帮助你解决--"+date+"发布的委托案件哟！请及时回复~O(∩_∩)O~";
			//创建消息实体
			ZaSysNotice zaSysNotice = new ZaSysNotice();
			zaSysNotice.setSystemId(Constant.SYSTEMID);
			zaSysNotice.setFromUserId(userId);//律师发出帮助通知
			zaSysNotice.setToUserId(publishCase.getUserId()); //接受通知用户id
			zaSysNotice.setEventId(id); //提出帮助的案件
			zaSysNotice.setNoticeType(1);  //消息类型案件委托通知
			zaSysNotice.setMessage(msg);
			//序列化为json字符串
			String string = JSONObject.toJSONString(zaSysNotice);
			//创建消息目的对象Destination (点对点Queue)
			 Destination destination = new ActiveMQQueue(Constant.LAWYERHANDLECASE_QUEUE);  
			 //发送消息
			 producer.sendMessage(destination,string);
			 
			 //对该次请求设置缓存记录 默认缓存1天
			 String token = userId+id;
			 cache.putCacheWithExpireTime(lawHandlingCasecache_key, token, RedisCache.CAHCETIME);
			 result = AppMsgResult.result(200,"已成功通知了客户，请等待客户同意与否！",null);
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		
		return result;
	}
	
	
	/**
	 * 用户允许律师接单(修改案件状态)或者忽略律师
	 * @param id  系统通知主键id
	 * @param agree  //忽略0还是接受1
	 * @param userId  //用户id
	 * @param userToken
	 * @return
	 */
	@Override
	public AppMsgResult userOrderTaking(Long id, Integer agree,String userId, String userToken) {
		AppMsgResult result =null;
		AppMsgResult msgResult = checkUserToken(userId, userToken);
		if((boolean)msgResult.getFlag()) {
			if(agree==null || agree!=0 && agree!=1) {
				return AppMsgResult.result(543, "是否接受参数标识传值有误！",null);
			}
			//查询通知
			ZaSysNotice new_zaSysNotice = zaSysNoticeMapperVo.querySystNoticeById(id);
			if(new_zaSysNotice==null) {
				return AppMsgResult.result(543, "该条通知记录过期找不到了！",null);
			}
			//修改通知状态
			new_zaSysNotice.setAgree(agree);
			new_zaSysNotice.setHandleState(1);  //律师请求通知已处理
			Integer i = zaSysNoticeMapperVo.updateSysNoticeById(new_zaSysNotice);
			if(i!=1) {
				return AppMsgResult.result(543, "系统未找到该记录！！",null);
			}
			//判断是否忽略
			if(agree==0) {
				//忽略拒绝该请求
				//创建消息目的对象Destination (点对点Queue)
				 Destination destination = new ActiveMQQueue(Constant.USERORDETAKING_QUEUE);
				 //发送消息
				 String msg = "客户拒绝了您受理案件的请求！！";
				//创建消息实体
					ZaSysNotice zaSysNotice = new ZaSysNotice();
					zaSysNotice.setSystemId(Constant.SYSTEMID);
					zaSysNotice.setFromUserId(userId);
					zaSysNotice.setToUserId(new_zaSysNotice.getFromUserId());
					zaSysNotice.setEventId(new_zaSysNotice.getEventId());
					zaSysNotice.setNoticeType(0);
					zaSysNotice.setHandleState(1);
					zaSysNotice.setMessage(msg);
					//序列化为json字符串
					String string = JSONObject.toJSONString(zaSysNotice);
				    producer.sendMessage(destination,string);
				    result =  AppMsgResult.result(200, "您已拒绝了律师处理该案件的请求！系统以为你通知了律师",null);
			}else if(agree==1) {
				//修改案件状态
				ZaUserPublishCase publishCase = zaUserPublishCaseMapperVo.queryPublishCaseById(new_zaSysNotice.getEventId());
				if(publishCase.getStatus()==0) {
					publishCase.setStatus(1);//案件正在解决中
					publishCase.setLawId(new_zaSysNotice.getFromUserId());//认证律师id
					Integer k = zaUserPublishCaseMapperVo.updatePublishCase(publishCase);
					if(k==1) {
						//系统通知案件用户，已被律师接受
						String uId = publishCase.getUserId();
						 //发送消息
						 String msg = "该案件客户接受了您受理案件的请求！！";
						//创建消息实体
						ZaSysNotice zaSysNotice = new ZaSysNotice();
						zaSysNotice.setSystemId(Constant.SYSTEMID);
						zaSysNotice.setFromUserId(uId); //提出通知用户id
						zaSysNotice.setToUserId(new_zaSysNotice.getFromUserId()); //接受通知用户id
						zaSysNotice.setEventId(new_zaSysNotice.getEventId());
						zaSysNotice.setNoticeType(0);
						zaSysNotice.setHandleState(1);
						zaSysNotice.setMessage(msg);
						//序列化为json字符串
						String string = JSONObject.toJSONString(zaSysNotice);
						//创建消息目的对象Destination (点对点Queue)
						 Destination destination = new ActiveMQQueue(Constant.USERORDETAKING_QUEUE);
						 producer.sendMessage(destination,string);
						
						result = AppMsgResult.result(200, "案件已成功接受，系统已通知了您的客户，请尽快与客户保持联系！",null);
						
					}else {
						result = AppMsgResult.result(559, "接单失败！",null);
					}
				}else if(publishCase.getStatus()==1) {
					return AppMsgResult.result(538, "该案件已有律师接收！",null);
				}else {
					return AppMsgResult.result(538, "系统异常，请稍后再试！",null);
				}
				
			}
			
			
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		return result;
	}


	/**
	 * 用户或者律师放弃案例解决 userType =1普通用户 2律师用户(清除law_id至为null)
	 *  案件id
	 */
	@Override
	public AppMsgResult abandonPublishCase(String id, Integer userType, String userId, String userToken) {
		AppMsgResult result =null;
		AppMsgResult msgResult = checkUserToken(userId, userToken);
		if((boolean)msgResult.getFlag()) {
			
			if(userType==null || userType!=1 && userType!=2) {
				return AppMsgResult.result(538, "用户类型值传输有误！",null);
			}
			//查询案例获取案例请求者和律师id
			ZaUserPublishCase zaUserPublishCase = zaUserPublishCaseMapperVo.queryPublishCaseById(id);
			if(zaUserPublishCase==null) {
				return AppMsgResult.result(538, "系统没有找到案件！！",null);
			}
			Integer status = zaUserPublishCase.getStatus();
			String userId2 = zaUserPublishCase.getUserId();  //获取案件发布用户id
			//修改案件状态
			if(userType==1) {
				if(status!=0 && status!=1) {
					return AppMsgResult.result(538, "系统逻辑错误，请联系管理员！",null);
				}
				//普通用户放弃 status=3
				zaUserPublishCase.setStatus(3);
				//修改案件状态
				Integer i = zaUserPublishCaseMapperVo.updatePublishCase(zaUserPublishCase);
				if(i==1) {
					   //将案件悬赏赏金退回给用户
						String awardId = zaUserPublishCase.getAwardId();
						//获取悬赏金额并将打赏状态设置为打赏取消award_status=2
						ZaUserAwardRecord zaUserAwardRecord = zaUserAwardRecordMapperVo.getRewardRecordById(awardId);
						BigDecimal reward = zaUserAwardRecord.getReward();
						//设置为取消打赏状态 设置为打赏取消2
						Integer k = zaUserAwardRecordMapperVo.alertAwardRecordStatus(awardId);
						if(k==1) {
							//状态修改成功后，1、将金额返还给用户增加剩余金额，2、记录至收益表，
							ZaUserProfit userProfit= zaUserProfitMapperVo.getUserProfitById(userId2);
							userProfit.setCoinSurplus(userProfit.getCoinSurplus().add(reward));
							Integer l = zaUserProfitMapperVo.updateUserProfitById(userProfit);
							if(l==1) {
								ZaUserIncomeRecord zaUserIncomeRecord =new ZaUserIncomeRecord();
								String genId = IDUtils.genId();
								zaUserIncomeRecord.setId(genId);
								zaUserIncomeRecord.setUserId(userId2);
								zaUserIncomeRecord.setUserType(1);//普通用户
								zaUserIncomeRecord.setDealCode(awardId);
								zaUserIncomeRecord.setInCash(reward);
								zaUserIncomeRecord.setInStatus(1);
								zaUserIncomeRecord.setInType(8);//取消悬赏
								zaUserIncomeRecord.setInOut(2);
								zaUserIncomeRecord.setEventDesc("您取消了一个悬赏案件，系统返还案件打赏。");
								zaUserIncomeRecord.setIncomeTime(new Date());
								zaUserIncomeRecordMapperVo.insertUserIncomeRecord(zaUserIncomeRecord);
							}else {
								return AppMsgResult.result(563, "用户收益表更新失败！",null);
							}
							
						}else {
							return AppMsgResult.result(563, "悬赏修改失败！",null);
						}
						
						//向律师发出通知消息
						String lawId = zaUserPublishCase.getLawId();
						if(lawId!=null) {
							//发送消息
							String msg = "您的正在处理中的案例，客户已经放弃解决了，请及时与客户沟通！！";
							//创建消息实体
							ZaSysNotice zaSysNotice = new ZaSysNotice();
							zaSysNotice.setSystemId(Constant.SYSTEMID);
							zaSysNotice.setFromUserId(userId);  //通知用户id
							zaSysNotice.setToUserId(lawId); //接受消息用户id
							zaSysNotice.setEventId(id); //放弃案件
							zaSysNotice.setNoticeType(0);
							zaSysNotice.setHandleState(1); //默认消息接受者已处理
							zaSysNotice.setMessage(msg);
							//序列化为json字符串
							String string = JSONObject.toJSONString(zaSysNotice);
							//创建消息目的对象Destination (点对点Queue)
							Destination destination = new ActiveMQQueue(Constant.ABANDDONPUBLISHCASE_QUEUE);
							producer.sendMessage(destination,string);
							
						}
						result = AppMsgResult.result(200, "您已放弃解决了发布的案件请求，稍后系统会返还您悬赏的金额至个人余额账户中！",null);
					
				}else {
					result = AppMsgResult.result(559,"操作失败，请稍后再试！",null);
				}
				  
				
			}else if(userType==2) {
				if(status!=1) {
					return AppMsgResult.result(538, "系统逻辑错误，请联系管理员！",null);
				}
				//律师放弃案例，1 status=5 2.将该案件对象law_id=null切断关联 3 需要系统发出通知普通用户
				zaUserPublishCase.setStatus(5);
				zaUserPublishCase.setLawId("");
				Integer i = zaUserPublishCaseMapperVo.updatePublishCase(zaUserPublishCase);
				if(i==1) {
					//系统通知客户律师已放弃案例
					String uId = zaUserPublishCase.getUserId();
					 //发送消息
					 String msg = "尊敬的用户，您发布的案例，律师已经放弃解决了，请及时与律师沟通！！";
					//创建消息实体
					ZaSysNotice zaSysNotice = new ZaSysNotice();
					zaSysNotice.setSystemId(Constant.SYSTEMID);
					zaSysNotice.setFromUserId(userId); //律师id
					zaSysNotice.setToUserId(uId);  //接受通知的用户id
					zaSysNotice.setEventId(id);  //案件id
					zaSysNotice.setNoticeType(0);
					zaSysNotice.setHandleState(1);  //默认接受者已经处理该条信息
					zaSysNotice.setMessage(msg);
					//序列化为json字符串
					String string = JSONObject.toJSONString(zaSysNotice);
					//创建消息目的对象Destination (点对点Queue)
					 Destination destination = new ActiveMQQueue(Constant.ABANDDONPUBLISHCASE_QUEUE);
					 producer.sendMessage(destination,string);
					
					
					result = AppMsgResult.result(200, "您已放弃了客户案例！系统已为您通知了客户",null);
				}else {
					result = AppMsgResult.result(559,"操作失败，请稍后再试！",null);
				}
			}
			
			
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		 return result;
	}


	/**
	 *   律师提出方案已经解决完成，请求客户回应
	 */
	@Override
	public AppMsgResult lawyerCompleteCase(String id, String userId, String userToken) {
		AppMsgResult result =null;
		AppMsgResult msgResult = checkUserToken(userId, userToken);
		if((boolean)msgResult.getFlag()) {
			//查询该案件状态
			ZaUserPublishCase publishCase = zaUserPublishCaseMapperVo.queryPublishCaseById(id);
			if(publishCase==null) {
				return AppMsgResult.result(566, "案件没有找到!!",null);
			}
			Integer status = publishCase.getStatus();
			if(status==1) {
				//向客户发出请求
				String uId = publishCase.getUserId();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String date = format.format(publishCase.getUpdatedTime());
				 //发送消息
				 String msg = "律师提示您"+date+"发布的案件已解决完成，请及时回复！！";
				//创建消息实体
				ZaSysNotice zaSysNotice = new ZaSysNotice();
				zaSysNotice.setSystemId(Constant.SYSTEMID);
				zaSysNotice.setFromUserId(userId);//发出通知的律师用户id
				zaSysNotice.setToUserId(uId); //接受通知的案件用户id
				zaSysNotice.setEventId(id);  //案件id
				zaSysNotice.setNoticeType(1);  //委托通知
				zaSysNotice.setMessage(msg);
				//序列化为json字符串
				String string = JSONObject.toJSONString(zaSysNotice);
				//创建消息目的对象Destination (点对点Queue)
				 Destination destination = new ActiveMQQueue(Constant.LAWYERCOMPLETE_QUEUE);
				 producer.sendMessage(destination,string);
				 
				 result = AppMsgResult.result(200, "您已针对该案件向您的客户发出了结案请求！",null);
			}else {
				//案件状态不符
				return AppMsgResult.result(567, "案件状态不能进行此操作!!",null);
			}
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
			
		return result;
	}

	//用户回复律师案件是否完成
	@Override
	public AppMsgResult userReplyCompleteCase(Long id, Integer agree,String userId,String userToken) {
		AppMsgResult result =null;
		AppMsgResult msgResult = checkUserToken(userId, userToken);
		if((boolean)msgResult.getFlag()) {
			if(agree==null || agree!=0 && agree!=1 && agree!=2) {
				return AppMsgResult.result(543, "是否接受参数标识传值有误！",null);
			}
			//查询通知
			ZaSysNotice new_zaSysNotice = zaSysNoticeMapperVo.querySystNoticeById(id);
			if(new_zaSysNotice==null) {
				return AppMsgResult.result(543, "该条通知记录过期找不到了！",null);
			}
			//查询该案件状态
			ZaUserPublishCase publishCase = zaUserPublishCaseMapperVo.queryPublishCaseById(new_zaSysNotice.getEventId());
			if(publishCase==null) {
				return AppMsgResult.result(566, "案件没有找到!!",null);
			}
			if(publishCase.getStatus()!=1) {
				return AppMsgResult.result(556, "该案件状态不能进行此完成案件操作！！",null);
			}
			//修改通知状态
			new_zaSysNotice.setAgree(agree);
			new_zaSysNotice.setHandleState(1);
			Integer i = zaSysNoticeMapperVo.updateSysNoticeById(new_zaSysNotice);
			if(i!=1) {
				return AppMsgResult.result(543, "系统未找到该记录！！",null);
			}
			if(agree==0) {
				//忽略拒绝该请求
				//创建消息目的对象Destination (点对点Queue)
				 Destination destination = new ActiveMQQueue(Constant.USERREPLEYCOMPLETECASE_QUEUE);
				 //发送消息
				 String msg = "客户拒绝了案件完成结束的请求！！";
				//创建消息实体
					ZaSysNotice zaSysNotice = new ZaSysNotice();
					zaSysNotice.setSystemId(Constant.SYSTEMID);
					zaSysNotice.setFromUserId(userId);  //回复用户id
					zaSysNotice.setToUserId(new_zaSysNotice.getFromUserId());  //消息接受者id
					zaSysNotice.setEventId(new_zaSysNotice.getEventId()); //案件id
					zaSysNotice.setNoticeType(0);
					zaSysNotice.setHandleState(1);
					zaSysNotice.setMessage(msg);
					//序列化为json字符串
					String string = JSONObject.toJSONString(zaSysNotice);
				    producer.sendMessage(destination,string);
				    result =  AppMsgResult.result(200, "处理成功，系统正通知律师！",null);
				    
			}else if(agree==1) {
				//客户同意完成案件
				//接受律师请求
					//案件处理已完成 并修改案件状态
					publishCase.setStatus(2);//案件已完成
					Integer k = zaUserPublishCaseMapperVo.updatePublishCase(publishCase);
					if(k==1) {
					   //将案件赏金打赏给律师用户
						String awardId = publishCase.getAwardId();
						//获取悬赏金额
						ZaUserAwardRecord zaUserAwardRecord = zaUserAwardRecordMapperVo.getRewardRecordById(awardId);
						if(zaUserAwardRecord==null) {
							return AppMsgResult.result(545, "未找到打赏记录！", null);
						}
						//更新悬赏记录受赏人信息
						zaUserAwardRecord.setAwardeeId(new_zaSysNotice.getFromUserId());
						zaUserAwardRecord.setAwardTime(new Date());
						//更新操作
						Integer l  =zaUserAwardRecordMapperVo.updateUserAwardRecordSelective(zaUserAwardRecord);
						if(l!=1) {
							return AppMsgResult.result(562, "修改打赏记录失败！", null);
						}
						BigDecimal reward = zaUserAwardRecord.getReward();
						//修改律师总的收益表数据
						ZaUserIncomeRecord zaUserIncomeRecord = new ZaUserIncomeRecord();
						String incomeRecord = IDUtils.genId();
						zaUserIncomeRecord.setId(incomeRecord);
						zaUserIncomeRecord.setUserId(new_zaSysNotice.getFromUserId());
						zaUserIncomeRecord.setUserType(2);//律师用户类型
						zaUserIncomeRecord.setDealCode(awardId);  //打赏记录id
						zaUserIncomeRecord.setInCash(reward);
						zaUserIncomeRecord.setInStatus(1);
						zaUserIncomeRecord.setInType(7); //悬赏收益
						zaUserIncomeRecord.setInOut(0); //收入
						zaUserIncomeRecord.setIncomeTime(new Date());
						zaUserIncomeRecord.setEventDesc("获得了用户案件悬赏佣金的收益");
						zaUserIncomeRecordMapperVo.insertUserIncomeRecord(zaUserIncomeRecord);
						
						//在收益表中记录本次交易操作
						//修改该受益人用户的收益及总收益金额信息
						ZaUserProfit userProfit2= zaUserProfitMapperVo.getUserProfitById(new_zaSysNotice.getFromUserId());
						if(userProfit2==null) {
							userProfit2 = new ZaUserProfit();
							String userProfitId =IDUtils.genId();
							userProfit2.setId(userProfitId);
							userProfit2.setUserId(new_zaSysNotice.getFromUserId());
							userProfit2.setCoinIncome(reward);
							userProfit2.setCoinInTotal(reward);
							userProfit2.setUserType(2);  //收益者类型为律师用户
							zaUserProfitMapperVo.insertUserProfit(userProfit2);
						}else {
							this.addCoinIncome(userProfit2, reward);
						}
						
						//创建消息目的对象Destination (点对点Queue)
						 Destination destination = new ActiveMQQueue(Constant.USERREPLEYCOMPLETECASE_QUEUE);
						 //发送消息
						 String msg = "恭喜您，客户已同意该案件可以结束，您获得了客户的悬赏佣金，快去查看一下吧！！";
						//创建消息实体
							ZaSysNotice zaSysNotice = new ZaSysNotice();
							zaSysNotice.setSystemId(Constant.SYSTEMID);
							zaSysNotice.setFromUserId(userId);  //发出通知id
							zaSysNotice.setToUserId(new_zaSysNotice.getFromUserId());  //接受者id
							zaSysNotice.setEventId(new_zaSysNotice.getEventId());
							zaSysNotice.setNoticeType(0);
							zaSysNotice.setMessage(msg);
							//序列化为json字符串
							String string = JSONObject.toJSONString(zaSysNotice);
						    producer.sendMessage(destination,string);
							result =  AppMsgResult.result(200, "您已同意了该律师结束案件的请求，该案件悬赏的赏金系统已支付律师账户！",null);
					}else {
						return AppMsgResult.result(566, "案件修改状态失败!请稍后操作!",null);
					}
				 
			
		}else if(agree==2) {
			//更换律师 1.删除案件律师关系 2.案件状态设置0并更新案件 3.通知律师已被替换 4.提示用户系统已为其重新发布安排律师
			publishCase.setLawId("");//删除律师
			publishCase.setStatus(0); //设置案件为请求处理状态 (重新发布)
			Integer k = zaUserPublishCaseMapperVo.updatePublishCase(publishCase);
			if(k==1) {
				//重新发布成功！
				//通知该律师已被解雇
				//创建消息目的对象Destination (点对点Queue)
				 Destination destination = new ActiveMQQueue(Constant.USERREPLEYCOMPLETECASE_QUEUE);
				 //发送消息
				 String msg = "抱歉，您处理的案件用户不满意，该用户已申请重新更换律师！";
				//创建消息实体
					ZaSysNotice zaSysNotice = new ZaSysNotice();
					zaSysNotice.setSystemId(Constant.SYSTEMID);
					zaSysNotice.setFromUserId(userId);  //发出通知id
					zaSysNotice.setToUserId(new_zaSysNotice.getFromUserId());  //接受者id
					zaSysNotice.setEventId(new_zaSysNotice.getEventId());
					zaSysNotice.setNoticeType(1);
					zaSysNotice.setMessage(msg);
					//序列化为json字符串
					String string = JSONObject.toJSONString(zaSysNotice);
				    producer.sendMessage(destination,string);
				
				result =  AppMsgResult.result(200, "系统已为您重新发布了案件并通知了附近律师前来处理！",null);
				
			}else {
				return AppMsgResult.result(567, "操作失败！",null);
			}
			
		}
	}else {
		result = AppMsgResult.result(538, "用户未登录！",null);
	}
		return result;

}
	
	
	/**
	 * 用户删除发布的案件
	 */
	
	@Override
	public AppMsgResult deletePublishCase(String id, String userId, String userToken) {
		AppMsgResult result =null;
		AppMsgResult msgResult = checkUserToken(userId, userToken);
		if((boolean)msgResult.getFlag()) {
			//查询该案件状态
			ZaUserPublishCase publishCase = zaUserPublishCaseMapperVo.queryPublishCaseById(id);
			if(publishCase==null) {
				return AppMsgResult.result(566, "案件没有找到!!",null);
			}
			Integer status = publishCase.getStatus();
			if(status==2 || status==3 || status==7 ) {
				//可执行删除
				publishCase.setStatus(4);
				Integer i = zaUserPublishCaseMapperVo.updatePublishCase(publishCase);
				if(i==1) {
					result=  AppMsgResult.result(200, "案件删除成功！",null);
				}else {
					result=  AppMsgResult.result(562, "操作失败，请稍后重试！",null);
				}
			}else if(status==1) {
				result=  AppMsgResult.result(562, "案件正在解决中，不能删除哟！",null);
			}else {
				result=  AppMsgResult.result(562, "该案件状态不能进行，不能删除操作哟！",null);
			}
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		return result;
	}
	
		/**
		 * 收益用户获得货币值，添加收益数据，修改数据库值
		 * @param zaUserProfit
		 * @param reward
		 */
		private void addCoinIncome(ZaUserProfit zaUserProfit,BigDecimal reward) {
			zaUserProfit.setCoinIncome(zaUserProfit.getCoinIncome().add(reward).setScale(2, BigDecimal.ROUND_HALF_UP));
			zaUserProfit.setCoinInTotal(zaUserProfit.getCoinInTotal().add(reward).setScale(2, BigDecimal.ROUND_HALF_UP));
			//将修改收益数据
		    zaUserProfitMapperVo.updateUserProfitById(zaUserProfit);
		}
	
			
}

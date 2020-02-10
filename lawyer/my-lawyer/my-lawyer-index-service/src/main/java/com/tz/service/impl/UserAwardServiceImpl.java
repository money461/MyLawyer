package com.tz.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
import com.tz.mapper.index.vo.ZaGiftMapperVo;
import com.tz.mapper.index.vo.ZaUserAwardRecordMapperVo;
import com.tz.mapper.index.vo.ZaUserProfitMapperVo;
import com.tz.mapper.index.vo.ZaUserPublishCaseMapperVo;
import com.tz.pojo.ZaSysNotice;
import com.tz.pojo.ZaUserAwardRecord;
import com.tz.pojo.ZaUserIncomeRecord;
import com.tz.pojo.ZaUserProfit;
import com.tz.pojo.ZaUserPublishCase;
import com.tz.pojo.admin.ZaUserAwardRecordAdminVo;
import com.tz.pojo.index.vo.UserRewardOperation;
import com.tz.pojo.index.vo.ZaGiftVo;
import com.tz.res.AppMsgResult;
import com.tz.res.Constant;
import com.tz.service.UserAwardService;

@Service
public class UserAwardServiceImpl implements UserAwardService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RedisCache cache;
	
	@Autowired
	private ZaGiftMapperVo zaGiftMapperVo;
	
	@Autowired
	private ZaUserPublishCaseMapperVo zaUserPublishCaseMapperVo;
	
	@Autowired
	private ZaUserProfitMapperVo zaUserProfitMapperVo;
	
	@Autowired
	private ZaUserAwardRecordMapperVo zaUserAwardRecordMapperVo;
	
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


	
	
	/*
	 * 展示奖赏礼品
	 */
	@Override
	public AppMsgResult getGift(String userId, String userToken) {
		AppMsgResult result =null;
		//判断用户是否登录
		AppMsgResult msgResult = checkUserToken(userId, userToken);
		if((boolean)msgResult.getFlag()) {
			String giftCache_key = RedisCache.CAHCENAME+"|getGift|";
			List<ZaGiftVo> listCache = cache.getListCache(giftCache_key, ZaGiftVo.class);
			if(listCache!=null) {
				LOG.info("get cache with key:"+giftCache_key);
			}else {
				listCache = zaGiftMapperVo.getGift();
				if(listCache.size()!=0) {
					cache.putListCache(giftCache_key,listCache);
					LOG.info("put cache with key:"+giftCache_key);
				}else {
					return AppMsgResult.result(401,"数据没找到！","");
				}
			}
			result = AppMsgResult.result(200, "success",listCache);
			
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		return result;
	}



	/**
	 * paymentType : 8直播间打赏,3普通打赏 4 案件委托悬赏 7代写文书悬赏',
	 * 打赏操作
	 * @param type
	 * @param giftId 
	 * @param giftNum
	 * @param awardeeId
	 * @param awardeeName
	 * @param userPublishCaseId //案件悬赏id
	 * @param userId
	 * @param userToken
	 */
	@Override
	public AppMsgResult rewardOperation(Integer paymentType, ZaUserAwardRecord zaUserAwardRecord,String awardeeName, String userPublishCaseId,String userToken) {
		String userId = zaUserAwardRecord.getUserId();
		if(StringUtils.isEmpty(userId)) {
			return AppMsgResult.result(543, "用户id必须传值！",null);
		}
		
		AppMsgResult result =null;
		//判断用户是否登录
		AppMsgResult msgResult = checkUserToken(userId, userToken);
		if((boolean)msgResult.getFlag()) {
			if(paymentType!=4 && paymentType!=7 &&  paymentType!=3 &&  paymentType!=8) {
				return AppMsgResult.result(543, "奖赏类型参数传值有误！",null);
			}
			
			//定义全局变量
			BigDecimal reward = null;   //打赏金额
			//判断操作类型
			//打赏行为
			if(paymentType==3||paymentType==8) {
				 Integer giftNum = null;  //打赏礼品数量
				 String giftName = null;  //打赏礼品名称
				 BigDecimal price=null; //礼品价钱
				String awardeeId = zaUserAwardRecord.getAwardeeId();
				if(StringUtils.isEmpty(awardeeId)) {
					return AppMsgResult.result(543, "受赏者id必须传值！",null);
				}
				if(StringUtils.isEmpty(awardeeName)) {
					return AppMsgResult.result(543, "受赏者简称必须传值！",null);
				}
				 reward = zaUserAwardRecord.getReward();  //受赏金额
				String giftId = zaUserAwardRecord.getGiftId();  //受赏礼品id
				giftNum = zaUserAwardRecord.getGiftNum();  //受赏礼品数量
				
				if(StringUtils.isNotBlank(giftId) && giftNum==null) {
					return AppMsgResult.result(543, "礼品与礼品数量必须同时传值！", null);
				}else if(StringUtils.isBlank(giftId) && giftNum!=null) {
					return AppMsgResult.result(543, "礼品与礼品数量必须同时传值！", null);
				}else if(StringUtils.isNotBlank(giftId) && giftNum!=null) {
					//查询礼品
					if(giftNum<1) {
						return AppMsgResult.result(543, "礼品数量必须是大于等于1的正整数！",null);
					}
					  ZaGiftVo gift = zaGiftMapperVo.queryGiftById(giftId);
					 if(gift!=null) {
						  price = gift.getPrice();//礼品价钱
						 //计算所需支付赏金
						 reward = price.multiply(new BigDecimal(giftNum));
						 //设置礼品名称
						 giftName = gift.getGiftName();
					 }else {
						 return AppMsgResult.result(543, "悬赏礼品信息未找到！", null);
					 }
			   }
				//扣款操作
				AppMsgResult deductCoinResult = this.deductCoinSurplus(userId, reward);
				if((Integer)deductCoinResult.getFlag()==200) {
					//剩余货币值立即完成支付成功
					String id = IDUtils.genId();
					//创建打赏记录对象
					ZaUserAwardRecord awardRecord = new ZaUserAwardRecord();
					awardRecord.setId(id);
					awardRecord.setUserId(userId);
					awardRecord.setAwardeeId(awardeeId);
					awardRecord.setGiftId(giftId);
					awardRecord.setPrice(price);
					awardRecord.setGiftNum(giftNum);
					awardRecord.setReward(reward);
					awardRecord.setAwardStatus(1); //赏金支付成功
					if(paymentType==3) {
						awardRecord.setType(3); //普通打赏记录
					}else if(paymentType==8) {
						awardRecord.setType(4); //直播间打赏记录
					}
					awardRecord.setAwardTime(new Date());
					//将数据写入打赏记录表中
					zaUserAwardRecordMapperVo.insertUserAwardRecord(awardRecord);
					
					//为打赏用户向消费表中插入数据
					ZaUserAwardRecordAdminVo zaUserAwardRecordAdminVo = new  ZaUserAwardRecordAdminVo();
					zaUserAwardRecordAdminVo.setAwardeeId(awardeeId);
					zaUserAwardRecordAdminVo.setAwardeeName(awardeeName);
					zaUserAwardRecordAdminVo.setUserId(userId);
					zaUserAwardRecordAdminVo.setId(id);
					zaUserAwardRecordAdminVo.setGiftId(giftId);
					zaUserAwardRecordAdminVo.setGiftNum(giftNum);
					zaUserAwardRecordAdminVo.setGiftName(giftName);
					if(paymentType==3) {
						zaUserAwardRecordAdminVo.setType(3); //普通打赏记录
					}else if(paymentType==8) {
						zaUserAwardRecordAdminVo.setType(4); //直播间打赏记录
					}
					zaUserAwardRecordAdminVo.setReward(reward);
				
						//消费记录填写完成 填写律师收益纪律
						//当为直播打赏或者普通打赏时候，需要将受赏人的收益记录收益表中
						ZaUserIncomeRecord zaUserIncomeRecord = new ZaUserIncomeRecord();
						String incomeRecord = IDUtils.genId();
						zaUserIncomeRecord.setId(incomeRecord);
						zaUserIncomeRecord.setUserId(awardeeId);
						zaUserIncomeRecord.setUserType(2);//收益用户类型律师
						zaUserIncomeRecord.setDealCode(id);
						zaUserIncomeRecord.setInCash(reward);
						zaUserIncomeRecord.setInStatus(1);
						zaUserIncomeRecord.setInType(6); //打赏收益
						zaUserIncomeRecord.setInOut(0); //收入
						zaUserIncomeRecord.setIncomeTime(new Date());
						if(giftNum!=null &&giftName!=null) {
							zaUserIncomeRecord.setEventDesc("获得用户送的"+giftNum+"个"+giftName);
						}else {
							zaUserIncomeRecord.setEventDesc("获得用户打赏的"+reward+"元");
						}
//						zaUserIncomeRecordMapperVo.insertUserIncomeRecord(zaUserIncomeRecord);
						
					/*	//修改该受益人用户的收益及总收益金额信息
						ZaUserProfit userProfit2= zaUserProfitMapperVo.getUserProfitById(awardeeId);
						if(userProfit2==null) {
							userProfit2 = new ZaUserProfit();
							String userProfitId =IDUtils.genId();
							userProfit2.setId(userProfitId);
							userProfit2.setUserId(awardeeId);
							userProfit2.setCoinIncome(reward);
							userProfit2.setCoinInTotal(reward);
							userProfit2.setUserType(2);  //收益者类型为律师用户
							zaUserProfitMapperVo.insertUserProfit(userProfit2);
						}else {
							this.addCoinIncome(userProfit2, reward);
						}
					*/
						//将打赏消息通知受益者
						//创建消息目的对象Destination (点对点Queue)
						 Destination destination = new ActiveMQQueue(Constant.USERREWARDNOTICE_QUEUE);
						 //发送消息
						 String msg = "您获得了一个用户的打赏哟！";
						//创建消息实体
							ZaSysNotice zaSysNotice = new ZaSysNotice();
							zaSysNotice.setSystemId(Constant.SYSTEMID);
							zaSysNotice.setFromUserId(userId);  //发出通知id
							zaSysNotice.setToUserId(awardeeId);  //接受者id
							zaSysNotice.setNoticeType(2);  //打赏消息
							zaSysNotice.setHandleState(1);
							zaSysNotice.setMessage(msg);
							//序列化为json字符串
//							String string = JSONObject.toJSONString(zaSysNotice);
							UserRewardOperation userRewardOperation = new UserRewardOperation();
							userRewardOperation.setZaUserAwardRecordAdminVo(zaUserAwardRecordAdminVo);
							userRewardOperation.setZaUserIncomeRecord(zaUserIncomeRecord);
							userRewardOperation.setZaSysNotice(zaSysNotice);
							String string = JSONObject.toJSONString(userRewardOperation);
						    producer.sendMessage(destination,string);
						
						    result = AppMsgResult.result(200, "打赏成功！",null);
					
				}else {
					return  deductCoinResult;
				}
				
			  //案件悬赏行为	
			}else if(paymentType==4 || paymentType==7) {
				if(StringUtils.isBlank(userPublishCaseId)) {
					return  AppMsgResult.result(543, "悬赏委托案件id不能为空！",null);
				}
				//查询该案件悬赏的金额值
				ZaUserAwardRecordAdminVo zaUserAwardRecordAdminVo=zaUserAwardRecordMapperVo.queryUserAwardRecordByCaseId(userPublishCaseId);
				if(zaUserAwardRecordAdminVo==null) {
					return AppMsgResult.result(538, "该案件发布时，没有悬赏金额！",null);
				}else {
					Integer awardStatus = zaUserAwardRecordAdminVo.getAwardStatus();
					if(awardStatus!=0) {
						return AppMsgResult.result(538, "该案件赏金状态不正确！",null);
					}
					//进行扣款
					reward = zaUserAwardRecordAdminVo.getReward();
					AppMsgResult deductCoinResult = this.deductCoinSurplus(userId, reward);
					if((Integer)deductCoinResult.getFlag()==200) {
						//扣款成功
						//修改案件状态
						ZaUserPublishCase zaUserPublishCase = new ZaUserPublishCase();
						zaUserPublishCase.setId(userPublishCaseId);
						zaUserPublishCase.setStatus(0); //案件支付成功等待解决
						Integer i = zaUserPublishCaseMapperVo.updatePublishCase(zaUserPublishCase);
						if(i==1) {
							//修改打赏记录支付状态
							zaUserAwardRecord.setId(zaUserAwardRecordAdminVo.getId());
							zaUserAwardRecord.setAwardStatus(1); //支付成功
							zaUserAwardRecord.setAwardTime(new Date());
							zaUserAwardRecord.setUpdatedTime(new Date());
							Integer k = zaUserAwardRecordMapperVo.updateUserAwardRecordSelective(zaUserAwardRecord);
							if(k!=1) {
								return AppMsgResult.result(538, "该案件悬赏记录支付状态修改失败！",null);
							}
							
							//向消费表中插入数据
							
							//将悬赏成功消息通知用户
							//创建消息目的对象Destination (点对点Queue)
							 Destination destination = new ActiveMQQueue(Constant.USERPUBLISHCASESUCCESSNOTICE_QUEUE);
							 //发送消息
							 String msg = "您已成功发布了一件案件,系统已向附近律师发出了邀请！";
							//创建消息实体
								ZaSysNotice zaSysNotice = new ZaSysNotice();
								zaSysNotice.setSystemId(Constant.SYSTEMID);
								zaSysNotice.setFromUserId(userId);  //发出通知id
								zaSysNotice.setToUserId(userId);  //接受者id
								zaSysNotice.setNoticeType(0);  //系统消息
								zaSysNotice.setHandleState(1);
								zaSysNotice.setMessage(msg);
								//序列化为json字符串
//								String string = JSONObject.toJSONString(zaSysNotice);
								UserRewardOperation userRewardOperation = new UserRewardOperation();
								userRewardOperation.setZaUserAwardRecordAdminVo(zaUserAwardRecordAdminVo);
								userRewardOperation.setZaSysNotice(zaSysNotice);
								String string = JSONObject.toJSONString(userRewardOperation);
							    producer.sendMessage(destination,string);
							
								result = AppMsgResult.result(200, "悬赏成功！",null);
						}else {
							return AppMsgResult.result(538, "该案件状态修改失败！",null);
						}
						
					}else {
						//扣款失败
						return deductCoinResult;
					}
				}
				
			}
			
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		
		return result;
	}
	
	/**
	 * 用户使用货币值支付扣除用户账户剩余货币，并修改值数据库值
	 * 
	 */
	private AppMsgResult deductCoinSurplus(String userId,BigDecimal reward) {
		//计算操作
		//获取用户剩余充值总金额
		AppMsgResult result=null;
		if(reward==null){
			return AppMsgResult.result(543, "案件悬赏金额必须传值！", null);
		}else if( reward.compareTo(new BigDecimal(1))!=1) {
			return AppMsgResult.result(568, "悬赏金额必须大于1", null);
		}
		ZaUserProfit userProfit= zaUserProfitMapperVo.getUserProfitById(userId);
		if(userProfit==null) {
			//剩余货币值小于所需支付打赏金额，调用支付宝接口
			return AppMsgResult.result(569,"您账户中的剩余资金不够了,请选择充值后再支付，或者使用其他支付方式！",null);
		}
		if(userProfit.getStatus()==0) {
			 //该用户剩余货币值被冻结不能使用，调用支付宝接口
			return AppMsgResult.result(586, "您的账户中的资金已被冻结,请联系管理员或者改选其他支付方式！", null);
			
			
		}else if(userProfit.getCoinSurplus().compareTo(reward)==-1) {
			//剩余货币值小于所需支付打赏金额，调用支付宝接口
			return AppMsgResult.result(569,"您账户中的剩余资金不够了,请选择充值后再支付，或者使用其他支付方式！",null);
			
			
		}else if(userProfit.getCoinSurplus().compareTo(reward)==1 ||userProfit.getCoinSurplus().compareTo(reward)==0) {
			userProfit.setCoinSurplus(userProfit.getCoinSurplus().subtract(reward).setScale(2, BigDecimal.ROUND_HALF_UP));
			userProfit.setCoinConsumTotal(userProfit.getCoinConsumTotal().add(reward).setScale(2,BigDecimal.ROUND_HALF_UP));
			//记录最后一次使用时间
			userProfit.setLastUseTime(new Date());
			//将修改数据剩余值
			Integer i = zaUserProfitMapperVo.updateUserProfitById(userProfit);
			if(i==1) {
				result =  AppMsgResult.result(200,"true",null);
			}else {
				result =  AppMsgResult.result(500,"系统扣款失败！请稍后重试！",null);
			}
			
		}
		return result;
	}
	
	/**
	 * 向消费表中插入数据
	 * @param zaUserAwardRecordAdminVo
	 * @return
	 *//*
	private AppMsgResult insertUserPurchaseRecord(ZaUserAwardRecordAdminVo zaUserAwardRecordAdminVo) {
		//将打赏记录写入到消费信息表中
		String id = zaUserAwardRecordAdminVo.getId();
		Integer type = zaUserAwardRecordAdminVo.getType();
		String userId = zaUserAwardRecordAdminVo.getUserId();
		String awardeeName = zaUserAwardRecordAdminVo.getAwardeeName();
		Integer giftNum = zaUserAwardRecordAdminVo.getGiftNum();
		String giftId = zaUserAwardRecordAdminVo.getGiftId();
		String giftName = zaUserAwardRecordAdminVo.getGiftName();
		BigDecimal reward = zaUserAwardRecordAdminVo.getReward();
		ZaUserPurchaseRecord zaUserPurchaseRecord = new ZaUserPurchaseRecord();
		String recordId = IDUtils.genId();
		zaUserPurchaseRecord.setId(recordId);
		zaUserPurchaseRecord.setUserId(userId);
		zaUserPurchaseRecord.setUserType(1);//普通用户
		zaUserPurchaseRecord.setDealCode(id);
		zaUserPurchaseRecord.setPayCash(reward);
		zaUserPurchaseRecord.setPayType(2);
		zaUserPurchaseRecord.setPayStatus(1);
		if(type==0||type==1) {		
			zaUserPurchaseRecord.setConsumType(1);//1打赏消费
			if(type==0) {
				//记录用户直播间打赏行为
				if(giftId!=null && giftNum!=null) {
					zaUserPurchaseRecord.setEventDesc("在"+awardeeName+"直播间送出了"+giftNum+"个"+giftName);
				}else {
					zaUserPurchaseRecord.setEventDesc("在"+awardeeName+"直播间送出打赏了"+reward+"元");
				}
			}else if(type==1) {
				//记录用户普通打赏行为
				if(giftId!=null && giftNum!=null) {
					zaUserPurchaseRecord.setEventDesc("向"+awardeeName+"进行一次打赏，送了"+giftNum+"个"+giftName);
				}else {
					zaUserPurchaseRecord.setEventDesc("向"+awardeeName+"进行一次打赏，打赏了"+reward+"元");
				}
			}
			
		}else if(type==2 ||type==3) {
			zaUserPurchaseRecord.setConsumType(2);//2 案件悬赏消费
			if(type==2) {
				//案件发布悬赏
				if(giftId!=null && giftNum!=null) {
					zaUserPurchaseRecord.setEventDesc("发布了一个案件委托,悬赏了"+giftNum+"个"+giftName);
				}else {
					zaUserPurchaseRecord.setEventDesc("发布了一个案件委托,悬赏了"+reward+"元");
				}
			}else if(type==3) {
				//代写文书
				if(giftId!=null && giftNum!=null) {
					zaUserPurchaseRecord.setEventDesc("发布了一个代写文书,悬赏了"+giftNum+"个"+giftName);
				}else {
					zaUserPurchaseRecord.setEventDesc("发布了一个代写文书,悬赏了"+reward+"元");
				}
			}
		}	
		zaUserPurchaseRecord.setInOut(1);
		zaUserPurchaseRecord.setConsumTime(new Date());
		AppMsgResult result=null;
		Integer k = zaUserPurchaseRecordMapperVo.insertUserPurchaseRecord(zaUserPurchaseRecord);
		if(k==1) {
			result =  AppMsgResult.result(200,"true",null);
		}else {
			result =  AppMsgResult.result(500,"系统插入消费记录失败！请稍后重试！",null);
		}
		
		return result;
	}*/
	
	
/*	*//**
	 * 收益用户获得货币值，添加收益数据，修改数据库值
	 * @param zaUserProfit
	 * @param reward
	 *//*
	private void addCoinIncome(ZaUserProfit zaUserProfit,BigDecimal reward) {
		zaUserProfit.setCoinIncome(zaUserProfit.getCoinIncome().add(reward).setScale(2, BigDecimal.ROUND_HALF_UP));
		zaUserProfit.setCoinInTotal(zaUserProfit.getCoinInTotal().add(reward).setScale(2, BigDecimal.ROUND_HALF_UP));
		//将修改收益数据
	     zaUserProfitMapperVo.updateUserProfitById(zaUserProfit);
	}*/
	
}

/*	

//查询该礼品信息
ZaGift gift = zaGiftMapperVo.queryGiftById(giftId);
BigDecimal price = gift.getPrice();//礼品价钱
//计算所需支付赏金
BigDecimal reward = new BigDecimal(0);
reward = price.multiply(new BigDecimal(giftNum)).setScale(2, BigDecimal.ROUND_HALF_UP);
//获取用户剩余充值总金额
ZaUserProfit userProfit= zaUserProfitMapperVo.getUserProfitById(userId);
if(userProfit==null) {
	//剩余货币值小于所需支付打赏金额，调用支付宝接口
	return AppMsgResult.result(569,"您账户中的剩余资金不够了,请选择充值后再支付，或者使用其他支付方式！",null);
}
if(userProfit.getStatus()==0) {
	 //该用户剩余货币值被冻结不能使用，调用支付宝接口
	return AppMsgResult.result(586, "您的账户中的资金已被冻结,请联系管理员或者改选其他支付方式！", null);
	
	
}else if(userProfit.getCoinSurplus().compareTo(reward)==-1) {
	//剩余货币值小于所需支付打赏金额，调用支付宝接口
	return AppMsgResult.result(569,"您账户中的剩余资金不够了,请选择充值后再支付，或者使用其他支付方式！",null);
	
	
}else if(userProfit.getCoinSurplus().compareTo(reward)==1 ||userProfit.getCoinSurplus().compareTo(reward)==0) {
	//扣除该用户的剩余货币值
	Boolean flag = this.deductCoinSurplus(userProfit, reward);
	if(flag.equals(false)) {
	  return AppMsgResult.result(568, "扣款失败！系统繁忙请稍后重试！", null);	
	}
	
	//剩余货币值立即完成支付成功
	String id = IDUtils.genId();
	//创建打赏记录对象
	ZaUserAwardRecord awardRecord = new ZaUserAwardRecord();
	awardRecord.setId(id);
	awardRecord.setUserId(userId);
	awardRecord.setAwardeeId(awardeeId);
	awardRecord.setGiftId(giftId);
	awardRecord.setPrice(price);
	awardRecord.setGiftNum(giftNum);
	awardRecord.setReward(reward);
	awardRecord.setAwardStatus(1); //赏金支付成功
	awardRecord.setType(type);
	awardRecord.setAwardTime(new Date());
	//将数据写入打赏记录表中
	zaUserAwardRecordMapperVo.insertUserAwardRecord(awardRecord);
	
	//将打赏记录写入到消费信息表中
	ZaUserPurchaseRecord zaUserPurchaseRecord = new ZaUserPurchaseRecord();
	String recordId = IDUtils.genId();
	zaUserPurchaseRecord.setId(recordId);
	zaUserPurchaseRecord.setUserId(userId);
	zaUserPurchaseRecord.setUserType(1);//普通用户
	zaUserPurchaseRecord.setDealCode(id);
	zaUserPurchaseRecord.setPayCash(reward);
	zaUserPurchaseRecord.setPayType(2);
	zaUserPurchaseRecord.setPayStatus(1);
	if(type==0||type==1) {		
		zaUserPurchaseRecord.setConsumType(1);
		if(type==0) {
			//记录用户直播间打赏行为
			zaUserPurchaseRecord.setEventDesc("在"+awardeeName+"直播间送出了"+giftNum+"个"+gift.getGiftName());
		}else if(type==1) {
			//记录用户普通打赏行为
			zaUserPurchaseRecord.setEventDesc("向"+awardeeName+"进行一次打赏，送了"+giftNum+"个"+gift.getGiftName());
		}
		
	}else if(type==2 ||type==3) {
		zaUserPurchaseRecord.setConsumType(2);
		if(type==2) {
			//案件发布悬赏
			zaUserPurchaseRecord.setEventDesc("发布了一个案件委托,悬赏了"+giftNum+"个"+gift.getGiftName());
		}else if(type==3) {
			//代写文书
			zaUserPurchaseRecord.setEventDesc("发布了一个代写文书,悬赏了"+giftNum+"个"+gift.getGiftName());
		}
	}	
	zaUserPurchaseRecord.setInOut(1);
	zaUserPurchaseRecord.setConsumTime(new Date());
	zaUserPurchaseRecordMapperVo.insertUserPurchaseRecord(zaUserPurchaseRecord);
	
	
	//当为直播打赏或者普通打赏时候，需要将受赏人的收益记录收益表中
	if(type==0||type==1) {
		ZaUserIncomeRecord zaUserIncomeRecord = new ZaUserIncomeRecord();
		String incomeRecord = IDUtils.genId();
		zaUserIncomeRecord.setId(incomeRecord);
		zaUserIncomeRecord.setUserId(awardeeId);
		zaUserIncomeRecord.setUserType(2);//收益用户类型律师
		zaUserIncomeRecord.setDealCode(id);
		zaUserIncomeRecord.setInCash(reward);
		zaUserIncomeRecord.setInStatus(1);
		zaUserIncomeRecord.setInType(6); //打赏收益
		zaUserIncomeRecord.setInOut(0); //收入
		zaUserIncomeRecord.setIncomeTime(new Date());
		zaUserIncomeRecord.setEventDesc("获得用户送的"+giftNum+"个"+gift.getGiftName());
		zaUserIncomeRecordMapperVo.insertUserIncomeRecord(zaUserIncomeRecord);
		
		//修改该受益人用户的收益及总收益金额信息
		ZaUserProfit userProfit2= zaUserProfitMapperVo.getUserProfitById(awardeeId);
		if(userProfit2==null) {
			userProfit2 = new ZaUserProfit();
			String userProfitId =IDUtils.genId();
			userProfit2.setId(userProfitId);
			userProfit2.setUserId(awardeeId);
			userProfit2.setCoinIncome(reward);
			userProfit2.setCoinInTotal(reward);
			userProfit2.setUserType(2);  //收益者类型为律师用户
			zaUserProfitMapperVo.insertUserProfit(userProfit2);
		}else {
			this.addCoinIncome(userProfit2, reward);
		}
	
		//将打赏消息通知受益者
		//创建消息目的对象Destination (点对点Queue)
		 Destination destination = new ActiveMQQueue(Constant.USERREWARDNOTICE_QUEUE);
		 //发送消息
		 String msg = "您获得了一个用户的打赏哟！";
		//创建消息实体
			ZaSysNotice zaSysNotice = new ZaSysNotice();
			zaSysNotice.setSystemId(Constant.SYSTEMID);
			zaSysNotice.setFromUserId(userId);  //发出通知id
			zaSysNotice.setToUserId(awardeeId);  //接受者id
			zaSysNotice.setNoticeType(2);
			zaSysNotice.setMessage(msg);
			//序列化为json字符串
			String string = JSONObject.toJSONString(zaSysNotice);
		    producer.sendMessage(destination,string);
		
		result = AppMsgResult.result(200, "打赏成功！",null);
	}else {
		result = AppMsgResult.result(200, "悬赏成功！",id);
	}
	
}

}else {

}

return result;*/


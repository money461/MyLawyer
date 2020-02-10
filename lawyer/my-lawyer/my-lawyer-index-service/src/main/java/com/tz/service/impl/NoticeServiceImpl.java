package com.tz.service.impl;


import java.util.List;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tz.activemq.Producer;
import com.tz.cache.RedisCache;
import com.tz.id.IDUtils;
import com.tz.mapper.index.vo.ZaSysNoticeMapperVo;
import com.tz.mapper.index.vo.ZaUserLawyerMapperVo;
import com.tz.pojo.ZaSysNotice;
import com.tz.pojo.ZaUserLawyer;
import com.tz.pojo.index.vo.ZaSysNoticeVo;
import com.tz.res.AppMsgResult;
import com.tz.res.Constant;
import com.tz.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RedisCache cache;
	
	@Autowired
	private Producer producer;
	
	@Autowired
	private ZaUserLawyerMapperVo zaUserLawyerMapperVo;
	
	@Autowired
	private ZaSysNoticeMapperVo zaSysNoticeMapperVo;
	
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
	 * 用户邀请个人律师或者删除个人律师
	 */
	@Override
	public AppMsgResult inviteIOrDelMyLawyer(String lawId,Integer type, String userId, String userToken) {
		AppMsgResult result =null;
		AppMsgResult msgResult = checkUserToken(userId, userToken);
		if((boolean)msgResult.getFlag()) {
			if(type!=1 && type!=2) {
				return  AppMsgResult.result(543, "type参数有误！",null);
			}
			if(type==1) {
				//判断是否已经有个人专属律师
				Integer i = zaUserLawyerMapperVo.checkUserToOneLawyer(userId);
				if(i==1) {
					//已经有个人律师了，不能发出通知
					return AppMsgResult.result(559, "用户只能拥有一个专属律师！",null);
				}else if(i==0) {
					//向律师发出邀请
					//发出通知
					String msg = "用户邀请你成为他的专属律师！~O(∩_∩)O~";
					//创建消息实体
					ZaSysNotice zaSysNotice = new ZaSysNotice();
					zaSysNotice.setSystemId(Constant.SYSTEMID);
					zaSysNotice.setFromUserId(userId);
					zaSysNotice.setToUserId(lawId); //通知对象
					zaSysNotice.setNoticeType(4);  //邀请律师信息
					zaSysNotice.setMessage(msg);
					//序列化为json字符串
					String string = JSONObject.toJSONString(zaSysNotice);
					//创建消息目的对象Destination (点对点Queue)
					 Destination destination = new ActiveMQQueue(Constant.USERINVITELAWYER_QUEUE);
					producer.sendMessage(destination, string);
					result = AppMsgResult.result(200,"已成功通知了律师，请等待律师同意与否！",null);
				}
			}else if(type==2) {
				//删除个人律师
				//判断是否已经有个人专属律师
				Integer i = zaUserLawyerMapperVo.checkUserLawyer(lawId,userId);
				if(i==1) {
					zaUserLawyerMapperVo.unbundleMyLawyer(lawId,userId);
					result = AppMsgResult.result(200, "您的专属律师解绑成功！",null);
				}else {
					result = AppMsgResult.result(543, "您没有绑定该律师！",null);
				}
			}
			
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		return result;
	}

	/**
	 * 律师拒绝或者接受成为客户的专属律师
	 */
	@Override
	public AppMsgResult lawyerAgreeOrRefuse(Long id, Integer agree, String userId, String userToken) {
		AppMsgResult result =null;
		AppMsgResult msgResult = checkUserToken(userId, userToken);
		if((boolean)msgResult.getFlag()) {
			if(agree!=0 && agree!=1) {
				result = AppMsgResult.result(543, "是否接受参数值有误！",null);
			}
			
			//修改通知状态
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
			
			if(agree==0) {
				//律师拒绝成为该用户的专属律师
				//发出通知
				String msg = "律师无情的拒绝了成为您专属律师的请求~~~~(>_<)~~~~";
				//创建消息实体
				ZaSysNotice zaSysNotice = new ZaSysNotice();
				zaSysNotice.setSystemId(Constant.SYSTEMID);
				zaSysNotice.setFromUserId(userId); //通知对象
				zaSysNotice.setToUserId(new_zaSysNotice.getFromUserId());
				zaSysNotice.setNoticeType(0);
				zaSysNotice.setHandleState(1);
				zaSysNotice.setMessage(msg);
				//序列化为json字符串
				String string = JSONObject.toJSONString(zaSysNotice);
				//创建消息目的对象Destination (点对点Queue)
				 Destination destination = new ActiveMQQueue(Constant.LAWYERAGREEORREFUSE_QUEUE);
				producer.sendMessage(destination, string);
				result = AppMsgResult.result(200,"您已拒绝了该成为该客户专属律师的请求，系统以为您通知了该客户！",null);
			}else if(agree==1) {
				//律师同意成为专属律师
				//在邀请中间表中写入记录
				ZaUserLawyer zaUserLawyer = new ZaUserLawyer();
				zaUserLawyer.setId(IDUtils.genId());
				zaUserLawyer.setLawId(userId);
				zaUserLawyer.setUserId(new_zaSysNotice.getFromUserId());
				Integer k = zaUserLawyerMapperVo.insertUserLawyer(zaUserLawyer);
				if(k==1) {
					//发出通知
					String msg = "恭喜您，律师已同意成为您专属律师的请求~O(∩_∩)O~,赶紧联系他吧！";
					//创建消息实体
					ZaSysNotice zaSysNotice = new ZaSysNotice();
					zaSysNotice.setSystemId(Constant.SYSTEMID);
					zaSysNotice.setFromUserId(userId); //通知对象
					zaSysNotice.setToUserId(new_zaSysNotice.getFromUserId());  //通知对象
					zaSysNotice.setNoticeType(0);
					zaSysNotice.setHandleState(1);
					zaSysNotice.setMessage(msg);
					//序列化为json字符串
					String string = JSONObject.toJSONString(zaSysNotice);
					//创建消息目的对象Destination (点对点Queue)
					Destination destination = new ActiveMQQueue(Constant.LAWYERAGREEORREFUSE_QUEUE);
					producer.sendMessage(destination, string);
					
					result = AppMsgResult.result(200,"您已同意成为该客户专属律师的请求，系统以为您通知了该客户！",null);
					
				}else {
					result = AppMsgResult.result(553,"系统异常，请稍后再试！",null);
				}
			}
			
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		return result;
	}

	/**
	 * 展示不同用户的不同类型的消息
	 */
	@Override
	public AppMsgResult getSystemNotice(Integer noticeType, Integer curPage, Integer rows,String userId, String userToken) {
		AppMsgResult result =null;
		AppMsgResult msgResult = checkUserToken(userId, userToken);
		if((boolean)msgResult.getFlag()) {
			 rows = rows == null?10:rows;
			   curPage = curPage == null?1:curPage;
			   //分页处理
			   PageHelper.startPage(curPage, rows);
			List<ZaSysNoticeVo> list = zaSysNoticeMapperVo.querySysNoticeByUserId(noticeType,userId);
			//数据分页
	        PageInfo<ZaSysNoticeVo> pageInfo = new PageInfo<ZaSysNoticeVo>(list);
	        result = AppMsgResult.result(200,"success",pageInfo);
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		return result;
	}

	/**
	 * 删除通知消息
	 */
	@Override
	public AppMsgResult delSysNoticeById(String ids, String userId, String userToken) {
		AppMsgResult result =null;
		AppMsgResult msgResult = checkUserToken(userId, userToken);
		if((boolean)msgResult.getFlag()) {
			String[] idStr = ids.split(",");
			zaSysNoticeMapperVo.batchDelNoticeById(idStr);
			 result = AppMsgResult.result(200,"删除成功",null);
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		return result;
	}


}

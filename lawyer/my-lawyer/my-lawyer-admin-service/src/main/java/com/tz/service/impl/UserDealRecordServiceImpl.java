package com.tz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tz.date.DateUtile;
import com.tz.mapper.vo.ZaUserDealRecordMapperAdminVo;
import com.tz.pojo.admin.ZaUserIncomeRecordAdminVo;
import com.tz.pojo.admin.ZaUserPurchaseRecordAdminVo;
import com.tz.res.AppMsgResult;
import com.tz.service.UserDealRecordService;
import com.tz.service.UserService;
@Service
public class UserDealRecordServiceImpl implements UserDealRecordService {

	// 注入用户接口类
	@Autowired
	private UserService userService;
	
	@Autowired
	private  ZaUserDealRecordMapperAdminVo zaUserDealRecordMapperAdminVo;
	
	/**
	 * 获取用户消费信息
	 */
	@Override
	public AppMsgResult getUserPurchaseRecordList(ZaUserPurchaseRecordAdminVo zaUserPurchaseRecordAdminVo,
			Integer curPage, Integer rows, String userId, String token) {
		//校验是否登录
		AppMsgResult msgResult = userService.validateAdminLogin(userId, token);
		if(200 != (int)msgResult.getFlag()){
//					return  msgResult;
		}
		Integer userType = zaUserPurchaseRecordAdminVo.getUserType();
		if(userType==null || userType!=1 && userType!=2 && userType!=3) {
			return AppMsgResult.result(543, "用户类型错误！", null);
		}

		rows = rows == null?10:rows;
		curPage = curPage == null?1:curPage;
		//分页处理
        PageHelper.startPage(curPage, rows);
		
        //封装map集合
        Map<String,Object> map = new HashMap<String, Object>();
        
        map.put("userType", userType);//用户类型
        
        //	 0.微信支付 1.支付宝支付 2.充值货币消费
        Integer payType = zaUserPurchaseRecordAdminVo.getPayType();
        if(payType!=null && payType!=0 && payType!=1 && payType!=2) {
        	return AppMsgResult.result(543, "消费支付方式类型参数错误！", null);
        }else {
        	map.put("payType",payType);
        }
       //0购物消费 1打赏消费 2 案件悬赏消费 3提现消费
        Integer consumType = zaUserPurchaseRecordAdminVo.getConsumType();
        if(consumType!=null && consumType!=0 && consumType!=1 && consumType!=2 &&consumType!=3) {
        	return AppMsgResult.result(543, "消费类型参数错误！", null);
        }else {
        	map.put("consumType",consumType);
        }
        //该记录属于支出或收入 0 收入 +    1消费支出 -   2其他
        Integer inOut = zaUserPurchaseRecordAdminVo.getInOut();
        if(inOut!=null && inOut!=0 && inOut!=1 && inOut!=2) {
        	return AppMsgResult.result(543, "支出或者收入参数类型错误！", null);
        }else {
        	map.put("inOut", inOut);
        }
        
        //0消费付款处理中 1消费付款成功 2消费付款失败
        Integer payStatus = zaUserPurchaseRecordAdminVo.getPayStatus();
        if(payStatus!=null && payStatus!=0 && payStatus!=1 && payStatus!=2) {
        	return AppMsgResult.result(543, "消费支付状态参数类型错误！", null);
        }else {
        	map.put("payStatus",payStatus);
        }
        
        //消费者账户模糊查询
		String userName = zaUserPurchaseRecordAdminVo.getUserName();
		if(StringUtils.isNotBlank(userName)) {
			map.put("userName", "%"+userName+"%");
		}else {
			map.put("userName", null);
		}
		
		 //时间段
      	if(null != zaUserPurchaseRecordAdminVo.getStartTime() && null != zaUserPurchaseRecordAdminVo.getEndTime()){
      		map.put("startTime",DateUtile.pushDays(zaUserPurchaseRecordAdminVo.getStartTime(),0) );
      		map.put("endTime", DateUtile.pushDays(zaUserPurchaseRecordAdminVo.getEndTime(),0));
      	}else{
      		map.put("startTime",null);
      		map.put("endTime",null);
      	}
		
		List<ZaUserPurchaseRecordAdminVo> zaUserPurchaseRecordAdminVoList =zaUserDealRecordMapperAdminVo.getUserPurchaseRecordList(map);
		PageInfo<ZaUserPurchaseRecordAdminVo> pageInfo = new PageInfo<ZaUserPurchaseRecordAdminVo>(zaUserPurchaseRecordAdminVoList);
		return AppMsgResult.result(200, "success", pageInfo);
	}

	
    /**
     * 获取用户收益信息
     */
	@Override
	public AppMsgResult getUserIncomeRecordList(ZaUserIncomeRecordAdminVo zaUserIncomeRecordAdminVo, Integer curPage,
			Integer rows, String userId, String token) {
		//校验是否登录
		AppMsgResult msgResult = userService.validateAdminLogin(userId, token);
		if(200 != (int)msgResult.getFlag()){
//					return  msgResult;
		}
		Integer userType = zaUserIncomeRecordAdminVo.getUserType();
		if(userType==null || userType!=1 && userType!=2 && userType!=3) {
			return AppMsgResult.result(543, "用户类型错误！", null);
		}
		rows = rows == null?10:rows;
		curPage = curPage == null?1:curPage;
		//分页处理
        PageHelper.startPage(curPage, rows);
		
        //封装map集合
        Map<String,Object> map = new HashMap<String, Object>();
        
        map.put("userType", userType);//用户类型
        
        //收益状态 0处理中 1已到账 2处理失败
        Integer inStatus = zaUserIncomeRecordAdminVo.getInStatus();
        if(inStatus!=null && inStatus!=0 && inStatus!=1 && inStatus!=2) {
        	return AppMsgResult.result(543, "收益状态参数错误！", null);
        }else {
        	map.put("inStatus", inStatus);
        }
        
        //收益类型：4充值 5购物收益 6打赏收益 7悬赏收益 8取消悬赏
        Integer inType = zaUserIncomeRecordAdminVo.getInType();
        if(inType!=null && inType!=4 && inType!=5 && inType!=6 && inType!=7 && inType!=8) {
        	return AppMsgResult.result(543, "收益类型参数错误！", null);
        }else {
        	map.put("inType", inType);
        }
        
        //属于支出或收入 0 收入 +    1消费支出 -   2其他
        Integer inOut = zaUserIncomeRecordAdminVo.getInOut();
        if(inOut!=null && inOut!=0 && inOut!=1  && inOut!=2) {
        	return AppMsgResult.result(543, "收益类型参数错误！", null);
        }else {
        	map.put("inOut", inOut);
        }
        
        String userName = zaUserIncomeRecordAdminVo.getUserName();
		if(StringUtils.isNotBlank(userName)) {
			map.put("userName", "%"+userName+"%");
		}else {
			map.put("userName", null);
		}
		
		 //时间段
      	if(null != zaUserIncomeRecordAdminVo.getStartTime() && null != zaUserIncomeRecordAdminVo.getEndTime()){
      		map.put("startTime",DateUtile.pushDays(zaUserIncomeRecordAdminVo.getStartTime(),0) );
      		map.put("endTime", DateUtile.pushDays(zaUserIncomeRecordAdminVo.getEndTime(),0));
      	}else{
      		map.put("startTime",null);
      		map.put("endTime",null);
      	}
		
		List<ZaUserIncomeRecordAdminVo> zaUserIncomeRecordAdminVoList  = zaUserDealRecordMapperAdminVo.getUserIncomeRecordList(map);
		 PageInfo<ZaUserIncomeRecordAdminVo> pageInfo = new PageInfo<ZaUserIncomeRecordAdminVo>(zaUserIncomeRecordAdminVoList);
		 return AppMsgResult.result(200, "success", pageInfo);
	}

}

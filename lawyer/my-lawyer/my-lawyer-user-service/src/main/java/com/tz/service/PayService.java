package com.tz.service;

import java.math.BigDecimal;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tz.pojo.ZaUserAwardRecord;
import com.tz.pojo.ZaUserDealLog;
import com.tz.pojo.ZaUserIncomeRecord;
import com.tz.pojo.ZaUserProfit;
import com.tz.pojo.ZaUserPurchaseRecord;
import com.tz.res.AppMsgResult;

/**
 * 支付接口
 * 
 * @author menglin 2018年1月11日14:15:36
 *
 */
public interface PayService {

	// 支付宝app支付
	AppMsgResult zfbAppPay(String userId, String token, String type, BigDecimal amount, String userIdTo, String orderId,
			String userType,String giftId,int giftNum);

	// 微信app支付
	AppMsgResult wechatAppPay(HttpServletRequest request, String userId, String token, String type, BigDecimal amount,
			String userIdTo, String orderId, String userType,String giftId,int giftNum);

	// 支付宝app支付回调
	void zfbAppNotify(HttpServletRequest request);

	// 微信app支付回调
	void wechatAppNotify(String packageParamsStr,HttpServletResponse response);

	// 添加账户收益记录信息 4充值 5购物收益 6打赏收益 7悬赏收益 8取消悬赏',
	AppMsgResult userIncomeRecord(ZaUserIncomeRecord incomeRecord);

	// 添加账户消费记录 0购物消费 1打赏消费 2 案件悬赏消费 3提现消费',
	AppMsgResult userPurchaseRecord(ZaUserPurchaseRecord purchaseRecord);

	// 修改或者增加用户收益法币信息 ---
	AppMsgResult addOrUpdateUserProfit(String userId, BigDecimal bd, Integer userType);
	
	// 支付回调公共方法  payType:支付类型 buyer_logon_id 支付账号 amount 支付金额
	AppMsgResult payPublicNotify(ZaUserDealLog userDealLog,String buyer_logon_id,String amount, Integer payType);
	
	// 支付公共方法 
	AppMsgResult payPublic(String userId, String type, BigDecimal amount, String userIdTo, String orderId,
			String userType,String giftId,int giftNum);
	
	int addUserAwardRecord(ZaUserAwardRecord awardRecord);

}

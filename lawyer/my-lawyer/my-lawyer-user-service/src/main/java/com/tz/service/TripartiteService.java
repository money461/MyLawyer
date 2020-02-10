package com.tz.service;

import com.tz.pojo.ZaLawyerAuthentication;
import com.tz.pojo.ZaUserPurchaseRecord;
import com.tz.res.AppMsgResult;

/**
 * 三方登录接口类
 * 
 * @author menglin 2017年12月26日17:37:55
 */
public interface TripartiteService {
	
	//微信登录获取token
	AppMsgResult weixinGetAccessToken(String code);
	//微信登录验证token是否有效
	AppMsgResult weixinIsAccessTokenIsInvalid(String accessToken,String openID);
	//微信登录刷新token
	AppMsgResult weixinRefreshAccessToken(String refreshToken,String openID);
	

}

package com.tz.service;

import com.tz.pojo.ZaLawyerAuthentication;
import com.tz.pojo.ZaUserPurchaseRecord;
import com.tz.res.AppMsgResult;

/**
 * 用户接口类
 * 
 * @author menglin 2017年12月26日17:37:55
 */
public interface UserService {

	// 登录公共验证
	AppMsgResult validateUserLogin(String userId, String token);
	
	AppMsgResult getUserList(Integer curPage, Integer rows);

	// 用户注册
	AppMsgResult regist(String userPhone, String userPassword, String code);

	// 用户注册或者修改密码获取验证码
	AppMsgResult getUserPhoneCode(String userPhone, String type);

	// 用户找回密码
	AppMsgResult forgetPassWord(String userPhone, String userPassword, String code);

	// 用户登录
	AppMsgResult login(String userPhone, String userPassword, String userType);

	// 根据用户id修改密码 userToken：用户登录返回token
	AppMsgResult updatePassWord(String userId, String oldUserPassword, String userPassword, String token);

	// 根据用户id 修改用户资料---普通用户才能修改修改
	AppMsgResult updateUser(String userId, String token, String userNick, Integer userGender, String userEmail);
	
	// 登录状态切换身份
	AppMsgResult switchUser(String userId, String token,String userPhone,String currentType,String type);
	
	// 修改律师用户状态
	AppMsgResult updateLawyerState(ZaLawyerAuthentication lawyerAuthentication);
	
	// 律师用户提现
	AppMsgResult showMoney(ZaUserPurchaseRecord userPurchaseRecord,String token,String userPassword);
	
	// 更新用户信息
	AppMsgResult getNewUserinfo(String userId, String token,String userType);

}

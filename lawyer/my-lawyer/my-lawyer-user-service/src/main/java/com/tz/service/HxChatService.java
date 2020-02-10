package com.tz.service;

import com.tz.pojo.ZaLawyerAuthentication;
import com.tz.pojo.ZaUserPurchaseRecord;
import com.tz.res.AppMsgResult;

/**
 * 环信接口
 * 
 * @author menglin 2018年1月25日11:55:06
 */
public interface HxChatService {

	// 用户注册
	AppMsgResult adduser(String username, String password,String usernick);
	// 用户注册或者修改密码获取验证码
	AppMsgResult getuser(String username);
	// 用户注册或者修改密码获取验证码
	AppMsgResult deleteuser(String username);
	// 用户注册或者修改密码获取验证码
	AppMsgResult updateuser(String username, String nickName);
	// 用户注册或者修改密码获取验证码
	AppMsgResult addchatrooms(String owner, String members);
	// 用户注册或者修改密码获取验证码
	AppMsgResult joined_chatrooms(String username);

}

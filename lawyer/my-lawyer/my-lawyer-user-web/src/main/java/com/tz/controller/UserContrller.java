package com.tz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tz.pojo.ZaComAuthentication;
import com.tz.pojo.ZaUserPurchaseRecord;
import com.tz.remote.UserRemote;
import com.tz.res.AppMsgResult;

/**
 * 用户类
 * 
 * @author menglin 2017年12月28日10:03:48
 *
 */
@RestController
@RequestMapping("/user/api")
public class UserContrller {

	@Autowired
	UserRemote userRemote;

	/**
	 * 用户注册
	 * 
	 * @param userPhone
	 *            手机号码
	 * @param userPassword
	 *            密码
	 * @param code
	 *            验证码
	 * @return
	 */
	@PostMapping("/regist")
	public AppMsgResult regist(String userPhone, String userPassword, String code) {
		return userRemote.regist(userPhone, userPassword, code);
	}

	/**
	 * 获取手机验证码
	 * 
	 * @param userPhone
	 *            手机号码
	 * @param type
	 *            注册还是找回
	 * @return
	 */
	@GetMapping("/getUserPhoneCode")
	public AppMsgResult getUserPhoneCode(String userPhone, String type) {
		return userRemote.getUserPhoneCode(userPhone, type);
	}

	/**
	 * 忘记密码找回
	 * 
	 * @param userPhone
	 *            手机号码
	 * @param userPassword
	 *            密码
	 * @param code
	 *            验证码
	 * @return
	 */
	@PostMapping("/forgetPassWord")
	public AppMsgResult forgetPassWord(String userPhone, String userPassword, String code) {
		return userRemote.forgetPassWord(userPhone, userPassword, code);
	}

	/**
	 * 用户登录
	 * 
	 * @param userPhone
	 *            手机号码
	 * @param userPassword
	 *            密码
	 * @return
	 */
	@PostMapping("/login")
	public AppMsgResult login(String userPhone, String userPassword,String userType) {
		return userRemote.login(userPhone, userPassword,userType);
	}
	/**
	 * 修改密码
	 * @param userId
	 * @param oldUserPassword
	 * @param userPassword
	 * @param token
	 * @return
	 */
	@PostMapping("/updatePassWord")
	public AppMsgResult updatePassWord(String userId, String oldUserPassword,String userPassword,String token) {
		return userRemote.updatePassWord(userId, oldUserPassword, userPassword, token);
	}
	/**
	 * 修改个人用户资料
	 * @param userId
	 * @param token
	 * @param userNick 用户昵称
	 * @param userGender 性别
	 * @param userEmail	 邮箱
	 * @return
	 */
	@PostMapping("/updateUser")
	public AppMsgResult updateUser(String userId, String token,String userNick,Integer userGender,String userEmail) {
		return userRemote.updateUser(userId, token, userNick, userGender, userEmail);
	}
	/**
	 * 用户登录切换身份
	 * @param userId 用户id
	 * @param token  用户token
	 * @param userPhone 手机号码
	 * @param currentType 当前登录的用户 1 个人 2 律师 3企业
	 * @param type  切换的身份类型 1 个人 2 律师 3企业
	 * @return
	 */
	@PostMapping("/switchUser")
	public AppMsgResult switchUser(String userId, String token,String userPhone,String currentType,String type) {
		return userRemote.switchUser(userId, token, userPhone, currentType, type);
	}
	/**
	 * 律师账户提现
	 * @param userPurchaseRecord 
	 * @param token 
	 * @param userPassword
	 * @return
	 */
	@PostMapping("/showMoney")
	public AppMsgResult showMoney(ZaUserPurchaseRecord userPurchaseRecord,String token,String userPassword) {
		return userRemote.showMoney(userPurchaseRecord, token, userPassword);
	}
	
	/**
	 * 更新用户信息
	 * @param userId
	 * @param token
	 * @param userType
	 * @return
	 */
	@PostMapping("/getNewUserinfo")
	public AppMsgResult getNewUserinfo(String userId,String token,String userType) {
		return userRemote.getNewUserinfo(userId, token, userType);
	}
}

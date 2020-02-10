package com.tz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tz.pojo.ZaComAuthentication;
import com.tz.pojo.ZaUserPurchaseRecord;
import com.tz.res.AppMsgResult;
import com.tz.service.UserService;;

/**
 * 用户类
 * 
 * @author menglin 2017年12月26日17:37:55
 */

@RestController
@RequestMapping("user/api")
public class UserComtroller {

	// 用户服务
	@Autowired
	private UserService userService;

	/**
	 * 获取手机验证码
	 * 
	 * @param userPhone
	 *            手机号码
	 * @param type
	 *            forget 忘记 ，regist 注册
	 * @return
	 */
	@GetMapping("/getUserPhoneCode")
	public AppMsgResult getUserPhoneCode(@RequestParam(required = true) String userPhone,
			@RequestParam(required = false) String type) {
		return userService.getUserPhoneCode(userPhone, type);
	}

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
	public AppMsgResult regist(@RequestParam(required = true) String userPhone,
			@RequestParam(required = true) String userPassword, @RequestParam(required = true) String code) {
		return userService.regist(userPhone, userPassword, code);
	}

	/**
	 * 用户找回密码
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
	public AppMsgResult forgetPassWord(@RequestParam(required = true) String userPhone,
			@RequestParam(required = true) String userPassword, @RequestParam(required = true) String code) {
		return userService.forgetPassWord(userPhone, userPassword, code);
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
	public AppMsgResult login(@RequestParam(required = true) String userPhone,
			@RequestParam(required = true) String userPassword, String userType) {
		return userService.login(userPhone, userPassword, userType);
	}

	/**
	 * 修改密码
	 * 
	 * @param userId
	 * @param oldUserPassword
	 *            原密码
	 * @param userPassword
	 *            新密码
	 * @param token
	 * @return
	 */
	@PostMapping("/updatePassWord")
	public AppMsgResult updatePassWord(@RequestParam(required = true) String userId,
			@RequestParam(required = true) String oldUserPassword, @RequestParam(required = true) String userPassword,
			@RequestParam(required = true) String token) {
		return userService.updatePassWord(userId, oldUserPassword, userPassword, token);
	}

	/**
	 * 修改个人用户资料
	 * 
	 * @param userId
	 * @param token
	 * @param userNick
	 *            昵称
	 * @param userGender
	 *            性别
	 * @param userEmail
	 *            邮箱
	 * @return
	 */
	@PostMapping("/updateUser")
	public AppMsgResult updateUser(@RequestParam(required = true) String userId,
			@RequestParam(required = true) String token, @RequestParam(required = false) String userNick,
			@RequestParam(required = false) Integer userGender, @RequestParam(required = false) String userEmail) {
		return userService.updateUser(userId, token, userNick, userGender, userEmail);
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
	public AppMsgResult switchUser(@RequestParam(required = true) String userId,
			@RequestParam(required = true) String token, @RequestParam(required = true) String userPhone,
			@RequestParam(required = true) String currentType, @RequestParam(required = true) String type) {
		return userService.switchUser(userId, token, userPhone, currentType, type);
	}
	/**
	 * 律师账户提现
	 * @param userPurchaseRecord 
	 * @param token 
	 * @param userPassword
	 * @return
	 */
	@PostMapping("/showMoney")
	public AppMsgResult showMoney(@RequestBody ZaUserPurchaseRecord userPurchaseRecord,
			@RequestParam(required = true) String token, @RequestParam(required = true) String userPassword) {
		return userService.showMoney(userPurchaseRecord, token, userPassword);
	}
	/**
	 * 更新用户信息
	 * @param userId
	 * @param token
	 * @param userType
	 * @return
	 */
	@PostMapping("/getNewUserinfo")
	public AppMsgResult getNewUserinfo(@RequestParam(required = true) String userId ,
			@RequestParam(required = true) String token, @RequestParam(required = true) String userType) {
		return userService.getNewUserinfo(userId, token, userType);
	}

}

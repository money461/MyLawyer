package com.tz.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.config.FeignConfiguration;
import com.tz.pojo.ZaUserPurchaseRecord;
import com.tz.res.AppMsgResult;

/**
 * Created by summer on 2017/5/11.
 */
@FeignClient(name = "my-lawyer-user-service",configuration=FeignConfiguration.class, fallback = UserRemoteHystrix.class)
public interface UserRemote {
	

	// 用户注册
	@PostMapping("user/api/regist")
	public AppMsgResult regist(@RequestParam(value = "userPhone", required = true) String userPhone,
			@RequestParam(value = "userPassword", required = true) String userPassword,
			@RequestParam(value = "code", required = true) String code);

	// 获取手机验证码
	@GetMapping("user/api/getUserPhoneCode")
	public AppMsgResult getUserPhoneCode(@RequestParam(value = "userPhone", required = true) String userPhone,
			@RequestParam(value = "type", required = false) String type);

	// 忘记找回密码
	@PostMapping("user/api/forgetPassWord")
	public AppMsgResult forgetPassWord(@RequestParam(value = "userPhone", required = true) String userPhone,
			@RequestParam(value = "userPassword", required = true) String userPassword,
			@RequestParam(value = "code", required = true) String code);

	// 用户登录
	@PostMapping("user/api/login")
	public AppMsgResult login(@RequestParam(value = "userPhone", required = true) String userPhone,
			@RequestParam(value = "userPassword", required = true) String userPassword,
			@RequestParam(value = "userType", required = true) String userType);

	// 用户修改密码
	@PostMapping("user/api/updatePassWord")
	public AppMsgResult updatePassWord(@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "oldUserPassword", required = true) String oldUserPassword,
			@RequestParam(value = "userPassword", required = true) String userPassword,
			@RequestParam(value = "token", required = true) String token);

	// 个人用户修改资料
	@PostMapping("user/api/updateUser")
	public AppMsgResult updateUser(@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "userNick", required = false) String userNick,
			@RequestParam(value = "userGender", required = false) Integer userGender,
			@RequestParam(value = "userEmail", required = false) String userEmail);
	
	// 切换身份
	@PostMapping("user/api/switchUser")
	public AppMsgResult switchUser(@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "userPhone", required = true) String userPhone,
			@RequestParam(value = "currentType", required = true) String currentType,
			@RequestParam(value = "type", required = true) String type);
	// 律师账户提现
    @PostMapping("user/api/showMoney")
	public AppMsgResult showMoney(ZaUserPurchaseRecord userPurchaseRecord,
			@RequestParam(value = "token",required = true) String token, @RequestParam(value = "userPassword",required = true) String userPassword);
    
	// 更新用户信息
	@PostMapping("user/api/getNewUserinfo")
	public AppMsgResult getNewUserinfo(@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "userType", required = true) String userType);
			
}

package com.tz.remote;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.pojo.ZaUserPurchaseRecord;
import com.tz.res.AppMsgResult;

/**
 * 用户服务熔断类
 * @author menglin 2017年12月28日10:00:31
 *
 */
@Component
public class UserRemoteHystrix implements UserRemote{

	@Override
	public AppMsgResult regist(String userPhone, String userPassword, String code) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult getUserPhoneCode(String userPhone, String type) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult forgetPassWord(String userPhone, String userPassword, String code) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult login(String userPhone, String userPassword, String userType) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult updatePassWord(String userId, String oldUserPassword, String userPassword, String token) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult updateUser(String userId, String token, String userNick, Integer userGender, String userEmail) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult switchUser(String userId, String token, String userPhone, String currentType, String type) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult showMoney(ZaUserPurchaseRecord userPurchaseRecord, String token, String userPassword) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult getNewUserinfo(String userId, String token, String userType) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

}

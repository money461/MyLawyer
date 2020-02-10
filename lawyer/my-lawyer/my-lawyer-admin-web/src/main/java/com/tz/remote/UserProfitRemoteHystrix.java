package com.tz.remote;

import org.springframework.stereotype.Component;

import com.tz.pojo.admin.ZaUserProfitVo;
import com.tz.res.AppMsgResult;

@Component
public class UserProfitRemoteHystrix implements UserProfitRemote {

	@Override
	public AppMsgResult getUserProfitList(ZaUserProfitVo zaUserProfitVo, Integer curPage, Integer rows, String userId,
			String token) {
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult UserProfitFreezeById(String id, Integer status, String userId, String token) {
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

}

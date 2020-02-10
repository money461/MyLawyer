package com.tz.remote;

import org.springframework.stereotype.Component;

import com.tz.pojo.ZaUserAwardRecord;
import com.tz.res.AppMsgResult;

@Component
public class UserAwardRemoteHystrix implements UserAwardRemote {

	@Override
	public AppMsgResult getGift(String userId, String userToken) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult rewardOperation(Integer paymentType, ZaUserAwardRecord zaUserAwardRecord, String awardeeName,
			String userPublishCaseId, String userToken) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	
}

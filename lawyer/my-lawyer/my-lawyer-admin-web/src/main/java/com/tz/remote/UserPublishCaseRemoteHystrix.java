package com.tz.remote;

import org.springframework.stereotype.Component;

import com.tz.pojo.admin.ZaUserPublishCaseAdminVo;
import com.tz.res.AppMsgResult;

@Component
public class UserPublishCaseRemoteHystrix implements UserPublishCaseRemote {

	@Override
	public AppMsgResult getUserPublishCaseList(ZaUserPublishCaseAdminVo zaUserPublishCaseAdminVo, Integer curPage,
			Integer rows, String userId, String token) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult queryUserPublishCaseById(String id, String userId, String token) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult downUserPublishCase(String id, String userId, String token) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult delUserPublishCase(String id, String userId, String token) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult getCaseCategoryList(Integer status, String userId, String token) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

}

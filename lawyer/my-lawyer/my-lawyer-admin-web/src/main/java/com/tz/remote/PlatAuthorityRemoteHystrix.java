package com.tz.remote;

import org.springframework.stereotype.Component;

import com.tz.pojo.ZaPlatAuthority;
import com.tz.pojo.admin.ZaPlatAuthorityVo;
import com.tz.res.AppMsgResult;

@Component
public class PlatAuthorityRemoteHystrix implements PlatAuthorityRemote {

	@Override
	public AppMsgResult selectPlatAuthUsable(Integer status, String userId, String token) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult addOrUpdateAuth(ZaPlatAuthority zaPlatAuthority, String type, String userId, String token) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult getPlatAuthorityList(ZaPlatAuthorityVo zaPlatAuthorityVo, Integer curPage, Integer rows,
			String userId, String token) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult getPlatAuthorityById(String id, String userId, String token) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult delPlatAuthorityById(String id, String userId, String token) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult authorityFreezeById(String id, Integer status, String userId, String token) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

}

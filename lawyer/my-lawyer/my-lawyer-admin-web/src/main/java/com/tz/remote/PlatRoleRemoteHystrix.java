package com.tz.remote;

import org.springframework.stereotype.Component;

import com.tz.pojo.ZaPlatRole;
import com.tz.pojo.vo.ZaPlatRoleVo;
import com.tz.res.AppMsgResult;

@Component
public class PlatRoleRemoteHystrix implements PlatRoleRemote {

	@Override
	public AppMsgResult selectPlatRoleUsable(Integer status, String userId, String token) {
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult addOrUpdaterPlatRole(ZaPlatRole zaPlatRole, String authIds, String type, String userId,
			String token) {
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult queryPlatRoleById(String id, String userId, String token) {
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult getPlatRoleList(ZaPlatRoleVo zaPlatRoleVo, Integer curPage, Integer rows, String userId,
			String token) {
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult delPlatRoleById(String id, String userId, String token) {
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult roleFreezeById(String id, Integer status, String userId, String token) {
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

}

package com.tz.remote;

import org.springframework.stereotype.Component;

import com.tz.pojo.ZaPlatManager;
import com.tz.pojo.vo.ZaPlatManagerVo;
import com.tz.res.AppMsgResult;

@Component
public class PlatManagerRemoteHystrix implements PlatManagerRemote {

	@Override
	public AppMsgResult addOrUpdatePlatManager(ZaPlatManager platManager, String roleIds, String type, String userId,
			String token) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult getPlatManagerList(ZaPlatManagerVo zaPlatManagerVo, Integer curPage, Integer rows) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult queryPlatManagerById(String id, String userId, String token) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult delManagerById(String id, String userId, String token) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult accountFreezeById(String id, Integer status, String userId, String token) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

}

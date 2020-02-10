package com.tz.remote;

import org.springframework.stereotype.Component;

import com.tz.pojo.ZaContent;
import com.tz.pojo.admin.ZaContentAdminVo;
import com.tz.res.AppMsgResult;
@Component
public class ContentRemoteHystrix implements ContentRemote {

	@Override
	public AppMsgResult getContentList(ZaContentAdminVo zaContentAdminVo, Integer curPage, Integer rows, String userId,
			String token) {
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult addOrUpdateContent(ZaContent zaContent, String type, String userId, String token) {
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult queryContentById(String id, String userId, String token) {
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult delContentById(String id, String userId, String token) {
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

}

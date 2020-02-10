package com.tz.remote;

import org.springframework.stereotype.Component;

import com.tz.pojo.ZaContentCategory;
import com.tz.pojo.admin.ZaContentCategoryAdminVo;
import com.tz.res.AppMsgResult;

@Component
public class ContentCategoryRemoteHystrix implements ContentCategoryRemote {

	@Override
	public AppMsgResult getContentCategoryList(ZaContentCategoryAdminVo zaContentCategoryAdminVo, Integer curPage,
			Integer rows, String userId, String token) {
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult addOrUpdateContentCategory(ZaContentCategory zaContentCategory, String type, String userId,
			String token) {
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult queryContentCategoryById(Integer id, String userId, String token) {
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult disOrEnableContentCategoryById(Integer id, Integer status, String userId, String token) {
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult delContentCategoryById(Integer id, String userId, String token) {
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult selectAllContentCategory(Integer status, String userId, String token) {
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

}

package com.tz.remote;

import org.springframework.stereotype.Component;

import com.tz.res.AppMsgResult;
@Component
public class ComRemoteHystrix implements ComRemote {

	@Override
	public AppMsgResult getCompanyCategory() {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult getCompanys(String comName, String comCategoryName, String categoryId,Integer curPage, Integer rows) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult getCompanyDetailInfo(String id,String userId) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

}

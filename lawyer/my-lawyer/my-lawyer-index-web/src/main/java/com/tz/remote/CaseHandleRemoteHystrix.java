package com.tz.remote;

import org.springframework.stereotype.Component;

import com.tz.res.AppMsgResult;

@Component
public class CaseHandleRemoteHystrix implements CaseHandleRemote {

	@Override
	public AppMsgResult lawHandlingCase(String id, String userId, String userToken) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult userOrderTaking(Long id, Integer agree, String userId, String userToken) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult abandonPublishCase(String id, Integer userType, String userId, String userToken) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult lawyerCompleteCase(String id, String userId, String userToken) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult userReplyCompleteCase(Long id, Integer agree, String userId, String userToken) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult deletePublishCase(String id, String userId, String userToken) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

}

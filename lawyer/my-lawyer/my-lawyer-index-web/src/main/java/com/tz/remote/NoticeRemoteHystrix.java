package com.tz.remote;

import org.springframework.stereotype.Component;

import com.tz.res.AppMsgResult;

@Component
public class NoticeRemoteHystrix implements NoticeRemote {

	@Override
	public AppMsgResult inviteIOrDelMyLawyer(String lawId, Integer type, String userId, String userToken) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult lawyerAgreeOrRefuse(Long id, Integer agree, String userId, String userToken) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult getSystemNotice(Integer noticeType, Integer curPage, Integer rows, String userId,
			String userToken) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult delSysNoticeById(String ids, String userId, String userToken) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

}

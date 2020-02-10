package com.tz.service;

import com.tz.res.AppMsgResult;

public interface NoticeService {

    //用户删除或者邀请律师
	AppMsgResult inviteIOrDelMyLawyer(String lawId, Integer type, String userId, String userToken);

   //律师同意或者拒绝用户请求
	AppMsgResult lawyerAgreeOrRefuse(Long id, Integer agree, String userId, String userToken);

	//根据用户展示不同类型的通知信息
	AppMsgResult getSystemNotice(Integer noticeType, Integer curPage, Integer rows, String userId, String userToken);

	//删除消息通知
	AppMsgResult delSysNoticeById(String ids, String userId, String userToken);

}

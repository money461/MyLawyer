package com.tz.service;

import com.tz.res.AppMsgResult;

public interface CaseHandleService {
	
	//律师发出帮助请求
	AppMsgResult lawHandlingCase(String id, String userId, String userToken);

	//用户允许律师接单(修改案件状态)或者拒绝律师
	AppMsgResult userOrderTaking(Long id, Integer agree, String userId, String userToken);

	//用户放弃解决案件
	AppMsgResult abandonPublishCase(String id, Integer userType, String userId, String userToken);

	//用户删除发布的案件
	AppMsgResult deletePublishCase(String id, String userId, String userToken);

	//律师提出方案已经解决完成，请求客户回应
	AppMsgResult lawyerCompleteCase(String id, String userId, String userToken);

	//用户回复案件完成与否
	AppMsgResult userReplyCompleteCase(Long id, Integer agree, String userId, String userToken);


}

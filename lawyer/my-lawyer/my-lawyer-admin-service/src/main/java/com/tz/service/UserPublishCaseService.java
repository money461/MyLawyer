package com.tz.service;

import com.tz.pojo.admin.ZaUserPublishCaseAdminVo;
import com.tz.res.AppMsgResult;

public interface UserPublishCaseService {

	AppMsgResult getUserPublishCaseList(ZaUserPublishCaseAdminVo zaUserPublishCaseAdminVo, Integer curPage,
			Integer rows, String userId, String token);

	AppMsgResult downUserPublishCase(String id, String userId, String token);

	AppMsgResult delUserPublishCase(String id, String userId, String token);

	AppMsgResult queryUserPublishCaseById(String id, String userId, String token);

	AppMsgResult getCaseCategoryList(Integer status, String userId, String token);

}

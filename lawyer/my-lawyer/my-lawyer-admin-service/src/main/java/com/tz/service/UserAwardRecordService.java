package com.tz.service;

import com.tz.pojo.admin.ZaUserAwardRecordAdminVo;
import com.tz.res.AppMsgResult;

public interface UserAwardRecordService {

	AppMsgResult getUserAwardRecordList(ZaUserAwardRecordAdminVo zaUserAwardRecordAdminVo, Integer curPage,
			Integer rows, String userId, String token);

}

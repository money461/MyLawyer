package com.tz.remote;

import org.springframework.stereotype.Component;

import com.tz.pojo.admin.ZaUserAwardRecordAdminVo;
import com.tz.res.AppMsgResult;

@Component
public class UserAwardRecordRemoteHystrix implements UserAwardRecordRemote {

	@Override
	public AppMsgResult getUserAwardRecordList(ZaUserAwardRecordAdminVo zaUserAwardRecordAdminVo, Integer curPage,
			Integer rows, String userId, String token) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

}

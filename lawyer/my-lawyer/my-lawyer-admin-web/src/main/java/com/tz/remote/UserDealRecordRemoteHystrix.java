package com.tz.remote;

import org.springframework.stereotype.Component;

import com.tz.pojo.admin.ZaUserIncomeRecordAdminVo;
import com.tz.pojo.admin.ZaUserPurchaseRecordAdminVo;
import com.tz.res.AppMsgResult;

@Component
public class UserDealRecordRemoteHystrix implements UserDealRecordRemote {

	@Override
	public AppMsgResult getUserPurchaseRecordList(ZaUserPurchaseRecordAdminVo zaUserPurchaseRecordAdminVo,
			Integer curPage, Integer rows, String userId, String token) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult getUserIncomeRecordList(ZaUserIncomeRecordAdminVo zaUserIncomeRecordAdminVo, Integer curPage,
			Integer rows, String userId, String token) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

}

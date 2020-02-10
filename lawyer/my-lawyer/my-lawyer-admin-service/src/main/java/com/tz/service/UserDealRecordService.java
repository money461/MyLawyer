package com.tz.service;

import com.tz.pojo.admin.ZaUserIncomeRecordAdminVo;
import com.tz.pojo.admin.ZaUserPurchaseRecordAdminVo;
import com.tz.res.AppMsgResult;

public interface UserDealRecordService {

	AppMsgResult getUserPurchaseRecordList(ZaUserPurchaseRecordAdminVo zaUserPurchaseRecordAdminVo, Integer curPage,
			Integer rows, String userId, String token);

	AppMsgResult getUserIncomeRecordList(ZaUserIncomeRecordAdminVo zaUserIncomeRecordAdminVo, Integer curPage,
			Integer rows, String userId, String token);



}

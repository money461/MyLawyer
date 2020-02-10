package com.tz.service;

import com.tz.pojo.ZaLawyerAchievements;
import com.tz.pojo.ZaLawyerAuthentication;
import com.tz.res.AppMsgResult;

public interface LawyerService {
	// 律师认证
	AppMsgResult lawyerAuth(ZaLawyerAuthentication lawyerAuth, String caseIds, String token);

	// 添加律师案例
	AppMsgResult addAchievements(ZaLawyerAchievements lawyerAchievements, String userId, String token);

	// 删除案例
	AppMsgResult deleteAchievements(String id, String userId, String token);

	// 修改案例
	AppMsgResult updateAchievements(ZaLawyerAchievements lawyerAchievements, String userId, String token);

	// 查询案例列表信息
	AppMsgResult selectAchievements(String userId, String token);

	// 查询律师资料
	AppMsgResult selectLawyer(String userId, String token, String type);

}

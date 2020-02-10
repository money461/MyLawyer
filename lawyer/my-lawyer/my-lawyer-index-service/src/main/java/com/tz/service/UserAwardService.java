package com.tz.service;

import com.tz.pojo.ZaUserAwardRecord;
import com.tz.res.AppMsgResult;

public interface UserAwardService {

	//展示礼品
	AppMsgResult getGift(String userId, String userToken);

	//打赏操作
	AppMsgResult rewardOperation(Integer paymentType, ZaUserAwardRecord zaUserAwardRecord,String awardeeName, String userPublishCaseId,
			String userToken);

}

package com.tz.service;

import com.tz.pojo.admin.ZaUserProfitVo;
import com.tz.res.AppMsgResult;

public interface UserProfitService {

	AppMsgResult getUserProfitList(ZaUserProfitVo zaUserProfitVo,Integer curPage,Integer rows, String userId, String token);

	AppMsgResult UserProfitFreezeById(String id, Integer status, String userId, String token);

}

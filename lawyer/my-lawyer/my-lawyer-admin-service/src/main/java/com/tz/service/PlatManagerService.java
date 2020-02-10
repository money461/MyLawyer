package com.tz.service;

import com.tz.pojo.ZaPlatManager;
import com.tz.pojo.vo.ZaPlatManagerVo;
import com.tz.res.AppMsgResult;

public interface PlatManagerService {

	//添加管理员信息
	AppMsgResult addOrUpdatePlatManager(ZaPlatManager platManager, String roleIds, String type, String userId, String token);

	//获取管理员列表信息
	AppMsgResult getPlatManagerList(ZaPlatManagerVo zaPlatManagerVo, Integer curPage, Integer rows);

	//根据id查询管理员信息
	AppMsgResult queryPlatManagerById(String id, String userId, String token);

	//删除管理员
	AppMsgResult delManagerById(String id, String userId, String token);

	//冻解账户
	AppMsgResult accountFreezeById(String id, Integer status, String userId, String token);


}

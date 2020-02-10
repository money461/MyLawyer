package com.tz.service;

import java.util.List;

import com.tz.pojo.ZaPlatAuthority;
import com.tz.pojo.admin.ZaPlatAuthorityVo;
import com.tz.res.AppMsgResult;

public interface PlatAuthorityService {

	//查询所有权限信息
	AppMsgResult selectPlatAuthUsable(Integer status, String userId, String token);

	//添加或者修改权限
	AppMsgResult addOrUpdateAuth(ZaPlatAuthority zaPlatAuthority, String type, String userId, String token);

	//获取权限列表信息
	AppMsgResult getPlatAuthorityList(ZaPlatAuthorityVo zaPlatAuthorityVo, Integer curPage, Integer rows, String userId,
			String token);

	//查询指定权限
	AppMsgResult getPlatAuthorityById(String id, String userId, String token);

	//删除指定的权限
	AppMsgResult delPlatAuthorityById(String id, String userId, String token);

	//冻解权限
	AppMsgResult authorityFreezeById(String id, Integer status, String userId, String token);
	
	//查询某个管理员下所有的启用的权限
	List<ZaPlatAuthority> getAllAuthorityByManagerId(String userId);

}

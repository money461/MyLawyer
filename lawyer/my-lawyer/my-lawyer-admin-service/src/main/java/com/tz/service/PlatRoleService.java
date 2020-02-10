package com.tz.service;

import com.tz.pojo.ZaPlatRole;
import com.tz.pojo.vo.ZaPlatRoleVo;
import com.tz.res.AppMsgResult;

public interface PlatRoleService {

	//查询所有未被停用的角色
	AppMsgResult selectPlatRoleUsable(Integer status, String userId, String token);
	
	//添加或者更新角色信息
	AppMsgResult addOrUpdaterPlatRole(ZaPlatRole zaPlatRole, String authIds, String type, String userId, String token);
	
	//根据角色id查询
	AppMsgResult queryPlatRoleById(String id, String userId, String token);

	//获取角色列表信息
	AppMsgResult getPlatRoleList(ZaPlatRoleVo zaPlatRoleVo, Integer curPage, Integer rows, String userId, String token);

	//删除角色
	AppMsgResult delPlatRoleById(String id, String userId, String token);

	//冻结角色
	AppMsgResult roleFreezeById(String id, Integer status, String userId, String token);



}

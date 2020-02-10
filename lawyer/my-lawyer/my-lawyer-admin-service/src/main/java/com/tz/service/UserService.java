package com.tz.service;

import com.tz.pojo.ZaUser;
import com.tz.pojo.vo.ZaAdminUserVo;
import com.tz.pojo.vo.ZaComAuthenticationVo;
import com.tz.pojo.vo.ZaLawyerAuthenticationVo;
import com.tz.res.AppMsgResult;

public interface UserService {

	// 获取会员用户信息
	AppMsgResult getUserList(ZaAdminUserVo user, Integer curPage, Integer rows, String userId, String token);

	// 添加或者修改会员信息
	AppMsgResult addOrUpdateUser(ZaUser user, String type, String userId, String token);

	// 删除会员信息
	AppMsgResult deleteUser(ZaUser user, String userId, String token);
	
	// 获取律师用户信息
	AppMsgResult getLawyerUserList(ZaLawyerAuthenticationVo user, Integer curPage, Integer rows,String type, String userId, String token);
	
	// 修改律师用户信息
	AppMsgResult updateLawyerUser(ZaLawyerAuthenticationVo lawyerAuthenticationVo, String userId, String token);

	// 修改用户状态  1 用户 2 律师 3 企业
	AppMsgResult updateUserStatusById(String id, String status,String type,String userId, String token);
	
	// 获取企业用户信息
	AppMsgResult getComUserList(ZaComAuthenticationVo user, Integer curPage, Integer rows,String type, String userId, String token);
	
	// 后台管理员登录
	AppMsgResult adminLogin(String managerAccount, String managerPassword);
	
	// 后台管理员登录公共验证
	AppMsgResult validateAdminLogin(String userId, String token);
	
	// 查询会员信息
	AppMsgResult selectById(String id,String type, String userId, String token);

	//获取所有被删除的会员信息
	AppMsgResult getDeletedUserList(ZaAdminUserVo zaAdminUserVo,Integer curPage, Integer rows, String userId, String token);

	//删除或恢复会员
	AppMsgResult forceDelOrRecoveryById(String id, String type, Integer status, Integer userType, String userId,
			String token);
	

}

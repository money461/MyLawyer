package com.tz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tz.pojo.ZaUser;
import com.tz.pojo.vo.ZaAdminUserVo;
import com.tz.pojo.vo.ZaComAuthenticationVo;
import com.tz.pojo.vo.ZaLawyerAuthenticationVo;
import com.tz.remote.UserRemote;
import com.tz.res.AppMsgResult;



@RestController
@RequestMapping("/admin/user/api")
public class UserContrller {


    @Autowired
    UserRemote HelloRemote;
	
	/**
	 * 
	 * 查询用户信息列表
	 * 
	 * @param userVo
	 * @param curPage
	 * @param rows
	 * @param userId
	 *            用户id
	 * @param token
	 *            登录token
	 * @return
	 */
	@PostMapping("/getUserList")
	public AppMsgResult getUserList( ZaAdminUserVo userVo,Integer curPage,Integer rows, String userId,String token) {
		return HelloRemote.getUserList(userVo, curPage, rows, userId, token);
	}

	/**
	 * 添加或者修改用户信息
	 * 
	 * @param user
	 * @param userId
	 * @param token
	 * @param type
	 * @return
	 */
	@PostMapping("/addOrUpdateUser")
	public AppMsgResult addOrUpdateUser(ZaUser user,String type,String userId,String token) {
		return HelloRemote.addOrUpdateUser(user,type,userId, token);
	}

	/**
	 * 删除用户 默认假删除
	 * 
	 * @param user
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/deleteUser")
	public AppMsgResult deleteUser( ZaUser user,String userId,String token) {
		return HelloRemote.deleteUser(user, userId, token);
	}

	/**
	 * 查询律师信息
	 * 
	 * @param user
	 * @param curPage
	 * @param rows
	 * @param userId
	 * @param token
	 * @param type
	 *            anth:未认证律师， all:已认证律师
	 * @return
	 */
	@PostMapping("/getLawyerUserList")
	public AppMsgResult getLawyerUserList( ZaLawyerAuthenticationVo user, Integer curPage, Integer rows,String type,String userId,String token) {
		return HelloRemote.getLawyerUserList(user, curPage, rows,type,userId, token);
	}

	/**
	 * 修改律师信息
	 * 
	 * @param lawyerAuthenticationVo
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/updateLawyerUser")
	public AppMsgResult updateLawyerUser( ZaLawyerAuthenticationVo lawyerAuthenticationVo,String userId,String token) {
		return HelloRemote.updateLawyerUser(lawyerAuthenticationVo, userId, token);
	}

	/**
	 * 修改用户状态 启用 禁用等
	 * @param id
	 * @param status
	 * @param userId
	 * @param token
	 * @param type  1 用户 2 律师 3 企业
	 * @return
	 */
	@PostMapping("/updateUserStatusById")
	public AppMsgResult updateUserStatusById(String id,String status,String type,String userId,String token) {
		return HelloRemote.updateUserStatusById(id, status, type, userId, token);
	}
	
	/**
	 * 查询企业信息
	 * @param user
	 * @param curPage
	 * @param rows
	 * @param userId
	 * @param token
	 * @param type anth:未认证企业， all:已认证企业
	 * @return
	 */
	@PostMapping("/getComUserList")
	public AppMsgResult getComUserList( ZaComAuthenticationVo user, Integer curPage, Integer rows,
			String type,String userId,String token) {
		return HelloRemote.getComUserList(user, curPage, rows, type, userId, token);
	}
	/**
	 * 平台管理员登录
	 * @param managerAccount
	 * @param managerPassword
	 * @return
	 */
	@PostMapping("/adminLogin")
	public AppMsgResult adminLogin(String managerAccount,String managerPassword) {
		return HelloRemote.adminLogin(managerAccount, managerPassword);
	}
	
	/**
	 * 根据id查询会员信息
	 * 
	 * @param user
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/selectById")
	public AppMsgResult selectById(String id,String type,String userId,String token) {
		return HelloRemote.selectById(id, type, userId, token);
	}
 

	/**
	 * 获取被删除的用户数据
	 */
	@PostMapping("/getDeletedUserList")
	public AppMsgResult getDeletedUserList( ZaAdminUserVo zaAdminUserVo,Integer curPage, Integer rows,@RequestParam(required = true) String userId, @RequestParam(required = true) String token) {
		AppMsgResult result =  HelloRemote.getDeletedUserList(zaAdminUserVo,curPage,rows,userId,token);
		return result;
	}
	
	/**
	 * 删除账户或者恢复用户到指定的状态
	 * @param id  账户id
	 * @param type // forceDel强制删除   恢复recovery
	 * @param status  //恢复 的状态
	 * @param userType
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/forceDelOrRecoveryById")
	public AppMsgResult forceDelOrRecoveryById(@RequestParam(required = true)String id,@RequestParam(required = true) String type,Integer status, @RequestParam(required = true) Integer userType,@RequestParam(required = true) String userId, @RequestParam(required = true) String token) {
		AppMsgResult result =  HelloRemote.forceDelOrRecoveryById(id,type,status,userType,userId,token);
	    return result;
	}
	
}

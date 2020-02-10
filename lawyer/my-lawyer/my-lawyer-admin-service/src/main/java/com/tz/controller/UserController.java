package com.tz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tz.pojo.ZaComAuthentication;
import com.tz.pojo.ZaUser;
import com.tz.pojo.vo.ZaAdminUserVo;
import com.tz.pojo.vo.ZaComAuthenticationVo;
import com.tz.pojo.vo.ZaLawyerAuthenticationVo;
import com.tz.res.AppMsgResult;
import com.tz.service.UserService;;

/**
 * 用户类
 * 
 * @author menglin 2018年1月23日10:26:24
 *
 */
@RestController
@RequestMapping("admin/user/api")
public class UserController {

	@Autowired
	private UserService userService;

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
	public AppMsgResult getUserList(@RequestBody ZaAdminUserVo userVo, @RequestParam(required = false) Integer curPage,
			@RequestParam(required = false) Integer rows, @RequestParam(required = true) String userId,
			@RequestParam(required = true) String token) {
		return userService.getUserList(userVo, curPage, rows, userId, token);
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
	public AppMsgResult addOrUpdateUser(@RequestBody ZaUser user, @RequestParam(required = true) String type,
			@RequestParam(required = true) String userId, @RequestParam(required = true) String token) {
		return userService.addOrUpdateUser(user, type, userId, token);
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
	public AppMsgResult deleteUser(@RequestBody ZaUser user, @RequestParam(required = true) String userId,
			@RequestParam(required = true) String token) {
		return userService.deleteUser(user, userId, token);
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
	public AppMsgResult getLawyerUserList(@RequestBody ZaLawyerAuthenticationVo user, Integer curPage, Integer rows,
			@RequestParam(required = true) String type, @RequestParam(required = true) String userId,
			@RequestParam(required = true) String token) {
		return userService.getLawyerUserList(user, curPage, rows, type, userId, token);
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
	public AppMsgResult updateLawyerUser(@RequestBody ZaLawyerAuthenticationVo lawyerAuthenticationVo,
			@RequestParam(required = true) String userId, @RequestParam(required = true) String token) {
		return userService.updateLawyerUser(lawyerAuthenticationVo, userId, token);
	}

	/**
	 * 修改用户状态 启用 禁用等
	 * 
	 * @param id
	 * @param status
	 * @param userId
	 * @param token
	 * @param type
	 *            1 用户 2 律师 3 企业
	 * @return
	 */
	@PostMapping("/updateUserStatusById")
	public AppMsgResult updateUserStatusById(@RequestParam(required = true) String id,
			@RequestParam(required = true) String status, @RequestParam(required = true) String type,
			@RequestParam(required = true) String userId, @RequestParam(required = true) String token) {
		return userService.updateUserStatusById(id, status, type, userId, token);
	}

	/**
	 * 查询企业信息
	 * 
	 * @param user
	 * @param curPage
	 * @param rows
	 * @param userId
	 * @param token
	 * @param type
	 *            anth:未认证企业， all:已认证企业
	 * @return
	 */
	@PostMapping("/getComUserList")
	public AppMsgResult getComUserList(@RequestBody ZaComAuthenticationVo user, Integer curPage, Integer rows,
			@RequestParam(required = true) String type, @RequestParam(required = true) String userId,
			@RequestParam(required = true) String token) {
		return userService.getComUserList(user, curPage, rows, type, userId, token);
	}

	/**
	 * 平台管理员登录
	 * 
	 * @param managerAccount
	 * @param managerPassword
	 * @return
	 */
	@PostMapping("/adminLogin")
	public AppMsgResult adminLogin(@RequestParam(required = true) String managerAccount,
			@RequestParam(required = true) String managerPassword) {
		return userService.adminLogin(managerAccount, managerPassword);
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
	public AppMsgResult selectById(@RequestParam(required = true) String id,
			@RequestParam(required = true) String type, @RequestParam(required = true) String userId,
			@RequestParam(required = true) String token) {
		return userService.selectById(id, type, userId, token);
	}
	
	/**
	 * 获取被删除的用户数据
	 */
	@PostMapping("/getDeletedUserList")
	public AppMsgResult getDeletedUserList(@RequestBody ZaAdminUserVo zaAdminUserVo,Integer curPage, Integer rows,@RequestParam(required = true) String userId, @RequestParam(required = true) String token) {
		AppMsgResult result =  userService.getDeletedUserList(zaAdminUserVo,curPage,rows,userId,token);
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
		AppMsgResult result =  userService.forceDelOrRecoveryById(id,type,status,userType,userId,token);
	    return result;
	}
	
}

package com.tz.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.config.FeignConfiguration;
import com.tz.pojo.ZaComAuthentication;
import com.tz.pojo.ZaUser;
import com.tz.pojo.vo.ZaAdminUserVo;
import com.tz.pojo.vo.ZaComAuthenticationVo;
import com.tz.pojo.vo.ZaLawyerAuthenticationVo;
import com.tz.res.AppMsgResult;

/**
 * Created by summer on 2017/5/11.
 */
@FeignClient(name = "my-lawyer-admin-service",configuration=FeignConfiguration.class, fallback = UserRemoteHystrix.class)
public interface UserRemote {

	// 查询用户信息列表
	@PostMapping("admin/user/api/getUserList")
	public AppMsgResult getUserList(ZaAdminUserVo userVo,
			@RequestParam(value = "curPage", required = false) Integer curPage,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "token", required = true) String token);

	// 添加或者修改用户信息
	@PostMapping("admin/user/api/addOrUpdateUser")
	public AppMsgResult addOrUpdateUser(ZaUser user, @RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "token", required = true) String token);

	// 删除用户 默认假删除
	@PostMapping("admin/user/api/deleteUser")
	public AppMsgResult deleteUser(ZaUser user, @RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "token", required = true) String token);

	// 查询律师信息
	@PostMapping("admin/user/api/getLawyerUserList")
	public AppMsgResult getLawyerUserList(ZaLawyerAuthenticationVo user,
			@RequestParam(value = "curPage", required = false) Integer curPage,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "token", required = true) String token);

	// 修改律师信息
	@PostMapping("admin/user/api/updateLawyerUser")
	public AppMsgResult updateLawyerUser(ZaLawyerAuthenticationVo lawyerAuthenticationVo,
			@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "token", required = true) String token);

	// 修改用户状态 启用 禁用等 type 1 用户 2 律师 3 企业
	@PostMapping("admin/user/api/updateUserStatusById")
	public AppMsgResult updateUserStatusById(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "status", required = true) String status,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "token", required = true) String token);

	// 查询企业信息
	@PostMapping("admin/user/api/getComUserList")
	public AppMsgResult getComUserList(ZaComAuthenticationVo user,
			@RequestParam(value = "curPage", required = false) Integer curPage,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "token", required = true) String token);

	// 平台管理员登录
	@PostMapping("admin/user/api/adminLogin")
	public AppMsgResult adminLogin(@RequestParam(value = "managerAccount", required = true) String managerAccount,
			@RequestParam(value = "managerPassword", required = true) String managerPassword);
	
	// 查询用户信息
	@PostMapping("admin/user/api/selectById")
	public AppMsgResult selectById(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "token", required = true) String token);

	@PostMapping("admin/user/api/getDeletedUserList")
	public AppMsgResult getDeletedUserList(@RequestBody ZaAdminUserVo zaAdminUserVo,
			@RequestParam(value = "curPage", required = false) Integer curPage,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "token", required = true) String token);

	@PostMapping("admin/user/api/forceDelOrRecoveryById")
	public AppMsgResult forceDelOrRecoveryById(
			@RequestParam(value = "id", required = true) String id, 
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "userType", required = true) Integer userType,
			@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "token", required = true) String token);

}

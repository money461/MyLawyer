package com.tz.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.config.FeignConfiguration;
import com.tz.pojo.ZaPlatRole;
import com.tz.pojo.vo.ZaPlatRoleVo;
import com.tz.res.AppMsgResult;

@FeignClient(name = "my-lawyer-admin-service",configuration=FeignConfiguration.class, fallback = PlatRoleRemoteHystrix.class)
public interface PlatRoleRemote {

	@GetMapping("admin/api/selectPlatRoleUsable")
	AppMsgResult selectPlatRoleUsable(@RequestParam(value="status") Integer status,@RequestParam(value="userId") String userId,@RequestParam(value="token") String token);
	
	
	@PostMapping("admin/api/addOrUpdaterPlatRole")
	AppMsgResult addOrUpdaterPlatRole(@RequestBody ZaPlatRole zaPlatRole, @RequestParam(value="authIds")String authIds, @RequestParam(value="type")String type, @RequestParam(value="userId")String userId, @RequestParam(value="token")String token);


	@GetMapping("admin/api/queryPlatRoleById")
	AppMsgResult queryPlatRoleById(@RequestParam(value="id")String id, @RequestParam(value="userId")String userId,@RequestParam(value="token") String token);


	@PostMapping("admin/api/getPlatRoleList")
	AppMsgResult getPlatRoleList(@RequestBody ZaPlatRoleVo zaPlatRoleVo, @RequestParam(value="curPage")Integer curPage,@RequestParam(value="rows") Integer rows,@RequestParam(value="userId") String userId, @RequestParam(value="token")String token);


	@PostMapping("admin/api/delPlatRoleById")
	AppMsgResult delPlatRoleById(@RequestParam(value="id")String id, @RequestParam(value="userId")String userId, @RequestParam(value="token")String token);

	@PostMapping("admin/api/roleFreezeById")
	AppMsgResult roleFreezeById(@RequestParam(value="id")String id, @RequestParam(value="status")Integer status,@RequestParam(value="userId") String userId, @RequestParam(value="token")String token);


	
	
}

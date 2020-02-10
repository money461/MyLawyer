package com.tz.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.config.FeignConfiguration;
import com.tz.pojo.ZaPlatAuthority;
import com.tz.pojo.admin.ZaPlatAuthorityVo;
import com.tz.res.AppMsgResult;
@FeignClient(name = "my-lawyer-admin-service",configuration=FeignConfiguration.class, fallback = PlatAuthorityRemoteHystrix.class)
public interface PlatAuthorityRemote {

	@GetMapping("admin/api/selectPlatAuthUsable")
	AppMsgResult selectPlatAuthUsable(@RequestParam(value="status") Integer status,@RequestParam(value="userId") String userId,@RequestParam(value="token")  String token);

	@PostMapping("admin/api/addOrUpdateAuth")
	AppMsgResult addOrUpdateAuth(@RequestBody ZaPlatAuthority zaPlatAuthority, @RequestParam(value="type") String type, @RequestParam(value="userId") String userId, @RequestParam(value="token") String token);

	@PostMapping("admin/api/getPlatAuthorityList")
	AppMsgResult getPlatAuthorityList(@RequestBody ZaPlatAuthorityVo zaPlatAuthorityVo, @RequestParam(value="curPage") Integer curPage, @RequestParam(value="rows") Integer rows, @RequestParam(value="userId") String userId,
			@RequestParam(value="token") String token);

	@GetMapping("admin/api/getPlatAuthorityById")
	AppMsgResult getPlatAuthorityById(@RequestParam(value="id") String id, @RequestParam(value="userId") String userId, @RequestParam(value="token") String token);

	@PostMapping("admin/api/delPlatAuthorityById")
	AppMsgResult delPlatAuthorityById(@RequestParam(value="id") String id,@RequestParam(value="userId")  String userId, @RequestParam(value="token") String token);

	@PostMapping("admin/api/authorityFreezeById")
	AppMsgResult authorityFreezeById(@RequestParam(value="id") String id, @RequestParam(value="status") Integer status, @RequestParam(value="userId") String userId,@RequestParam(value="token")  String token);


}

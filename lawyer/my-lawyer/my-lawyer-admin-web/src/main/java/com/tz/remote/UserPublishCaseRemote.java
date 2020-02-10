package com.tz.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.config.FeignConfiguration;
import com.tz.pojo.admin.ZaUserPublishCaseAdminVo;
import com.tz.res.AppMsgResult;

@FeignClient(name = "my-lawyer-admin-service",configuration=FeignConfiguration.class, fallback = UserPublishCaseRemoteHystrix.class)
public interface UserPublishCaseRemote {

	@PostMapping("admin/case/api/getUserPublishCaseList")
	AppMsgResult getUserPublishCaseList(@RequestBody ZaUserPublishCaseAdminVo zaUserPublishCaseAdminVo, @RequestParam(value="curPage") Integer curPage,
			@RequestParam(value="rows") Integer rows, @RequestParam(value="userId") String userId, @RequestParam(value="token") String token);

	@GetMapping("admin/case/api/queryUserPublishCaseById")
	AppMsgResult queryUserPublishCaseById(@RequestParam(value="id") String id, @RequestParam(value="userId") String userId, @RequestParam(value="token") String token);

	@PostMapping("admin/case/api/downUserPublishCase")
	AppMsgResult downUserPublishCase(@RequestParam(value="id") String id, @RequestParam(value="userId") String userId, @RequestParam(value="token") String token);

	@PostMapping("admin/case/api/delUserPublishCase")
	AppMsgResult delUserPublishCase(@RequestParam(value="id") String id, @RequestParam(value="userId") String userId, @RequestParam(value="token") String token);

	@GetMapping("admin/case/api/getCaseCategoryList")
	AppMsgResult getCaseCategoryList(@RequestParam(value="status") Integer status, @RequestParam(value="userId")String userId,@RequestParam(value="token") String token);

}

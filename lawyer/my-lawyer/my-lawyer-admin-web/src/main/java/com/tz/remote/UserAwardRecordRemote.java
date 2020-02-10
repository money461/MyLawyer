package com.tz.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.config.FeignConfiguration;
import com.tz.pojo.admin.ZaUserAwardRecordAdminVo;
import com.tz.res.AppMsgResult;

@FeignClient(name = "my-lawyer-admin-service",configuration=FeignConfiguration.class, fallback = UserAwardRecordRemoteHystrix.class)
public interface UserAwardRecordRemote {

	@PostMapping("admin/award/api/getUserAwardRecordList")
	AppMsgResult getUserAwardRecordList(@RequestBody ZaUserAwardRecordAdminVo zaUserAwardRecordAdminVo, @RequestParam(value="curPage") Integer curPage,
			@RequestParam(value="rows") Integer rows, @RequestParam(value="userId") String userId,@RequestParam(value="token") String token);

}

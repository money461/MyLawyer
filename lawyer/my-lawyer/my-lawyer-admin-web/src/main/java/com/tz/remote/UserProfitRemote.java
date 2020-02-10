package com.tz.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.config.FeignConfiguration;
import com.tz.pojo.admin.ZaUserProfitVo;
import com.tz.res.AppMsgResult;

@FeignClient(name = "my-lawyer-admin-service",configuration=FeignConfiguration.class, fallback = UserProfitRemoteHystrix.class)
public interface UserProfitRemote {
	
	@PostMapping("/admin/user/api/getUserProfitList")
	AppMsgResult getUserProfitList(@RequestBody ZaUserProfitVo zaUserProfitVo,@RequestParam(value="curPage") Integer curPage,@RequestParam(value="rows") Integer rows,@RequestParam(value="userId") String userId,
			@RequestParam(value="token")String token);

	
	@PostMapping("/admin/user/api/UserProfitFreezeById")
	AppMsgResult UserProfitFreezeById(@RequestParam(value="id")String id, @RequestParam(value="status")Integer status, @RequestParam(value="userId") String userId, @RequestParam(value="token")String token);


}

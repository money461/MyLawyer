package com.tz.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.config.FeignConfiguration;
import com.tz.pojo.ZaPlatManager;
import com.tz.pojo.vo.ZaPlatManagerVo;
import com.tz.res.AppMsgResult;

@FeignClient(name = "my-lawyer-admin-service",configuration=FeignConfiguration.class, fallback = PlatManagerRemoteHystrix.class)
public interface PlatManagerRemote {

	@PostMapping("admin/api/addOrUpdaterPlatManager")
	AppMsgResult addOrUpdatePlatManager(@RequestBody ZaPlatManager platManager, @RequestParam(value="roleIds") String roleIds,@RequestParam(value="type") String type, @RequestParam(value="userId")String userId,
			@RequestParam(value="token") String token);

	@PostMapping("admin/api/getPlatManagerList")
	AppMsgResult getPlatManagerList(@RequestBody ZaPlatManagerVo zaPlatManagerVo, @RequestParam(value="curPage")Integer curPage, @RequestParam(value="rows")Integer rows);

	@GetMapping("admin/api/queryPlatManagerById")
	AppMsgResult queryPlatManagerById(@RequestParam(value="id")String id,@RequestParam(value="userId") String userId, @RequestParam(value="token")String token);

	@PostMapping("admin/api/delManagerById")
	AppMsgResult delManagerById(@RequestParam(value="id")String id,@RequestParam(value="userId") String userId,@RequestParam(value="token") String token);

	@PostMapping("admin/api/accountFreezeById")
	AppMsgResult accountFreezeById(@RequestParam(value="id")String id, @RequestParam(value="status")Integer status, @RequestParam(value="userId")String userId, @RequestParam(value="token")String token);

	
}

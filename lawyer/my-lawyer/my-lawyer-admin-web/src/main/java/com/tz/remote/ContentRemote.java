package com.tz.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.config.FeignConfiguration;
import com.tz.pojo.ZaContent;
import com.tz.pojo.admin.ZaContentAdminVo;
import com.tz.res.AppMsgResult;
@FeignClient(name = "my-lawyer-admin-service",configuration=FeignConfiguration.class, fallback = ContentRemoteHystrix.class)
public interface ContentRemote {

	@PostMapping("admin/content/api/getContentList")
	AppMsgResult getContentList(@RequestBody ZaContentAdminVo zaContentAdminVo,@RequestParam(value="curPage")  Integer curPage, @RequestParam(value="rows") Integer rows, @RequestParam(value="userId") String userId,
			@RequestParam(value="token") String token);

	@PostMapping("admin/content/api/addOrUpdateContent")
	AppMsgResult addOrUpdateContent(@RequestBody ZaContent zaContent, @RequestParam(value="type") String type, @RequestParam(value="userId") String userId, @RequestParam(value="token") String token);

	@GetMapping("admin/content/api/queryContentById")
	AppMsgResult queryContentById(@RequestParam(value="id") String id, @RequestParam(value="userId") String userId,@RequestParam(value="token")  String token);

	@GetMapping("admin/content/api/delContentById")
	AppMsgResult delContentById(@RequestParam(value="id") String id,@RequestParam(value="userId")  String userId, @RequestParam(value="token") String token);

}

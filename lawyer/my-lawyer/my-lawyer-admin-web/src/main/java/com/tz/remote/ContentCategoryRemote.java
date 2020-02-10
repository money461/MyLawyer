package com.tz.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.config.FeignConfiguration;
import com.tz.pojo.ZaContentCategory;
import com.tz.pojo.admin.ZaContentCategoryAdminVo;
import com.tz.res.AppMsgResult;

@FeignClient(name = "my-lawyer-admin-service",configuration=FeignConfiguration.class, fallback = ContentCategoryRemoteHystrix.class)
public interface ContentCategoryRemote {

	@PostMapping("admin/content/api/getContentCategoryList")
	AppMsgResult getContentCategoryList(@RequestBody ZaContentCategoryAdminVo zaContentCategoryAdminVo, @RequestParam(value="curPage") Integer curPage,
			@RequestParam(value="rows")Integer rows, @RequestParam(value="userId")String userId, @RequestParam(value="token")String token);

	@PostMapping("admin/content/api/addOrUpdateContentCategory")
	AppMsgResult addOrUpdateContentCategory(@RequestBody ZaContentCategory zaContentCategory, @RequestParam(value="type")String type, @RequestParam(value="userId")String userId,
			@RequestParam(value="token")String token);

	@GetMapping("admin/content/api/queryContentCategoryById")
	AppMsgResult queryContentCategoryById(@RequestParam(value="id")Integer id, @RequestParam(value="userId")String userId,@RequestParam(value="token") String token);

	@PostMapping("admin/content/api/disOrEnableContentCategoryById")
	AppMsgResult disOrEnableContentCategoryById(@RequestParam(value="id")Integer id,@RequestParam(value="status") Integer status, @RequestParam(value="userId")String userId,@RequestParam(value="token") String token);

	@PostMapping("admin/content/api/delContentCategoryById")
	AppMsgResult delContentCategoryById(@RequestParam(value="id")Integer id, @RequestParam(value="userId")String userId, @RequestParam(value="token")String token);

	@GetMapping("admin/content/api/selectAllContentCategory")
	AppMsgResult selectAllContentCategory(@RequestParam(value="status")Integer status,@RequestParam(value="userId") String userId,@RequestParam(value="token") String token);

}

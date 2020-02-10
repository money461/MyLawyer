package com.tz.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.config.FeignConfiguration;
import com.tz.pojo.ZaComAuthentication;
import com.tz.pojo.ZaUserPurchaseRecord;
import com.tz.res.AppMsgResult;

/**
 * Created by summer on 2017/5/11.
 */
@FeignClient(name = "my-lawyer-user-service", configuration = FeignConfiguration.class, fallback = HxChatRemoteHystrix.class)
public interface HxChatRemote {

	@PostMapping("hxChat/api/adduser")
	public AppMsgResult adduser(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "usernick", required = false) String usernick);

	@PostMapping("hxChat/api/deleteuser")
	public AppMsgResult deleteuser(@RequestParam(value = "username", required = true) String username);

	@PostMapping("hxChat/api/getuser")
	public AppMsgResult getuser(@RequestParam(value = "username", required = true) String username);

	@PostMapping("hxChat/api/updateusernick")
	public AppMsgResult updateusernick(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "nickName", required = true) String nickName);

	@PostMapping("hxChat/api/addchatrooms")
	public AppMsgResult addchatrooms(@RequestParam(value = "owner", required = true) String owner,
			@RequestParam(value = "members", required = true) String members);

	@PostMapping("hxChat/api/joined_chatrooms")
	public AppMsgResult joined_chatrooms(@RequestParam(value = "username", required = true) String username);
}

package com.tz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tz.pojo.ZaComAuthentication;
import com.tz.pojo.ZaUserPurchaseRecord;
import com.tz.res.AppMsgResult;
import com.tz.service.HxChatService;
import com.tz.service.UserService;;

/**
 * 用户类
 * 
 * @author menglin 2017年12月26日17:37:55
 */

@RestController
@RequestMapping("hxChat/api")
public class HxChatComtroller {

	// 用户服务
	@Autowired
	private HxChatService hxChatService;
	
	@PostMapping("/adduser")
	public AppMsgResult adduser(@RequestParam(required = true) String username,
			@RequestParam(required = true) String password,@RequestParam(required = false) String usernick) {
		return hxChatService.adduser(username, password, usernick);
	}
	@PostMapping("/deleteuser")
	public AppMsgResult deleteuser(@RequestParam(required = true) String username) {
		return hxChatService.deleteuser(username);
	}
	@PostMapping("/getuser")
	public AppMsgResult getuser(@RequestParam(required = true) String username) {
		return hxChatService.getuser(username);
	}
	@PostMapping("/updateusernick")
	public AppMsgResult updateusernick(@RequestParam(required = true) String username,
			@RequestParam(required = true) String nickName) {
		return hxChatService.updateuser(username, nickName);
	}
	@PostMapping("/addchatrooms")
	public AppMsgResult addchatrooms(@RequestParam(required = true) String owner,
			@RequestParam(required = true) String members) {
		return hxChatService.addchatrooms(owner, members);
	}
	@PostMapping("/joined_chatrooms")
	public AppMsgResult joined_chatrooms(@RequestParam(required = true) String username) {
		return hxChatService.joined_chatrooms(username);
	}
	
	
}

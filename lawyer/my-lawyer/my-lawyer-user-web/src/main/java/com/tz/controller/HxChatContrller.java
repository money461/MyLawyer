package com.tz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tz.pojo.ZaComAuthentication;
import com.tz.pojo.ZaUserPurchaseRecord;
import com.tz.remote.HxChatRemote;
import com.tz.remote.UserRemote;
import com.tz.res.AppMsgResult;

/**
 * 用户类
 * 
 * @author menglin 2017年12月28日10:03:48
 *
 */
@RestController
@RequestMapping("/hxChat/api")
public class HxChatContrller {

	@Autowired
	HxChatRemote hxChatRemote;
	
	@PostMapping("/adduser")
	public AppMsgResult adduser( String username,String password,String usernick) {
		return hxChatRemote.adduser(username, password, usernick);
	}
	@PostMapping("/deleteuser")
	public AppMsgResult deleteuser(String username) {
		return hxChatRemote.deleteuser(username);
	}
	@PostMapping("/getuser")
	public AppMsgResult getuser(String username) {
		return hxChatRemote.getuser(username);
	}
	@PostMapping("/updateusernick")
	public AppMsgResult updateusernick(String username,String nickName) {
		return hxChatRemote.updateusernick(username, nickName);
	}
	@PostMapping("/addchatrooms")
	public AppMsgResult addchatrooms(String owner,
			@RequestParam(required = true) String members) {
		return hxChatRemote.addchatrooms(owner, members);
	}
	@PostMapping("/joined_chatrooms")
	public AppMsgResult joined_chatrooms(String username) {
		return hxChatRemote.joined_chatrooms(username);
	}
	

}

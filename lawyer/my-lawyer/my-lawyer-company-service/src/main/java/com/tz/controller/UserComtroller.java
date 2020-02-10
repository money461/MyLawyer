package com.tz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tz.res.AppMsgResult;
import com.tz.service.UserService;;


@RestController
@RequestMapping("company/api")
public class UserComtroller {
	
//	@Autowired
	private UserService userService;
	
	@RequestMapping("/test")
	public String test(@RequestParam String name) {
		return "hello " + name + "，this is first messge";
	}
	
	@GetMapping("/getUserList")
	public AppMsgResult getUserList(@RequestParam(required=true,defaultValue="1") Integer curPage,@RequestParam(required=false) Integer rows) {
		return userService.getUserList(curPage, rows);
	}
	

}

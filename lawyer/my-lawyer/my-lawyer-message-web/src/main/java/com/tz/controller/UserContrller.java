package com.tz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tz.remote.UserRemote;
import com.tz.res.AppMsgResult;



@RestController
public class UserContrller {


    @Autowired
    UserRemote HelloRemote;
	
    @RequestMapping("/test/{name}")
    public String test(@PathVariable("name") String name) {
        return HelloRemote.test(name);
    }
    @GetMapping("/getUserList/{curPage}")
    public AppMsgResult getUserList(@PathVariable("curPage") Integer curPage,Integer rows) {
        return HelloRemote.getUserList(curPage, rows);
    }
}

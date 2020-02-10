package com.tz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tz.pojo.admin.ZaUserAwardRecordAdminVo;
import com.tz.remote.UserAwardRecordRemote;
import com.tz.res.AppMsgResult;

@RestController
@RequestMapping("admin/award/api")
public class UserAwardRecordController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserAwardRecordRemote userAwardRecordRemote;
	
	@PostMapping("/getUserAwardRecordList")
	public AppMsgResult getUserAwardRecordList( ZaUserAwardRecordAdminVo zaUserAwardRecordAdminVo,Integer curPage,Integer rows,  @RequestParam(required = true) String userId,@RequestParam(required = true)String token) {
		LOG.info("invoke-----------getUserAwardRecordList");
		AppMsgResult result= userAwardRecordRemote.getUserAwardRecordList(zaUserAwardRecordAdminVo,curPage,rows,userId,token);
		return result;
	}
	
	
}

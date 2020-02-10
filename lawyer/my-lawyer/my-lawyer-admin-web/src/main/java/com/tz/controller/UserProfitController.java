package com.tz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tz.pojo.admin.ZaUserProfitVo;
import com.tz.remote.UserProfitRemote;
import com.tz.res.AppMsgResult;

/**
 * 用户总收益信息处理
 * @author QL
 *
 */
@RestController
@RequestMapping("/admin/user/api")
public class UserProfitController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserProfitRemote userProfitRemote;
	
	/**
	 * 查询用户总收益信息
	 * @param zaUserProfitVo
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/getUserProfitList")
	public AppMsgResult getUserProfitList(ZaUserProfitVo zaUserProfitVo,Integer curPage,Integer rows,@RequestParam(required=true) String userId,@RequestParam(required=true)String token) {
		LOG.info("invoke-----------getUserProfitList");
		AppMsgResult result = userProfitRemote.getUserProfitList(zaUserProfitVo,curPage,rows,userId,token);
		return result;
	}
	
	/**
	 * 冻解用户总收益
	 * @param id
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/UserProfitFreezeById")
	public AppMsgResult UserProfitFreezeById(@RequestParam(required=true)  String id, @RequestParam(required=true)  Integer status,@RequestParam(required=true) String userId,@RequestParam(required=true)String token) {
		LOG.info("invoke-----------UserProfitFreezeById");
		AppMsgResult result = userProfitRemote.UserProfitFreezeById(id,status,userId,token);
		return result;
		
	}
	
}

package com.tz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tz.pojo.admin.ZaUserPublishCaseAdminVo;
import com.tz.remote.UserPublishCaseRemote;
import com.tz.res.AppMsgResult;

@RestController
@RequestMapping("admin/case/api")
public class UserPublishCaseController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserPublishCaseRemote userPublishCaseRemote;
	
	/**
	 * 
	 * @param zaUserPublishCaseAdminVo
	 * @param curPage
	 * @param rows
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/getUserPublishCaseList")
	public AppMsgResult getUserPublishCaseList( ZaUserPublishCaseAdminVo zaUserPublishCaseAdminVo,Integer curPage,Integer rows,  @RequestParam(required = true) String userId,@RequestParam(required = true)String token) {
		LOG.info("invoke-----------getUserPublishCaseList");
		AppMsgResult result= userPublishCaseRemote.getUserPublishCaseList(zaUserPublishCaseAdminVo,curPage,rows,userId,token);
		return result;
	}
	
	/**
	 * 获取所有的案件类型
	 * @return
	 */
	@GetMapping("/getCaseCategoryList")
	public AppMsgResult getCaseCategoryList(Integer status, @RequestParam(required = true) String userId,@RequestParam(required = true)String token) {
		LOG.info("invoke-----------getUserPublishCaseList");
		AppMsgResult result= userPublishCaseRemote.getCaseCategoryList(status,userId,token);
		return result;
		
	}
	
	/**
	 * 根据id查询案件详情
	 * @param id
	 * @param userId
	 * @param token
	 * @return
	 */
	@GetMapping("/queryUserPublishCaseById")
	public AppMsgResult queryUserPublishCaseById(@RequestParam(required=true) String id, @RequestParam(required=true) String userId,@RequestParam(required=true)String token) {
		LOG.info("invoke-----------queryUserPublishCaseById");
		AppMsgResult result= userPublishCaseRemote.queryUserPublishCaseById(id,userId,token);
		return result;
		
	}
	
	
	/**
	 * 下架某一非法案件
	 * @param id
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/downUserPublishCase")
	public AppMsgResult downUserPublishCase(@RequestParam(required=true) String id, @RequestParam(required=true) String userId,@RequestParam(required=true)String token) {
		LOG.info("invoke-----------downUserPublishCase");
		AppMsgResult result= userPublishCaseRemote.downUserPublishCase(id,userId,token);
		return result;
		
	}
	
	/**
	 * 删除非法案件
	 * @param id
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/delUserPublishCase")
	public AppMsgResult delUserPublishCase(@RequestParam(required=true) String id, @RequestParam(required=true) String userId,@RequestParam(required=true)String token) {
		LOG.info("invoke-----------delUserPublishCase");
		AppMsgResult result= userPublishCaseRemote.delUserPublishCase(id,userId,token);
		return result;
	}
	
}

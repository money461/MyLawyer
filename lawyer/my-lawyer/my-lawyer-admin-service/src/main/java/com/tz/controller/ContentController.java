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

import com.tz.pojo.ZaContent;
import com.tz.pojo.admin.ZaContentAdminVo;
import com.tz.res.AppMsgResult;
import com.tz.service.ContentService;

@RestController
@RequestMapping("admin/content/api")
public class ContentController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ContentService contentService;
	

	/**
	 * 查询内容列表信息
	 * @param zaContentAdminVo
	 * @param curPage
	 * @param rows
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/getContentList")
	public AppMsgResult getContentList(@RequestBody ZaContentAdminVo zaContentAdminVo,Integer curPage,Integer rows, @RequestParam(required = true) String userId,@RequestParam(required = true)String token) {
		LOG.info("invoke-----------getContentList");
		AppMsgResult result= contentService.getContentList(zaContentAdminVo,curPage,rows,userId,token);
		return result;
	}
	
	/**
	 * 添加或者更新内容
	 * @param ZaContent
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/addOrUpdateContent")
	public AppMsgResult addOrUpdateContent(@RequestBody ZaContent ZaContent,@RequestParam(required = true) String type,@RequestParam(required = true) String userId,@RequestParam(required = true)String token) {
		LOG.info("invoke-----------addOrUpdateContent");
		AppMsgResult result=contentService.addOrUpdateContent(ZaContent,type,userId,token);
		return result;
	}

	/**
	 * 查询内容详情
	 * @param id
	 * @param userId
	 * @param token
	 * @return
	 */
	@GetMapping("/queryContentById")
	public AppMsgResult queryContentById(@RequestParam(required = true) String id,@RequestParam(required = true) String userId,@RequestParam(required = true)String token) {
		LOG.info("invoke-----------addOrUpdateContent");
		AppMsgResult result=contentService.queryContentById(id,userId,token);
		return result;
		
	}
	
	//删除或者批量删除
	@GetMapping("/delContentById")
	public AppMsgResult delContentById(@RequestParam(required = true) String id,@RequestParam(required = true) String userId,@RequestParam(required = true)String token) {
		LOG.info("invoke-----------delContentById");
		AppMsgResult result=contentService.delContentById(id,userId,token);
		return result;
		
	}
	
	
	
}

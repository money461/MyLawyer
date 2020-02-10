package com.tz.controller;


import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tz.res.AppMsgResult;
import com.tz.service.ComService;

/**
 * 关于企业信息数据的接口WEB层服务
 * @author QL
 *
 */

@RestController
@RequestMapping("company/api")
public class ComController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ComService comService;
	
	/**
	 * 获取企业分类
	 * @param userId
	 * @param userToken
	 * @return
	 */
	@GetMapping("/getCompanyCategory")
	public AppMsgResult getCompanyCategory() {
		AppMsgResult result = comService.getCompanyCategory();
		return result;
	}
	
	
	/**
	 * 获取企业信息列表
	 * @param comName
	 * @param categoryId
	 * @param userId
	 * @param userToken
	 * @return
	 */
	@GetMapping("/getCompanys")
	public AppMsgResult getCompanys(String comName,String comCategoryName,String categoryId,Integer curPage,Integer rows ) {
		 LOG.info("invoke----/getCompanys");
		AppMsgResult result = comService.getCompanys(comName,comCategoryName,categoryId,curPage,rows);
		return result;
		
	}
	
	/**
	 * 展示企业详细信息
	 * @param id
	 * @param userId
	 * @param userToken
	 * @return
	 */
	@GetMapping("/getCompanyDetailInfo")
	public AppMsgResult getCompanyDetailInfo(@RequestParam(required=true) String id, String userId) {
		LOG.info("invoke----/getCompanyDetailInfo");
		AppMsgResult result = comService.getCompanyDetailInfo(id,userId);
		return result;
		
	}
	
	
}

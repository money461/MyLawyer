package com.tz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tz.pojo.admin.ZaUserIncomeRecordAdminVo;
import com.tz.pojo.admin.ZaUserPurchaseRecordAdminVo;
import com.tz.res.AppMsgResult;
import com.tz.service.UserDealRecordService;

/**
 * 用户收益或者消费管理WEB层
 * @author QL
 *
 */
@RestController
@RequestMapping("admin/deal/api")
public class UserDealRecordController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserDealRecordService userDealRecordService;
	
	/**
	 * 获取用户消费信息
	 * @param zaUserPurchaseRecordAdminVo
	 * @param curPage
	 * @param rows
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/getUserPurchaseRecordList")
	public AppMsgResult getUserPurchaseRecordList(@RequestBody ZaUserPurchaseRecordAdminVo zaUserPurchaseRecordAdminVo, Integer curPage,Integer rows,  @RequestParam(required = true) String userId,@RequestParam(required = true)String token) {
		LOG.info("invoke-----------addOrUpdatePlatManager");
		AppMsgResult result=userDealRecordService.getUserPurchaseRecordList(zaUserPurchaseRecordAdminVo,curPage,rows,userId,token);
		return result;
	}
	
	/**
	 * 获取用户收益信息
	 * @param zaUserIncomeRecordAdminVo
	 * @param curPage
	 * @param rows
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/getUserIncomeRecordList")
	public AppMsgResult getUserIncomeRecordList(@RequestBody ZaUserIncomeRecordAdminVo zaUserIncomeRecordAdminVo, Integer curPage,Integer rows,  @RequestParam(required = true) String userId,@RequestParam(required = true)String token) {
		LOG.info("invoke-----------getUserIncomeRecordList");
		AppMsgResult result=userDealRecordService.getUserIncomeRecordList(zaUserIncomeRecordAdminVo,curPage,rows,userId,token);
		return result;
	}
	
	
	
	
}

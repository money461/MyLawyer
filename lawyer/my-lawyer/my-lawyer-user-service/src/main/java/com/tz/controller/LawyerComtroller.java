package com.tz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tz.pojo.ZaLawyerAchievements;
import com.tz.pojo.ZaLawyerAuthentication;
import com.tz.res.AppMsgResult;
import com.tz.service.LawyerService;

/**
 * 律师认证类
 * 
 * @author menglin 2017年12月28日09:49:17
 */

@RestController
@RequestMapping("lawyer/api")
public class LawyerComtroller {

	// 律师服务
	@Autowired
	private LawyerService lawyerService;

	/**
	 * 律师认证
	 * 
	 * @param lawyerAuth
	 *            律认证信息
	 * @param caseIds
	 *            律师分类（多个，用逗号分隔）
	 * @return
	 */
	@PostMapping("/lawyerAuth")
	public AppMsgResult lawyerAuth(@RequestBody ZaLawyerAuthentication lawyerAuth,
			@RequestParam(required = true) String caseIds, @RequestParam(required = true) String token) {
		return lawyerService.lawyerAuth(lawyerAuth, caseIds, token);
	}

	/**
	 * 添加律师案例
	 * 
	 * @param lawyerAchievements
	 *            律师案例信息
	 * @return
	 */
	@PostMapping("/addAchievements")
	public AppMsgResult addAchievements(@RequestBody ZaLawyerAchievements lawyerAchievements,
			@RequestParam(required = true) String userId, @RequestParam(required = true) String token) {
		return lawyerService.addAchievements(lawyerAchievements, userId, token);
	}

	/**
	 * 删除律师案例
	 * 
	 * @param id
	 *            律师案例id主键
	 * @return
	 */
	@PostMapping("/deleteAchievements")
	public AppMsgResult deleteAchievements(@RequestParam String id, @RequestParam(required = true) String userId,
			@RequestParam(required = true) String token) {
		return lawyerService.deleteAchievements(id, userId, token);
	}

	/**
	 * 修改律师案例
	 * 
	 * @param lawyerAchievements
	 *            律师案例信息 注意一定要传id主键
	 * @return
	 */
	@PostMapping("/updateAchievements")
	public AppMsgResult updateAchievements(@RequestBody ZaLawyerAchievements lawyerAchievements,
			@RequestParam(required = true) String userId, @RequestParam(required = true) String token) {
		return lawyerService.updateAchievements(lawyerAchievements, userId, token);
	}

	/**
	 * 查询律师下所有案例
	 * 
	 * @param lawId
	 *            律师查询id
	 * @return
	 */
	@GetMapping("/selectAchievements")
	public AppMsgResult selectAchievements(@RequestParam(required = true) String userId,
			@RequestParam(required = true) String token) {
		return lawyerService.selectAchievements(userId, token);
	}
	/**
	 * 查询律师下所有案例
	 * 
	 * @param lawId
	 *            律师查询id
	 * @return
	 */
	@GetMapping("/selectLawyer")
	public AppMsgResult selectLawyer(@RequestParam(required = true) String userId,
			@RequestParam(required = true) String token,@RequestParam(required = true) String type) {
		return lawyerService.selectLawyer(userId, token,type);
	}

}

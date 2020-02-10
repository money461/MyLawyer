package com.tz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tz.pojo.ZaLawyerAchievements;
import com.tz.pojo.ZaLawyerAuthentication;
import com.tz.remote.LawyerRemote;
import com.tz.res.AppMsgResult;

/**
 * 律师类
 * 
 * @author menglin 2017年12月28日10:03:32
 *
 */
@RestController
@RequestMapping("/user/api")
public class LawyerContrller {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	LawyerRemote lawyerRemote;
	
	/**
	 * 律师认证
	 * 
	 * @param lawyerAuth
	 *            律师认证信息
	 * @param caseIds
	 *            律师分类
	 * @return
	 */
	@PostMapping("/lawyerAuth")
	public AppMsgResult lawyerAuth(ZaLawyerAuthentication lawyerAuth, String caseIds, String token) {
		return lawyerRemote.lawyerAuth(lawyerAuth, caseIds,token);
	}

	/**
	 * 添加律师案例
	 * 
	 * @param lawyerAchievements
	 *            律师案例信息
	 * @return
	 */
	@PostMapping("/addAchievements")
	public AppMsgResult addAchievements(ZaLawyerAchievements lawyerAchievements, String userId, String token) {
		LOG.info("addAchievements："+lawyerAchievements);
		LOG.info("bibi");
		return lawyerRemote.addAchievements(lawyerAchievements,userId,token);

	}

	/**
	 * 删除律师案例
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	@PostMapping("/deleteAchievements")
	public AppMsgResult deleteAchievements(String id, String userId, String token) {
		return lawyerRemote.deleteAchievements(id,userId,token);
	}

	/**
	 * 修改律师案例
	 * 
	 * @param lawyerAchievements
	 *            律师案例信息
	 * @return
	 */
	@PostMapping("/updateAchievements")
	public AppMsgResult updateAchievements(ZaLawyerAchievements lawyerAchievements, String userId, String token) {
		return lawyerRemote.updateAchievements(lawyerAchievements,userId,token);
	}
	/**
	 * 查询律师下所有案例
	 * @param lawId 律师主键id
	 * @return
	 */
	@GetMapping("/selectAchievements")
	public AppMsgResult selectAchievements(String userId, String token) {
		return lawyerRemote.selectAchievements(userId,token);
	}
	/**
	 * 查询律师资料
	 * @param lawId 律师主键id
	 * @return
	 */
	@GetMapping("/selectLawyer")
	public AppMsgResult selectLawyer(String userId, String token,String type) {
		return lawyerRemote.selectLawyer(userId, token,type);
	}


}

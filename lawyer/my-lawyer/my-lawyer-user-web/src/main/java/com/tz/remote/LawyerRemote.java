package com.tz.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.config.FeignConfiguration;
import com.tz.pojo.ZaLawyerAchievements;
import com.tz.pojo.ZaLawyerAuthentication;
import com.tz.res.AppMsgResult;

/**
 * 律师服务远程调用类
 * 
 * @author menglin 2017年12月28日09:56:36
 */
@FeignClient(name = "my-lawyer-user-service",configuration=FeignConfiguration.class, fallback = LawyerRemoteHystrix.class)
public interface LawyerRemote {

	// 律师认证
	@PostMapping("lawyer/api/lawyerAuth")
	public AppMsgResult lawyerAuth(ZaLawyerAuthentication lawyerAuth,@RequestParam(value = "caseIds",required = true) String caseIds, @RequestParam(value = "token",required = true) String token);

	// 添加律师案例
	@PostMapping("lawyer/api/addAchievements")
	public AppMsgResult addAchievements(ZaLawyerAchievements lawyerAchievements,
			@RequestParam(value = "userId",required = true) String userId, @RequestParam(value = "token",required = true) String token);

	// 删除律师案例
	@PostMapping("lawyer/api/deleteAchievements")
	public AppMsgResult deleteAchievements(@RequestParam(value = "id",required = true) String id, @RequestParam(value = "userId",required = true) String userId,
			@RequestParam(value = "token",required = true) String token);

	// 修改律师案例
	@PostMapping("lawyer/api/updateAchievements")
	public AppMsgResult updateAchievements(ZaLawyerAchievements lawyerAchievements,
			@RequestParam(value = "userId",required = true) String userId, @RequestParam(value = "token",required = true) String token);
	
	// 查询律师下所有案例
	@GetMapping("lawyer/api/selectAchievements")
	public AppMsgResult selectAchievements(@RequestParam(value = "userId",required = true) String userId,
			@RequestParam(value = "token",required = true) String token);
	// 查询律师资料
	@GetMapping("lawyer/api/selectLawyer")
	public AppMsgResult selectLawyer(@RequestParam(value = "userId",required = true) String userId,
			@RequestParam(value = "token",required = true) String token,
			@RequestParam(value = "type",required = true) String type);
}

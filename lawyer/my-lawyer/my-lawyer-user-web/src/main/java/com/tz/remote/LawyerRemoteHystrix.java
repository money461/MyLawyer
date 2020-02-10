package com.tz.remote;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.pojo.ZaLawyerAchievements;
import com.tz.pojo.ZaLawyerAuthentication;
import com.tz.res.AppMsgResult;

/**
 * 律师服务熔断类
 * @author menglin 2017年12月28日10:00:54
 *
 */
@Component
public class LawyerRemoteHystrix implements LawyerRemote{

	@Override
	public AppMsgResult lawyerAuth(ZaLawyerAuthentication lawyerAuth, String caseIds, String token) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult addAchievements(ZaLawyerAchievements lawyerAchievements, String userId, String token) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult deleteAchievements(String id, String userId, String token) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult updateAchievements(ZaLawyerAchievements lawyerAchievements, String userId, String token) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult selectAchievements(String userId, String token) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult selectLawyer(String userId, String token,String type) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	

   
}

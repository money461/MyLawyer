package com.tz.remote;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.pojo.ZaComAuthentication;
import com.tz.res.AppMsgResult;

/**
 * 企业服务熔断类
 * @author menglin 2017年12月28日09:59:39
 *
 */
@Component
public class CompanyRemoteHystrix implements CompanyRemote{

	@Override
	public AppMsgResult companyAuth(ZaComAuthentication comAuth, String scope, String token) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult selectCompany(String userId, String token,String type) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}



   
}

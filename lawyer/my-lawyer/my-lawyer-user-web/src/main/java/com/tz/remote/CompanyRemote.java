package com.tz.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.config.FeignConfiguration;
import com.tz.pojo.ZaComAuthentication;
import com.tz.res.AppMsgResult;

/**
 *  企业远程服务调用
 * @author menglin 2017年12月28日09:58:43
 *
 */
@FeignClient(name= "my-lawyer-user-service",configuration=FeignConfiguration.class,fallback = CompanyRemoteHystrix.class)
public interface CompanyRemote {


    //企业认证
    @PostMapping("company/api/companyAuth")
	public AppMsgResult companyAuth(ZaComAuthentication comAuth,
			@RequestParam(value = "scope",required = true) String scope, @RequestParam(value = "token",required = true) String token);
 // 查询律师资料
 	@GetMapping("lawyer/api/selectCompany")
 	public AppMsgResult selectCompany(@RequestParam(value = "userId",required = true) String userId,
 			@RequestParam(value = "token",required = true) String token,
 			@RequestParam(value = "type",required = true) String type);

}

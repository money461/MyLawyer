package com.tz.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.config.FeignConfiguration;
import com.tz.res.AppMsgResult;

@FeignClient(name="my-lawyer-company-service",configuration=FeignConfiguration.class,fallback=ComRemoteHystrix.class)
public interface ComRemote {

	@GetMapping(value="company/api/getCompanyCategory")
	AppMsgResult getCompanyCategory();

	@GetMapping(value="company/api/getCompanys")
	AppMsgResult getCompanys(@RequestParam(value="comName") String comName,@RequestParam(value="comCategoryName") String comCategoryName, @RequestParam(value="categoryId") String categoryId,@RequestParam(value="curPage")Integer curPage, @RequestParam(value="rows") Integer rows);

	@GetMapping(value="company/api/getCompanyDetailInfo")
	AppMsgResult getCompanyDetailInfo(@RequestParam(value="id")String id,@RequestParam(value="userId") String userId);
	
}

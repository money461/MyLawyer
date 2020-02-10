package com.tz.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.config.FeignConfiguration;
import com.tz.pojo.admin.ZaUserIncomeRecordAdminVo;
import com.tz.pojo.admin.ZaUserPurchaseRecordAdminVo;
import com.tz.res.AppMsgResult;

@FeignClient(name = "my-lawyer-admin-service",configuration=FeignConfiguration.class, fallback = UserDealRecordRemoteHystrix.class)
public interface UserDealRecordRemote {

	@PostMapping("admin/deal/api/getUserPurchaseRecordList")
	AppMsgResult getUserPurchaseRecordList(@RequestBody ZaUserPurchaseRecordAdminVo zaUserPurchaseRecordAdminVo, @RequestParam(value="curPage") Integer curPage,
			@RequestParam(value="rows")Integer rows, @RequestParam(value="userId") String userId, @RequestParam(value="token")String token);

	@PostMapping("admin/deal/api/getUserIncomeRecordList")
	AppMsgResult getUserIncomeRecordList(@RequestBody ZaUserIncomeRecordAdminVo zaUserIncomeRecordAdminVo,  @RequestParam(value="curPage") Integer curPage,
			@RequestParam(value="rows")Integer rows, @RequestParam(value="userId") String userId, @RequestParam(value="token")String token);

}

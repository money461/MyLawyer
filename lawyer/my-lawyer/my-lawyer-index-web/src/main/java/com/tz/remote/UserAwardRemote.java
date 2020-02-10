package com.tz.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.config.FeignConfiguration;
import com.tz.pojo.ZaUserAwardRecord;
import com.tz.res.AppMsgResult;

@FeignClient(value = "my-lawyer-index-service",configuration=FeignConfiguration.class, fallback = UserAwardRemoteHystrix.class)
public interface UserAwardRemote {

	@GetMapping(value = "userAward/api/getGift")
	AppMsgResult getGift(@RequestParam(value = "userId") String userId,
			@RequestParam(value = "userToken") String userToken);

	@PostMapping(value = "userAward/api/rewardOperation")
	AppMsgResult rewardOperation(@RequestParam(value="paymentType")Integer paymentType, @RequestBody ZaUserAwardRecord zaUserAwardRecord,@RequestParam(value="awardeeName") String awardeeName,
			@RequestParam(value="userPublishCaseId") String userPublishCaseId, @RequestParam(value = "userToken") String userToken);

}

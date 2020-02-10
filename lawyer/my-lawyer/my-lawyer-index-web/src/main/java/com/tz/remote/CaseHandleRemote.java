package com.tz.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.config.FeignConfiguration;
import com.tz.res.AppMsgResult;

@FeignClient(value = "my-lawyer-index-service",configuration=FeignConfiguration.class, fallback = CaseHandleRemoteHystrix.class)
public interface CaseHandleRemote {

	@PostMapping(value = "case/api/lawHandlingCase")
	AppMsgResult lawHandlingCase(@RequestParam(value = "id") String id, @RequestParam(value = "userId") String userId,
			@RequestParam(value = "userToken") String userToken);

	@PostMapping(value = "case/api/userOrderTaking")
	AppMsgResult userOrderTaking(@RequestParam(value = "id") Long id, @RequestParam(value = "agree") Integer agree,
			@RequestParam(value = "userId") String userId, @RequestParam(value = "userToken") String userToken);

	@PostMapping(value = "case/api/abandonPublishCase")
	AppMsgResult abandonPublishCase(@RequestParam(value = "id") String id,
			@RequestParam(value = "userType") Integer userType, @RequestParam(value = "userId") String userId,
			@RequestParam(value = "userToken") String userToken);

	@PostMapping(value = "case/api/lawyerComplete")
	AppMsgResult lawyerCompleteCase(@RequestParam(value = "id") String id,
			@RequestParam(value = "userId") String userId, @RequestParam(value = "userToken") String userToken);

	@PostMapping(value = "case/api/userReplyCompleteCase")
	AppMsgResult userReplyCompleteCase(@RequestParam(value = "id") Long id,
			@RequestParam(value = "agree") Integer agree, @RequestParam(value = "userId") String userId,
			@RequestParam(value = "userToken") String userToken);

	@PostMapping(value = "case/api/deletePublishCase")
	AppMsgResult deletePublishCase(@RequestParam(value = "id") String id, @RequestParam(value = "userId") String userId,
			@RequestParam(value = "userToken") String userToken);

}

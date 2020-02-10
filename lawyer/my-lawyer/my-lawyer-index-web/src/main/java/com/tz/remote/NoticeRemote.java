package com.tz.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.config.FeignConfiguration;
import com.tz.res.AppMsgResult;

@FeignClient(value = "my-lawyer-index-service",configuration=FeignConfiguration.class, fallback = NoticeRemoteHystrix.class)
public interface NoticeRemote {

	@PostMapping(value = "notice/api/inviteIOrDelMyLawyer")
	AppMsgResult inviteIOrDelMyLawyer(@RequestParam(value = "lawId") String lawId,
			@RequestParam(value = "type") Integer type, @RequestParam(value = "userId") String userId,
			@RequestParam(value = "userToken") String userToken);

	@PostMapping(value = "notice/api/lawyerAgreeOrRefuse")
	AppMsgResult lawyerAgreeOrRefuse(@RequestParam(value = "id") Long id, @RequestParam(value = "agree") Integer agree,
			@RequestParam(value = "userId") String userId, @RequestParam(value = "userToken") String userToken);

	@PostMapping(value = "notice/api/getSystemNotice")
	AppMsgResult getSystemNotice(@RequestParam(value = "noticeType") Integer noticeType,
			@RequestParam(value = "curPage") Integer curPage, @RequestParam(value = "rows") Integer rows,
			@RequestParam(value = "userId") String userId, @RequestParam(value = "userToken") String userToken);

	@PostMapping(value = "notice/api/delSysNotice")
	AppMsgResult delSysNoticeById(@RequestParam(value = "ids") String ids,
			@RequestParam(value = "userId") String userId, @RequestParam(value = "userToken") String userToken);

}

package com.tz.remote;

import java.math.BigDecimal;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.config.FeignConfiguration;
import com.tz.pojo.ZaUserPublishCase;
import com.tz.pojo.index.vo.SelectionCriteria;
import com.tz.res.AppMsgResult;

@FeignClient(name = "my-lawyer-index-service",configuration=FeignConfiguration.class, fallback = IndexRemoteHystrix.class)
public interface IndexRemote {

	@GetMapping("index/api/getSelectionModel")
	AppMsgResult getSelectionModel(
			@RequestParam(required = true, defaultValue = "1", value = "userType") Integer userType);

	@GetMapping("index/api/getIndexContent")
	AppMsgResult getIndexContent(
			@RequestParam(required = true, defaultValue = "1", value = "userType") Integer userType);

	@PostMapping("index/api/getRecommendOrConsultingService")
	AppMsgResult getRecommendOrConsultingService(@RequestBody SelectionCriteria selectionCriteria);
	
	@PostMapping("index/api/publishCaseOrAllograph")
	AppMsgResult publishCaseOrAllograph(@RequestBody ZaUserPublishCase zaUserPublishCase,@RequestParam(value="reward") BigDecimal reward, @RequestParam(value="giftId") String giftId,
			@RequestParam(value="giftNum")Integer giftNum, @RequestParam(value = "userToken") String userToken);


	@GetMapping("index/api/getCaseCategory")
	AppMsgResult getCaseCategory();


	@GetMapping("index/api/getLawyerDetail")
	AppMsgResult getLawyerDetail(@RequestParam(value = "id") String id, @RequestParam(value = "userId") String userId,
			@RequestParam(value = "userToken") String userToken);

	@PostMapping("index/api/userCollection")
	AppMsgResult userCollection(@RequestParam(value = "id") String id, @RequestParam(value = "obType") Integer obType,
			@RequestParam(value = "userId") String userId, @RequestParam(value = "userToken") String userToken);

	@GetMapping("index/api/showPersonalCenter")
	AppMsgResult showPersonalCenter(@RequestParam(value = "userType") Integer userType,
			@RequestParam(value = "userId") String userId);

	@PostMapping("index/api/updateOnlineState")
	AppMsgResult updateOnlineState(@RequestParam(value = "userType") Integer userType,
			@RequestParam(value = "state") Integer state, @RequestParam(value = "userId") String userId,
			@RequestParam(value = "userToken") String userToken);

	@GetMapping("index/api/getMyLawyer")
	AppMsgResult getMyLawyer(@RequestParam(value = "userAddress") String userAddress,
			@RequestParam(value = "userType") Integer userType, @RequestParam(value = "userId") String userId,
			@RequestParam(value = "userToken") String userToken);

	@GetMapping("index/api/getMyPublishCase")
	AppMsgResult getMyPublishCaseById(@RequestParam(value = "userId") String userId,
			@RequestParam(value = "userToken") String userToken, @RequestParam(value = "curPage") Integer curPage,
			@RequestParam(value = "rows") Integer rows);

	@GetMapping("index/api/getMyCollectionById")
	AppMsgResult getMyCollectionById(@RequestParam(value = "obType") Integer obType,
			@RequestParam(value = "lon") Double lon, @RequestParam(value = "lat") Double lat,
			@RequestParam(value = "userId") String userId, @RequestParam(value = "userToken") String userToken);

	@PostMapping("index/api/cancelCollection")
	AppMsgResult cancelCollection(@RequestParam(value = "id") String id, @RequestParam(value = "userId") String userId,
			@RequestParam(value = "userToken") String userToken);

	@GetMapping("index/api/getUserProfitById")
	AppMsgResult getUserProfitById(@RequestParam(value = "userId") String userId,
			@RequestParam(value = "userToken") String userToken);

	@GetMapping("index/api/getDealDetail")
	AppMsgResult getDealDetail(@RequestParam(value = "userId") String userId,
			@RequestParam(value = "userToken") String userToken, @RequestParam(value = "curPage") Integer curPage,
			@RequestParam(value = "rows") Integer rows);

	@GetMapping("index/api/getMyCustomer")
	AppMsgResult getMyCustomer(@RequestParam(value = "lon") Double lon, @RequestParam(value = "lat") Double lat,
			@RequestParam(value = "userId") String userId, @RequestParam(value = "userToken") String userToken);

	@GetMapping("index/api/getPublishCaseByLawId")
	AppMsgResult getPublishCaseByLawId(@RequestParam(value = "userId") String userId,
			@RequestParam(value = "userToken") String userToken);

}

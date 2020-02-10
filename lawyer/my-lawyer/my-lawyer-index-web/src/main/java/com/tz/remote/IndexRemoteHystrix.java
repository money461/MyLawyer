package com.tz.remote;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.pojo.ZaUserPublishCase;
import com.tz.pojo.index.vo.SelectionCriteria;
import com.tz.res.AppMsgResult;

@Component
public class IndexRemoteHystrix implements IndexRemote {

	@Override
	public AppMsgResult getSelectionModel(@RequestParam(value = "userType") Integer userType) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult getIndexContent(@RequestParam(value = "userType") Integer userType) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult getRecommendOrConsultingService(SelectionCriteria selectionCriteria) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult getCaseCategory() {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}


	@Override
	public AppMsgResult getLawyerDetail(String id, String userId, String userToken) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult userCollection(String id, Integer obType, String userId, String userToken) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult showPersonalCenter(Integer userType, String userId) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult updateOnlineState(Integer userType, Integer state, String userId, String userToken) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult getMyLawyer(String userAddress, Integer userType, String userId, String userToken) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult getMyPublishCaseById(String userId, String userToken, Integer curPage, Integer rows) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult getMyCollectionById(Integer obType, Double lon, Double lat, String userId, String userToken) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult cancelCollection(String id, String userId, String userToken) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult getUserProfitById(String userId, String userToken) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult getDealDetail(String userId, String userToken, Integer curPage, Integer rows) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult getMyCustomer(Double lon, Double lat, String userId, String userToken) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult getPublishCaseByLawId(String userId, String userToken) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult publishCaseOrAllograph(ZaUserPublishCase zaUserPublishCase, BigDecimal reward, String giftId,
			Integer giftNum, String userToken) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}


}

package com.tz.service;

import java.math.BigDecimal;

import com.tz.pojo.ZaUserPublishCase;
import com.tz.pojo.index.vo.SelectionCriteria;
import com.tz.res.AppMsgResult;

public interface IndexService {

	//获取用户主页面选择模块
	AppMsgResult getSelectionModel(Integer userType);

	//获取用户主页面轮播图和法律资讯内容
	AppMsgResult getIndexContent(Integer userType);

	//用户获取推荐律师或者咨询律师或者自主寻找律师信息
	AppMsgResult getRecommendOrConsultingService(SelectionCriteria selectionCriteria);

	//获取案件类型
	AppMsgResult getCaseCategory();

	//用户发布案件委托或者代写文书
	AppMsgResult publishCaseOrAllograph(ZaUserPublishCase zaUserPublishCase,BigDecimal reward,String giftId,Integer giftNum, String userToken);

	//获取律师详情信息
	AppMsgResult getLawyerDetail(String id, String userId, String userToken);

	//用户收藏 1 用户收藏律师 2 用户收藏企业 3 用户收藏商品
	AppMsgResult userCollection(String id, Integer obType, String userId, String userToken);

	//展示用户个人中心页面
	AppMsgResult showPersonalCenter(Integer userType, String userId);

	//修改用户离线在线状态
	AppMsgResult updateOnlineState(Integer userType, Integer state, String userId, String userToken);
	
	//查看我的律师
	AppMsgResult getMyLawyer(String userAddress, Integer userType, String userId, String userToken);

	//我的委托
	AppMsgResult getMyPublishCaseById(String userId, String userToken, Integer curPage, Integer rows);

	//查看我的收藏
	AppMsgResult getMyCollectionById(Integer obType,Double lon,Double lat, String userToken, String userToken2);

	//取消收藏
	AppMsgResult cancelCollection(String id, String userId, String userToken);

	//查看用户收益
	AppMsgResult getUserProfitById(String userId, String userToken);

	//获取交易明细
	AppMsgResult getDealDetail(String userId, String userToken, Integer curPage, Integer rows);

	//律师获取我的客户
	AppMsgResult getMyCustomer(Double lon, Double lat, String userId, String userToken);

	//获取律师用户承接的案件列表信息
	AppMsgResult getPublishCaseByLawId(String userId, String userToken);



	

}

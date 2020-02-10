package com.tz.service;

import com.tz.res.AppMsgResult;

public interface ComService {
	
	//获取企业分类信息
	AppMsgResult getCompanyCategory();

	//获取企业列表信息
	AppMsgResult getCompanys(String comName,String comCategoryName, String categoryId, Integer curPage, Integer rows);

	//展示企业详情信息
	AppMsgResult getCompanyDetailInfo(String id,String userId);


}

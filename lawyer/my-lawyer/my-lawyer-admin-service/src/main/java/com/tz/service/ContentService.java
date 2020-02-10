package com.tz.service;

import com.tz.pojo.ZaContent;
import com.tz.pojo.admin.ZaContentAdminVo;
import com.tz.res.AppMsgResult;

public interface ContentService {

	//查询内容信息
	AppMsgResult getContentList(ZaContentAdminVo zaContentAdminVo,Integer curPage,Integer rows, String userId, String token);

	//添加或者更新内容
	AppMsgResult addOrUpdateContent(ZaContent zaContent, String type,String userId, String token);

	//查询内容详情
	AppMsgResult queryContentById(String id, String userId, String token);

	//删除广告内容
	AppMsgResult delContentById(String id, String userId, String token);

}

package com.tz.service;

import com.tz.pojo.ZaContentCategory;
import com.tz.pojo.admin.ZaContentCategoryAdminVo;
import com.tz.res.AppMsgResult;

public interface ContentCategoryService {

	//获取广告内容分类数据
	AppMsgResult getContentCategoryList(ZaContentCategoryAdminVo zaContentCategoryAdminVo,Integer curPage,Integer rows, String userId, String token);

	//更新或者修改广告内容分类
	AppMsgResult addOrUpdateContentCategory(ZaContentCategory zaContentCategory, String type,String userId, String token);

	//根据id查询广告内容分类
	AppMsgResult queryContentCategoryById(Integer id, String userId, String token);

	//删除广告内容分类
	AppMsgResult delContentCategoryById(Integer id, String userId, String token);

	//启用或者停用广告内容分类
	AppMsgResult disOrEnableContentCategoryById(Integer id, Integer status, String userId, String token);

	//查询所有的广告内容分类
	AppMsgResult selectAllContentCategory(Integer status, String userId, String token);

}

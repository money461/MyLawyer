package com.tz.service;

import com.tz.pojo.ZaComCategory;
import com.tz.pojo.vo.ZaComCategoryVo;
import com.tz.res.AppMsgResult;

/**
 * 企业分类接口
 * 
 * @author menglin 2018年1月19日10:09:13
 */
public interface ZaComCategoryService {

	// 查询所有的企业分类或者查询父类下的所有子类分类列表
	AppMsgResult findCategoryOrSonList(ZaComCategoryVo comCategoryVo, Integer curPage, Integer rows, String type, String userId,
			String token);
	
	// 添加或者修改企业分类信息
	AppMsgResult addOrUpdateCategory(ZaComCategory comCategory, String type, String userId, String token);

	// 根据id修改企业分类状态
	AppMsgResult updateStatusById(String id, String caseStatus,String userId, String token);
	
	// 根据id删除企业分类信息
	AppMsgResult deleteById(String id,String userId, String token);
	
	// 查询企业分类信息
	AppMsgResult selectById(String id,String userId, String token);
	
}

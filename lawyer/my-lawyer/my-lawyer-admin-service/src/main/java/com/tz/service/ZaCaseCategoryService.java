package com.tz.service;

import com.tz.pojo.ZaCaseCategory;
import com.tz.pojo.vo.ZaCaseCategoryVo;
import com.tz.res.AppMsgResult;

/**
 * 律师分类接口
 * 
 * @author menglin 2018年1月19日10:09:13
 */
public interface ZaCaseCategoryService {

	// 查询所有的律师分类或者查询父类下的所有子类分类列表
	AppMsgResult findCategoryOrSonList(ZaCaseCategoryVo caseCategoryVo, Integer curPage, Integer rows, String type, String userId,
			String token);
	
	// 添加或者修改律师分类信息
	AppMsgResult addOrUpdateCategory(ZaCaseCategory caseCategory, String type, String userId, String token);

	// 根据id修改律师分类状态
	AppMsgResult updateStatusById(String id, String caseStatus,String userId, String token);
	
	// 根据id删除律师分类信息
	AppMsgResult deleteById(String id,String userId, String token);
	
	// 查询律师分类信息
	AppMsgResult selectById(String id,String userId, String token);
	
	
}

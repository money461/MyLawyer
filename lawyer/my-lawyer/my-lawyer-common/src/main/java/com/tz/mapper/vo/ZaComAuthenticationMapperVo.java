package com.tz.mapper.vo;

import java.util.List;
import java.util.Map;

import com.tz.pojo.vo.CategoryNames;
import com.tz.pojo.vo.ZaComAuthenticationVo;

public interface ZaComAuthenticationMapperVo {
	
	//查询企业的分类名称
    List<CategoryNames> selectComCategoryNames(String id);
    
    //查询平台所有律师用户信息
    List<ZaComAuthenticationVo> selectComAndUserList(Map map);
    
    //查询平台所有正在认证的律师用户信息
    List<ZaComAuthenticationVo> selectComAndUserAuthList(Map map);
}
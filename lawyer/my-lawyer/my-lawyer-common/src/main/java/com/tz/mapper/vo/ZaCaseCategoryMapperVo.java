package com.tz.mapper.vo;

import com.tz.pojo.vo.CategoryNames;
import com.tz.pojo.vo.LawyerAchievementsVo;
import com.tz.pojo.vo.ZaCaseCategoryVo;
import com.tz.pojo.vo.ZaLawyerAuthenticationVo;

import java.util.List;
import java.util.Map;

public interface ZaCaseCategoryMapperVo {
	
	//查询律师的分类名称
    List<ZaCaseCategoryVo> selectCategoryAndChSonList(Map map);
    
    

}
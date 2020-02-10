package com.tz.mapper.vo;

import com.tz.pojo.vo.CategoryNames;
import com.tz.pojo.vo.LawyerAchievementsVo;
import com.tz.pojo.vo.ZaCaseCategoryVo;
import com.tz.pojo.vo.ZaComCategoryVo;
import com.tz.pojo.vo.ZaLawyerAuthenticationVo;

import java.util.List;
import java.util.Map;

public interface ZaComCategoryMapperVo {
	
	//查询企业经营类型二级分类
    List<ZaComCategoryVo> selectComCategoryAndChSonList(Map map);
    
    

}
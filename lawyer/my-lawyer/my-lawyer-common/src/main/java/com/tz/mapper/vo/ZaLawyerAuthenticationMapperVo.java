package com.tz.mapper.vo;

import com.tz.pojo.vo.CategoryNames;
import com.tz.pojo.vo.LawyerAchievementsVo;
import com.tz.pojo.vo.ZaLawyerAuthenticationVo;

import java.util.List;
import java.util.Map;

public interface ZaLawyerAuthenticationMapperVo {
	
	//查询律师的分类名称
    List<CategoryNames> selectLawyerCategoryNames(String id);
    //查询律师下所有案件列表
    List<LawyerAchievementsVo> selectLawyerAchievements(String id);
    //查询平台所有律师用户信息
    List<ZaLawyerAuthenticationVo> selectLawyerAndUserList(Map map);
    //查询平台所有正在认证的律师用户信息
    List<ZaLawyerAuthenticationVo> selectLawyerAndUserAuthList(Map map);
    
    

}
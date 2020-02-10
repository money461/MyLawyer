package com.tz.mapper.index.vo;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.index.vo.IndexContentVo;
import com.tz.pojo.index.vo.ZaContentCategoryVo;


public interface ZaContentCategoryMapperVo {

	//查询用户主页选择项
	List<ZaContentCategoryVo> getSelectionModel(@Param("userType") Integer userType);

	//查询用户主页广告信息
	List<IndexContentVo> getIndexContent(@Param("userType") Integer userType);

	//获取个人设置中心图标信息
	IndexContentVo getPersonalCenter(@Param("userType") Integer userType);

}
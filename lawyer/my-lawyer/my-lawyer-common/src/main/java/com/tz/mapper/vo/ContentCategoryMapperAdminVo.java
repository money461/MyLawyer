package com.tz.mapper.vo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.ZaContentCategory;
import com.tz.pojo.admin.ZaContentCategoryAdminVo;

public interface ContentCategoryMapperAdminVo {

	//查询广告内容信息(子类信息)
	List<ZaContentCategoryAdminVo> selectContentCategoryList(Map<String, Object> map);

	//校验code是否存在
	Integer checkContentCategorySortCode(Integer sortCode);

	//查询内容分类
	Integer insertContentCategorySelective(ZaContentCategory zaContentCategory);

	//更新内容分类
	Integer updateContentCategorySelectvice(ZaContentCategory zaContentCategory);

	//根据id查询内容分类
	ZaContentCategoryAdminVo queryContentCategoryById(@Param("id") Integer id);

	//删除内容分类
	void delContentCategoryById(@Param("id") Integer id);

	//查询所有的内容分类
	List<ZaContentCategory> selectAllContentCategory(Integer status);

}

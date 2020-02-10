package com.tz.mapper.vo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.ZaContent;
import com.tz.pojo.admin.ZaContentAdminVo;

public interface ZaContentMapperAdminVo {

	//获取内容信息
	List<ZaContentAdminVo> getContentList(Map<String, Object> map);

	//校验在同一类别中，排序sort是否重复
	Integer checkContentSort(@Param("contentCategoryId") Integer contentCategoryId,@Param("sort") Integer sort);

	//插入内容
	Integer insertContentSelective(ZaContent zaContent);

	//修改内容
	Integer updateContentSelective(ZaContent zaContent);

	//根据id查询内容
	ZaContent queryContentById(@Param("id") String id);

	//删除内容
	void delContentById(@Param("id") String id);

}

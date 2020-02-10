package com.tz.mapper.vo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.ZaCaseCategory;
import com.tz.pojo.admin.ZaUserPublishCaseAdminVo;

public interface UserPublishCaseMapperAdminVo {

	List<ZaUserPublishCaseAdminVo> selectUserPublishCaseList(Map<String, Object> map);

	ZaUserPublishCaseAdminVo queryUserPublishCaseById(@Param("id") String id);

	Integer checkUserPublishCaseById(@Param("id") String id);

	void delUserPublishCase(@Param("id") String id);

	void downUserPublishCase(@Param("id") String id);

	List<ZaCaseCategory> getCaseCategoryList(@Param("status") Integer status);

}

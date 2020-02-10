package com.tz.mapper.vo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.ZaPlatRoleAuthority;

public interface ZaPlatRoleAuthorityMapperVo {

	//批量插入角色权限信息
	void batchInsertRoleAuthData(@Param("platRoleAuthList") List<ZaPlatRoleAuthority> platRoleAuthList);

	//删除平台角色权限关系
	void delPlatRoleAuthByRoleId(@Param("id") String id);

	//删除平台角色权限关系
	void delPlatRoleAuthByAuthId(@Param("id") String id);

}

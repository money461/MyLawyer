package com.tz.mapper.vo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.ZaPlatManagerRole;

public interface ZaPlatManagerRoleMapperVo {

	//批量插入管理员与角色信息
	void batchInsertManagerRoleData(@Param("managerRoleList")  List<ZaPlatManagerRole> managerRoleList);

	//删除该用户角色中间表数据
	void deleteManagerRoleByManagerId(@Param("id") String id);

	//删除该角色用户中间数据
	void delPlatManagerRoleByRoleId(@Param("id") String id);

}

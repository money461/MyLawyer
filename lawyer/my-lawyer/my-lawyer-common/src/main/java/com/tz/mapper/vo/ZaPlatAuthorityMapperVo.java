package com.tz.mapper.vo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.ZaPlatAuthority;

public interface ZaPlatAuthorityMapperVo {

	//查询所有的权限信息
	List<ZaPlatAuthority> selectPlatAuthUsable(@Param("status") Integer status);

	//校验code是否重复
	Integer checkPlatAuthorityByCode(@Param("code") String code);

	//向权限表中插入数据
	Integer insertPlatAuthSelective( ZaPlatAuthority zaPlatAuthority);

	//根据id查询权限id
	ZaPlatAuthority queryAuthorityById(@Param("id") String id);

	//更新权限
	Integer updateAuthSelective(ZaPlatAuthority zaPlatAuthority);

	List<ZaPlatAuthority> selectPlatAuthorityList(Map<String, Object> map);

	//查询该权限子节点
	List<ZaPlatAuthority> querySonAuthorityByPid(String id);

	//删除权限
	void delPlatAuthorityById(String id);
	
	//查询某个管理员下所有的启用的权限
	List<ZaPlatAuthority> queryAllAuthorityByManagerId(String id);
	

}

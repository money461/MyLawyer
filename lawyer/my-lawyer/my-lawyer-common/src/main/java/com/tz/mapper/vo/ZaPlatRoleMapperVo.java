package com.tz.mapper.vo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.ZaPlatRole;
import com.tz.pojo.vo.ZaPlatRoleVo;

public interface ZaPlatRoleMapperVo {

	//查询所有未被禁用的角色信息
	List<ZaPlatRole> selectPlatRoleUsable(@Param("status") Integer status);

	//检验code是否唯一
	Integer checkRoleCodeByCode(@Param("code")String code);

	//插入角色信息
	Integer insertPlatRoleSelective(ZaPlatRole zaPlatRole);
	
	//根据id查询角色
	ZaPlatRoleVo queryPlatRoleById(@Param("id") String id);

	//修改角色
	Integer updateSelectiveById(ZaPlatRole zaPlatRole);

	//查询角色列表信息
	List<ZaPlatRoleVo> selectPlatRoleList(Map<String, Object> map);

	//删除角色
	void delPlatRoleById(@Param("id") String id);

	//查询当前角色下的子角色
	List<ZaPlatRole> queryPlatSonRoleByPid(@Param("id") String id);

}

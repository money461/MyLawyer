package com.tz.mapper.vo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.ZaPlatManager;
import com.tz.pojo.vo.ZaPlatManagerVo;

public interface ZaPlatManagerMapperVo {

    //校验该用户的账户号是否已经存在
	Integer checkPlatManagerAccount(@Param("managerAccount") String managerAccount);

	//插入用户账户信息
	int insertSelective(ZaPlatManager platManager);

	//查询管理员列表信息
	List<ZaPlatManagerVo> selectPlatManagerList(Map<String, Object> map);

	//查询管理员信息
	ZaPlatManagerVo queryPlatManagerById(@Param("id") String id);

	//更新管理员信息
	Integer updateSelectiveById(ZaPlatManager platManager);

	//删除管理员id
	void deletePlatManagerById(String id);


}

package com.tz.mapper.vo;

import java.util.List;
import java.util.Map;

import com.tz.pojo.admin.ZaUserAwardRecordAdminVo;

public interface UserAwardRecordMapperAdminVo {

	//查询用户打赏记录
	List<ZaUserAwardRecordAdminVo> selectUserAwardRecordList(Map<String, Object> map);

}

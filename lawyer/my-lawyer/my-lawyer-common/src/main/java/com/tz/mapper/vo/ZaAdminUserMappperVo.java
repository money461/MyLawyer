package com.tz.mapper.vo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.vo.ZaAdminUserVo;

public interface ZaAdminUserMappperVo {

	List<ZaAdminUserVo> getDeletedUserList(Map<String, Object> map);

	void forceDelUserById(@Param("id") String id,@Param("userType") Integer userType);

}

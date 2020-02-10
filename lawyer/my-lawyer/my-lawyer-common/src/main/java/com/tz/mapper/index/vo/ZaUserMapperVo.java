package com.tz.mapper.index.vo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.ZaUser;
import com.tz.pojo.index.vo.MyCustomer;
import com.tz.pojo.index.vo.PersonalInfo;

public interface ZaUserMapperVo {

	//获取用户个人信息
	PersonalInfo getPersonalCenter(@Param("userType") Integer userType,@Param("userId") String userId);

	//律师获取律师的客户信息
	List<MyCustomer> getMyCustomert(String userId);

	//查询用户昵称
	ZaUser queryUserById(@Param("id") String id);

}

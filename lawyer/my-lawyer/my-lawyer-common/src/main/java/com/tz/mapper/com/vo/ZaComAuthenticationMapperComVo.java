package com.tz.mapper.com.vo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.com.vo.ComAuthenDetailInfo;
import com.tz.pojo.com.vo.ZaComAuthenticationComVo;

public interface ZaComAuthenticationMapperComVo {

	//获取认证企业
	List<ZaComAuthenticationComVo> getCompanys(Map<String, Object> map);

	//判断用户是否已收藏
	Integer queryUserCollectionById(@Param("userId") String userId,@Param("id") String id);

	//查询企业详情信息
	ComAuthenDetailInfo getCompanyDetailInfo(@Param("id") String id);

}

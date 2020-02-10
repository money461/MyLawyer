package com.tz.mapper.index.vo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.index.vo.DealDetail;


public interface ZaUserDealDetailMapperVo {

     	//获取用户消费交易记录
		List<DealDetail> getUserDealDetailById(@Param("userId") String userId);
	
}

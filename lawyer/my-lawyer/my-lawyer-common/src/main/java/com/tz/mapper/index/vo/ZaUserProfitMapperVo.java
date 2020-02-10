package com.tz.mapper.index.vo;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.ZaUserProfit;

public interface ZaUserProfitMapperVo {

	//查询用户剩余货币值
	ZaUserProfit getUserProfitById(@Param("userId") String userId);
	
	//插入用户收益数据信息
	void insertUserProfit(ZaUserProfit userProfit);

	//修改用户打赏后剩余值
	Integer updateUserProfitById(ZaUserProfit zaUserProfit);

   
}
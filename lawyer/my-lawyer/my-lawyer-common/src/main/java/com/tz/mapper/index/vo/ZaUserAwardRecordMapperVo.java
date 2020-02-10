package com.tz.mapper.index.vo;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.ZaUserAwardRecord;
import com.tz.pojo.admin.ZaUserAwardRecordAdminVo;

public interface ZaUserAwardRecordMapperVo {

	//记录用户打赏或者悬赏数据
	Integer insertUserAwardRecord(ZaUserAwardRecord awardRecord);
	
	//根据案件查询该案件用户设定的悬赏值
	ZaUserAwardRecordAdminVo queryUserAwardRecordByCaseId(@Param("id") String userPublishCaseId);
	
	//获取打赏数据
	ZaUserAwardRecord getRewardRecordById(String awardId);

	//设置为打赏取消状态2
	Integer alertAwardRecordStatus(String awardId);

	//修改打赏记录
	Integer updateUserAwardRecordSelective(ZaUserAwardRecord zaUserAwardRecord);

	//删除悬赏记录
	void deleteUserAwardRecordById(@Param("id") String id);

   
}
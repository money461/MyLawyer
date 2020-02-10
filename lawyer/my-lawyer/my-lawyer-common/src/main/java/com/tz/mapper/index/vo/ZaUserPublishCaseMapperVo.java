package com.tz.mapper.index.vo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.ZaUserAwardRecord;
import com.tz.pojo.ZaUserPublishCase;
import com.tz.pojo.index.vo.RecomPublishCaseVo;
import com.tz.pojo.index.vo.ZaUserPublishCaseVo;

public interface ZaUserPublishCaseMapperVo {

	//获取用户发布的案件类型信息
	List<RecomPublishCaseVo> getUserPublishCase(Map<String, Object> map);

	//用户发布案件或者发布代写文书
	Integer insertUserPublishCase(ZaUserPublishCase zaUserPublishCase);
	
	//防止赏金发布多个案件
	Integer avoidPublishCase(String awardId);

	//查看我的委托数据
	List<ZaUserPublishCaseVo> getMyPublishCaseById(String userId);

	//获取律师用户承接的案件信息
	List<ZaUserPublishCaseVo> getPublishCaseByLawId(String userId);

	//更新发布的案例
	Integer updatePublishCase(ZaUserPublishCase zaUserPublishCase);

	//查询案例获取案例请求者和律师id以及案件状态
	ZaUserPublishCase queryPublishCaseById(@Param("id") String id);
	
	//定时查询所有正在寻求解决中已过期的案件
	List<ZaUserPublishCase> queryPublishCaseOverdue(@Param("format") String format);

   
}
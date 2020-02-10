package com.tz.mapper.index.vo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.index.vo.LawyerDetailInfo;
import com.tz.pojo.index.vo.RecomSelectionLawyerVo;
import com.tz.pojo.index.vo.ZaLawyerAuthenticationIndexVo;

public interface ZaLawyerAuthenticationMapperIndexVo {

	//推荐律师信息
	List<RecomSelectionLawyerVo> getRecommendLawInfo(Map<String, Object> map);

	//查询律师详情
	LawyerDetailInfo getLawyerDetailById(String id);

	//查看我的律师
	List<ZaLawyerAuthenticationIndexVo> getMyLawyer(String userId);

	//修改用户在线或者离线状态
	Integer updateOnlineState(@Param("userType") Integer userType,@Param("userId") String userId, @Param("state") Integer state);

}

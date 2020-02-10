package com.tz.mapper.index.vo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.ZaUserCollection;
import com.tz.pojo.index.vo.ZaComAuthenticationIndexVo;
import com.tz.pojo.index.vo.ZaLawyerAuthenticationIndexVo;

public interface ZaUserCollectionMapperVo {

	//查询用户是否收藏该对象
	Integer queryUserCollectionById(@Param("userId") String userId,@Param("id") String id);
	
	//将收藏关系写入收藏表中
	void insertUserCollection(ZaUserCollection zaUserCollection);

	//获取收藏的律师
	List<ZaLawyerAuthenticationIndexVo> getMyCollectionLawyerById(@Param("userId") String userId);

	List<ZaComAuthenticationIndexVo> getMyCollectionComById(@Param("userId") String userId);

	//取消收藏
	Integer cancelCollection(@Param("id")String id, @Param("userId") String userId);
	

}

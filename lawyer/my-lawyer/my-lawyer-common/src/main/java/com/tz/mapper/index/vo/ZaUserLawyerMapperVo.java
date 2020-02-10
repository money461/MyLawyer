package com.tz.mapper.index.vo;

import org.apache.ibatis.annotations.Param;
import com.tz.pojo.ZaUserLawyer;

public interface ZaUserLawyerMapperVo {

	//检查用户是否值存在一个律师
	Integer checkUserToOneLawyer(@Param("userId") String userId);
	
	//检测是否已经存在个人律师
	Integer checkUserLawyer(@Param("lawId") String lawId,@Param("userId") String userId);

	//解绑我的专属律师
	Integer unbundleMyLawyer(@Param("lawId") String lawId, @Param("userId") String userId);

    //用户绑定律师成为自己的专属律师
	Integer insertUserLawyer(ZaUserLawyer zaUserLawyer);


}

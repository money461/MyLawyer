package com.tz.mapper;

import com.tz.pojo.ZaLawyerAuthentication;
import com.tz.pojo.ZaLawyerAuthenticationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaLawyerAuthenticationMapper {
    int countByExample(ZaLawyerAuthenticationExample example);

    int deleteByExample(ZaLawyerAuthenticationExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaLawyerAuthentication record);

    int insertSelective(ZaLawyerAuthentication record);

    List<ZaLawyerAuthentication> selectByExample(ZaLawyerAuthenticationExample example);

    ZaLawyerAuthentication selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaLawyerAuthentication record, @Param("example") ZaLawyerAuthenticationExample example);

    int updateByExample(@Param("record") ZaLawyerAuthentication record, @Param("example") ZaLawyerAuthenticationExample example);

    int updateByPrimaryKeySelective(ZaLawyerAuthentication record);

    int updateByPrimaryKey(ZaLawyerAuthentication record);
}
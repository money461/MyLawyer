package com.tz.mapper;

import com.tz.pojo.ZaComAuthentication;
import com.tz.pojo.ZaComAuthenticationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaComAuthenticationMapper {
    int countByExample(ZaComAuthenticationExample example);

    int deleteByExample(ZaComAuthenticationExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaComAuthentication record);

    int insertSelective(ZaComAuthentication record);

    List<ZaComAuthentication> selectByExample(ZaComAuthenticationExample example);

    ZaComAuthentication selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaComAuthentication record, @Param("example") ZaComAuthenticationExample example);

    int updateByExample(@Param("record") ZaComAuthentication record, @Param("example") ZaComAuthenticationExample example);

    int updateByPrimaryKeySelective(ZaComAuthentication record);

    int updateByPrimaryKey(ZaComAuthentication record);
}
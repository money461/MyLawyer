package com.tz.mapper;

import com.tz.pojo.ZaComAuthority;
import com.tz.pojo.ZaComAuthorityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaComAuthorityMapper {
    int countByExample(ZaComAuthorityExample example);

    int deleteByExample(ZaComAuthorityExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaComAuthority record);

    int insertSelective(ZaComAuthority record);

    List<ZaComAuthority> selectByExample(ZaComAuthorityExample example);

    ZaComAuthority selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaComAuthority record, @Param("example") ZaComAuthorityExample example);

    int updateByExample(@Param("record") ZaComAuthority record, @Param("example") ZaComAuthorityExample example);

    int updateByPrimaryKeySelective(ZaComAuthority record);

    int updateByPrimaryKey(ZaComAuthority record);
}
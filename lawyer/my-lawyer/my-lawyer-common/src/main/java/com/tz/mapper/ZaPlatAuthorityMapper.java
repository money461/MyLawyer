package com.tz.mapper;

import com.tz.pojo.ZaPlatAuthority;
import com.tz.pojo.ZaPlatAuthorityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaPlatAuthorityMapper {
    int countByExample(ZaPlatAuthorityExample example);

    int deleteByExample(ZaPlatAuthorityExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaPlatAuthority record);

    int insertSelective(ZaPlatAuthority record);

    List<ZaPlatAuthority> selectByExample(ZaPlatAuthorityExample example);

    ZaPlatAuthority selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaPlatAuthority record, @Param("example") ZaPlatAuthorityExample example);

    int updateByExample(@Param("record") ZaPlatAuthority record, @Param("example") ZaPlatAuthorityExample example);

    int updateByPrimaryKeySelective(ZaPlatAuthority record);

    int updateByPrimaryKey(ZaPlatAuthority record);
}
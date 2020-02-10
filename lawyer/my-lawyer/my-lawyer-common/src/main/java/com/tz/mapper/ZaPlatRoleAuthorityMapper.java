package com.tz.mapper;

import com.tz.pojo.ZaPlatRoleAuthority;
import com.tz.pojo.ZaPlatRoleAuthorityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaPlatRoleAuthorityMapper {
    int countByExample(ZaPlatRoleAuthorityExample example);

    int deleteByExample(ZaPlatRoleAuthorityExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaPlatRoleAuthority record);

    int insertSelective(ZaPlatRoleAuthority record);

    List<ZaPlatRoleAuthority> selectByExample(ZaPlatRoleAuthorityExample example);

    ZaPlatRoleAuthority selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaPlatRoleAuthority record, @Param("example") ZaPlatRoleAuthorityExample example);

    int updateByExample(@Param("record") ZaPlatRoleAuthority record, @Param("example") ZaPlatRoleAuthorityExample example);

    int updateByPrimaryKeySelective(ZaPlatRoleAuthority record);

    int updateByPrimaryKey(ZaPlatRoleAuthority record);
}
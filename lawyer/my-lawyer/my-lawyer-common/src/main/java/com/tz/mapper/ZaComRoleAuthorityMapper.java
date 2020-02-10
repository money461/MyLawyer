package com.tz.mapper;

import com.tz.pojo.ZaComRoleAuthority;
import com.tz.pojo.ZaComRoleAuthorityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaComRoleAuthorityMapper {
    int countByExample(ZaComRoleAuthorityExample example);

    int deleteByExample(ZaComRoleAuthorityExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaComRoleAuthority record);

    int insertSelective(ZaComRoleAuthority record);

    List<ZaComRoleAuthority> selectByExample(ZaComRoleAuthorityExample example);

    ZaComRoleAuthority selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaComRoleAuthority record, @Param("example") ZaComRoleAuthorityExample example);

    int updateByExample(@Param("record") ZaComRoleAuthority record, @Param("example") ZaComRoleAuthorityExample example);

    int updateByPrimaryKeySelective(ZaComRoleAuthority record);

    int updateByPrimaryKey(ZaComRoleAuthority record);
}
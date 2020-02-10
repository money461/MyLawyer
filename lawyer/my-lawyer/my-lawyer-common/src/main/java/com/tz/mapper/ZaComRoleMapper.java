package com.tz.mapper;

import com.tz.pojo.ZaComRole;
import com.tz.pojo.ZaComRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaComRoleMapper {
    int countByExample(ZaComRoleExample example);

    int deleteByExample(ZaComRoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaComRole record);

    int insertSelective(ZaComRole record);

    List<ZaComRole> selectByExample(ZaComRoleExample example);

    ZaComRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaComRole record, @Param("example") ZaComRoleExample example);

    int updateByExample(@Param("record") ZaComRole record, @Param("example") ZaComRoleExample example);

    int updateByPrimaryKeySelective(ZaComRole record);

    int updateByPrimaryKey(ZaComRole record);
}
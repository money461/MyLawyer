package com.tz.mapper;

import com.tz.pojo.ZaPlatRole;
import com.tz.pojo.ZaPlatRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaPlatRoleMapper {
    int countByExample(ZaPlatRoleExample example);

    int deleteByExample(ZaPlatRoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaPlatRole record);

    int insertSelective(ZaPlatRole record);

    List<ZaPlatRole> selectByExample(ZaPlatRoleExample example);

    ZaPlatRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaPlatRole record, @Param("example") ZaPlatRoleExample example);

    int updateByExample(@Param("record") ZaPlatRole record, @Param("example") ZaPlatRoleExample example);

    int updateByPrimaryKeySelective(ZaPlatRole record);

    int updateByPrimaryKey(ZaPlatRole record);
}
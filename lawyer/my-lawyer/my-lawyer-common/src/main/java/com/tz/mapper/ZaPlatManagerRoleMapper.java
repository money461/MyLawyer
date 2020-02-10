package com.tz.mapper;

import com.tz.pojo.ZaPlatManagerRole;
import com.tz.pojo.ZaPlatManagerRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaPlatManagerRoleMapper {
    int countByExample(ZaPlatManagerRoleExample example);

    int deleteByExample(ZaPlatManagerRoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaPlatManagerRole record);

    int insertSelective(ZaPlatManagerRole record);

    List<ZaPlatManagerRole> selectByExample(ZaPlatManagerRoleExample example);

    ZaPlatManagerRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaPlatManagerRole record, @Param("example") ZaPlatManagerRoleExample example);

    int updateByExample(@Param("record") ZaPlatManagerRole record, @Param("example") ZaPlatManagerRoleExample example);

    int updateByPrimaryKeySelective(ZaPlatManagerRole record);

    int updateByPrimaryKey(ZaPlatManagerRole record);
}
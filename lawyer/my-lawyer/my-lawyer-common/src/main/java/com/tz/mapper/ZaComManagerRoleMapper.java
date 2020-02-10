package com.tz.mapper;

import com.tz.pojo.ZaComManagerRole;
import com.tz.pojo.ZaComManagerRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaComManagerRoleMapper {
    int countByExample(ZaComManagerRoleExample example);

    int deleteByExample(ZaComManagerRoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaComManagerRole record);

    int insertSelective(ZaComManagerRole record);

    List<ZaComManagerRole> selectByExample(ZaComManagerRoleExample example);

    ZaComManagerRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaComManagerRole record, @Param("example") ZaComManagerRoleExample example);

    int updateByExample(@Param("record") ZaComManagerRole record, @Param("example") ZaComManagerRoleExample example);

    int updateByPrimaryKeySelective(ZaComManagerRole record);

    int updateByPrimaryKey(ZaComManagerRole record);
}
package com.tz.mapper;

import com.tz.pojo.ZaUser;
import com.tz.pojo.ZaUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaUserMapper {
    int countByExample(ZaUserExample example);

    int deleteByExample(ZaUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaUser record);

    int insertSelective(ZaUser record);

    List<ZaUser> selectByExample(ZaUserExample example);

    ZaUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaUser record, @Param("example") ZaUserExample example);

    int updateByExample(@Param("record") ZaUser record, @Param("example") ZaUserExample example);

    int updateByPrimaryKeySelective(ZaUser record);

    int updateByPrimaryKey(ZaUser record);
}
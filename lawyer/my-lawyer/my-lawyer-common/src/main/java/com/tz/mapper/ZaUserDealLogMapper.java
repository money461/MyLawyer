package com.tz.mapper;

import com.tz.pojo.ZaUserDealLog;
import com.tz.pojo.ZaUserDealLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaUserDealLogMapper {
    int countByExample(ZaUserDealLogExample example);

    int deleteByExample(ZaUserDealLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaUserDealLog record);

    int insertSelective(ZaUserDealLog record);

    List<ZaUserDealLog> selectByExample(ZaUserDealLogExample example);

    ZaUserDealLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaUserDealLog record, @Param("example") ZaUserDealLogExample example);

    int updateByExample(@Param("record") ZaUserDealLog record, @Param("example") ZaUserDealLogExample example);

    int updateByPrimaryKeySelective(ZaUserDealLog record);

    int updateByPrimaryKey(ZaUserDealLog record);
}
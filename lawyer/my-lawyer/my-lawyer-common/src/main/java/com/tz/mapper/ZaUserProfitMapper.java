package com.tz.mapper;

import com.tz.pojo.ZaUserProfit;
import com.tz.pojo.ZaUserProfitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaUserProfitMapper {
    int countByExample(ZaUserProfitExample example);

    int deleteByExample(ZaUserProfitExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaUserProfit record);

    int insertSelective(ZaUserProfit record);

    List<ZaUserProfit> selectByExample(ZaUserProfitExample example);

    ZaUserProfit selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaUserProfit record, @Param("example") ZaUserProfitExample example);

    int updateByExample(@Param("record") ZaUserProfit record, @Param("example") ZaUserProfitExample example);

    int updateByPrimaryKeySelective(ZaUserProfit record);

    int updateByPrimaryKey(ZaUserProfit record);
}
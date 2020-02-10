package com.tz.mapper;

import com.tz.pojo.ZaComScope;
import com.tz.pojo.ZaComScopeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaComScopeMapper {
    int countByExample(ZaComScopeExample example);

    int deleteByExample(ZaComScopeExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaComScope record);

    int insertSelective(ZaComScope record);

    List<ZaComScope> selectByExample(ZaComScopeExample example);

    ZaComScope selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaComScope record, @Param("example") ZaComScopeExample example);

    int updateByExample(@Param("record") ZaComScope record, @Param("example") ZaComScopeExample example);

    int updateByPrimaryKeySelective(ZaComScope record);

    int updateByPrimaryKey(ZaComScope record);
}
package com.tz.mapper;

import com.tz.pojo.ZaItem;
import com.tz.pojo.ZaItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaItemMapper {
    int countByExample(ZaItemExample example);

    int deleteByExample(ZaItemExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaItem record);

    int insertSelective(ZaItem record);

    List<ZaItem> selectByExample(ZaItemExample example);

    ZaItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaItem record, @Param("example") ZaItemExample example);

    int updateByExample(@Param("record") ZaItem record, @Param("example") ZaItemExample example);

    int updateByPrimaryKeySelective(ZaItem record);

    int updateByPrimaryKey(ZaItem record);
}
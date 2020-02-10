package com.tz.mapper;

import com.tz.pojo.ZaChatPricture;
import com.tz.pojo.ZaChatPrictureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaChatPrictureMapper {
    int countByExample(ZaChatPrictureExample example);

    int deleteByExample(ZaChatPrictureExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ZaChatPricture record);

    int insertSelective(ZaChatPricture record);

    List<ZaChatPricture> selectByExample(ZaChatPrictureExample example);

    ZaChatPricture selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ZaChatPricture record, @Param("example") ZaChatPrictureExample example);

    int updateByExample(@Param("record") ZaChatPricture record, @Param("example") ZaChatPrictureExample example);

    int updateByPrimaryKeySelective(ZaChatPricture record);

    int updateByPrimaryKey(ZaChatPricture record);
}
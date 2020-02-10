package com.tz.mapper;

import com.tz.pojo.ZaContent;
import com.tz.pojo.ZaContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaContentMapper {
    int countByExample(ZaContentExample example);

    int deleteByExample(ZaContentExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaContent record);

    int insertSelective(ZaContent record);

    List<ZaContent> selectByExample(ZaContentExample example);

    ZaContent selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaContent record, @Param("example") ZaContentExample example);

    int updateByExample(@Param("record") ZaContent record, @Param("example") ZaContentExample example);

    int updateByPrimaryKeySelective(ZaContent record);

    int updateByPrimaryKey(ZaContent record);
}
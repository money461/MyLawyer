package com.tz.mapper;

import com.tz.pojo.ZaComManager;
import com.tz.pojo.ZaComManagerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaComManagerMapper {
    int countByExample(ZaComManagerExample example);

    int deleteByExample(ZaComManagerExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaComManager record);

    int insertSelective(ZaComManager record);

    List<ZaComManager> selectByExample(ZaComManagerExample example);

    ZaComManager selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaComManager record, @Param("example") ZaComManagerExample example);

    int updateByExample(@Param("record") ZaComManager record, @Param("example") ZaComManagerExample example);

    int updateByPrimaryKeySelective(ZaComManager record);

    int updateByPrimaryKey(ZaComManager record);
}
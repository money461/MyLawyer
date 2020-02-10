package com.tz.mapper;

import com.tz.pojo.ZaPlatManager;
import com.tz.pojo.ZaPlatManagerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaPlatManagerMapper {
    int countByExample(ZaPlatManagerExample example);

    int deleteByExample(ZaPlatManagerExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaPlatManager record);

    int insertSelective(ZaPlatManager record);

    List<ZaPlatManager> selectByExample(ZaPlatManagerExample example);

    ZaPlatManager selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaPlatManager record, @Param("example") ZaPlatManagerExample example);

    int updateByExample(@Param("record") ZaPlatManager record, @Param("example") ZaPlatManagerExample example);

    int updateByPrimaryKeySelective(ZaPlatManager record);

    int updateByPrimaryKey(ZaPlatManager record);
}
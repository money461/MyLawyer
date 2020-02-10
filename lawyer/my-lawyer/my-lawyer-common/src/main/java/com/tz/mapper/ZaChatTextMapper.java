package com.tz.mapper;

import com.tz.pojo.ZaChatText;
import com.tz.pojo.ZaChatTextExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaChatTextMapper {
    int countByExample(ZaChatTextExample example);

    int deleteByExample(ZaChatTextExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ZaChatText record);

    int insertSelective(ZaChatText record);

    List<ZaChatText> selectByExampleWithBLOBs(ZaChatTextExample example);

    List<ZaChatText> selectByExample(ZaChatTextExample example);

    ZaChatText selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ZaChatText record, @Param("example") ZaChatTextExample example);

    int updateByExampleWithBLOBs(@Param("record") ZaChatText record, @Param("example") ZaChatTextExample example);

    int updateByExample(@Param("record") ZaChatText record, @Param("example") ZaChatTextExample example);

    int updateByPrimaryKeySelective(ZaChatText record);

    int updateByPrimaryKeyWithBLOBs(ZaChatText record);

    int updateByPrimaryKey(ZaChatText record);
}
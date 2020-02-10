package com.tz.mapper;

import com.tz.pojo.ZaContentEvaluate;
import com.tz.pojo.ZaContentEvaluateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaContentEvaluateMapper {
    int countByExample(ZaContentEvaluateExample example);

    int deleteByExample(ZaContentEvaluateExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaContentEvaluate record);

    int insertSelective(ZaContentEvaluate record);

    List<ZaContentEvaluate> selectByExample(ZaContentEvaluateExample example);

    ZaContentEvaluate selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaContentEvaluate record, @Param("example") ZaContentEvaluateExample example);

    int updateByExample(@Param("record") ZaContentEvaluate record, @Param("example") ZaContentEvaluateExample example);

    int updateByPrimaryKeySelective(ZaContentEvaluate record);

    int updateByPrimaryKey(ZaContentEvaluate record);
}
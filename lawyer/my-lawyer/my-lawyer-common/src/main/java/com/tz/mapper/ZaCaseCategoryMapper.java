package com.tz.mapper;

import com.tz.pojo.ZaCaseCategory;
import com.tz.pojo.ZaCaseCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaCaseCategoryMapper {
    int countByExample(ZaCaseCategoryExample example);

    int deleteByExample(ZaCaseCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZaCaseCategory record);

    int insertSelective(ZaCaseCategory record);

    List<ZaCaseCategory> selectByExample(ZaCaseCategoryExample example);

    ZaCaseCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZaCaseCategory record, @Param("example") ZaCaseCategoryExample example);

    int updateByExample(@Param("record") ZaCaseCategory record, @Param("example") ZaCaseCategoryExample example);

    int updateByPrimaryKeySelective(ZaCaseCategory record);

    int updateByPrimaryKey(ZaCaseCategory record);
}
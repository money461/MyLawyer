package com.tz.mapper;

import com.tz.pojo.ZaComCategory;
import com.tz.pojo.ZaComCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaComCategoryMapper {
    int countByExample(ZaComCategoryExample example);

    int deleteByExample(ZaComCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZaComCategory record);

    int insertSelective(ZaComCategory record);

    List<ZaComCategory> selectByExample(ZaComCategoryExample example);

    ZaComCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZaComCategory record, @Param("example") ZaComCategoryExample example);

    int updateByExample(@Param("record") ZaComCategory record, @Param("example") ZaComCategoryExample example);

    int updateByPrimaryKeySelective(ZaComCategory record);

    int updateByPrimaryKey(ZaComCategory record);
}
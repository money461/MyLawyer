package com.tz.mapper;

import com.tz.pojo.ZaItemCategory;
import com.tz.pojo.ZaItemCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaItemCategoryMapper {
    int countByExample(ZaItemCategoryExample example);

    int deleteByExample(ZaItemCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZaItemCategory record);

    int insertSelective(ZaItemCategory record);

    List<ZaItemCategory> selectByExample(ZaItemCategoryExample example);

    ZaItemCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZaItemCategory record, @Param("example") ZaItemCategoryExample example);

    int updateByExample(@Param("record") ZaItemCategory record, @Param("example") ZaItemCategoryExample example);

    int updateByPrimaryKeySelective(ZaItemCategory record);

    int updateByPrimaryKey(ZaItemCategory record);
}
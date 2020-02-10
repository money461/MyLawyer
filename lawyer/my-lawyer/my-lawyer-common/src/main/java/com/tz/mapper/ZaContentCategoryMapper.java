package com.tz.mapper;

import com.tz.pojo.ZaContentCategory;
import com.tz.pojo.ZaContentCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaContentCategoryMapper {
    int countByExample(ZaContentCategoryExample example);

    int deleteByExample(ZaContentCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZaContentCategory record);

    int insertSelective(ZaContentCategory record);

    List<ZaContentCategory> selectByExample(ZaContentCategoryExample example);

    ZaContentCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZaContentCategory record, @Param("example") ZaContentCategoryExample example);

    int updateByExample(@Param("record") ZaContentCategory record, @Param("example") ZaContentCategoryExample example);

    int updateByPrimaryKeySelective(ZaContentCategory record);

    int updateByPrimaryKey(ZaContentCategory record);
}
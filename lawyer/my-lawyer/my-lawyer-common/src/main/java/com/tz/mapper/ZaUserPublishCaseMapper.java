package com.tz.mapper;

import com.tz.pojo.ZaUserPublishCase;
import com.tz.pojo.ZaUserPublishCaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaUserPublishCaseMapper {
    int countByExample(ZaUserPublishCaseExample example);

    int deleteByExample(ZaUserPublishCaseExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaUserPublishCase record);

    int insertSelective(ZaUserPublishCase record);

    List<ZaUserPublishCase> selectByExample(ZaUserPublishCaseExample example);

    ZaUserPublishCase selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaUserPublishCase record, @Param("example") ZaUserPublishCaseExample example);

    int updateByExample(@Param("record") ZaUserPublishCase record, @Param("example") ZaUserPublishCaseExample example);

    int updateByPrimaryKeySelective(ZaUserPublishCase record);

    int updateByPrimaryKey(ZaUserPublishCase record);
}
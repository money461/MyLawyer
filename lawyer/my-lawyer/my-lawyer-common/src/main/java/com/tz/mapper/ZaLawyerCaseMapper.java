package com.tz.mapper;

import com.tz.pojo.ZaLawyerCase;
import com.tz.pojo.ZaLawyerCaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaLawyerCaseMapper {
    int countByExample(ZaLawyerCaseExample example);

    int deleteByExample(ZaLawyerCaseExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaLawyerCase record);

    int insertSelective(ZaLawyerCase record);

    List<ZaLawyerCase> selectByExample(ZaLawyerCaseExample example);

    ZaLawyerCase selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaLawyerCase record, @Param("example") ZaLawyerCaseExample example);

    int updateByExample(@Param("record") ZaLawyerCase record, @Param("example") ZaLawyerCaseExample example);

    int updateByPrimaryKeySelective(ZaLawyerCase record);

    int updateByPrimaryKey(ZaLawyerCase record);
}
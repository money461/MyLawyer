package com.tz.mapper;

import com.tz.pojo.ZaUserLawyer;
import com.tz.pojo.ZaUserLawyerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaUserLawyerMapper {
    int countByExample(ZaUserLawyerExample example);

    int deleteByExample(ZaUserLawyerExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaUserLawyer record);

    int insertSelective(ZaUserLawyer record);

    List<ZaUserLawyer> selectByExample(ZaUserLawyerExample example);

    ZaUserLawyer selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaUserLawyer record, @Param("example") ZaUserLawyerExample example);

    int updateByExample(@Param("record") ZaUserLawyer record, @Param("example") ZaUserLawyerExample example);

    int updateByPrimaryKeySelective(ZaUserLawyer record);

    int updateByPrimaryKey(ZaUserLawyer record);
}
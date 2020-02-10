package com.tz.mapper;

import com.tz.pojo.ZaUserEscrow;
import com.tz.pojo.ZaUserEscrowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaUserEscrowMapper {
    int countByExample(ZaUserEscrowExample example);

    int deleteByExample(ZaUserEscrowExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaUserEscrow record);

    int insertSelective(ZaUserEscrow record);

    List<ZaUserEscrow> selectByExample(ZaUserEscrowExample example);

    ZaUserEscrow selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaUserEscrow record, @Param("example") ZaUserEscrowExample example);

    int updateByExample(@Param("record") ZaUserEscrow record, @Param("example") ZaUserEscrowExample example);

    int updateByPrimaryKeySelective(ZaUserEscrow record);

    int updateByPrimaryKey(ZaUserEscrow record);
}
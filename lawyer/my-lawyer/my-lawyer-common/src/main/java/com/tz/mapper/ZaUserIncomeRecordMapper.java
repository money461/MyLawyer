package com.tz.mapper;

import com.tz.pojo.ZaUserIncomeRecord;
import com.tz.pojo.ZaUserIncomeRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaUserIncomeRecordMapper {
    int countByExample(ZaUserIncomeRecordExample example);

    int deleteByExample(ZaUserIncomeRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaUserIncomeRecord record);

    int insertSelective(ZaUserIncomeRecord record);

    List<ZaUserIncomeRecord> selectByExample(ZaUserIncomeRecordExample example);

    ZaUserIncomeRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaUserIncomeRecord record, @Param("example") ZaUserIncomeRecordExample example);

    int updateByExample(@Param("record") ZaUserIncomeRecord record, @Param("example") ZaUserIncomeRecordExample example);

    int updateByPrimaryKeySelective(ZaUserIncomeRecord record);

    int updateByPrimaryKey(ZaUserIncomeRecord record);
}
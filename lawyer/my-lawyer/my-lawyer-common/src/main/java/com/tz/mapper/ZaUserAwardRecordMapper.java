package com.tz.mapper;

import com.tz.pojo.ZaUserAwardRecord;
import com.tz.pojo.ZaUserAwardRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaUserAwardRecordMapper {
    int countByExample(ZaUserAwardRecordExample example);

    int deleteByExample(ZaUserAwardRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaUserAwardRecord record);

    int insertSelective(ZaUserAwardRecord record);

    List<ZaUserAwardRecord> selectByExample(ZaUserAwardRecordExample example);

    ZaUserAwardRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaUserAwardRecord record, @Param("example") ZaUserAwardRecordExample example);

    int updateByExample(@Param("record") ZaUserAwardRecord record, @Param("example") ZaUserAwardRecordExample example);

    int updateByPrimaryKeySelective(ZaUserAwardRecord record);

    int updateByPrimaryKey(ZaUserAwardRecord record);
}
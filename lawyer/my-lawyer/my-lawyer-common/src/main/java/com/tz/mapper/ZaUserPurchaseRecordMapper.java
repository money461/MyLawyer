package com.tz.mapper;

import com.tz.pojo.ZaUserPurchaseRecord;
import com.tz.pojo.ZaUserPurchaseRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaUserPurchaseRecordMapper {
    int countByExample(ZaUserPurchaseRecordExample example);

    int deleteByExample(ZaUserPurchaseRecordExample example);

    int insert(ZaUserPurchaseRecord record);

    int insertSelective(ZaUserPurchaseRecord record);

    List<ZaUserPurchaseRecord> selectByExample(ZaUserPurchaseRecordExample example);

    int updateByExampleSelective(@Param("record") ZaUserPurchaseRecord record, @Param("example") ZaUserPurchaseRecordExample example);

    int updateByExample(@Param("record") ZaUserPurchaseRecord record, @Param("example") ZaUserPurchaseRecordExample example);
}
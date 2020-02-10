package com.tz.mapper;

import com.tz.pojo.ZaGift;
import com.tz.pojo.ZaGiftExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaGiftMapper {
    int countByExample(ZaGiftExample example);

    int deleteByExample(ZaGiftExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaGift record);

    int insertSelective(ZaGift record);

    List<ZaGift> selectByExample(ZaGiftExample example);

    ZaGift selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaGift record, @Param("example") ZaGiftExample example);

    int updateByExample(@Param("record") ZaGift record, @Param("example") ZaGiftExample example);

    int updateByPrimaryKeySelective(ZaGift record);

    int updateByPrimaryKey(ZaGift record);
}
package com.tz.mapper;

import com.tz.pojo.ZaSysNotice;
import com.tz.pojo.ZaSysNoticeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaSysNoticeMapper {
    int countByExample(ZaSysNoticeExample example);

    int deleteByExample(ZaSysNoticeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ZaSysNotice record);

    int insertSelective(ZaSysNotice record);

    List<ZaSysNotice> selectByExampleWithBLOBs(ZaSysNoticeExample example);

    List<ZaSysNotice> selectByExample(ZaSysNoticeExample example);

    ZaSysNotice selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ZaSysNotice record, @Param("example") ZaSysNoticeExample example);

    int updateByExampleWithBLOBs(@Param("record") ZaSysNotice record, @Param("example") ZaSysNoticeExample example);

    int updateByExample(@Param("record") ZaSysNotice record, @Param("example") ZaSysNoticeExample example);

    int updateByPrimaryKeySelective(ZaSysNotice record);

    int updateByPrimaryKeyWithBLOBs(ZaSysNotice record);

    int updateByPrimaryKey(ZaSysNotice record);
}
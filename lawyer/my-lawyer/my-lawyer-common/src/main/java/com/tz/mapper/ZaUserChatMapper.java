package com.tz.mapper;

import com.tz.pojo.ZaUserChat;
import com.tz.pojo.ZaUserChatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaUserChatMapper {
    int countByExample(ZaUserChatExample example);

    int deleteByExample(ZaUserChatExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ZaUserChat record);

    int insertSelective(ZaUserChat record);

    List<ZaUserChat> selectByExample(ZaUserChatExample example);

    ZaUserChat selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ZaUserChat record, @Param("example") ZaUserChatExample example);

    int updateByExample(@Param("record") ZaUserChat record, @Param("example") ZaUserChatExample example);

    int updateByPrimaryKeySelective(ZaUserChat record);

    int updateByPrimaryKey(ZaUserChat record);
}
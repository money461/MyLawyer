package com.tz.mapper;

import com.tz.pojo.ZaCart;
import com.tz.pojo.ZaCartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaCartMapper {
    int countByExample(ZaCartExample example);

    int deleteByExample(ZaCartExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaCart record);

    int insertSelective(ZaCart record);

    List<ZaCart> selectByExample(ZaCartExample example);

    ZaCart selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaCart record, @Param("example") ZaCartExample example);

    int updateByExample(@Param("record") ZaCart record, @Param("example") ZaCartExample example);

    int updateByPrimaryKeySelective(ZaCart record);

    int updateByPrimaryKey(ZaCart record);
}
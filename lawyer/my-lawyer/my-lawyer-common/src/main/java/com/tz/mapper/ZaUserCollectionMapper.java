package com.tz.mapper;

import com.tz.pojo.ZaUserCollection;
import com.tz.pojo.ZaUserCollectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaUserCollectionMapper {
    int countByExample(ZaUserCollectionExample example);

    int deleteByExample(ZaUserCollectionExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaUserCollection record);

    int insertSelective(ZaUserCollection record);

    List<ZaUserCollection> selectByExample(ZaUserCollectionExample example);

    ZaUserCollection selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaUserCollection record, @Param("example") ZaUserCollectionExample example);

    int updateByExample(@Param("record") ZaUserCollection record, @Param("example") ZaUserCollectionExample example);

    int updateByPrimaryKeySelective(ZaUserCollection record);

    int updateByPrimaryKey(ZaUserCollection record);
}
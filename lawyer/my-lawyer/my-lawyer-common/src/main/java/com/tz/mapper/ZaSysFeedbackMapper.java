package com.tz.mapper;

import com.tz.pojo.ZaSysFeedback;
import com.tz.pojo.ZaSysFeedbackExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaSysFeedbackMapper {
    int countByExample(ZaSysFeedbackExample example);

    int deleteByExample(ZaSysFeedbackExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaSysFeedback record);

    int insertSelective(ZaSysFeedback record);

    List<ZaSysFeedback> selectByExample(ZaSysFeedbackExample example);

    ZaSysFeedback selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaSysFeedback record, @Param("example") ZaSysFeedbackExample example);

    int updateByExample(@Param("record") ZaSysFeedback record, @Param("example") ZaSysFeedbackExample example);

    int updateByPrimaryKeySelective(ZaSysFeedback record);

    int updateByPrimaryKey(ZaSysFeedback record);
}
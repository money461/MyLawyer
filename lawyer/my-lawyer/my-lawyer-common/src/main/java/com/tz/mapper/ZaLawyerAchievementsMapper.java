package com.tz.mapper;

import com.tz.pojo.ZaLawyerAchievements;
import com.tz.pojo.ZaLawyerAchievementsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZaLawyerAchievementsMapper {
    int countByExample(ZaLawyerAchievementsExample example);

    int deleteByExample(ZaLawyerAchievementsExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZaLawyerAchievements record);

    int insertSelective(ZaLawyerAchievements record);

    List<ZaLawyerAchievements> selectByExample(ZaLawyerAchievementsExample example);

    ZaLawyerAchievements selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZaLawyerAchievements record, @Param("example") ZaLawyerAchievementsExample example);

    int updateByExample(@Param("record") ZaLawyerAchievements record, @Param("example") ZaLawyerAchievementsExample example);

    int updateByPrimaryKeySelective(ZaLawyerAchievements record);

    int updateByPrimaryKey(ZaLawyerAchievements record);
}
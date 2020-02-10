package com.tz.mapper.vo;

import java.util.List;
import java.util.Map;

import com.tz.pojo.ZaUserProfit;
import com.tz.pojo.admin.ZaUserProfitVo;

public interface ZaUserProfitMapperAdminVo {

	List<ZaUserProfitVo> getZaUserProfitList(Map<String, Object> map);

	Integer updateUserProfitSelective(ZaUserProfit zaUserProfit);

}

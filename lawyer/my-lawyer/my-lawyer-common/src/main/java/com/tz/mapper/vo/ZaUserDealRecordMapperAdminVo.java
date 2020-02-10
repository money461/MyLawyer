package com.tz.mapper.vo;

import java.util.List;
import java.util.Map;

import com.tz.pojo.admin.ZaUserIncomeRecordAdminVo;
import com.tz.pojo.admin.ZaUserPurchaseRecordAdminVo;

public interface ZaUserDealRecordMapperAdminVo {

	List<ZaUserPurchaseRecordAdminVo> getUserPurchaseRecordList(Map<String, Object> map);

	List<ZaUserIncomeRecordAdminVo> getUserIncomeRecordList(Map<String, Object> map);

}

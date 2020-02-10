package com.tz.service;

import com.tz.pojo.ZaComAuthentication;
import com.tz.res.AppMsgResult;

public interface CompanyService {
	// 企业认证
	AppMsgResult companyAuth(ZaComAuthentication comAuth, String scope, String token);

	// 查询企业资料
	AppMsgResult selectCompany(String userId, String token, String type);
}

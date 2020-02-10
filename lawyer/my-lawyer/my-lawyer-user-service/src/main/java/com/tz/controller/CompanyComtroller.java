package com.tz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tz.pojo.ZaComAuthentication;
import com.tz.res.AppMsgResult;
import com.tz.service.CompanyService;

/**
 * 企业认证类
 * 
 * @author menglin 2017年12月28日09:49:05
 */

@RestController
@RequestMapping("company/api")
public class CompanyComtroller {

	// 公司服务
	@Autowired
	private CompanyService companyService;

	/**
	 * 公司认证
	 * 
	 * @param comAuth
	 *            企业认证信息
	 * @param scope
	 *            企业类型
	 * @return
	 */
	@PostMapping("/companyAuth")
	public AppMsgResult companyAuth(@RequestBody ZaComAuthentication comAuth,
			@RequestParam(required = true) String scope, @RequestParam(required = true) String token) {
		return companyService.companyAuth(comAuth, scope, token);
	}

	/**
	 * 查询律师资料
	 * 
	 * @param lawId
	 *            律师查询id
	 * @return
	 */
	@GetMapping("/selectCompany")
	public AppMsgResult selectCompany(@RequestParam(required = true) String userId,
			@RequestParam(required = true) String token, @RequestParam(required = true) String type) {
		return companyService.selectCompany(userId, token, type);
	}

}

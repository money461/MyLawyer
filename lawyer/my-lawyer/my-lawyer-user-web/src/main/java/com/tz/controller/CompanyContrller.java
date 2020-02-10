package com.tz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tz.pojo.ZaComAuthentication;
import com.tz.remote.CompanyRemote;
import com.tz.remote.UserRemote;
import com.tz.res.AppMsgResult;


/**
 * 企业类
 * @author menglin 2017年12月28日10:02:56
 *
 */
@RestController
@RequestMapping("/user/api")
public class CompanyContrller {


    @Autowired
    CompanyRemote companyRemote;
	
   /**
    * 企业认证
    * @param comAuth 认证信息
    * @param scope 企业类型
    * @return
    */
    @PostMapping("/companyAuth")
    public AppMsgResult companyAuth(ZaComAuthentication comAuth,String scope,String token) {
        return companyRemote.companyAuth(comAuth, scope,token);
    }
    /**
	 * 查询律师资料
	 * @param lawId 律师主键id
	 * @return
	 */
	@GetMapping("/selectCompany")
	public AppMsgResult selectCompany(String userId, String token,String type) {
		return companyRemote.selectCompany(userId, token,type);
	}
}

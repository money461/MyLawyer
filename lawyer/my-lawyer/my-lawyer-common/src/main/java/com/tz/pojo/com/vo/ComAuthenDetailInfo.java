package com.tz.pojo.com.vo;

import java.util.List;

import com.tz.pojo.ZaComCategory;

/**
 * 企业认证详情信息展示
 * @author QL
 *
 */
public class ComAuthenDetailInfo extends ZaComAuthenticationComVo {

	private Integer isCollection; //是否已收藏
	
	private String comAuthen; //企业认证
	
	private Integer lawEnsure;//担保律师 0 无律师担保 1律师担保
	
	private List<ZaComCategory> comScopeList; //企业经营范围

	public Integer getIsCollection() {
		return isCollection;
	}

	public void setIsCollection(Integer isCollection) {
		this.isCollection = isCollection;
	}

	public String getComAuthen() {
		return comAuthen;
	}

	public void setComAuthen(String comAuthen) {
		this.comAuthen = comAuthen;
	}

	public Integer getLawEnsure() {
		return lawEnsure;
	}

	public void setLawEnsure(Integer lawEnsure) {
		this.lawEnsure = lawEnsure;
	}

	public List<ZaComCategory> getComScopeList() {
		return comScopeList;
	}

	public void setComScopeList(List<ZaComCategory> comScopeList) {
		this.comScopeList = comScopeList;
	}
	
	
	
	
}

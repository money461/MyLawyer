package com.tz.pojo.index.vo;

import java.util.List;

public class RecomPublishCaseVo {

	private String categoryId;  //分类id
	
	private String sortCode;  //唯一code
	
	private String categoryName; //分类名称
	
	private List<ZaUserPublishCaseVo> userPublishCaseList; //用户发布的案件信息

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getSortCode() {
		return sortCode;
	}

	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<ZaUserPublishCaseVo> getUserPublishCaseList() {
		return userPublishCaseList;
	}

	public void setUserPublishCaseList(List<ZaUserPublishCaseVo> userPublishCaseList) {
		this.userPublishCaseList = userPublishCaseList;
	}
	
	
}

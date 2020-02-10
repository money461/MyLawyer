package com.tz.pojo.index.vo;

import java.util.List;

/**
 * 推荐选择律师信息实体类
 * @author QL
 *
 */
public class RecomSelectionLawyerVo{

	private String categoryId;  //分类id
	
	private String sortCode;  //唯一code
	
	private String categoryName; //分类名称
	
	private List<ZaLawyerAuthenticationIndexVo> RecommendLawInfoList; //推荐律师信息

	
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

	public List<ZaLawyerAuthenticationIndexVo> getRecommendLawInfoList() {
		return RecommendLawInfoList;
	}

	public void setRecommendLawInfoList(List<ZaLawyerAuthenticationIndexVo> recommendLawInfoList) {
		RecommendLawInfoList = recommendLawInfoList;
	}

	
	
	
}

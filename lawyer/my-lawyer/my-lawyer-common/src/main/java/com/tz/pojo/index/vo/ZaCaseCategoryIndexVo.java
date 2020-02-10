package com.tz.pojo.index.vo;

import java.util.List;

import com.tz.pojo.ZaCaseCategory;

public class ZaCaseCategoryIndexVo extends ZaCaseCategory {

	private List<ZaCaseCategoryIndexVo> caseCategoryList;  //子目录案件

	public List<ZaCaseCategoryIndexVo> getCaseCategoryList() {
		return caseCategoryList;
	}

	public void setCaseCategoryList(List<ZaCaseCategoryIndexVo> caseCategoryList) {
		this.caseCategoryList = caseCategoryList;
	}

	
	
	
}

package com.tz.pojo.index.vo;

import java.util.List;

/**
 * 首页广告实体类
 * @author Administrator
 *
 */
public class IndexContentVo  {

	private String contentCategoryId;  //内容分类id
	
	private String sortCode;  //內容分類唯一code
	
	private String contentCategoryName; //广告分类名称
	
	private List<ZaContentVo> contentList; //广告具体内容
	

	public String getSortCode() {
		return sortCode;
	}

	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}

	public String getContentCategoryId() {
		return contentCategoryId;
	}

	public void setContentCategoryId(String contentCategoryId) {
		this.contentCategoryId = contentCategoryId;
	}

	public String getContentCategoryName() {
		return contentCategoryName;
	}

	public void setContentCategoryName(String contentCategoryName) {
		this.contentCategoryName = contentCategoryName;
	}

	public List<ZaContentVo> getContentList() {
		return contentList;
	}

	public void setContentList(List<ZaContentVo> contentList) {
		this.contentList = contentList;
	}
	
	
}

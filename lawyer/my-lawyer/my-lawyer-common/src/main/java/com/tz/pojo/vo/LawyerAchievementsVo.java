package com.tz.pojo.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LawyerAchievementsVo implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 7329341667563853267L;
	//案件主键
	private String id;
	//案件分类名
	private String categoryName;
	//案件描述
	private String caseDesc;
	//案件开始时间
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date caseBegin;
	//案件结束时间
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date caseEnd;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCaseDesc() {
		return caseDesc;
	}

	public void setCaseDesc(String caseDesc) {
		this.caseDesc = caseDesc;
	}

	public Date getCaseBegin() {
		return caseBegin;
	}

	public void setCaseBegin(Date caseBegin) {
		this.caseBegin = caseBegin;
	}

	public Date getCaseEnd() {
		return caseEnd;
	}

	public void setCaseEnd(Date caseEnd) {
		this.caseEnd = caseEnd;
	}

}

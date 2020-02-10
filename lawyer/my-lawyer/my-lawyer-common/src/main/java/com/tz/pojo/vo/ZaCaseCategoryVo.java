package com.tz.pojo.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.tz.pojo.ZaCaseCategory;

//封装律师分类数据
public class ZaCaseCategoryVo extends ZaCaseCategory  implements  Serializable{
  
	/**
	 * 
	 */
	private static final long serialVersionUID = -7283718900408619474L;
	//查询开始时间
	private Date startTime;
	//更新结束时间
	private Date endTime;
	//封装二级分类的集合
	private List<ZaCaseCategory> zaCaseCategorys;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<ZaCaseCategory> getZaCaseCategorys() {
		return zaCaseCategorys;
	}

	public void setZaCaseCategorys(List<ZaCaseCategory> zaCaseCategorys) {
		this.zaCaseCategorys = zaCaseCategorys;
	}

}
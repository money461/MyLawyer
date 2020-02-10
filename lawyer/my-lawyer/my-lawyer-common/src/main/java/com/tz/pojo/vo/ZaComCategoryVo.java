package com.tz.pojo.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.tz.pojo.ZaCaseCategory;
import com.tz.pojo.ZaComCategory;
import com.tz.res.Constant;

//封装律师分类数据
public class ZaComCategoryVo extends ZaComCategory  implements  Serializable{
  
	/**
	 * 
	 */
	private static final long serialVersionUID = -7283718900408619474L;
	//查询开始时间
	private Date startTime;
	//更新结束时间
	private Date endTime;
	//封装二级分类的集合
	private List<ZaComCategory> zaComCategorys;

	//修改分类图标地址
	@Override
	public String getLogoUrl() {
		String logoUrl = super.getLogoUrl();
		if(logoUrl!=null && !"".equals( logoUrl)){
			logoUrl =Constant.FILESERVER_URL+logoUrl;
		}else{
			logoUrl ="";
		}
		return logoUrl;
	}

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

	public List<ZaComCategory> getZaComCategorys() {
		return zaComCategorys;
	}

	public void setZaComCategorys(List<ZaComCategory> zaComCategorys) {
		this.zaComCategorys = zaComCategorys;
	}
	
	

}
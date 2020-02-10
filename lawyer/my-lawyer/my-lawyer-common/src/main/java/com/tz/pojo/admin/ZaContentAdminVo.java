package com.tz.pojo.admin;

import java.io.Serializable;
import java.util.Date;

import com.tz.pojo.ZaContent;
import com.tz.res.Constant;

public class ZaContentAdminVo extends ZaContent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6320126520305971363L;
	
	private String contentCategoryName; //所属分类名称
	
	//查询开始时间
	private Date startTime;
	//更新结束时间
	private Date endTime;
	
	
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


	public String getContentCategoryName() {
		return contentCategoryName;
	}


	public void setContentCategoryName(String contentCategoryName) {
		this.contentCategoryName = contentCategoryName;
	}


	@Override
	public String getHomepageUrl() {
		String homepageUrl = super.getHomepageUrl();
		if(homepageUrl!=null && !"".equals(homepageUrl)) {
			homepageUrl = Constant.FILESERVER_URL+homepageUrl;
		}else {
			homepageUrl = "";
		}
		return homepageUrl;
	}


	//获取轮播图片
	public String[] getPrictures(){
		//获取从数据库查询出来的图片名称字符串
		String string = this.getPrictureUrl();
		if(string !=null && !"".equals(string)){
			String[] prictures = string.split(",");
			for(int i=0;i<prictures.length;i++){
				prictures[i] = Constant.FILESERVER_URL+prictures[i];
			}
			return prictures;
		}
		return new String[]{};
	}
    
	

}

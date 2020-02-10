package com.tz.pojo.admin;

import java.io.Serializable;
import java.util.Date;

import com.tz.pojo.ZaUserAwardRecord;
import com.tz.res.Constant;

public class ZaUserAwardRecordAdminVo extends ZaUserAwardRecord implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5492603059039122808L;
	//查询开始时间
	private Date startTime;
	//更新结束时间
	private Date endTime;
	
	private String userName; //打赏人
	
	private String awardeeName; //受赏人
	
	private String giftName ;//礼品名称
	
	private String logoUrl;  //礼品图片

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAwardeeName() {
		return awardeeName;
	}

	public void setAwardeeName(String awardeeName) {
		this.awardeeName = awardeeName;
	}

	public String getGiftName() {
		return giftName;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}

	public String getLogoUrl() {
		return Constant.FILESERVER_URL+logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	
	
	
	
	

}

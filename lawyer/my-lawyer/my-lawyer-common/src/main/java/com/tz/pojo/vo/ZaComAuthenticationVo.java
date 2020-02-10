package com.tz.pojo.vo;

import java.io.Serializable;
import java.util.Date;

import com.tz.pojo.ZaComAuthentication;
import com.tz.pojo.ZaLawyerAuthentication;

public class ZaComAuthenticationVo extends ZaComAuthentication implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7402033165419840936L;
	
	//用户手机号码
	private String userPhone;
	//查询开始时间
	private Date startTime;
	//更新结束时间
	private Date endTime;
	
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
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
	
	
	
    
}
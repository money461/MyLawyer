package com.tz.pojo.admin;

import java.io.Serializable;
import java.util.Date;

import com.tz.pojo.ZaUserProfit;

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class ZaUserProfitVo extends ZaUserProfit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1404349075500241064L;

	private String name;
	
	private String account;
	
	private String phone;
	
	//查询开始时间
	private Date startTime;
	//更新结束时间
	private Date endTime;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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

package com.tz.pojo.admin;

import java.io.Serializable;
import java.util.Date;

import com.tz.pojo.ZaUserPurchaseRecord;

public class ZaUserPurchaseRecordAdminVo extends ZaUserPurchaseRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3622087755552744656L;
	
	private String userName; //消费者账户
	
	private String pay_type; //付款方式类型
	
	private String pay_status; //支付状态
	
	private String consum_type; //消费事件类型
	
	private String in_out; //支出或者收入
	
	//查询开始时间
	private Date startTime;
	//更新结束时间
	private Date endTime;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	public String getPay_status() {
		return pay_status;
	}
	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}
	public String getConsum_type() {
		return consum_type;
	}
	public void setConsum_type(String consum_type) {
		this.consum_type = consum_type;
	}
	public String getIn_out() {
		return in_out;
	}
	public void setIn_out(String in_out) {
		this.in_out = in_out;
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

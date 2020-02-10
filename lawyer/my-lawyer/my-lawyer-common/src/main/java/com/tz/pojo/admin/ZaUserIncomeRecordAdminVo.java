package com.tz.pojo.admin;

import java.io.Serializable;
import java.util.Date;

import com.tz.pojo.ZaUserIncomeRecord;

public class ZaUserIncomeRecordAdminVo extends ZaUserIncomeRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -899611153106242956L;

	private String userName; //用户类型
	
	private String in_status; //收益状态 0处理中 1已到账 2处理失败
	
	private String in_type; //收益类型：4充值 5购物收益 6打赏收益 7悬赏收益 8取消悬赏
	
	private String in_out;  //该记录属于支出或收入 0 收入 +    1消费支出 -   2其他',
	
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
	public String getIn_status() {
		return in_status;
	}
	public void setIn_status(String in_status) {
		this.in_status = in_status;
	}
	public String getIn_type() {
		return in_type;
	}
	public void setIn_type(String in_type) {
		this.in_type = in_type;
	}
	public String getIn_out() {
		return in_out;
	}
	public void setIn_out(String in_out) {
		this.in_out = in_out;
	}
	
	
	
}

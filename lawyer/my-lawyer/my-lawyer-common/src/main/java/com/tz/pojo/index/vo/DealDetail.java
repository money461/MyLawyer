package com.tz.pojo.index.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 交易明细列表展示实体类
 * @author QL
 *
 */
public class DealDetail {

	 private String dealCode;
	
	 @JsonFormat(timezone = "GMT+8", pattern = "yyyy/MM/dd HH:mm:ss")
	 private Date createdTime;
	 
	 private String eventDesc;
	 
	 private String money;
	 

	public String getDealCode() {
		return dealCode;
	}

	public void setDealCode(String dealCode) {
		this.dealCode = dealCode;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getEventDesc() {
		return eventDesc;
	}

	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
	 
	 
	
}

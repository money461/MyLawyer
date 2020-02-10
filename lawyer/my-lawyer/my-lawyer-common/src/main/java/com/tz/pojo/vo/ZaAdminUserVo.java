package com.tz.pojo.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.tz.pojo.ZaUser;
import com.tz.res.Constant;

//封装登录或者注册返回的数据
public class ZaAdminUserVo extends ZaUser implements  Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2792668079089645922L;
	
	//用户头像
	@Override
	public String getHeadUrl() {
		String headUrl =  super.getHeadUrl();
		if(headUrl!=null && !"".equals(headUrl)) {
			headUrl = Constant.FILESERVER_URL+headUrl;
		}else {
			headUrl = "";
		}
		return headUrl;
	}

	private String userStatus; //用户状态
	
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
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	
	
	
}
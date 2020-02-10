package com.tz.pojo.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.tz.pojo.ZaPlatAuthority;
import com.tz.pojo.ZaPlatRole;

public class ZaPlatRoleVo extends ZaPlatRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3975757119228913364L;

	//查询开始时间
	private Date startTime;
	//更新结束时间
	private Date endTime;
	
	//该角色对应的权限id
	List<String>  ListId;

	List<ZaPlatAuthority> ListPlatAuth;  //权限集合信息

	public List<ZaPlatAuthority> getListPlatAuth() {
		return ListPlatAuth;
	}

	public void setListPlatAuth(List<ZaPlatAuthority> listPlatAuth) {
		ListPlatAuth = listPlatAuth;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public List<String> getListId() {
		return ListId;
	}

	public void setListId(List<String> listId) {
		ListId = listId;
	}
	
	
	
}

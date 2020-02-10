package com.tz.pojo.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.tz.pojo.ZaPlatAuthority;
import com.tz.pojo.ZaPlatManager;
import com.tz.pojo.ZaPlatRole;
import com.tz.pojo.ZaUser;

//封装后台用户登录返回的数据
public class ZaPlatManagerVo extends ZaPlatManager implements  Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2792668079089645922L;
	
	private String token;
	
	//查询开始时间
	private Date startTime;
	//更新结束时间
	private Date endTime;
	
	//该管理员对应的角色id
	List<String>  ListId;
	
	//一个账户对应多个角色
	List<ZaPlatRole> roleList;  //账户赋予角色
	
	List<ZaPlatAuthority> authorities;//权限
	
	
	public List<ZaPlatAuthority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<ZaPlatAuthority> authorities) {
		this.authorities = authorities;
	}
	public List<String> getListId() {
		return ListId;
	}
	public void setListId(List<String> listId) {
		ListId = listId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
	
	public List<ZaPlatRole> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<ZaPlatRole> roleList) {
		this.roleList = roleList;
	}
	
	
	
}
package com.tz.pojo.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

//封装登录或者注册返回的数据
public class ZaUserVo implements  Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4607300260854007541L;
	//登录用户的id
	private String id;
	//登录用户账号
    private String userAccount;
    //登录的用户名--个人用户可以修改、企业和律师需要审核
    private String name;
    //性别
    private Integer userGender;
    //登录电话
    private String userPhone;
    //头像 不同的用户不同的头像
    private String headUrl;
    //用户类型  1 普通用户 2 律师用户  3 企业用户
    private Integer userType;
    //用户账户 0 冻结 1 不冻结 2 删除 
    private Integer status;
    //用户状态 默认 0在线  离线 1 隐身
    private Integer state;
    //律师的星级
    private String grade;
    //律师事务所
    private String lawOffice;
    //企业分类名称 律师擅长案件类型 使用逗号，分隔
    private String categoryNames; 
    //用户登录token
    private String token;
    //用户邮箱
    private String userEmail;
    //用户剩余余额
    private BigDecimal coinSurplus;
    
    //环信用户名
    private String hxusername;
    
    //环信密码
    private String hxpassword;
    
    //环信昵称
    private String hxusernicke;
    
    
	
	public String getHxusername() {
		return hxusername;
	}
	public void setHxusername(String hxusername) {
		this.hxusername = hxusername;
	}
	public String getHxpassword() {
		return hxpassword;
	}
	public void setHxpassword(String hxpassword) {
		this.hxpassword = hxpassword;
	}
	public String getHxusernicke() {
		return hxusernicke;
	}
	public void setHxusernicke(String hxusernicke) {
		this.hxusernicke = hxusernicke;
	}
	public BigDecimal getCoinSurplus() {
		return coinSurplus;
	}
	public void setCoinSurplus(BigDecimal coinSurplus) {
		this.coinSurplus = coinSurplus;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	
	public Integer getUserGender() {
		return userGender;
	}
	public void setUserGender(Integer userGender) {
		this.userGender = userGender;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getHeadUrl() {
		return headUrl;
	}
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getLawOffice() {
		return lawOffice;
	}
	public void setLawOffice(String lawOffice) {
		this.lawOffice = lawOffice;
	}
	public String getCategoryNames() {
		return categoryNames;
	}
	public void setCategoryNames(String categoryNames) {
		this.categoryNames = categoryNames;
	}
	
	
    
    
    

}
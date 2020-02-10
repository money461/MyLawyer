package com.tz.pojo.index.vo;
/**
 * 
 * @author QL
 *个人中心的个人信息实体类
 */

import java.util.List;

import com.tz.pojo.ZaComCategory;
import com.tz.res.Constant;

public class PersonalInfo {

	private String userId;
	
	private String headUrl;
	
	private String userName;
	
	private String coin;
	
	private String grade;  //律师等级
	
	private String lawOffice;  //律师办公室
	
	private Integer state; //是否在线
	
	private String lawCase; //律师技能
	
	private String comStatus; //企业认证状态
	
	private Integer lawEnsure;  //律师担保 0 无律师担保 1律师担保
	
	private List<ZaComCategory> comScopeList; //企业经营范围

	//律师技能拆分成数据 ()
	public String[] getLawCases(){
		String string  = this.lawCase;
		if(string !=null && !"".equals(string)) {
			String[] lawCases = string.split(",");
			return lawCases;
		}else {
			return new String[]{};
		}
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getHeadUrl() {
			return Constant.FILESERVER_URL+headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getCoin() {
		return coin;
	}

	public void setCoin(String coin) {
		this.coin = coin;
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


	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getLawCase() {
		return lawCase;
	}

	public void setLawCase(String lawCase) {
		this.lawCase = lawCase;
	}

	public String getComStatus() {
		return comStatus;
	}

	public void setComStatus(String comStatus) {
		this.comStatus = comStatus;
	}

	
	public Integer getLawEnsure() {
		return lawEnsure;
	}

	public void setLawEnsure(Integer lawEnsure) {
		this.lawEnsure = lawEnsure;
	}

	public List<ZaComCategory> getComScopeList() {
		return comScopeList;
	}

	public void setComScopeList(List<ZaComCategory> comScopeList) {
		this.comScopeList = comScopeList;
	}

	

	
}

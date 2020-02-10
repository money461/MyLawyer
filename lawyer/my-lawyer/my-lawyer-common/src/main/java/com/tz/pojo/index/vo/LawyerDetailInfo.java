package com.tz.pojo.index.vo;

import java.util.List;

/**
 * 律师详情信息展示实体类
 * @author QL
 *
 */
public class LawyerDetailInfo extends ZaLawyerAuthenticationIndexVo {

	private Integer isCollection; //是否已收藏
	
	private String online;  //律师是否在线
	
	private String usernick;  //环信注册昵称
	
	private String password;  //环信注册uuid
	
	private String username;  //第三方登录名称
	
	private List<ZaLawyerAchievementsVo> LawyerAchievementList; //律师成绩信息

	
	public String getUsernick() {
		return usernick;
	}

	public void setUsernick(String usernick) {
		this.usernick = usernick;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getIsCollection() {
		return isCollection;
	}

	public void setIsCollection(Integer isCollection) {
		this.isCollection = isCollection;
	}

	public String getOnline() {
		return online;
	}

	public void setOnline(String online) {
		this.online = online;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<ZaLawyerAchievementsVo> getLawyerAchievementList() {
		return LawyerAchievementList;
	}

	public void setLawyerAchievementList(List<ZaLawyerAchievementsVo> lawyerAchievementList) {
		LawyerAchievementList = lawyerAchievementList;
	}


	
	
	
	
}

package com.tz.pojo.index.vo;

import com.tz.pojo.ZaLawyerAuthentication;
import com.tz.res.Constant;

public class ZaLawyerAuthenticationIndexVo extends ZaLawyerAuthentication{

	private String usernick;  //环信注册昵称
	
	private String password;  //环信注册uuid
	
	private String username; // 第三方登录账户
	
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

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}




	private String distance; //當前用戶與律師的距离;
	
		public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

		//律师头像地址
		@Override
		public String getLawLogo() {
			String lawLogo = super.getLawLogo();
			if(lawLogo!=null && !"".equals(lawLogo)) {
				lawLogo =Constant.FILESERVER_URL+lawLogo;
			}else {
				lawLogo ="";
			}
			return lawLogo;
		}

		//律师技能拆分成数据 ()
		public String[] getLawCases(){
			String string  = super.getLawCase();
			if(string !=null && !"".equals(string)) {
				String[] lawCases = string.split(",");
				return lawCases;
			}else {
				return new String[]{};
			}
		}

		
	
}

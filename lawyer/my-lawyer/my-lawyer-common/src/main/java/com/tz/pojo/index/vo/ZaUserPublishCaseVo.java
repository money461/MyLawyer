package com.tz.pojo.index.vo;

import java.math.BigDecimal;

import com.tz.pojo.ZaUserPublishCase;
import com.tz.res.Constant;
/**
 * 用户发布案件信息展示
 * @author Ql
 *
 */
public class ZaUserPublishCaseVo extends ZaUserPublishCase {

//	private String giftUrl;//礼品图标地址
	
//	private Integer giftNum; //礼品件数
	
	private BigDecimal reward; //赏金
	
	private String caseName; //案件类型名称
	
	private String headUrl; //用户头像
	
	private String userNick; //用户昵称
	
	private String deadline; //最后期限
	
	
/*
	public String getGiftUrl() {
		if(giftUrl!=null && !"".equals(giftUrl)) {
			giftUrl = Constant.FILESERVER_URL+giftUrl;
		}else {
			giftUrl = "";
		}
		return giftUrl;
	}

	public void setGiftUrl(String giftUrl) {
		this.giftUrl = giftUrl;
		
	}

	public Integer getGiftNum() {
		return giftNum;
	}

	public void setGiftNum(Integer giftNum) {
		this.giftNum = giftNum;
	}
*/
	
	
	public String getCaseName() {
		return caseName;
	}

	public BigDecimal getReward() {
		return reward;
	}

	public void setReward(BigDecimal reward) {
		this.reward = reward;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getHeadUrl() {
		if(headUrl!=null && !"".equals(headUrl)) {
			headUrl = Constant.FILESERVER_URL+headUrl;
		}else {
			headUrl = "";
		}
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	
	
}

package com.tz.pojo.admin;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.tz.pojo.ZaUserPublishCase;
import com.tz.res.Constant;

/**
 * @author Administrator
 *
 */
public class ZaUserPublishCaseAdminVo extends ZaUserPublishCase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1509311127512713852L;
	
	//查询开始时间
	private Date startTime;
	//更新结束时间
	private Date endTime;
	
	private String userName;  //案件发布人
	
	private String lawName;  //案件接受律师
	
	private String giftName; //礼品名称
  
	private String logoUrl; //礼品图标
	
	private BigDecimal price; //礼品单价
	
	private Integer giftNum; //礼品件数
	
	private BigDecimal reward; //礼品金额
	
	private String caseName; //案件所属类型名称

	private String awardStatus; //案件悬赏支付状态
	
	private String caseStatus; //案件状态
	
	private String caseType;  //案件类型名称
	
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLawName() {
		return lawName;
	}

	public void setLawName(String lawName) {
		this.lawName = lawName;
	}

	public String getGiftName() {
		return giftName;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}

	public String getLogoUrl() {
		return Constant.FILESERVER_URL+logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getGiftNum() {
		return giftNum;
	}

	public void setGiftNum(Integer giftNum) {
		this.giftNum = giftNum;
	}

	public BigDecimal getReward() {
		return reward;
	}

	public void setReward(BigDecimal reward) {
		this.reward = reward;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getAwardStatus() {
		return awardStatus;
	}

	public void setAwardStatus(String awardStatus) {
		this.awardStatus = awardStatus;
	}

	public String getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}


	


	
	
	
	
	
	

}

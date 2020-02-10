package com.tz.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class ZaUserAwardRecord {
    private String id;

    private String userId;

    private String awardeeId;

    private String giftId;

    private BigDecimal price;

    private Integer giftNum;

    private BigDecimal reward;

    private Integer awardStatus;

    private Integer type;

    private Date awardTime;

    private Date createdTime;

    private Date updatedTime;

    private String operator;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getAwardeeId() {
        return awardeeId;
    }

    public void setAwardeeId(String awardeeId) {
        this.awardeeId = awardeeId == null ? null : awardeeId.trim();
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId == null ? null : giftId.trim();
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

    public Integer getAwardStatus() {
        return awardStatus;
    }

    public void setAwardStatus(Integer awardStatus) {
        this.awardStatus = awardStatus;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getAwardTime() {
        return awardTime;
    }

    public void setAwardTime(Date awardTime) {
        this.awardTime = awardTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }
}
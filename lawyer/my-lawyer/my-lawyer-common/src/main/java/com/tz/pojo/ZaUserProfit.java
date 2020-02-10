package com.tz.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class ZaUserProfit {
    private String id;

    private String userId;

    private Integer userType;

    private BigDecimal coinConsumTotal;

    private BigDecimal coinSurplus;

    private BigDecimal coinIncome;

    private BigDecimal coinInTotal;

    private Integer status;

    private Date createdTime;

    private Date lastUseTime;

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

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public BigDecimal getCoinConsumTotal() {
        return coinConsumTotal;
    }

    public void setCoinConsumTotal(BigDecimal coinConsumTotal) {
        this.coinConsumTotal = coinConsumTotal;
    }

    public BigDecimal getCoinSurplus() {
        return coinSurplus;
    }

    public void setCoinSurplus(BigDecimal coinSurplus) {
        this.coinSurplus = coinSurplus;
    }

    public BigDecimal getCoinIncome() {
        return coinIncome;
    }

    public void setCoinIncome(BigDecimal coinIncome) {
        this.coinIncome = coinIncome;
    }

    public BigDecimal getCoinInTotal() {
        return coinInTotal;
    }

    public void setCoinInTotal(BigDecimal coinInTotal) {
        this.coinInTotal = coinInTotal;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastUseTime() {
        return lastUseTime;
    }

    public void setLastUseTime(Date lastUseTime) {
        this.lastUseTime = lastUseTime;
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
package com.tz.pojo;

import java.util.Date;

public class ZaUserChat {
    private Long id;

    private String userId;

    private String lawId;

    private String sessionCode;

    private Integer chatCategory;

    private Date startTime;

    private Date endTime;

    private Integer showState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getLawId() {
        return lawId;
    }

    public void setLawId(String lawId) {
        this.lawId = lawId == null ? null : lawId.trim();
    }

    public String getSessionCode() {
        return sessionCode;
    }

    public void setSessionCode(String sessionCode) {
        this.sessionCode = sessionCode == null ? null : sessionCode.trim();
    }

    public Integer getChatCategory() {
        return chatCategory;
    }

    public void setChatCategory(Integer chatCategory) {
        this.chatCategory = chatCategory;
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

    public Integer getShowState() {
        return showState;
    }

    public void setShowState(Integer showState) {
        this.showState = showState;
    }
}
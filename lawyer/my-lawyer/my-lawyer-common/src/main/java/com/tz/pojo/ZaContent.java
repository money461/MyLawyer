package com.tz.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ZaContent {
    private String id;

    private Integer contentCategoryId;

    private Integer sort;

    private String title;

    private String contentTitle;

    private String linkUrl;

    private String homepageUrl;

    private String prictureUrl;

    private String contentDesc;

    private String operator;

    private Date createdTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date updatedTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getContentCategoryId() {
        return contentCategoryId;
    }

    public void setContentCategoryId(Integer contentCategoryId) {
        this.contentCategoryId = contentCategoryId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle == null ? null : contentTitle.trim();
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    public String getHomepageUrl() {
        return homepageUrl;
    }

    public void setHomepageUrl(String homepageUrl) {
        this.homepageUrl = homepageUrl == null ? null : homepageUrl.trim();
    }

    public String getPrictureUrl() {
        return prictureUrl;
    }

    public void setPrictureUrl(String prictureUrl) {
        this.prictureUrl = prictureUrl == null ? null : prictureUrl.trim();
    }

    public String getContentDesc() {
        return contentDesc;
    }

    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc == null ? null : contentDesc.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
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
}
package com.tianzhi.shop520.entity.home;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by thinkpad on 2017/11/8.
 */

public class HomeListEntity implements Serializable
{
    public String id;
    public String contentCategoryId;
    public String contentTitle;
    public String subTitle;
    public String  titleDesc;
    public String url;
    public String contentDesc;
    public String picFirstUrl;
    public String picSecondUrl;
    public String operater;
    public String createdTime;
    public String updatedTime;
    public ArrayList<String> firstPrics;
    public ArrayList<String> secondPrics;

    public String getCreatedTime() {
        return createdTime;
    }

    @Override
    public String toString() {
        return "HomeListEntity{" +
                "id='" + id + '\'' +
                ", contentCategoryId='" + contentCategoryId + '\'' +
                ", contentTitle='" + contentTitle + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", titleDesc='" + titleDesc + '\'' +
                ", url='" + url + '\'' +
                ", contentDesc='" + contentDesc + '\'' +
                ", picFirstUrl='" + picFirstUrl + '\'' +
                ", picSecondUrl='" + picSecondUrl + '\'' +
                ", operater='" + operater + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", updatedTime='" + updatedTime + '\'' +
                ", firstPrics=" + firstPrics +
                ", secondPrics=" + secondPrics +
                '}';
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContentCategoryId() {
        return contentCategoryId;
    }

    public void setContentCategoryId(String contentCategoryId) {
        this.contentCategoryId = contentCategoryId;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getTitleDesc() {
        return titleDesc;
    }

    public void setTitleDesc(String titleDesc) {
        this.titleDesc = titleDesc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentDesc() {
        return contentDesc;
    }

    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc;
    }

    public String getPicFirstUrl() {
        return picFirstUrl;
    }

    public void setPicFirstUrl(String picFirstUrl) {
        this.picFirstUrl = picFirstUrl;
    }

    public String getPicSecondUrl() {
        return picSecondUrl;
    }

    public void setPicSecondUrl(String picSecondUrl) {
        this.picSecondUrl = picSecondUrl;
    }

    public String getOperater() {
        return operater;
    }

    public void setOperater(String operater) {
        this.operater = operater;
    }

    public ArrayList<String> getFirstPrics() {
        return firstPrics;
    }

    public void setFirstPrics(ArrayList<String> firstPrics) {
        this.firstPrics = firstPrics;
    }

    public ArrayList<String> getSecondPrics() {
        return secondPrics;
    }

    public void setSecondPrics(ArrayList<String> secondPrics) {
        this.secondPrics = secondPrics;
    }
}

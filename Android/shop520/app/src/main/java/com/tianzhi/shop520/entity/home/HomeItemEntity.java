package com.tianzhi.shop520.entity.home;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by thinkpad on 2017/11/14.
 */

public class HomeItemEntity implements Serializable{
    public  String contentCategoryId;
    public String contentCategoryName;
    public String linkAddress;
    public ArrayList<HomeListEntity> contentList;

    public String getLinkAddress() {
        return linkAddress;
    }

    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }

    public String getContentCategoryId() {
        return contentCategoryId;
    }

    public void setContentCategoryId(String contentCategoryId) {
        this.contentCategoryId = contentCategoryId;
    }

    public String getContentCategoryName() {
        return contentCategoryName;
    }

    public void setContentCategoryName(String contentCategoryName) {
        this.contentCategoryName = contentCategoryName;
    }

    public ArrayList<HomeListEntity> getContentList() {
        return contentList;
    }

    public void setContentList(ArrayList<HomeListEntity> contentList) {
        this.contentList = contentList;
    }

    @Override
    public String toString() {
        return "HomeItemEntity{" +
                "contentCategoryId='" + contentCategoryId + '\'' +
                ", contentCategoryName='" + contentCategoryName + '\'' +
                ", linkAddress='" + linkAddress + '\'' +
                ", contentList=" + contentList +
                '}';
    }
}

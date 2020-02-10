package com.tianzhi.shop520.entity.home;

import com.tianzhi.shop520.entity.store.ListCategoryItem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by thinkpad on 2017/11/8.
 */

public class HomeEntity implements Serializable{
    public ArrayList<HomeItemEntity> homePageContent;
    public   ArrayList<ListCategoryItem> itemCategoryList;
    public ArrayList<ContentCategoryList> contentCategoryList;

    public ArrayList<HomeItemEntity> getHomePageContent() {
        return homePageContent;
    }

    public void setHomePageContent(ArrayList<HomeItemEntity> homePageContent) {
        this.homePageContent = homePageContent;
    }

    public ArrayList<ListCategoryItem> getItemCategoryList() {
        return itemCategoryList;
    }

    public void setItemCategoryList(ArrayList<ListCategoryItem> itemCategoryList) {
        this.itemCategoryList = itemCategoryList;
    }

    public ArrayList<ContentCategoryList> getContentCategoryList() {
        return contentCategoryList;
    }

    public void setContentCategoryList(ArrayList<ContentCategoryList> contentCategoryList) {
        this.contentCategoryList = contentCategoryList;
    }

    @Override
    public String toString() {
        return "HomeEntity{" +
                "homePageContent=" + homePageContent +
                ", itemCategoryList=" + itemCategoryList +
                ", contentCategoryList=" + contentCategoryList +
                '}';
    }
}

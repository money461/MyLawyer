package com.tianzhi.shop520.entity.store;

import com.tianzhi.shop520.entity.home.HomeListEntity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by thinkpad on 2017/11/14.
 */

public class StoreInfo implements Serializable{
    public ArrayList<HomeListEntity> tzContentList;
    public ArrayList<ListCategoryItem> itemCategoryList;
    public ArrayList<StoreInfoEntity> indexItemList;

    @Override
    public String toString() {
        return "StoreInfo{" +
                "tzContentList=" + tzContentList +
                ", itemCategoryList=" + itemCategoryList +
                ", indexItemList=" + indexItemList +
                '}';
    }

    public ArrayList<HomeListEntity> getTzContentList() {
        return tzContentList;
    }

    public void setTzContentList(ArrayList<HomeListEntity> tzContentList) {
        this.tzContentList = tzContentList;
    }

    public ArrayList<ListCategoryItem> getItemCategoryList() {
        return itemCategoryList;
    }

    public void setItemCategoryList(ArrayList<ListCategoryItem> itemCategoryList) {
        this.itemCategoryList = itemCategoryList;
    }

    public ArrayList<StoreInfoEntity> getIndexItemList() {
        return indexItemList;
    }

    public void setIndexItemList(ArrayList<StoreInfoEntity> indexItemList) {
        this.indexItemList = indexItemList;
    }
}

package com.tianzhi.shop520.entity.store;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by thinkpad on 2017/11/14.
 */

public class StoreInfoEntity implements Serializable{
    public String id;//分类id
    public String name;//分类名称
    public String sort;//分类唯一标识
    public String operater;
    public ArrayList<GoodsInfoEntity> indexItemList;

    @Override
    public String toString() {
        return "StoreInfoEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sort='" + sort + '\'' +
                ", operater='" + operater + '\'' +
                ", indexItemList=" + indexItemList +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOperater() {
        return operater;
    }

    public void setOperater(String operater) {
        this.operater = operater;
    }

    public ArrayList<GoodsInfoEntity> getIndexItemList() {
        return indexItemList;
    }

    public void setIndexItemList(ArrayList<GoodsInfoEntity> indexItemList) {
        this.indexItemList = indexItemList;
    }
}

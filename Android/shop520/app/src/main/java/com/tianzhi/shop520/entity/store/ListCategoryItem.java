package com.tianzhi.shop520.entity.store;

import java.io.Serializable;

/**
 * Created by thinkpad on 2017/10/27.
 * 首页  四个tab
 */

public class ListCategoryItem implements Serializable{
    public String id;
    public String pId;
    public String name;
    public String logoUrl;
    public String linkAddress;
    public String isParent;
    public String sort;
    public String operater;

    @Override
    public String toString() {
        return "ListCategoryItem{" +
                "id='" + id + '\'' +
                ", pId='" + pId + '\'' +
                ", name='" + name + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", linkAddress='" + linkAddress + '\'' +
                ", isParent='" + isParent + '\'' +
                ", sort='" + sort + '\'' +
                ", operater='" + operater + '\'' +
                '}';
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getLinkAddress() {
        return linkAddress;
    }

    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
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


}

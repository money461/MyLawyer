package com.tianzhi.shop520.entity.shop;

import java.io.Serializable;

/**
 * Created by thinkpad on 2017/11/6.
 */

public class CarInfoEntity implements Serializable{
    public String id;
    public String userId;
    public String itemId;
    public int num;
    public String createdTime;
    public String updatedTime;
    public String itemTitle;
    public String description;
    public double price;
    public double memberPrice;
    public String homepageUrl;
    public String status;
    protected boolean isChoosed;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCreatedTime() {
        return createdTime;
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

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(double memberPrice) {
        this.memberPrice = memberPrice;
    }

    public String getHomepageUrl() {
        return homepageUrl;
    }

    public void setHomepageUrl(String homepageUrl) {
        this.homepageUrl = homepageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean isChoosed) {
        this.isChoosed = isChoosed;
    }
    @Override
    public String toString() {
        return "CarInfoEntity{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", num='" + num + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", updatedTime='" + updatedTime + '\'' +
                ", itemTitle='" + itemTitle + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", memberPrice='" + memberPrice + '\'' +
                ", homepageUrl='" + homepageUrl + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

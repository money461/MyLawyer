package com.tianzhi.shop520.entity.order;

import java.io.Serializable;

/**
 * Created by thinkpad on 2017/11/8.
 * 订单详情列表
 */

public class OrderItemList implements Serializable{
    public String id;
    public String orderId;
    public String itemId;
    public int num;
    public String description;
    public String  itemTitle;
    public double price;
    public double realPrice;
    public double memberPrice;
    public double itemPost; //邮费
    public double totalFee;
    public String homepageUrl;
    public String operater;
    public String createdTime;
    public String updatedTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(double realPrice) {
        this.realPrice = realPrice;
    }

    public double getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(double memberPrice) {
        this.memberPrice = memberPrice;
    }

    public double getItemPost() {
        return itemPost;
    }

    public void setItemPost(double itemPost) {
        this.itemPost = itemPost;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public String getHomepageUrl() {
        return homepageUrl;
    }

    public void setHomepageUrl(String homepageUrl) {
        this.homepageUrl = homepageUrl;
    }

    public String getOperater() {
        return operater;
    }

    public void setOperater(String operater) {
        this.operater = operater;
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

    @Override
    public String toString() {
        return "OrderItemList{" +
                "id='" + id + '\'' +
                ", orderId='" + orderId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", num=" + num +
                ", description='" + description + '\'' +
                ", itemTitle='" + itemTitle + '\'' +
                ", price=" + price +
                ", realPrice=" + realPrice +
                ", memberPrice=" + memberPrice +
                ", itemPost=" + itemPost +
                ", totalFee=" + totalFee +
                ", homepageUrl='" + homepageUrl + '\'' +
                ", operater='" + operater + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", updatedTime='" + updatedTime + '\'' +
                '}';
    }
}

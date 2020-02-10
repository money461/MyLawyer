package com.tianzhi.shop520.entity.store;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by thinkpad on 2017/10/28.
 */

public class GoodsInfoEntity implements Serializable{
    public String id;
    public String itemTitle;//商品名字
    public String description;//商品描述
    public double price;//价格
    public double memberPrice;//会员价格
    public int num;//数量
    public String barcode;//
    public String categoryId;//分类id
    public String imageUrl;
    public String homepageUrl;//商品缩略图
    public String status;
    public String operater;
    public String itemPost;
    public String salesNum;
    public String mallId;
    public String categoryName;//分类名字
    public String createdTime;
    public String updatedTime;
    public String realPrice;
    public ArrayList<String> images;//banner图
    public ArrayList<String> prictures;

    public String getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(String realPrice) {
        this.realPrice = realPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getOperater() {
        return operater;
    }

    public void setOperater(String operater) {
        this.operater = operater;
    }

    public String getItemPost() {
        return itemPost;
    }

    public void setItemPost(String itemPost) {
        this.itemPost = itemPost;
    }

    public String getSalesNum() {
        return salesNum;
    }

    public void setSalesNum(String salesNum) {
        this.salesNum = salesNum;
    }

    public String getMallId() {
        return mallId;
    }

    public void setMallId(String mallId) {
        this.mallId = mallId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public ArrayList<String> getPrictures() {
        return prictures;
    }

    public void setPrictures(ArrayList<String> prictures) {
        this.prictures = prictures;
    }

    @Override
    public String toString() {
        return "GoodsInfoEntity{" +
                "id='" + id + '\'' +
                ", itemTitle='" + itemTitle + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", memberPrice=" + memberPrice +
                ", num=" + num +
                ", barcode='" + barcode + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", homepageUrl='" + homepageUrl + '\'' +
                ", status='" + status + '\'' +
                ", operater='" + operater + '\'' +
                ", itemPost='" + itemPost + '\'' +
                ", salesNum='" + salesNum + '\'' +
                ", mallId='" + mallId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", updatedTime='" + updatedTime + '\'' +
                ", realPrice='" + realPrice + '\'' +
                ", images=" + images +
                ", prictures=" + prictures +
                '}';
    }
}

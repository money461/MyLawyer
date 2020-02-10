package com.tianzhi.shop520.entity.order;

import com.tianzhi.shop520.entity.store.ReceiverInfo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by thinkpad on 2017/11/8.
 * 确认订单 返回数据
 */

public class EnorderEntity implements Serializable{

    public String orderToken;
    public ReceiverInfo receiverinfo;
    public ArrayList<OrderItemList>orderItemList;
    public double cash;
    public double payment;
    public double postFee;
    public int totalNum ;   //总数量
    public double discount;
    public String  discountDesc;
    public int  loveValue;
    public String status;

    @Override
    public String toString() {
        return "EnorderEntity{" +
                "orderToken='" + orderToken + '\'' +
                ", receiverinfo=" + receiverinfo +
                ", orderItemList=" + orderItemList +
                ", cash=" + cash +
                ", payment=" + payment +
                ", postFee=" + postFee +
                ", totalNum=" + totalNum +
                ", discount=" + discount +
                ", discountDesc='" + discountDesc + '\'' +
                ", loveValue=" + loveValue +
                ", status='" + status + '\'' +
                '}';
    }

    public String getOrderToken() {
        return orderToken;
    }

    public void setOrderToken(String orderToken) {
        this.orderToken = orderToken;
    }

    public ReceiverInfo getReceiverinfo() {
        return receiverinfo;
    }

    public void setReceiverinfo(ReceiverInfo receiverinfo) {
        this.receiverinfo = receiverinfo;
    }

    public ArrayList<OrderItemList> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(ArrayList<OrderItemList> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public double getPostFee() {
        return postFee;
    }

    public void setPostFee(double postFee) {
        this.postFee = postFee;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getDiscountDesc() {
        return discountDesc;
    }

    public void setDiscountDesc(String discountDesc) {
        this.discountDesc = discountDesc;
    }

    public int getLoveValue() {

        return loveValue;
    }

    public void setLoveValue(int loveValue) {
        this.loveValue = loveValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

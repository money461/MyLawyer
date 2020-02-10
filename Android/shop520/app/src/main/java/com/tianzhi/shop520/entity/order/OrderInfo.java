package com.tianzhi.shop520.entity.order;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by thinkpad on 2017/11/15.
 * 订单详情
 */

public class OrderInfo implements Serializable{
    public String  id;
    public String userId;
    public String shippingCode;
    public String status;// 0、取消订单，1、未付款，2、已付款待发货、3、已发货，4、交易成功，5、交易关闭,6、已退款
    public double payment;
    public String paymentType;
    public String account;
    public String loveValue;
    public String discount;
    public String discountDesc;
    public double postFee;
    public String orderNum;
    public String mallId;
    public String buyerNick;
    public String createdTime;
    public String updatedTime;
    public String paymentTime;
    public String consignTime;
    public String endTime;
    public String closeTime;
    public String operater;
    public String buyerRate;
    public String buyerMessage;
    public String type;
    public ArrayList<OrderItemList> orderItems;
    public OrderShipping orderShipping;

    @Override
    public String toString() {
        return "OrderInfo{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", shippingCode='" + shippingCode + '\'' +
                ", status='" + status + '\'' +
                ", payment=" + payment +
                ", paymentType='" + paymentType + '\'' +
                ", account='" + account + '\'' +
                ", loveValue='" + loveValue + '\'' +
                ", discount='" + discount + '\'' +
                ", discountDesc='" + discountDesc + '\'' +
                ", postFee='" + postFee + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", mallId='" + mallId + '\'' +
                ", buyerNick='" + buyerNick + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", updatedTime='" + updatedTime + '\'' +
                ", paymentTime='" + paymentTime + '\'' +
                ", consignTime='" + consignTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", closeTime='" + closeTime + '\'' +
                ", operater='" + operater + '\'' +
                ", buyerRate='" + buyerRate + '\'' +
                ", buyerMessage='" + buyerMessage + '\'' +
                ", type='" + type + '\'' +
                ", orderItems=" + orderItems +
                ", orderShipping=" + orderShipping +
                '}';
    }

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

    public String getShippingCode() {
        return shippingCode;
    }

    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getLoveValue() {
        return loveValue;
    }

    public void setLoveValue(String loveValue) {
        this.loveValue = loveValue;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscountDesc() {
        return discountDesc;
    }

    public void setDiscountDesc(String discountDesc) {
        this.discountDesc = discountDesc;
    }

    public double getPostFee() {
        return postFee;
    }

    public void setPostFee(double postFee) {
        this.postFee = postFee;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getMallId() {
        return mallId;
    }

    public void setMallId(String mallId) {
        this.mallId = mallId;
    }

    public String getBuyerNick() {
        return buyerNick;
    }

    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick;
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

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getConsignTime() {
        return consignTime;
    }

    public void setConsignTime(String consignTime) {
        this.consignTime = consignTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getOperater() {
        return operater;
    }

    public void setOperater(String operater) {
        this.operater = operater;
    }

    public String getBuyerRate() {
        return buyerRate;
    }

    public void setBuyerRate(String buyerRate) {
        this.buyerRate = buyerRate;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<OrderItemList> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<OrderItemList> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderShipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(OrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }
}

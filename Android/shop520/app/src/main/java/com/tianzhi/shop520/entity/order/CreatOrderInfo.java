package com.tianzhi.shop520.entity.order;

import com.tianzhi.shop520.entity.shop.orderListItem;
import com.tianzhi.shop520.entity.store.ReceiverInfo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by thinkpad on 2017/11/8.
 * 生成订单   提交服务器数据
 */

public class CreatOrderInfo implements Serializable{
    public double payment;//订单总计和(商品总价+运费-爱心值-优惠券价)
    public String paymentType;//1.支付宝支付 2. 微信支付 3.网银支付 4. 爱心值支付
    public int loveValue;//抵扣的了多少爱心值
    public String  buyerNick;//买家昵称或者登录账户信息
    public String buyerMessage;//,买家留言
    public ArrayList<orderListItem> orderItems;
    public ReceiverInfo orderShipping;//上传收货人信息
    public String orderToken; //订单生成令牌
    public String userToken;

    @Override
    public String toString() {
        return "CreatOrderInfo{" +
                "payment='" + payment + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", loveValue='" + loveValue + '\'' +
                ", buyerNick='" + buyerNick + '\'' +
                ", buyerMessage='" + buyerMessage + '\'' +
                ", orderItems=" + orderItems +
                ", orderShipping=" + orderShipping +
                ", orderToken='" + orderToken + '\'' +
                ", userToken='" + userToken + '\'' +
                '}';
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

    public int getLoveValue() {
        return loveValue;
    }

    public void setLoveValue(int loveValue) {
        this.loveValue = loveValue;
    }

    public String getBuyerNick() {
        return buyerNick;
    }

    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    public ArrayList<orderListItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<orderListItem> orderItems) {
        this.orderItems = orderItems;
    }

    public ReceiverInfo getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(ReceiverInfo orderShipping) {
        this.orderShipping = orderShipping;
    }

    public String getOrderToken() {
        return orderToken;
    }

    public void setOrderToken(String orderToken) {
        this.orderToken = orderToken;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}

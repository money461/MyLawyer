package com.tianzhi.shop520.entity.order;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by thinkpad on 2017/11/4.
 */

public class OrderEntity implements Serializable{
    public String orderId;
    public String status;
    public String payment;
    public String postFee;
    public String orderNum;//订单商品数量
    public String com;
    public String shippingCode;//物流信息
    public ArrayList<OrderItemList> orderItems;

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getShippingCode() {
        return shippingCode;
    }

    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPostFee() {
        return postFee;
    }

    public void setPostFee(String postFee) {
        this.postFee = postFee;
    }

    public ArrayList<OrderItemList> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<OrderItemList> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "orderId='" + orderId + '\'' +
                ", status='" + status + '\'' +
                ", payment='" + payment + '\'' +
                ", postFee='" + postFee + '\'' +
                ", orderItems=" + orderItems +
                '}';
    }
}

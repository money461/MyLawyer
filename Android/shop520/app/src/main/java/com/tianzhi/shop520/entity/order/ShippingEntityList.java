package com.tianzhi.shop520.entity.order;

import java.io.Serializable;

/**
 * Created by thinkpad on 2017/11/24.
 */

public class ShippingEntityList implements Serializable{
    public String datetime;
    public String remark;
    public String zone;

    @Override
    public String toString() {
        return "ShippingEntityList{" +
                "datetime='" + datetime + '\'' +
                ", remark='" + remark + '\'' +
                ", zone='" + zone + '\'' +
                '}';
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}

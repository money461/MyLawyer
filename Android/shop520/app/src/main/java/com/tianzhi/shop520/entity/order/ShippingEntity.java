package com.tianzhi.shop520.entity.order;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by thinkpad on 2017/11/24.
 */

public class ShippingEntity implements Serializable{
    public String company;
    public String com;
    public String no;
    public String status;
    public ArrayList<ShippingEntityList> list;

    @Override
    public String toString() {
        return "ShippingEntity{" +
                "company='" + company + '\'' +
                ", com='" + com + '\'' +
                ", no='" + no + '\'' +
                ", status='" + status + '\'' +
                ", list=" + list +
                '}';
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<ShippingEntityList> getList() {
        return list;
    }

    public void setList(ArrayList<ShippingEntityList> list) {
        this.list = list;
    }
}

package com.tianzhi.shop520.entity.shop;

import java.io.Serializable;

/**
 * Created by thinkpad on 2017/11/8.
 * 确认订单  上传服务器参数
 */

public class orderListItem implements Serializable {
    public String id;
    public int num;

    @Override
    public String toString() {
        return "orderListItem{" +
                "id='" + id + '\'' +
                ", num=" + num +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}

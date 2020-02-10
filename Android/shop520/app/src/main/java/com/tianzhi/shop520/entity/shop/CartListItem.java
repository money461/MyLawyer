package com.tianzhi.shop520.entity.shop;

import java.io.Serializable;

/**
 * Created by thinkpad on 2017/11/2.
 * 购物车添加上传服务器的
 */

public class CartListItem implements Serializable{
    public String itemId;
    public int num;

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

    @Override
    public String toString() {
        return "cartListItem{" +
                "itemId='" + itemId + '\'' +
                ", num='" + num + '\'' +
                '}';
    }
}

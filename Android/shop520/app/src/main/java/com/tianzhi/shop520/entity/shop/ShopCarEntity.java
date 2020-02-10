package com.tianzhi.shop520.entity.shop;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by thinkpad on 2017/11/3.
 */

public class ShopCarEntity implements Serializable{
    public String id;
    public String name;
    public String sort;
    public String operater;
    protected boolean isChoosed;
    public ArrayList<CarInfoEntity> cartList;//购物车分类

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOperater() {
        return operater;
    }

    public void setOperater(String operater) {
        this.operater = operater;
    }

    public ArrayList<CarInfoEntity> getCartList() {
        return cartList;
    }

    public void setCartList(ArrayList<CarInfoEntity> cartList) {
        this.cartList = cartList;
    }

    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean choosed) {
        isChoosed = choosed;
    }

    @Override
    public String toString() {
        return "ShopCarEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sort='" + sort + '\'' +
                ", operater='" + operater + '\'' +
                ", isChoosed=" + isChoosed +
                ", cartList=" + cartList +
                '}';
    }
}

package com.tianzhi.shop520.entity.store;

import java.util.List;

/**
 * Created by thinkpad on 2017/10/27.
 * 查询商品分类目录
 */

public class ListcategoryEntity {
    public String flag;
    public String msg;
    public List<ListCategoryItem> data;

    @Override
    public String toString() {
        return "ListcategoryEntity{" +
                "flag='" + flag + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}

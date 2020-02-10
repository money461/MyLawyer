package com.tianzhi.shop520.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.entity.order.OrderItemList;

import java.util.List;

/**
 * Created by thinkpad on 2017/11/15.
 * 订单详情
 */

public class OrderInfoAdp extends BaseQuickAdapter<OrderItemList, BaseViewHolder> {

    public OrderInfoAdp(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderItemList item) {
        helper.setText(R.id.goods_name, item.itemTitle);
        helper.setText(R.id.goods_info,item.description)
                .setText(R.id.goods_price,"￥"+item.memberPrice)
                .setText(R.id.goods_num,"x"+item.num);
        Glide.with(mContext).load(item.homepageUrl).into((ImageView) helper.getView(R.id.shopcar_pic));
    }

}


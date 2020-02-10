package com.tianzhi.shop520.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.entity.store.GoodsClassItem;

import java.util.List;

/**
 * Created by thinkpad on 2017/10/28.
 * 商品分类Adp
 */

public class GoodsClassAdp extends BaseQuickAdapter<GoodsClassItem, BaseViewHolder> {

    public GoodsClassAdp(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsClassItem item) {
        helper.setText(R.id.item_title, item.itemTitle);
        helper.setText(R.id.item_description,item.description)
                .setText(R.id.item_memberPrice,"￥"+item.memberPrice)
                .setText(R.id.item_price,"普通价：￥"+item.price);
        Glide.with(mContext).load(item.homepageUrl).into((ImageView) helper.getView(R.id.icon));
//        helper.setImageResource(R.id.icon, item.getImageResource());
    }

}
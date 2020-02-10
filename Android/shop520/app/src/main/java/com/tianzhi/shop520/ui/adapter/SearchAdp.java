package com.tianzhi.shop520.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.entity.store.GoodsInfoEntity;

import java.util.List;

/**
 * Created by thinkpad on 2017/11/15.
 * 模糊查询
 */

public class SearchAdp extends BaseQuickAdapter<GoodsInfoEntity, BaseViewHolder> {

    public SearchAdp(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsInfoEntity item) {
        helper.setText(R.id.item_title, item.itemTitle);
        helper.setText(R.id.item_description,item.description)
                .setText(R.id.item_memberPrice,"￥"+item.memberPrice)
                .setText(R.id.item_price,"普通价：￥"+item.price);
        Glide.with(mContext).load(item.homepageUrl).into((ImageView) helper.getView(R.id.icon));
//        helper.setImageResource(R.id.icon, item.getImageResource());
    }


}

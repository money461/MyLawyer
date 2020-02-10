package com.tianzhi.shop520.ui.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.entity.store.GoodsInfoEntity;

import java.util.List;

/**
 * Created by thinkpad on 2017/10/23.
 */

public class StoreBagAdapter extends BaseQuickAdapter<GoodsInfoEntity, BaseViewHolder> {
    private Context context;
    int height;
    int width;
    public StoreBagAdapter(int layoutResId, List data, Context context,int width, int height) {
        super(layoutResId, data);
        this.context = context;
        this.width = width;
        this.height = height;
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsInfoEntity item) {
        if (getItemCount() <= 2) {
            helper.getView(R.id.ll_paysuccess_item).setLayoutParams(new LinearLayout.LayoutParams((width) / getItemCount(),
                    LinearLayout.LayoutParams.MATCH_PARENT));
        }
//        else {
//            helper.getView(R.id.ll_paysuccess_item).setLayoutParams(
//                    new LinearLayout.LayoutParams((width) / 3, LinearLayout.LayoutParams.MATCH_PARENT));
//        }
        helper.setText(R.id.item_land_title, item.itemTitle)
        .setText(R.id.item_land_price,"￥"+item.price)
        .setText(R.id.item_land_vipprice,"￥"+item.memberPrice);
        Glide.with(context).load(item.homepageUrl).into((ImageView) helper.getView(R.id.item_land_icon));
    }

}

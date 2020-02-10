package com.tianzhi.shop520.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.entity.store.ListCategoryItem;

import java.util.List;

/**
 * Created by thinkpad on 2017/10/20.
 * 首页8个tab
 */

public class HomeGirdAdapter  extends BaseQuickAdapter <ListCategoryItem, BaseViewHolder>{

     Context mContext;
    public HomeGirdAdapter(int layoutResId, List data,Context context) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ListCategoryItem item) {
        helper.setText(R.id.text, item.getName());
        Glide.with(mContext).load(item.getLogoUrl()).into((ImageView) helper.getView(R.id.icon));

    }

}

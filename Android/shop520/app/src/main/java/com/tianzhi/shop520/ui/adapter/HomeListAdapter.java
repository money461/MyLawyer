package com.tianzhi.shop520.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.entity.home.HomeListEntity;

import java.util.List;

/**
 * Created by thinkpad on 2017/10/23.
 */

public class HomeListAdapter  extends BaseQuickAdapter<HomeListEntity, BaseViewHolder> {
    Context context;
    public HomeListAdapter(int layoutResId, List data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeListEntity item) {
        helper.setText(R.id.item_news_title, item.getContentTitle())
                .setText(R.id.item_news_context, item.getContentDesc())
                .setText(R.id.item_news_time, item.getCreatedTime());
        if(null!=item.getFirstPrics()&&item.getFirstPrics().size()>0){
            Glide.with(context).load(item.getFirstPrics().get(0)).into((ImageView)helper.getView(R.id.news_pic));

        }
    }

}
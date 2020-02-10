package com.tianzhi.shop520.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.entity.order.OrderItemList;
import com.tianzhi.shop520.ui.activity.order.OrderInfoAct;

import java.util.ArrayList;

/**
 * Created by thinkpad on 2017/10/26.
 *订单多个商品时横向滑动adp
 */

public class HorizontalAdapter extends RecyclerView.Adapter<BaseHolder> {
    ArrayList<OrderItemList> List;
    Context mContext;
    int screenWidth;
    String id;
    public HorizontalAdapter(Context context, ArrayList<OrderItemList> List,int screenWidth,String OrderId) {
        this.mContext = context;
        this.List = List;
        this.screenWidth =screenWidth;
        this.id = OrderId;
    }
    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(R.layout.item_order_horizotal, parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        holder.refreshData(List.get(position), position);
    }

    @Override
    public int getItemCount() {
        return List.size();
    }
    /**
     * 通用子条目hodler
     */
    private class ItemViewHolder extends BaseHolder<OrderItemList> {

        private ImageView imageview_item;

        public ItemViewHolder(int viewId, ViewGroup parent, int viewType) {
            super(viewId, parent, viewType);
            imageview_item = (ImageView) itemView.findViewById(R.id.imageview_item);
            ViewGroup.LayoutParams layoutParams = imageview_item.getLayoutParams();
            layoutParams.width = layoutParams.height = screenWidth / 3;
            imageview_item.setLayoutParams(layoutParams);
        }

        @Override
        public void refreshData(OrderItemList data, final int position) {
            Glide.with(mContext).load(data.getHomepageUrl()).into(imageview_item);
//            imageview_item.setBackgroundResource(data);
            //跳转订单详情
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("orderid", id);
                    intent.setClass(mContext, OrderInfoAct.class);
                    mContext.startActivity(intent);
                }
            });
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }

}

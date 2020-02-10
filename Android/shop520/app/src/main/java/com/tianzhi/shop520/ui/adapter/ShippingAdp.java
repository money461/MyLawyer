package com.tianzhi.shop520.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tianzhi.shop520.R;
import com.tianzhi.shop520.entity.order.ShippingEntityList;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thinkpad on 2017/11/24.
 */

public class ShippingAdp extends RecyclerView.Adapter<ShippingAdp.MyViewHolder> {

    public ArrayList<ShippingEntityList> List;

    private Context mContext;
    private LayoutInflater inflater;
    // 申明一个点击事件接口变量
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, View v);
    }

    public ShippingAdp(Context context, ArrayList<ShippingEntityList> vehiclesList) {
        this.mContext = context;
        this.List = vehiclesList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final MyViewHolder myholder = (MyViewHolder) holder;
        myholder.shippingRemark.setText(List.get(position).getRemark());
        myholder.shippingDatetime.setText(List.get(position).getDatetime());
        if (position==0) {
            myholder.shippingIcon.setBackgroundResource(R.mipmap.nowshipping);
            myholder.shippingDatetime.setTextColor(Color.parseColor("#FFD21E"));
            myholder.shippingRemark.setTextColor(Color.parseColor("#FFD21E"));
        } else {
            myholder.shippingIcon.setBackgroundResource(R.mipmap.shipping);
            myholder.shippingDatetime.setTextColor(Color.parseColor("#666666"));
            myholder.shippingRemark.setTextColor(Color.parseColor("#666666"));
        }

    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_shipping, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    static class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.shipping_icon)
        ImageView shippingIcon;
        @BindView(R.id.shipping_remark)
        TextView shippingRemark;
        @BindView(R.id.shipping_datetime)
        TextView shippingDatetime;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

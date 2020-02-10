package com.tianzhi.shop520.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tianzhi.shop520.R;
import com.tianzhi.shop520.entity.personal.ConsumptionList;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thinkpad on 2017/11/2.
 * 爱心值记录
 */

public class HeartRecordAdp extends RecyclerView.Adapter<HeartRecordAdp.MyViewHolder> {

    public ArrayList<ConsumptionList> List;

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

    public HeartRecordAdp(Context context, ArrayList<ConsumptionList> vehiclesList) {
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
        myholder.tvName.setText(List.get(position).name);
        myholder.tvNum.setText(List.get(position).consumeVal);
        myholder.tvTime.setText(List.get(position).createdTime);
        if("1".equals(List.get(position).status)){
            myholder.tvNum.setTextColor(Color.parseColor("#FFD21E"));
        }else {
            myholder.tvNum.setTextColor(Color.parseColor("#666666"));
        }

    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_heartrecord, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_time)
        TextView tvTime;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
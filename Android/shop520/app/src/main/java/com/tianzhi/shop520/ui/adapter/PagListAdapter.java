package com.tianzhi.shop520.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.entity.store.GoodsClassItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thinkpad on 2017/10/26.
 * 套餐列表
 */

public class PagListAdapter extends RecyclerView.Adapter<PagListAdapter.MyViewHolder> {

    public List<GoodsClassItem> List;
    private Context mContext;
    private LayoutInflater inflater;
    // 申明一个点击事件接口变量
    private OnItemClickListener mOnItemClickListener;
    int w;
    int h;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, View v);
    }

    public PagListAdapter(Context context, List<GoodsClassItem> vehiclesList,int h,int w) {
        this.mContext = context;
        this.List = vehiclesList;
        inflater = LayoutInflater.from(mContext);
        this.w = w;
        this.h = h;
    }

    @Override
    public int getItemCount() {

        return List.size();
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final MyViewHolder myholder = (MyViewHolder) holder;
        myholder.bagBg.setLayoutParams(new RelativeLayout.LayoutParams(w,w));
        myholder.llLeft.setLayoutParams(new RelativeLayout.LayoutParams(w*2/5,w));
        RelativeLayout.LayoutParams  layoutParams =( RelativeLayout.LayoutParams) myholder.llRight.getLayoutParams();
        layoutParams.height = w;
        layoutParams.width = w*2/5;
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        myholder.llRight.setLayoutParams(layoutParams);


        Glide.with(mContext).load(List.get(position).getHomepageUrl()).into((ImageView) myholder.bagBg);
        if (position % 2 == 1) {
            myholder.llRight.setVisibility(View.GONE);
            myholder.llLeft.setVisibility(View.VISIBLE);
            myholder.bagTitleLeft.setText(List.get(position).getItemTitle());
            myholder.bagInfoLeft.setText(List.get(position).getDescription());
        } else {
            myholder.llLeft.setVisibility(View.GONE);
            myholder.llRight.setVisibility(View.VISIBLE);
            myholder.bagTitleRight.setText(List.get(position).getItemTitle());
            myholder.bagInfoRight.setText(List.get(position).getDescription());
        }

        myholder.itemRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = myholder.getLayoutPosition(); // 1
                mOnItemClickListener.onItemClick(myholder.itemRelativeLayout, position, null); // 2
            }
        });

    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_baglist, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.bag_title_left)
        TextView bagTitleLeft;
        @BindView(R.id.bag_info_left)
        TextView bagInfoLeft;
        @BindView(R.id.ll_left)
        RelativeLayout llLeft;
        @BindView(R.id.bag_title_right)
        TextView bagTitleRight;
        @BindView(R.id.bag_info_right)
        TextView bagInfoRight;
        @BindView(R.id.ll_right)
        RelativeLayout llRight;
        @BindView(R.id.bag_bg)
        ImageView bagBg;
        @BindView(R.id.item_relativeLayout)
        RelativeLayout itemRelativeLayout;
        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}

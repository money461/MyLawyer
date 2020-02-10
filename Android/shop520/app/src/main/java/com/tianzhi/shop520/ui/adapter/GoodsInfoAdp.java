package com.tianzhi.shop520.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.tianzhi.shop520.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thinkpad on 2017/11/23.
 */

public class GoodsInfoAdp extends RecyclerView.Adapter<GoodsInfoAdp.MyViewHolder> {

    public ArrayList<String> List;

    private Activity mContext;
    private LayoutInflater inflater;
    // 申明一个点击事件接口变量
    private OnItemClickListener mOnItemClickListener;
    private int width;
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, View v);
    }

    public GoodsInfoAdp(Activity context, ArrayList<String> imagelist) {
        this.mContext = context;
        this.List = imagelist;
        inflater = LayoutInflater.from(mContext);

        DisplayMetrics dm = new DisplayMetrics();
        mContext.getWindowManager().getDefaultDisplay().getMetrics(dm);

        width = dm.widthPixels;
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final MyViewHolder myholder = (MyViewHolder) holder;
//        int screenWidth = width;
//
//        ViewGroup.LayoutParams lp = holder.goodeInfo.getLayoutParams();
//        lp.width = screenWidth;
//        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
//
//        holder.goodeInfo.setLayoutParams(lp);
//
//        holder.goodeInfo.setMaxWidth(screenWidth);
//        holder.goodeInfo.setMaxHeight((int) (screenWidth * 10));// 这里其实可以根据需求而定，我这里测试为最大宽度的1.5倍
//        Glide.with(mContext)
//                .load(List.get(position))
//                .apply(centerInsideTransform()
//                        .placeholder(R.mipmap.default_image)
//                       )
//                .into(myholder.goodeInfo);
//        RequestOptions options = new RequestOptions()
//                .placeholder(R.mipmap.default_image)
//                .error(R.mipmap.default_image)
////                .priority(Priority.HIGH)
//                .dontAnimate()
//                .format(PREFER_ARGB_8888)
//                .diskCacheStrategy(DiskCacheStrategy.ALL);
//        Glide.with(mContext)
//                .load(List.get(position))
//                .apply(options)
//                .into(myholder.goodeInfo);
        Glide.with(mContext)
                .load(List.get(position))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (myholder.goodeInfo == null) {
                            return false;
                        }
                        if (myholder.goodeInfo.getScaleType() != ImageView.ScaleType.FIT_XY) {
                            myholder.goodeInfo.setScaleType(ImageView.ScaleType.FIT_XY);
                        }
                        ViewGroup.LayoutParams params = myholder.goodeInfo.getLayoutParams();
                        int vw = myholder.goodeInfo.getWidth() - myholder.goodeInfo.getPaddingLeft() - myholder.goodeInfo.getPaddingRight();
                        float scale = (float) vw / (float) resource.getIntrinsicWidth();
                        int vh = Math.round(resource.getIntrinsicHeight() * scale);
                        params.height = vh + myholder.goodeInfo.getPaddingTop() + myholder.goodeInfo.getPaddingBottom();
                        myholder.goodeInfo.setLayoutParams(params);
                        return false;
                    }
                })
                .placeholder(R.mipmap.default_image)
                .error(R.mipmap.default_image)
                .into(myholder.goodeInfo);

//        ImageLoader.with(mContext)
//                .placeHolder(R.mipmap.default_image,true)
//                .url(List.get(position))
//                .into(myholder.goodeInfo);

//        Picasso.with(mContext)
//                .load(List.get(position)).placeholder(R.mipmap.default_image)
//        //读取本地文件不缓存
//        .memoryPolicy(MemoryPolicy.NO_CACHE).
//                //读取网络文件不缓存
//                        networkPolicy(NetworkPolicy.NO_CACHE).
//        into(myholder.goodeInfo);
    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_goodsinfo_pic, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.goode_info)
        ImageView goodeInfo;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }

}
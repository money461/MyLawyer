package com.tianzhi.shop520.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.entity.store.GoodsClassItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by thinkpad on 2017/11/17.
 */

public class MemberPagAdp extends RecyclerView.Adapter<MemberPagAdp.MyViewHolder> {

    public ArrayList<GoodsClassItem> List;
    private CheckInterface checkInterface;
    private Context mContext;
    private LayoutInflater inflater;
    // 申明一个点击事件接口变量
    private OnItemClickListener mOnItemClickListener;
    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, View v);
    }

    public MemberPagAdp(Context context, ArrayList<GoodsClassItem> vehiclesList) {
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
        myholder.goodsName.setText(List.get(position).getItemTitle());
        myholder.goodsPrice.setText("￥"+List.get(position).getPrice());
//        RequestOptions options = new RequestOptions()
//                .placeholder(R.mipmap.default_image)
//                .error(R.mipmap.default_image)
////                .priority(Priority.HIGH)
//                .dontAnimate()
//                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(mContext)
                .load(List.get(position).getHomepageUrl())
                .placeholder(R.mipmap.default_image)
                .into(myholder.goodsPic);
        myholder.determineChekbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List.get(position).setChoosed(((CheckBox) v).isChecked());
                myholder.determineChekbox.setChecked(((CheckBox) v).isChecked());
                checkInterface.checkGroup(position, ((CheckBox) v).isChecked());// 暴露子选接口
            }
        });
    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_member_pag, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    /**
     * 复选框接口
     */
    public interface CheckInterface {
        /**
         * 组选框状态改变触发的事件
         *
         * @param groupPosition 组元素位置
         * @param isChecked     组元素选中与否
         */
        void checkGroup(int groupPosition, boolean isChecked);

    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.goods_pic)
        ImageView goodsPic;
        @BindView(R.id.goods_name)
        TextView goodsName;
        @BindView(R.id.goods_price)
        TextView goodsPrice;
        @BindView(R.id.determine_chekbox)
        CheckBox determineChekbox;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}


package com.tianzhi.shop520.ui.activity.store;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

import com.lzy.okgo.OkGo;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.callback.DialogCallback;
import com.tianzhi.shop520.entity.BaseResponse;
import com.tianzhi.shop520.entity.store.BagListEntity;
import com.tianzhi.shop520.entity.store.GoodsClassItem;
import com.tianzhi.shop520.ui.adapter.PagListAdapter;
import com.tianzhi.shop520.ui.adapter.ViewPagerAdapter;
import com.tianzhi.shop520.util.BaseConstant;
import com.tianzhi.shop520.util.Constants;
import com.tianzhi.shop520.util.LogUtils;
import com.tianzhi.shop520.util.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by thinkpad on 2017/10/26.
 * 套餐列表
 */

public class PagListAct extends BaseFragmentActivity {
    @BindView(R.id.pag_rlv)
    RecyclerView pagRlv;
    private ViewPagerAdapter adapter;
    private ArrayList<Object> items = new ArrayList();
    String id;//商品分类id
    PagListAdapter pagListAdapter;
    ArrayList<BagListEntity>   list;
    private List<GoodsClassItem> Goodslist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.act_paglist);
        initView();
    }

    @Override
    protected void initView() {
        id = paras.getString("ClassId");
        LogUtils.e("套餐id  act", id);
        setActivityTitle("套餐");
        getdata();
    }

    /*商品分类*/
    private void getdata() {
        String Url = (BaseConstant.GoodsUrl + BaseConstant.QUERYITEMBYCATEGORY);
        OkGo.get(Url)
                .tag(this)
                .params("id", id)
                .execute(new DialogCallback<BaseResponse<List<GoodsClassItem>>>(this, true) {
                    @Override
                    public void onSuccess(BaseResponse<List<GoodsClassItem>> listBaseResponse, Call call, Response response) {
                        super.onSuccess(listBaseResponse, call, response);
                        Goodslist = new ArrayList<GoodsClassItem>();
                        Goodslist = listBaseResponse.data;
                        LogUtils.e("商品分类详情", listBaseResponse.toString());
                        if(null!= Goodslist && Goodslist.size()>0){
                            initAdapter();
                        }

                    }
                });
    }


    private void initAdapter() {
        DisplayMetrics dm = new DisplayMetrics();// 获取手机分辨率
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Rect frame = new Rect();
        this.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        // 高度
        int height = dm.heightPixels;
        int w = dm.widthPixels;
        pagRlv.setLayoutManager(new LinearLayoutManager(context));
        pagRlv.setHasFixedSize(true);
        pagListAdapter = new PagListAdapter(context, Goodslist,height,w);
//设置Adapter
        pagRlv.setAdapter(pagListAdapter);
        //设置增加或删除条目的动画
        pagRlv.setItemAnimator(new DefaultItemAnimator());
        pagRlv.addItemDecoration(new SpaceItemDecoration(Util.dip2px(context, 10)));
        onitemClick();
    }

    private void onitemClick() {
        pagListAdapter.setOnItemClickListener(new PagListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, View v) {
                paras.putString(Constants.GOODSID,Goodslist.get(position).getId());
                goNext(GoodsInfoAct.class,paras,false);
            }
        });
    }


    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            if (parent.getChildLayoutPosition(view) != 0)
                outRect.top = space;
        }
    }


}

package com.tianzhi.shop520.ui.activity.order;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.okgo.OkGo;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.base.BaseLazyFragment;
import com.tianzhi.shop520.callback.DialogCallback;
import com.tianzhi.shop520.entity.BaseResponse;
import com.tianzhi.shop520.entity.order.OrderEntity;
import com.tianzhi.shop520.ui.adapter.OrderRclAdpter;
import com.tianzhi.shop520.util.AppShared;
import com.tianzhi.shop520.util.BaseConstant;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by thinkpad on 2017/11/9.
 * 等待收货
 */

public class OrderWaitReviceFragment extends BaseLazyFragment implements OrderRclAdpter.notifyDataInterface{
    @BindView(R.id.recylcerview)
    RecyclerView recylcerview;
    private View view;
    private int screenWidth;//屏幕宽度
    Context context;
    private ArrayList<OrderEntity> orderEntities;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.order_fragment, container, false);
        ButterKnife.bind(this, view);
        context = getActivity();
        basicParamInit();
//        initData();
//        initRecyclerView();
        return view;
    }
    /*我的订单*/
    private void queryOrderByUserId(){
        showLoading();
        OkGo.post(BaseConstant.OrderUrl+ BaseConstant.QUERYORDERBYUSERID)
                .params("userId", AppShared.getInstance(context).getLoginInfo().id)
                .params("status","3")
                .params("userToken",AppShared.getInstance(context).getLoginInfo().userToken)
                .params("curPage","1")
                .params("rows","20")
                .execute(new DialogCallback<BaseResponse<ArrayList<OrderEntity>>>(this.getActivity(),false) {
                    @Override
                    public void onSuccess(BaseResponse<ArrayList<OrderEntity>> arrayListBaseResponse, Call call, Response response) {
                        super.onSuccess(arrayListBaseResponse, call, response);
                        dismissLoading();
                        orderEntities = arrayListBaseResponse.data;
                        initRecyclerView();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dismissLoading();
                    }
                });
    }
    /**
     * 计算屏幕的宽度
     */
    private void basicParamInit() {
        DisplayMetrics metric = new DisplayMetrics();
        this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);
        this.screenWidth = metric.widthPixels;

    }
    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        //竖直排列、正向排序
        recylcerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        //添加了一个灰色背景
//        recylcerview.setBackgroundResource(R.color.white);
        OrderRclAdpter orderRclAdpter = new OrderRclAdpter(context,orderEntities,screenWidth,(BaseFragmentActivity)this.getActivity());
        orderRclAdpter.setCheckInterface(this);
        recylcerview.setAdapter(orderRclAdpter);
    }
    @Override
    public void notifyData() {
        queryOrderByUserId();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onFirstUserInvisible() {

    }

    @Override
    public void onFirstUserVisible() {
        queryOrderByUserId();
    }

    @Override
    public void onUserInvisible() {

    }

    @Override
    public void onUserVisible() {
        queryOrderByUserId();
    }
}

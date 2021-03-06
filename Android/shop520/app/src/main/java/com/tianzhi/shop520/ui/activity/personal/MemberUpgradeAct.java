package com.tianzhi.shop520.ui.activity.personal;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.callback.DialogCallback;
import com.tianzhi.shop520.entity.BaseResponse;
import com.tianzhi.shop520.entity.order.EnorderEntity;
import com.tianzhi.shop520.entity.shop.orderListItem;
import com.tianzhi.shop520.entity.store.GoodsClassItem;
import com.tianzhi.shop520.ui.activity.order.EnsureOrderAct;
import com.tianzhi.shop520.ui.adapter.MemberUpgradAdp;
import com.tianzhi.shop520.util.AppShared;
import com.tianzhi.shop520.util.BaseConstant;
import com.tianzhi.shop520.util.LogUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by thinkpad on 2017/11/16.
 * 会员升级
 */

public class MemberUpgradeAct extends BaseFragmentActivity implements MemberUpgradAdp.CheckInterface{
    @BindView(R.id.member_pag_rcl)
    RecyclerView memberPagRcl;
    @BindView(R.id.to_pay)
    Button toPay;
    private ArrayList<GoodsClassItem> Goodslist;
    private List<GoodsClassItem> checkedList;
    private EnorderEntity enorderEntity;//确认订单  接口返回
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.act_member_upgrade);
        initView();
        memberPagRcl.setHasFixedSize(true);
        memberPagRcl.setNestedScrollingEnabled(false);

    }

    @Override
    protected void initView() {
        setActivityTitle("会员升级");
        String Url = (BaseConstant.GoodsUrl + BaseConstant.QUERYITEMBYCATEGORY);
        OkGo.get(Url)
                .tag(this)
                .params("id", "4")
                .execute(new DialogCallback<BaseResponse<ArrayList<GoodsClassItem>>>(this, true) {
                    @Override
                    public void onSuccess(BaseResponse<ArrayList<GoodsClassItem>> listBaseResponse, Call call, Response response) {
                        super.onSuccess(listBaseResponse, call, response);
                        Goodslist = new ArrayList<GoodsClassItem>();
                        Goodslist = listBaseResponse.data;
                        LogUtils.e("会员升级套餐", listBaseResponse.toString());
                        initAdapter();
                    }
                });
    }

    private void initAdapter() {
        memberPagRcl.setLayoutManager(new LinearLayoutManager(this));
        memberPagRcl.setHasFixedSize(true);
       MemberUpgradAdp    memberUpgradAdp = new MemberUpgradAdp(context,Goodslist);
        memberUpgradAdp.setCheckInterface(this);// 关键步骤1,设置复选框接口
//        selva.setmListener(this);
        memberPagRcl.setAdapter(memberUpgradAdp);
    }

    @Override
    public void checkGroup(int groupPosition, boolean isChecked) {
        calculate();
    }
    /**
     * 统计操作<br>
     *
     * 1.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作<br>
     *
     * 2.遍历所有子元素，被选中，添加到集合
     */
    private void calculate() {
        checkedList = new ArrayList<GoodsClassItem>();
        for (int i = 0; i < Goodslist.size(); i++) {
            GoodsClassItem product = Goodslist.get(i);
                if (product.isChoosed()) {
                    checkedList.add(product);
                }
            }
    }
    /*确认订单*/
    private void validateOrder( ArrayList<orderListItem> cartlistitems){
        Gson gson =new Gson();
        String obj = gson.toJson(cartlistitems);
        LogUtils.e("确认订单参数",obj.toString());
        LogUtils.e("确认订单参数userId",AppShared.getInstance(context).getLoginInfo().id.toString());
        LogUtils.e("确认订单参数",obj.toString());
        LogUtils.e("确认订单参数",obj.toString());
        OkGo.post(BaseConstant.OrderUrl+BaseConstant.VALIDATEORDER)
                .params("userId", AppShared.getInstance(context).getLoginInfo().id)
                .params("userToken",AppShared.getInstance(context).getLoginInfo().userToken)
                .params("items",obj)
                .params("type","0")
                .execute(new DialogCallback<BaseResponse<EnorderEntity>>(this,true) {
                    @Override
                    public void onSuccess(BaseResponse<EnorderEntity> enorderEntityBaseResponse, Call call, Response response) {
                        super.onSuccess(enorderEntityBaseResponse, call, response);
                        enorderEntity =  enorderEntityBaseResponse.data;
                        paras.putSerializable("enorderEntity",(Serializable) enorderEntity);
                        paras.putString("type","0");
                        goNext(EnsureOrderAct.class,paras,true);
                    }
                });
    }



    @OnClick(R.id.to_pay)
    public void onViewClicked() {
        if(null == checkedList||checkedList.size()<0){
            Toast.makeText(context, "您还没有选择商品哦", Toast.LENGTH_LONG).show();
            return;
        }
        ArrayList<orderListItem> orderListItems = new ArrayList<>();
        for (int i = 0;i<checkedList.size();i++){
            orderListItem orderListItem = new orderListItem();
            orderListItem.setId(checkedList.get(i).getId());
            orderListItem.setNum(1);
            orderListItems.add(orderListItem);
        }
//                paras.putSerializable(Constants.CHECKEDLIST,orderListItems);
        validateOrder(orderListItems);
    }

}

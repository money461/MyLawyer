package com.tianzhi.shop520.ui.activity.order;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.callback.DialogCallback;
import com.tianzhi.shop520.callback.DialogJsonCallback;
import com.tianzhi.shop520.entity.BaseResponse;
import com.tianzhi.shop520.entity.BaseResponseData;
import com.tianzhi.shop520.entity.order.EnorderEntity;
import com.tianzhi.shop520.entity.order.OrderInfo;
import com.tianzhi.shop520.entity.order.OrderShipping;
import com.tianzhi.shop520.entity.shop.orderListItem;
import com.tianzhi.shop520.ui.activity.shop.PayAct;
import com.tianzhi.shop520.ui.activity.store.GoodsInfoAct;
import com.tianzhi.shop520.ui.adapter.OrderInfoAdp;
import com.tianzhi.shop520.util.AppShared;
import com.tianzhi.shop520.util.BaseConstant;
import com.tianzhi.shop520.util.Constants;
import com.tianzhi.shop520.util.LogUtils;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.tianzhi.shop520.R.id.receiver_name;
import static com.tianzhi.shop520.R.id.tv_remind_delivery;

/**
 * Created by thinkpad on 2017/11/1.
 * 订单详情
 */

public class OrderInfoAct extends BaseFragmentActivity {
    @BindView(R.id.tv_order_state)
    TextView tvOrderState;
    @BindView(R.id.tv_logistic_info)
    TextView tvLogisticInfo;
    @BindView(receiver_name)
    TextView receiverName;
    @BindView(R.id.receiver_phone)
    TextView receiverPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.rcl_order_info)
    RecyclerView rclOrderInfo;
    @BindView(R.id.tv_trans_price)
    TextView tvTransPrice;
    @BindView(R.id.tv_price)
    TextView tvPrice;

//    @BindView(R.id.btn_gonext)
//    TextView btnGonext;

    String orderid;
    OrderInfo orderInfo;
    OrderShipping orderShipping;
    @BindView(R.id.orderinfo_scrlv)
    ScrollView orderinfoScrlv;
    @BindView(R.id.tv_order_info)
    TextView tvOrderInfo;
    @BindView(R.id.tv_check_logistics)
    TextView tvCheckLogistics;
    @BindView(R.id.tv_sure_revice)
    TextView tvSureRevice;
    @BindView(R.id.tv_acncel_order)
    TextView tvAcncelOrder;
    @BindView(R.id.tv_btnto_pay)
    TextView tvBtntoPay;
    @BindView(R.id.tv_anginto_buy)
    TextView tvAngintoBuy;
    @BindView(tv_remind_delivery)
    TextView tvRemindDelivery;
    @BindView(R.id.tv_change_goods)
    TextView tvChangeGoods;
    private PopupWindow mAdressPopup;// 地区选择器
    TextView tv_pop_message;
    TextView text_colse_pop;
    TextView btn_Service;
    private EnorderEntity enorderEntity;//确认订单  接口返回
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.act_orderinfo);
        initView();
        initPop();
    }

    @Override
    protected void initView() {
        setActivityTitle("订单详情");
        orderid = paras.getString("orderid");
        LogUtils.e("订单详情订单号", orderid);
        queryOrderById();
    }


    private void queryOrderById() {
        LogUtils.e("订单号", orderid);
        LogUtils.e("订单号userId", AppShared.getInstance(context).getLoginInfo().id);
        LogUtils.e("订单号userToken", AppShared.getInstance(context).getLoginInfo().userToken);
        showLoading();
        OkGo.post(BaseConstant.OrderUrl + BaseConstant.QUERYORDERBYID)
                .params("orderId", orderid)
                .params("userId", AppShared.getInstance(context).getLoginInfo().id)
                .params("userToken", AppShared.getInstance(context).getLoginInfo().userToken)
                .execute(new DialogCallback<BaseResponse<OrderInfo>>(this, false) {
                    @Override
                    public void onSuccess(BaseResponse<OrderInfo> orderInfoBaseResponse, Call call, Response response) {
                        super.onSuccess(orderInfoBaseResponse, call, response);
                        dismissLoading();
                        orderInfo = orderInfoBaseResponse.data;
                        orderShipping = orderInfo.orderShipping;
                        orderinfoScrlv.setVisibility(View.VISIBLE);
                        if ("1".equals(orderInfo.getStatus())) {//
                            setGone();
                            tvOrderState.setText("未付款");
                            tvOrderState.setVisibility(View.VISIBLE);
                            tvAcncelOrder.setVisibility(View.VISIBLE);
                            tvBtntoPay.setVisibility(View.VISIBLE);
                        } else if ("2".equals(orderInfo.getStatus())) {
                            setGone();
                            tvOrderState.setText("待发货");
                            tvOrderState.setVisibility(View.VISIBLE);
                            tvRemindDelivery.setVisibility(View.VISIBLE);
                        } else if ("3".equals(orderInfo.getStatus())) {
                            setGone();
                            tvOrderState.setText("已发货");
                            tvOrderState.setVisibility(View.VISIBLE);
                            tvCheckLogistics.setVisibility(View.VISIBLE);
                            tvSureRevice.setVisibility(View.VISIBLE);
                            tvChangeGoods.setVisibility(View.VISIBLE);
                            tvLogisticInfo.setVisibility(View.VISIBLE);
                        } else if ("4".equals(orderInfo.getStatus())) {
                            setGone();
                            tvOrderState.setText("交易成功");
                            tvOrderState.setVisibility(View.VISIBLE);
                            tvAngintoBuy.setVisibility(View.VISIBLE);
                        } else if ("5".equals(orderInfo.getStatus())) {
                            setGone();
                            tvOrderState.setText("交易关闭");
                            tvOrderState.setVisibility(View.VISIBLE);
                            tvAngintoBuy.setVisibility(View.VISIBLE);
                        } else if ("6".equals(orderInfo.getStatus())) {
                            setGone();
                            tvOrderState.setText("已退款");
                            tvOrderState.setVisibility(View.VISIBLE);
                            tvAngintoBuy.setVisibility(View.VISIBLE);
                        }//028-65777367
                        if (!TextUtils.isEmpty(orderInfo.id) && !TextUtils.isEmpty(orderInfo.createdTime)) {
                            tvOrderInfo.setText("订单编号：" + orderInfo.id + "\n" + "创建时间：" + orderInfo.createdTime + "\n");
                            if (!TextUtils.isEmpty(orderInfo.paymentTime)) {
                                tvOrderInfo.setText("订单编号：" + orderInfo.id + "\n" + "创建时间：" + orderInfo.createdTime + "\n"
                                        + "付款时间：" + orderInfo.paymentTime);
                            }
                            if (!TextUtils.isEmpty(orderInfo.consignTime)) {
                                tvOrderInfo.setText("订单编号：" + orderInfo.id + "\n" + "创建时间：" + orderInfo.createdTime + "\n"
                                        + "付款时间：" + orderInfo.paymentTime + "\n" + "发货时间：" + orderInfo.consignTime);
                            }
                        }
                        setData();
                        initAdapter();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dismissLoading();
                    }
                });
//               .execute(new StringCallback() {
//                   @Override
//                   public void onSuccess(String s, Call call, Response response) {
//                       LogUtils.e("订单详情",s);
//                   }
//               });
    }

    /*设置adapter*/
    private void initAdapter() {
        rclOrderInfo.setLayoutManager(new LinearLayoutManager(context));
        OrderInfoAdp ensureOrderAdp = new OrderInfoAdp(R.layout.item_enorder, orderInfo.getOrderItems());
        ensureOrderAdp.openLoadAnimation();
        ensureOrderAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                paras.putString(Constants.GOODSID, orderInfo.getOrderItems().get(position).getItemId());
                goNext(GoodsInfoAct.class, paras, false);
            }
        });
        rclOrderInfo.setAdapter(ensureOrderAdp);
    }

    public void setData() {
        receiverName.setText("收货人：" + orderShipping.getReceiverName());
        receiverPhone.setText(orderShipping.getReceiverMobile());
        tvAddress.setText("收货人地址：" + orderShipping.getReceiverState() + orderShipping.getReceiverCity()
                + orderShipping.getReceiverDistrict() + orderShipping.getReceiverAddress());
        tvTransPrice.setText("￥" + orderInfo.postFee + "");
        tvPrice.setText("￥" + orderInfo.payment);

    }

    //按钮全部隐藏
    private void setGone() {
        tvOrderState.setVisibility(View.GONE);
        tvCheckLogistics.setVisibility(View.GONE);
        tvAcncelOrder.setVisibility(View.GONE);
        tvSureRevice.setVisibility(View.GONE);
        tvAngintoBuy.setVisibility(View.GONE);
        tvBtntoPay.setVisibility(View.GONE);
        tvRemindDelivery.setVisibility(View.GONE);
        tvLogisticInfo.setVisibility(View.GONE);
    }

    /*请求接口  取消订单*/
    private void cancelOrder(final String id,final String state) {
        showLoading();
        OkGo.post(BaseConstant.OrderUrl + BaseConstant.ALERTORDERSTATUS)
                .params("userId", AppShared.getInstance(context).getLoginInfo().id)
                .params("orderId", id)
                .params("status", state)
                .params("userToken", AppShared.getInstance(context).getLoginInfo().userToken)
                .execute(new DialogJsonCallback<BaseResponseData>(this,false) {
                    @Override
                    public void onSuccess(BaseResponseData baseResponseData, Call call, Response response) {
                       dismissLoading();
                        if ("200".equals(baseResponseData.flag)) {
                            finish();
                        } else {
                            toast(baseResponseData.msg);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dismissLoading();
                    }
                });
    }
/*订单状态：  0、取消订单，1、未付款，2、已付款待发货、3、已发货，4、交易成功，5、交易关闭,6、已退款.7 已删除*/
    @OnClick({R.id.tv_check_logistics, R.id.tv_sure_revice, R.id.tv_acncel_order,
            R.id.tv_btnto_pay, R.id.tv_anginto_buy, R.id.tv_remind_delivery,R.id.tv_change_goods})
    public void onViewClicked(View view) {
        AlertDialog alert;
        switch (view.getId()) {
            case R.id.tv_check_logistics://查看物流
                paras.putString("com",orderShipping.com);
                paras.putString("shippingCode",orderShipping.shippingCode);
                goNext(ShippingAct.class,paras,false);
                break;
            case R.id.tv_sure_revice://确认收货
                alert = new AlertDialog.Builder(context).create();
//                alert.setTitle("操作提示");
                alert.setMessage("确认收货？");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                cancelOrder(orderInfo.getId(),"4");
                            }
                        });
                alert.show();
                break;
            case R.id.tv_acncel_order://取消订单
                alert = new AlertDialog.Builder(context).create();
//                alert.setTitle("操作提示");
                alert.setMessage("您确定要取消该订单吗？");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                cancelOrder(orderInfo.getId(),"0");
                            }
                        });
                alert.show();

                break;
            case R.id.tv_btnto_pay:
                paras.putString("orderId", orderInfo.getId());
                goNext(PayAct.class, paras, false);
                break;
            case R.id.tv_anginto_buy://再次购买
                ArrayList<orderListItem> orderListItems = new ArrayList<>();
                for (int i = 0;i<orderInfo.getOrderItems().size();i++){
                    orderListItem orderListItem = new orderListItem();
                    orderListItem.setId(orderInfo.getOrderItems().get(i).getItemId());
                    orderListItem.setNum(orderInfo.getOrderItems().get(i).getNum());
                    orderListItems.add(orderListItem);
                }
//                paras.putSerializable(Constants.CHECKEDLIST,orderListItems);
                validateOrder(orderListItems);


                break;
            case R.id.tv_remind_delivery:
                toast("已提醒发货！");
                break;
            case R.id.tv_change_goods://申请换货
                showAddrPop(false ,3);
                break;
        }
    }

    /*确认订单*/
    private void validateOrder( ArrayList<orderListItem> cartlistitems){
        Gson gson =new Gson();
        String obj = gson.toJson(cartlistitems);
        LogUtils.e("确认订单参数",obj.toString());
        LogUtils.e("确认订单参数userId",AppShared.getInstance(context).getLoginInfo().id.toString());
        LogUtils.e("确认订单参数userToken",AppShared.getInstance(context).getLoginInfo().userToken);
        showLoading();
        OkGo.post(BaseConstant.OrderUrl+BaseConstant.VALIDATEORDER)
                .params("userId", AppShared.getInstance(context).getLoginInfo().id)
                .params("userToken",AppShared.getInstance(context).getLoginInfo().userToken)
                .params("items",obj)
                .params("type","1")
                .execute(new DialogCallback<BaseResponse<EnorderEntity>>(this,false) {
                    @Override
                    public void onSuccess(BaseResponse<EnorderEntity> enorderEntityBaseResponse, Call call, Response response) {
                        super.onSuccess(enorderEntityBaseResponse, call, response);
                        dismissLoading();
                        enorderEntity =  enorderEntityBaseResponse.data;
                        paras.putSerializable("enorderEntity",(Serializable) enorderEntity);
                        paras.putString("type","1");
                        goNext(EnsureOrderAct.class, paras, false);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dismissLoading();
                    }
                });
    }
    private void initPop() {
        // TODO 弹出底部popwindow 进行选择
        mAdressPopup = new PopupWindow();
        View popView = null;
        popView = LayoutInflater.from(context).inflate(R.layout.pop_message, null);
        tv_pop_message = (TextView) popView.findViewById(R.id.tv_pop_message);
        text_colse_pop = (TextView) popView.findViewById(R.id.text_colse_pop);
        btn_Service = (TextView)popView.findViewById(R.id.btn_Service);
        text_colse_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdressPopup.dismiss();
            }
        });
        btn_Service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+"02865777367"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
//                Intent intent = new Intent(Intent.ACTION_CALL, Uri
//                        .parse("tel:02869410086"));
//                startActivity(intent);
                finish();
            }
        });
        mAdressPopup.setContentView(popView);
        mAdressPopup.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mAdressPopup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mAdressPopup.setTouchable(true);
        mAdressPopup.setFocusable(true);
        mAdressPopup.setOutsideTouchable(true);
        mAdressPopup.setBackgroundDrawable(new ColorDrawable(0x00000000));
//		mAdressPopup.setAnimationStyle(R.style.AnimBottom);
        // 当PopupWindow消失时,恢复其为原来的颜色
        mAdressPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // popupwindow消失的时候恢复成原来的透明度
//				backgroundAlpha(1f);
            }
        });
    }

    private void showAddrPop(boolean isHaveDefault,int choice) {
        if (mAdressPopup.isShowing()) {
            mAdressPopup.dismiss();// 关闭
        } else {
            mAdressPopup.showAtLocation(OrderInfoAct.this.findViewById(R.id.orderinfo_scrlv),
                    Gravity.CENTER, 10, 20);// 显示
//			backgroundAlpha(0.7f);
        }
    }

}

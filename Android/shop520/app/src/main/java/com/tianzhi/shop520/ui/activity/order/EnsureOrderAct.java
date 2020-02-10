package com.tianzhi.shop520.ui.activity.order;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.tianzhi.shop520.APPLICATION;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.callback.DialogCallback;
import com.tianzhi.shop520.callback.DialogJsonCallback;
import com.tianzhi.shop520.entity.BaseResponse;
import com.tianzhi.shop520.entity.BaseResponseData;
import com.tianzhi.shop520.entity.order.CreatOrderInfo;
import com.tianzhi.shop520.entity.order.EnorderEntity;
import com.tianzhi.shop520.entity.shop.orderListItem;
import com.tianzhi.shop520.entity.store.ReceiverInfo;
import com.tianzhi.shop520.ui.activity.login.LoginAct;
import com.tianzhi.shop520.ui.activity.shop.AddressManageAct;
import com.tianzhi.shop520.ui.activity.shop.PayAct;
import com.tianzhi.shop520.ui.adapter.EnsureOrderAdp;
import com.tianzhi.shop520.util.AppShared;
import com.tianzhi.shop520.util.BaseConstant;
import com.tianzhi.shop520.util.Constants;
import com.tianzhi.shop520.util.LogUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.tianzhi.shop520.R.id.goods_totalnum;
import static com.tianzhi.shop520.R.id.pay_price;

/**
 * Created by thinkpad on 2017/10/24.
 * 确认订单
 */

public class EnsureOrderAct extends BaseFragmentActivity {

    @BindView(R.id.goto_address)
    TextView gotoAddress;
    @BindView(R.id.enorder_rcl)
    RecyclerView enorderRcl;
    @BindView(goods_totalnum)
    TextView goodsTotalnum;
    @BindView(pay_price)
    TextView payPrice;
    @BindView(R.id.tran_price)
    TextView tranPrice;
    @BindView(R.id.discount_num)
    TextView discountNum;
    @BindView(R.id.use_heart)
    EditText useHeart;
    @BindView(R.id.reality_price)
    TextView realityPrice;
    @BindView(R.id.sure_tobuy)
    TextView sureTobuy;
    //        private ArrayList<CarInfoEntity> checkedList;
    private final String activityName = "EnsureOrderAct";
    @BindView(R.id.rl_no_address)
    RelativeLayout rlNoAddress;
    @BindView(R.id.receiver_name)
    TextView receiverName;
    @BindView(R.id.receiver_phone)
    TextView receiverPhone;
    @BindView(R.id.receiver_address)
    TextView receiverAddress;
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
    @BindView(R.id.et_use_heart)
    TextView etUseHeart;
    @BindView(R.id.text1)
    TextView text1;
    private EnorderEntity enorderEntity;
    private ReceiverInfo addressInfo;
    ReceiverInfo receiverInfo;//选择的地址
    private ArrayList<ReceiverInfo> addresslist;
    private String type;//购买类型，商城购买1   会员升级套餐0
    int userHeart;//输入的爱心值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.act_ensureorder);
        initView();
    }

    private void setData() {
        if (enorderEntity.getLoveValue() <= 0) {
            useHeart.setVisibility(View.GONE);
            etUseHeart.setVisibility(View.GONE);

        }
        goodsTotalnum.setText("共计" + enorderEntity.getTotalNum() + "件商品");
        payPrice.setText("￥" + enorderEntity.getPayment() + "");
        tranPrice.setText(enorderEntity.getPostFee() + "");
        discountNum.setText(enorderEntity.getDiscountDesc());
        text1.setText(enorderEntity.getLoveValue()+"点");
        useHeart.setHint(enorderEntity.getLoveValue() + "");
        realityPrice.setText(enorderEntity.getPayment() + "元");
    }

    @Override
    protected void initView() {
//        checkedList = (ArrayList<CarInfoEntity>) paras.getSerializable(Constants.CHECKEDLIST);
        enorderEntity = (EnorderEntity) paras.getSerializable("enorderEntity");
        type = paras.getString("type");
        setActivityTitle("确认订单");
        setData();
        useHeart.addTextChangedListener(Watcher);
        initAdapter();

        setAddress();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //刷新地址
        getAddressList();
    }

    private void getAddressList() {
        OkGo.post(BaseConstant.OrderUrl + BaseConstant.LISTRECEIVERINFO)
                .params("userId", AppShared.getInstance(context).getLoginInfo().id)
                .params("userToken", AppShared.getInstance(context).getLoginInfo().userToken)
                .execute(new DialogCallback<BaseResponse<ArrayList<ReceiverInfo>>>(this, true) {
                    @Override
                    public void onSuccess(BaseResponse<ArrayList<ReceiverInfo>> arrayListBaseResponse, Call call, Response response) {
                        super.onSuccess(arrayListBaseResponse, call, response);
                        addresslist = arrayListBaseResponse.data;
                        if (null == addresslist || addresslist.size() <= 0) {
                            rlNoAddress.setVisibility(View.VISIBLE);
                            rlAddress.setVisibility(View.GONE);
                        }
                    }
                });
    }

    public TextWatcher Watcher = new TextWatcher() {
        private CharSequence t;
        private int Start;
        private int End;

        @SuppressWarnings("deprecation")
        @SuppressLint("NewApi")
        @Override
        public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
            t = s;
            double num = 0;
            Message msg = new Message();
            msg.what = 0;
            if (t.length() > 0) {
                String etnum = useHeart.getText().toString().trim();
                if (!TextUtils.isEmpty(etnum)) {
                    num = Double.parseDouble(etnum);
                }
                if (num > enorderEntity.getLoveValue()) {
                    Toast.makeText(context, "抵扣的爱心值不能超过剩余爱心值", Toast.LENGTH_SHORT).show();
                    useHeart.setText(enorderEntity.getLoveValue() + "");
                    realityPrice.setText(enorderEntity.getPayment() - enorderEntity.getLoveValue() + "元");
                }
                if (num > enorderEntity.getPayment()) {
                    Toast.makeText(context, "抵扣的爱心值不能超过商品价格", Toast.LENGTH_SHORT).show();
                    String str = String.valueOf(enorderEntity.getPayment());
                    int m = Integer.parseInt(str.split("\\.")[1].toString());
                    if (m > 0) {
                        useHeart.setText((int) enorderEntity.getPayment() + 1 + "");
                        realityPrice.setText("0 元");

                    } else {
                        useHeart.setText((int) enorderEntity.getPayment() + "");
                        realityPrice.setText("0元");
                    }
//                    mHandler.sendMessage(msg);
                }
//                mHandler.sendMessage(msg);
                if (num > 0 && (double) num < enorderEntity.getLoveValue()) {
                    if (num > enorderEntity.getPayment()) {
                        realityPrice.setText("0元");
                    } else {
                        realityPrice.setText(enorderEntity.getPayment() - num + "元");
                    }
                }
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }

        @Override
        public void afterTextChanged(Editable s) {
            double num = 0;
            Message msg = new Message();
            msg.what = 0;
            Start = useHeart.getSelectionStart();
            End = useHeart.getSelectionEnd();
            String etnum = s.toString().trim();

            if (!TextUtils.isEmpty(etnum))
                num = Double.parseDouble(etnum);
            if (t.length() == 1 && etnum.equals("0")) {
                s.clear();
//                mHandler.sendMessage(msg);
            }
            if (enorderEntity.getLoveValue() < num) {
                useHeart.setText(enorderEntity.getLoveValue() + "");
//                mHandler.sendMessage(msg);
            }
//            mHandler.sendMessage(msg);
//            if (num >0 &&(double) num < enorderEntity.getLoveValue()) {
//                realityPrice.setText(enorderEntity.getPayment() - num + "元");
//            }
//            if (enorderEntity.getPayment()<num  ) {
//                String str = String.valueOf(enorderEntity.getPayment());
//                int m = Integer.parseInt(str.split("\\.")[1].toString());
//                if(m>0){
//                    useHeart.setText((int)enorderEntity.getPayment()+1+"");
//                }
//                useHeart.setText((int)enorderEntity.getPayment()+"");
//            }
        }
    };

    /*设置收货地址*/
    private void setAddress() {
        addressInfo = enorderEntity.getReceiverinfo();
        if (null != addressInfo) {
            rlAddress.setVisibility(View.VISIBLE);
            rlNoAddress.setVisibility(View.GONE);
            receiverName.setText(addressInfo.getReceiverName());
            String address = addressInfo.getReceiverState() + addressInfo.getReceiverCity()
                    + addressInfo.getReceiverDistrict() + addressInfo.getReceiverAddress();
            String address2 = addressInfo.getReceiverCity()
                    + addressInfo.getReceiverDistrict() + addressInfo.getReceiverAddress();
            receiverAddress.setText(addressInfo.getReceiverState().equals(addressInfo.getReceiverCity()) ? address2 : address);
            receiverPhone.setText(addressInfo.getReceiverMobile());
            receiverInfo = addressInfo;
        } else {
            rlNoAddress.setVisibility(View.VISIBLE);
            rlAddress.setVisibility(View.GONE);
        }
    }


    /*设置adapter*/
    private void initAdapter() {
        enorderRcl.setLayoutManager(new LinearLayoutManager(context));
        EnsureOrderAdp ensureOrderAdp = new EnsureOrderAdp(R.layout.item_enorder, enorderEntity.getOrderItemList());
        ensureOrderAdp.openLoadAnimation();
        enorderRcl.setAdapter(ensureOrderAdp);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 200) {
            receiverInfo = (ReceiverInfo) data.getSerializableExtra(Constants.RECEIVERINFO);
            if (requestCode == 100 && null != receiverInfo) {
                rlAddress.setVisibility(View.VISIBLE);
                rlNoAddress.setVisibility(View.GONE);
            }
            receiverName.setText(receiverInfo.getReceiverName());
            String address = receiverInfo.getReceiverState() + receiverInfo.getReceiverCity()
                    + receiverInfo.getReceiverDistrict() + receiverInfo.getReceiverAddress();
            String address2 = receiverInfo.getReceiverCity()
                    + receiverInfo.getReceiverDistrict() + receiverInfo.getReceiverAddress();
            receiverAddress.setText(receiverInfo.getReceiverState().equals(receiverInfo.getReceiverCity()) ? address2 : address);
            receiverPhone.setText(receiverInfo.getReceiverMobile());
        }

    }


    @OnClick({R.id.goto_address, R.id.sure_tobuy, R.id.rl_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.goto_address:
                if (!APPLICATION.isLogin) {
                    goNext(LoginAct.class);
                    return;
                }
                paras.putString(Constants.GOACTITYNAME, "EnsureOrderAct");
                invokeActivity(AddressManageAct.class, null, paras, 100);
//                goNext(AddressManageAct.class);
                break;
            case R.id.sure_tobuy:
                //判断有没有添加收货地址
                if (rlNoAddress.getVisibility() == View.VISIBLE) {
                    toast("请填写收货地址");
                    return;
                }
                if (!APPLICATION.isLogin) {
                    goNext(LoginAct.class);
                    return;
                }
                createOrder();
                break;
            case R.id.rl_address:
                if (!APPLICATION.isLogin) {
                    goNext(LoginAct.class);
                    return;
                }
                paras.putString(Constants.GOACTITYNAME, "EnsureOrderAct");
                invokeActivity(AddressManageAct.class, null, paras, 200);
//                goNext(AddressManageAct.class);
                break;
        }
    }

    /*生成订单*/
    private void createOrder() {
        CreatOrderInfo creatOrderInfo = new CreatOrderInfo();
        if (TextUtils.isEmpty(useHeart.getText().toString())) {
            userHeart = 0;
        } else {
            userHeart = Integer.parseInt(useHeart.getText().toString());
        }

        creatOrderInfo.setPayment(enorderEntity.getPayment() - userHeart);
        creatOrderInfo.setBuyerNick(AppShared.getInstance(context).getLoginInfo().userNick);
        //设置要抵扣的爱心值
        creatOrderInfo.setLoveValue(userHeart);
//        creatOrderInfo.setPaymentType("1");
        creatOrderInfo.setBuyerMessage("");
        creatOrderInfo.setOrderShipping(receiverInfo);
        ArrayList<orderListItem> orderListItems = new ArrayList<>();
        for (int i = 0; i < enorderEntity.getOrderItemList().size(); i++) {
            orderListItem orderListItem = new orderListItem();
            orderListItem.setId(enorderEntity.getOrderItemList().get(i).getItemId());
            orderListItem.setNum(enorderEntity.getOrderItemList().get(i).getNum());
            orderListItems.add(orderListItem);
        }
        creatOrderInfo.setOrderItems(orderListItems);
        Gson gson = new Gson();
        String obj = gson.toJson(creatOrderInfo);
        LogUtils.e("生成订单userId", AppShared.getInstance(context).getLoginInfo().id);
        LogUtils.e("生成订单orderToken", enorderEntity.getOrderToken());
        LogUtils.e("生成订单userToken", AppShared.getInstance(context).getLoginInfo().userToken);
        LogUtils.e("生成订单orderInfo", obj);
        LogUtils.e("生成订单type", "1");
        showLoading();
        OkGo.post(BaseConstant.OrderUrl + BaseConstant.CREATEORDER)
                .params("userId", AppShared.getInstance(context).getLoginInfo().id)
                .params("orderToken", enorderEntity.getOrderToken())
                .params("userToken", AppShared.getInstance(context).getLoginInfo().userToken)
                .params("orderInfo", obj)
                .params("type", type)
                .execute(new DialogJsonCallback<BaseResponseData>(this,false) {
                    @Override
                    public void onSuccess(BaseResponseData baseResponseData, Call call, Response response) {
                        dismissLoading();
                        LogUtils.e("生成订单", baseResponseData.toString());
                        if ("200".equals(baseResponseData.flag)) {
                            if (!TextUtils.isEmpty(useHeart.getText().toString())
                                    && Integer.parseInt(useHeart.getText().toString()) >= enorderEntity.payment) {
                                goNext(MyorderAct.class, true);
                            }else {
                            paras.putString("orderId", baseResponseData.data);
                            goNext(PayAct.class, paras, true);
                            }
                        } else {
                            toast(baseResponseData.data);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dismissLoading();
                    }
                });
    }

}

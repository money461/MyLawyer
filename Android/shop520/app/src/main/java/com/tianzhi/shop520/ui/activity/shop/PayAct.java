package com.tianzhi.shop520.ui.activity.shop;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.lzy.okgo.OkGo;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.callback.DialogCallback;
import com.tianzhi.shop520.callback.DialogJsonCallback;
import com.tianzhi.shop520.entity.BaseResponse;
import com.tianzhi.shop520.entity.BaseResponseData;
import com.tianzhi.shop520.entity.order.WXmodel;
import com.tianzhi.shop520.ui.activity.order.MyorderAct;
import com.tianzhi.shop520.util.AppShared;
import com.tianzhi.shop520.util.BaseConstant;
import com.tianzhi.shop520.util.LogUtils;
import com.tianzhi.shop520.util.PayResult;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.tianzhi.shop520.R.id.ckb_wx;

/**
 * Created by thinkpad on 2017/10/20.
 * 支付界面
 */

public class PayAct extends BaseFragmentActivity {
    String pay_type;//支付类型，值：alipay 支付宝支付 wxpay 微信支付
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    @BindView(R.id.weixin_pay)
    ImageView weixinPay;
    @BindView(ckb_wx)
    CheckBox ckbWx;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.zhifubao_pay)
    ImageView zhifubaoPay;
    @BindView(R.id.ckb_zfb)
    CheckBox ckbZfb;
    @BindView(R.id.relativeLayout2)
    RelativeLayout relativeLayout2;
    @BindView(R.id.tv_topay)
    TextView tvTopay;
    private String orderId;
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    LogUtils.e("支付订单", resultInfo.toString());
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        goNext(PaySuccessAct.class, paras, true);
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.act_pay);
        //设置沙箱环境
//        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        initView();
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goNext(MyorderAct.class, true);
            }
        });
    }

    protected void initView() {
        orderId = paras.getString("orderId");
        setActivityTitle("支付");
    }

    private void aliPay() {
        showLoading();
        OkGo.post(BaseConstant.TestUrl + BaseConstant.ZFBAPPALIPAY)
                .tag(this)
                .params("userId", AppShared.getInstance(context).getLoginInfo().id)
                .params("userToken", AppShared.getInstance(context).getLoginInfo().userToken)
                .params("orderId", orderId)
                .execute(new DialogJsonCallback<BaseResponseData>(this, false) {
                    @Override
                    public void onSuccess(BaseResponseData baseResponseData, Call call, Response response) {
                        dismissLoading();
                        LogUtils.e("支付信息：", baseResponseData.toString());
                        startPayRunable(baseResponseData.data);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dismissLoading();
                    }
                });
    }

    private void weixinPay() {
        final IWXAPI api = WXAPIFactory.createWXAPI(context, null);
        showLoading();
        OkGo.post(BaseConstant.TestUrl + BaseConstant.WECHATAPPPAY)
                .tag(this)
                .params("userId", AppShared.getInstance(context).getLoginInfo().id)
                .params("userToken", AppShared.getInstance(context).getLoginInfo().userToken)
                .params("orderId", orderId)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        LogUtils.e("微信支付返回", s);
//                    }
//                });
                .execute(new DialogCallback<BaseResponse<WXmodel>>(this, false) {
                    @Override
                    public void onSuccess(BaseResponse<WXmodel> wXmodelBaseResponse, Call call, Response response) {
                        dismissLoading();
                        if (wXmodelBaseResponse.data != null) {
                            // 判断是否安装客户端
                            if (!api.isWXAppInstalled() && !api.isWXAppSupportAPI()) {
                                toast("请您先安装微信客户端！");
                                return;
                            }
                            // 将该app注册到微信
                            api.registerApp(wXmodelBaseResponse.data.appid);
                            PayReq request = new PayReq();
                            request.appId = wXmodelBaseResponse.data.appid;
                            request.partnerId = wXmodelBaseResponse.data.partnerid;
                            request.prepayId = wXmodelBaseResponse.data.prepayid;
                            request.packageValue = wXmodelBaseResponse.data.packagestr;
                            request.nonceStr = wXmodelBaseResponse.data.noncestr;
                            request.timeStamp = wXmodelBaseResponse.data.timestamp;
                            request.sign = wXmodelBaseResponse.data.paySign;
                            // 调用微信SDK，发起支付，回调WxPayEntryActivity
                            api.sendReq(request);

                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dismissLoading();
                    }
                });
    }

    private void startPayRunable(final String orderInfo) {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(PayAct.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            goNext(MyorderAct.class, true);
        }
        return false;
    }

    @OnClick({ckb_wx, R.id.ckb_zfb, R.id.tv_topay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case ckb_wx:
                if (ckbZfb.isChecked()) {
                    ckbZfb.setChecked(false);
                }
                ckbWx.setChecked(true);
                break;
            case R.id.ckb_zfb:
                if (ckbWx.isChecked()) {
                    ckbWx.setChecked(false);
                }
                ckbZfb.setChecked(true);
                break;
            case R.id.tv_topay:
                if (ckbZfb.isChecked()) {
                    aliPay();
                } else if (ckbWx.isChecked()) {
                    LogUtils.e("选择微信", "选择微信支付");
                    weixinPay();
                } else {
                    toast("请选择支付方式");
                }
                break;
        }
    }
}
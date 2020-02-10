package com.tianzhi.shop520.ui.activity.personal;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.entity.login.UserInfo;
import com.tianzhi.shop520.ui.diyview.BaseImageView;
import com.tianzhi.shop520.util.ACache;
import com.tianzhi.shop520.util.AppShared;
import com.tianzhi.shop520.util.Constants;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by thinkpad on 2017/10/24.
 * 爱心值提取
 */

public class HeartExtractAct extends BaseFragmentActivity {
    String loveSurplus;
    String status;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    @BindView(R.id.iv_head)
    BaseImageView ivHead;
    @BindView(R.id.personal_name)
    TextView personalName;
    @BindView(R.id.btn_extract)
    TextView btnExtract;
    @BindView(R.id.heart_record)
    RelativeLayout heartRecord;
    UserInfo userInfo;
    ACache aCache;
    @BindView(R.id.user_state)
    TextView userState;
    @BindView(R.id.tv_loveSurplus)
    TextView tvLoveSurplus;
    private Dialog dialog;
    private Dialog extractDialog;
    @SuppressLint("HandlerLeak")
//    private Handler mHandler = new Handler() {
//        @SuppressWarnings("unused")
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//
//                case SDK_AUTH_FLAG: {
//                    @SuppressWarnings("unchecked")
//                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
//                    String resultStatus = authResult.getResultStatus();
//
//                    // 判断resultStatus 为“9000”且result_code
//                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
//                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
//                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
//                        // 传入，则支付账户为该授权账户
//                        Toast.makeText(HeartExtractAct.this,
//                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
//                                .show();
//                        LogUtils.e("授权成功：", authResult.toString());
//                        /*authCode  传给后台*/
//                        String restult = authResult.getResult();
//                        String[] items = restult.split("&");
//                        alipayLoginAuthNotify(authResult.getAuthCode(), items[6].toString().substring(8, items[6].toString().length()));
//                        LogUtils.e("授权id", items[6].toString().substring(8, items[6].toString().length()));
//
//                    } else {
//                        // 其他状态值则为授权失败
//                        Toast.makeText(HeartExtractAct.this,
//                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();
//
//                    }
//                    break;
//                }
//                default:
//                    break;
//            }
//        }
//    };
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.act_heratextract);
        context = this;
        aCache = ACache.get(context);
        initView();
    }

    @Override
    protected void initView() {
        setActivityTitle("爱心值提取");
        loveSurplus = paras.getString(Constants.lovesurplus);
        status = paras.getString(Constants.STATUS);
        if(null!=(UserInfo) aCache.getAsObject(Constants.USERINFO)){
            userInfo=(UserInfo) aCache.getAsObject(Constants.USERINFO);
        }else
            userInfo = AppShared.getInstance(context).getLoginInfo();
        if (null != userInfo) {
            personalName.setText(userInfo.userNick);
            Glide.with(context).load(userInfo.headUrl).into(ivHead);
            if ("0".equals(userInfo.type)) {
                userState.setText("普通用户");
            } else if ("1".equals(userInfo.type)) {
                userState.setText("会员");
            } else if ("2".equals(userInfo.type)) {
                userState.setText("爱心会员");
            } else if ("3".equals(userInfo.type)) {
                userState.setText("爱心合伙人");
            } else if ("4".equals(userInfo.type)) {
                userState.setText("城市爱心合伙人");
            }
             /*用户头像*/
            if (!TextUtils.isEmpty(userInfo.headUrl)) {
                Glide.with(context).load(userInfo.headUrl).into(ivHead);
            }else {
                if (!TextUtils.isEmpty(userInfo.userSex)) {
                    if ("0".equals(userInfo.userSex)) {
                        Glide.with(context).load(R.drawable.user_default).into(ivHead);
                    } else if ("1".equals(userInfo.userSex)) {
                        Glide.with(context).load(R.drawable.user_default_avatar_women).into(ivHead);
                    }
                }
            }
             /*可用爱心值*/
            if (!TextUtils.isEmpty(userInfo.loveSurplus)) {
                tvLoveSurplus.setText("可用爱心值：" + userInfo.loveSurplus + "点");
            }
        }
    }
//
//    /*支付宝登录授权信息回调*/
//    public void alipayLoginAuthNotify(String zfbcode, String zfbuserid) {
//        OkGo.post(BaseConstant.TestUrl + BaseConstant.ALIPAYLOGINAUTHNOTIFY)
//                .params("userId", AppShared.getInstance(context).getLoginInfo().id)
//                .params("userToken", AppShared.getInstance(context).getLoginInfo().userToken)
//                .params("zfbAuthCode", zfbcode)
//                .params("zfbUserId", zfbuserid)
//                .execute(new StringDialogCallback(this) {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        LogUtils.e("支付宝授权回调：", s);
//                        goNext(HeartExtractToZfb.class);
//
////                        showDialog();
//                    }
//                });
//    }

//    /*支付宝授权*/
//    public void alipayLogin() {//alipayLoginAuth
//        OkGo.post(BaseConstant.TestUrl + BaseConstant.ALIPAYLOGINAUTH)
//                .params("userId", AppShared.getInstance(context).getLoginInfo().id)
//                .params("userToken", AppShared.getInstance(context).getLoginInfo().userToken)
//                .execute(new DialogJsonCallback<BaseResponseData>(this) {
//                    @Override
//                    public void onSuccess(BaseResponseData baseResponseData, Call call, Response response) {
//                        LogUtils.e("授权信息：", baseResponseData.toString());
//                        startAuthRunable(baseResponseData.data);
//                    }
//                });
//    }


//    private void startAuthRunable(final String authInfo) {
//        Runnable authRunnable = new Runnable() {
//
//            @Override
//            public void run() {
//                // 构造AuthTask 对象
//                AuthTask authTask = new AuthTask(HeartExtractAct.this);
//                // 调用授权接口，获取授权结果
//                Map<String, String> result = authTask.authV2(authInfo, true);
//                Message msg = new Message();
//                msg.what = SDK_AUTH_FLAG;
//                msg.obj = result;
//                mHandler.sendMessage(msg);
//            }
//        };
//
//        // 必须异步调用
//        Thread authThread = new Thread(authRunnable);
//        authThread.start();
//    }

    @OnClick({R.id.btn_extract, R.id.heart_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_extract:
                //判断剩余爱心值>0  账户是否被冻结
//                if (Integer.parseInt(loveSurplus) <= 0) {
//                    toast("您没有爱心值可提取");
//                    return;
//                } else if (!"1".equals(status)) {
//                    toast("您的账户已冻结！");
//                    return;
//                }
                goNext(HeartExtractToZfb.class);
//                alipayLogin();
                break;
            case R.id.heart_record:
                goNext(HeartRecordAct.class);

                break;
        }
    }

    /**
     * 支付宝授权弹窗
     */
    protected void showDialog() {
        if (dialog != null) {
            dialog.show();
            return;
        }
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dialog_zfb);
        dialog.show();
        Button btn_next = (Button) dialog.getWindow().findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
            }
        });
        Button btn_login = (Button) dialog.getWindow().findViewById(R.id.btn_extract);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
                showHeartDialog();
//                invokeActivity(LoginActivity.class, null, paras, 0);
            }
        });
    }

    /**
     * 支付宝授权弹窗
     */
    protected void showHeartDialog() {
        if (extractDialog != null) {
            extractDialog.show();
            return;
        }
        extractDialog = new Dialog(context);
        extractDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        extractDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        extractDialog.setContentView(R.layout.dialog_heartextract);
        extractDialog.show();
        Button btn_next = (Button) extractDialog.getWindow().findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extractDialog.hide();
            }
        });
        Button btn_login = (Button) extractDialog.getWindow().findViewById(R.id.btn_enextract);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extractDialog.hide();
//                invokeActivity(LoginActivity.class, null, paras, 0);
            }
        });
    }

}

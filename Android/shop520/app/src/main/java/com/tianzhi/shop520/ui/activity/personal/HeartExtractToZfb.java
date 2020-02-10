package com.tianzhi.shop520.ui.activity.personal;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.callback.DialogJsonCallback;
import com.tianzhi.shop520.entity.BaseResponseData;
import com.tianzhi.shop520.entity.login.UserInfo;
import com.tianzhi.shop520.util.ACache;
import com.tianzhi.shop520.util.AppShared;
import com.tianzhi.shop520.util.BaseConstant;
import com.tianzhi.shop520.util.Constants;
import com.tianzhi.shop520.util.LogUtils;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by thinkpad on 2017/11/9.
 * 爱心值提起到支付宝
 */

public class HeartExtractToZfb extends BaseFragmentActivity {
    @BindView(R.id.zfb_account)
    EditText zfbAccount;
    @BindView(R.id.zfb_Name)
    EditText zfbName;
    @BindView(R.id.tv_use_heart)
    TextView tvUseHeart;
    @BindView(R.id.et_heart)
    EditText etHeart;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.btn_extract)
    TextView btnExtract;
    ACache aCache;
    UserInfo userInfo;
    @BindView(R.id.et_pwd)
    EditText etPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.act_heartextr_tozfb);
        aCache = ACache.get(context);
        initView();
    }

    @Override
    protected void initView() {
        setActivityTitle("支付宝提取");
        if (null != (UserInfo) aCache.getAsObject(Constants.USERINFO)) {
            userInfo = (UserInfo) aCache.getAsObject(Constants.USERINFO);
        } else
            userInfo = AppShared.getInstance(context).getLoginInfo();
        tvUseHeart.setText(userInfo.loveSurplus);
    }

    @OnClick(R.id.btn_extract)
    public void onViewClicked() {
        if (TextUtils.isEmpty(zfbAccount.getText().toString())) {
            toast("支付宝账号不能为空！");
            return;
        }
        if (TextUtils.isEmpty(zfbName.getText().toString())) {
            toast("真实姓名不能为空！");
            return;
        }
        if (TextUtils.isEmpty(etHeart.getText().toString())) {
            toast("爱心值不能为空！");
            return;
        }
        if (Integer.parseInt(etHeart.getText().toString()) < 50) {
            toast("提现爱心值至少在50以上！");
            return;
        }
        if(TextUtils.isEmpty(etPwd.getText().toString())){
            toast("请输入提取密码");
            return;
        }
        showLoading();
        OkGo.post(BaseConstant.RECOMMENDURL + BaseConstant.WITHDRAWALS)
                .params("userId", AppShared.getInstance(context).getLoginInfo().id)
                .params("userToken", AppShared.getInstance(context).getLoginInfo().userToken)
                .params("account", zfbAccount.getText().toString())//提现账户
                .params("consumeVal", etHeart.getText().toString())//提现金额
                .params("type", "0")
                .params("payeeRealName", zfbName.getText().toString())
                .params("password",etPwd.getText().toString())
                .execute(new DialogJsonCallback<BaseResponseData>(this,false) {
                    @Override
                    public void onSuccess(BaseResponseData baseResponseData, Call call, Response response) {
                       dismissLoading();
                        LogUtils.e("提现到账户", baseResponseData.toString());
                        if ("200".equals(baseResponseData.flag)) {
                            goNext(HeartExtractSuccessAct.class, true);
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
}

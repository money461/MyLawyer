package com.tianzhi.shop520.ui.activity.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.tianzhi.shop520.APPLICATION;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.callback.DialogCallback;
import com.tianzhi.shop520.callback.DialogJsonCallback;
import com.tianzhi.shop520.entity.BaseResponse;
import com.tianzhi.shop520.entity.BaseResponseData;
import com.tianzhi.shop520.entity.login.UserInfo;
import com.tianzhi.shop520.util.AppShared;
import com.tianzhi.shop520.util.BaseConstant;
import com.tianzhi.shop520.util.LogUtils;
import com.tianzhi.shop520.util.StringUtils;
import com.tianzhi.shop520.util.ViewUtil;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.tianzhi.shop520.R.id.send_code;

/**
 * Created by thinkpad on 2017/10/24.
 * 注册
 */

public class RegistAct extends BaseFragmentActivity {
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(send_code)
    TextView sendCode;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_pwd2)
    EditText etPwd2;
    @BindView(R.id.btn_regist)
    TextView btnRegist;
    UserInfo userInfo;
    @BindView(R.id.et_invitation_code)
    EditText etInvitationCode;
    private ViewUtil.TimeCount timeCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.act_regist);
        initView();
    }

    @Override
    protected void initView() {
        setActivityTitle("注册");
        timeCount = new ViewUtil.TimeCount(60 * 1000, 1 * 1000);
        timeCount.setmBtnCode(sendCode);
    }

    @OnClick({send_code, R.id.btn_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case send_code:
                if (checkNullOrEmpty(etAccount)) {
                    toast("请输入手机号码");
                    return;
                }
                getCode();
                break;
            case R.id.btn_regist:
                regist();
                break;
        }
    }

    /*获取验证码*/
    private void getCode() {
        String Url = BaseConstant.TestUrl + BaseConstant.GETTELCODE;
        String geturl = Url.replace("phone", etAccount.getText().toString().trim())
                .replace("type", "regist");
        LogUtils.e("获取验证码", geturl);
        showLoading();
        OkGo.get(geturl)
//                .params("phone", etAccount.getText().toString().trim())
//                .params("type", "regist")
                .execute(new DialogJsonCallback<BaseResponseData>(this,false) {
                    @Override
                    public void onSuccess(BaseResponseData baseResponseData, Call call, Response response) {
                        dismissLoading();
                        if("200".equals(baseResponseData.flag)){
                            timeCount.start();
                        }else {
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

    /*注册*/
    private void regist() {
        if (checkNullOrEmpty(etAccount)) {
            toast("请输入手机号码");
            return;
        }
        if(!StringUtils.isPhoneNum(etAccount.getText().toString().trim())){
            toast("请输入正确的手机号码");
            return;
        }
        if (checkNullOrEmpty(etCode)) {
            toast("请输入短信验证码");
            return;
        }
        if (checkNullOrEmpty(etPwd)) {
            toast("请输入密码");
            return;
        }
        if (checkNullOrEmpty(etPwd2)) {
            toast("请再次输入密码");
            return;
        }

        if (StringUtils.isPwd(etPwd.getText().toString().trim())) {
            etPwd.setText("");
            etPwd2.setText("");
            // usr_password_confirm_edt.setText("");
            toast("密码必须为6-19位数字、字母组合");
            return;
        }
        if (!etPwd.getText().toString().trim().equals(etPwd2.getText().toString().trim()))
        {
            etPwd.setText("");
            etPwd2.setText("");
            // usr_password_confirm_edt.setText("");
            toast("俩次密码不一致");
            return;
        }
        OkGo.post(BaseConstant.TestUrl + BaseConstant.REGIST)
                .tag(this)
                .params("phone", etAccount.getText().toString().trim())
                .params("password", etPwd.getText().toString().trim())
                .params("code", etCode.getText().toString().trim())
                .params("recommendedCode",etInvitationCode.getText().toString().trim())
                .execute(new DialogCallback<BaseResponse<UserInfo>>(this, true) {
                    @Override
                    public void onSuccess(BaseResponse<UserInfo> userInfoBaseResponse, Call call, Response response) {
                        super.onSuccess(userInfoBaseResponse, call, response);
                        userInfo = userInfoBaseResponse.data;
                        if (!TextUtils.isEmpty(userInfo.userToken) &&
                                !TextUtils.isEmpty(userInfo.type)) {
                            //保存数据
                            AppShared.getInstance(context).cleanUserInfoEntity();
                            AppShared.getInstance(context).saveLoginUserInfo(userInfo);
                            String id = AppShared.getInstance(context).getLoginInfo().id;
                            String token = AppShared.getInstance(context).getLoginInfo().userToken;
                            LogUtils.e("存储的token", id);
                            APPLICATION.isLogin = true;
                            goNext(RegistSuccessAct.class,true);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });

    }

    /**
     * 检测输入文本框是否为空
     *
     * @param text
     * @return
     */
    public boolean checkNullOrEmpty(EditText text) {
        String trim = text.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            return true;
        }
        return false;
    }
}

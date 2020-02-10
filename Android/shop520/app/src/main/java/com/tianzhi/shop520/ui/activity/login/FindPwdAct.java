package com.tianzhi.shop520.ui.activity.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.callback.DialogJsonCallback;
import com.tianzhi.shop520.entity.BaseResponseData;
import com.tianzhi.shop520.entity.login.Findpwdentity;
import com.tianzhi.shop520.entity.login.UserInfo;
import com.tianzhi.shop520.util.BaseConstant;
import com.tianzhi.shop520.util.LogUtils;
import com.tianzhi.shop520.util.StringUtils;
import com.tianzhi.shop520.util.ViewUtil;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.tianzhi.shop520.R.id.et_pwd;

/**
 * Created by thinkpad on 2017/10/25.
 * 找回密码
 */

public class FindPwdAct extends BaseFragmentActivity {
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.send_code)
    TextView sendCode;
    @BindView(et_pwd)
    EditText etPwd;
    @BindView(R.id.et_pwd2)
    EditText etPwd2;
    @BindView(R.id.btn_findpwd)
    TextView btnFindpwd;
    String pattern = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,19}$";
    private String account = null;
    private String password = null;
    UserInfo userInfo;
    UserInfo tzUser;
    public ViewUtil.TimeCount timeCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.act_findpwd);
        initView();
    }

    @Override
    protected void initView() {
        setActivityTitle("找回密码");
        timeCount = new ViewUtil.TimeCount(60 * 1000, 1 * 1000);
        timeCount.setmBtnCode(sendCode);
    }



    /*获取验证码*/
    private void getCode() {
        String Url = BaseConstant.TestUrl + BaseConstant.GETTELCODE;
        String geturl = Url.replace("phone", etAccount.getText().toString().trim())
                .replace("type", "find");
        LogUtils.e("获取验证码", geturl);
        showLoading();
        OkGo.get(geturl)
                .execute(new DialogJsonCallback<BaseResponseData>(this,false) {
                    @Override
                    public void onSuccess(BaseResponseData baseResponseData, Call call, Response response) {
                      dismissLoading();
                        if("200".equals(baseResponseData.flag)){
                          timeCount.start();
                      }else{
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

    /*找回密码*/
    private void findPwd() {
        if (checkNullOrEmpty(etAccount)) {
            toast("请输入手机号码");
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
        if (StringUtils.isAllLetter(etPwd.getText().toString().trim())
                | StringUtils.isAllNumber(etPwd2.getText().toString().trim())) {
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
//        String pwd = etPwd.getText().toString().trim();
//        String recCode = etCode.getText().toString().trim();
//        String phone = etAccount.getText().toString().trim();
        OkGo.post(BaseConstant.TestUrl + BaseConstant.FINDPASSWORD)
                .tag(this)
                .params("phone", etAccount.getText().toString().trim())
                .params("password", etPwd.getText().toString().trim())
                .params("code", etCode.getText().toString().trim())
                .execute(new DialogJsonCallback<Findpwdentity>(this,false) {
                    @Override
                    public void onSuccess(Findpwdentity findpwdentity, Call call, Response response) {
                        LogUtils.e("找回密码",findpwdentity.toString());
                        tzUser = findpwdentity.data;
                        if("200".equals(findpwdentity.flag)){
                            toast("找回密码成功！");
                            goNext(LoginAct.class);
                            finish();
                        }
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

    @OnClick({R.id.send_code, R.id.btn_findpwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.send_code:
                account = etAccount.getText().toString();
                password = etPwd.getText().toString();
                if (TextUtils.isEmpty(account)) {
                    toast("请输入手机号码");
                    return;
                } else if (!StringUtils.isPhoneNum(account)) {
                    toast("手机号格式错误");
                    return;
                }
                getCode();
                break;
            case R.id.btn_findpwd:
                findPwd();
                break;
        }
    }
}

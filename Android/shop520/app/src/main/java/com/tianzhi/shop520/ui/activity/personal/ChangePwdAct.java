package com.tianzhi.shop520.ui.activity.personal;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.lzy.okgo.OkGo;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.callback.DialogJsonCallback;
import com.tianzhi.shop520.entity.BaseResponseData;
import com.tianzhi.shop520.entity.login.UserInfo;
import com.tianzhi.shop520.util.AppShared;
import com.tianzhi.shop520.util.BaseConstant;
import com.tianzhi.shop520.util.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by thinkpad on 2017/10/20.
 */

public class ChangePwdAct extends BaseFragmentActivity {

    @BindView(R.id.et_oldpwd)
    EditText etOldpwd;
    @BindView(R.id.et_newpwd)
    EditText etNewpwd;
    @BindView(R.id.et_newpwd1)
    EditText etNewpwd1;
    @BindView(R.id.btn_changepwd)
    Button btnChangepwd;
    UserInfo userInfo;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.act_changepwd);
        initView();
    }

    protected void initView() {
        setActivityTitle("修改密码");
    }

    @OnClick(R.id.btn_changepwd)
    public void onViewClicked() {
        if (checkNullOrEmpty(etOldpwd)) {
            toast("请输入旧密码");
            return;
        }
        if (checkNullOrEmpty(etNewpwd)) {
            toast("请输入新密码");
            return;
        }

        if (StringUtils.isPwd(etNewpwd.getText().toString().trim())) {
            etNewpwd.setText("");
            etNewpwd.setText("");
            // usr_password_confirm_edt.setText("");
            toast("密码必须为6-19位数字、字母组合");
            return;
        }
        if(etNewpwd.getText().toString().equals(etOldpwd.getText().toString())){
            toast("新密码与旧密码不能相同");
            return;
        }
        if(!etNewpwd.getText().toString().equals(etNewpwd1.getText().toString())){
            toast("新密码俩次输入不一致");
            return;
        }
        showLoading();
        OkGo.post(BaseConstant.TestUrl+BaseConstant.UPDATEPASSWORD)
                .tag(this)
                .params("userId", AppShared.getInstance(context).getLoginInfo().id)
                .params("userToken",AppShared.getInstance(context).getLoginInfo().userToken)
                .params("oldPassword",etOldpwd.getText().toString().trim())
                .params("password",etNewpwd.getText().toString().trim())
            .execute(new DialogJsonCallback<BaseResponseData>(this,false) {
            @Override
            public void onSuccess(BaseResponseData baseResponseData, Call call, Response response) {
               dismissLoading();
                if("200".equals(baseResponseData.flag)){
                   toast("修改密码成功");
                   finish();
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

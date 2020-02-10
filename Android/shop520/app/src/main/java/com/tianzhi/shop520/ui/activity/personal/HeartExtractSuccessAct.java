package com.tianzhi.shop520.ui.activity.personal;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.tianzhi.shop520.APPLICATION;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.callback.DialogCallback;
import com.tianzhi.shop520.entity.BaseResponse;
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
 * Created by thinkpad on 2017/11/10.
 */

public class HeartExtractSuccessAct extends BaseFragmentActivity {

    @BindView(R.id.btn_back)
    TextView btnBack;
    UserInfo userInfo;
    ACache aCache;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.act_heartextract_success);
            aCache =ACache.get(context);
        initView();
    }

    @Override
    protected void initView() {
        setActivityTitle("支付宝提取");
        UpdateUser();

    }

    /*即时更新用户信息*/
    public void UpdateUser() {
        OkGo.post(BaseConstant.TestUrl + BaseConstant.INSTANTUPDATEUSER)
                .tag(this)
                .params("userId", AppShared.getInstance(context).getLoginInfo().id)
                .params("userToken", AppShared.getInstance(context).getLoginInfo().userToken)
                .execute(new DialogCallback<BaseResponse<UserInfo>>(this, true) {
                    @Override
                    public void onSuccess(BaseResponse<UserInfo> userInfoBaseResponse, Call call, Response response) {
                        super.onSuccess(userInfoBaseResponse, call, response);
                        userInfo = userInfoBaseResponse.data;
                        if (!TextUtils.isEmpty(userInfo.userToken)
                                && !TextUtils.isEmpty(userInfo.id)
                                ) {
                            APPLICATION.isLogin = true;
                            LogUtils.e("爱心值提取刷新数据", userInfo.toString());
                            AppShared.getInstance(context).cleanUserInfoEntity();
                            aCache.put(Constants.USERINFO, userInfo);
                            AppShared.getInstance(context).saveHeadUrl(userInfo.headUrl);
                            AppShared.getInstance(context).saveLoginUserInfo(userInfo);
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        APPLICATION.isLogin = false;
                    }
                });
    }
    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        goNext(HeartExtractAct.class,true);
    }
}

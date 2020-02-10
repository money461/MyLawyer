package com.tianzhi.shop520.ui.activity.login;

import android.os.Bundle;
import android.widget.TextView;

import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by thinkpad on 2017/10/24.
 * 注册成功
 */

public class RegistSuccessAct extends BaseFragmentActivity {
    @BindView(R.id.btn_next)
    TextView btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.act_registsuccess);
        initView();
    }

    @Override
    protected void initView() {
        setActivityTitle("注册成功");
    }

    @OnClick(R.id.btn_next)
    public void onViewClicked() {
        goNext(MemberBenefitsAct.class,true);
    }
}

package com.tianzhi.shop520.ui.activity.login;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.ui.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by thinkpad on 2017/10/24.
 * 会员权益
 */

public class MemberBenefitsAct extends BaseFragmentActivity {
    @BindView(R.id.btn_member)
    TextView btnMember;
    @BindView(R.id.btn_next)
    TextView btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.act_memberbenefits);
        initView();
    }

    @Override
    protected void initView() {
        setActivityTitle("会员权益");
    }

    @OnClick({R.id.btn_member, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_member://成为会员
                goNext(MemberPagAct.class);
                break;
            case R.id.btn_next://先逛逛
                paras.putString("page","0");
                goNext(MainActivity.class,paras,true);
                break;
        }
    }
}

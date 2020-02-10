package com.tianzhi.shop520.ui.activity.login;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.util.ViewUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by thinkpad on 2017/10/24.
 * 用户注册协议
 */

public class RegistAgreeAct extends BaseFragmentActivity {
    @BindView(R.id.tv_agree)
    TextView tvAgree;
    @BindView(R.id.scrollview)
    ScrollView scrollview;
    @BindView(R.id.ll_regist)
    LinearLayout llRegist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.act_registagree);
        initView();
        DisplayMetrics dm = new DisplayMetrics();// 获取手机分辨率
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Rect frame = new Rect();
        this.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        // 高度
        int height = dm.heightPixels;
        int w = dm.widthPixels;
        LinearLayout.LayoutParams params =new LinearLayout.LayoutParams(w- ViewUtil.dip2px(context,30), height * 7 / 10);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        params.topMargin = ViewUtil.dip2px(context,20);
        llRegist.setLayoutParams(params);
//        llRegist.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
//        llRegist.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    @Override
    protected void initView() {
        setActivityTitle("用户注册协议");
    }

    @OnClick(R.id.tv_agree)
    public void onViewClicked() {
        goNext(RegistAct.class);
    }


}

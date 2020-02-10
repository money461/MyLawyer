package com.tianzhi.shop520.ui.activity.personal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.util.LogUtils;

import butterknife.BindView;

/**
 * Created by thinkpad on 2017/10/20.
 */

public class About520Act extends BaseFragmentActivity {
    @BindView(R.id.versionCode)
    TextView versionCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.act_about);
        initView();
    }

    protected void initView() {
        setActivityTitle("关于我们");
        versionCode.setText("v"+getAppVersionName(context));
    }

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            LogUtils.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }
}


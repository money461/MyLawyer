package com.tianzhi.shop520.ui.activity.personal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tianzhi.shop520.APPLICATION;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.util.ACache;
import com.tianzhi.shop520.util.AppShared;
import com.tianzhi.shop520.util.DataCleanManager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by thinkpad on 2017/10/20.
 * 设置
 */

public class PersonalSettingAct extends BaseFragmentActivity {
    @BindView(R.id.rl_changepwd)
    RelativeLayout rlChangepwd;
    @BindView(R.id.rl_clean)
    RelativeLayout rlClean;
    @BindView(R.id.btn_exit)
    TextView btnExit;
    @BindView(R.id.tv_cache)
    TextView tvCache;
    String cacheSize;//缓存大小
    ACache aCache;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aCache = ACache.get(context);
        setContentLayout(R.layout.act_personalsetting);
        try {
             cacheSize = DataCleanManager.getTotalCacheSize(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        initView();

    }

    protected void initView() {
        setActivityTitle("设置");
        tvCache.setText(cacheSize);
    }


    @OnClick({R.id.rl_changepwd, R.id.rl_clean, R.id.btn_exit})
    public void onViewClicked(View view) {
        AlertDialog alert;
        switch (view.getId()) {
            case R.id.rl_changepwd:
                goNext(ChangePwdAct.class);
                break;
            case R.id.rl_clean:
                //清除操作
                DataCleanManager.clearAllCache(context);
                tvCache.setText("0M");
                //清空缓存
                break;
            case R.id.btn_exit:
                alert = new AlertDialog.Builder(context).create();
//                alert.setTitle("操作提示");
                alert.setMessage("您确定要退出登录吗？");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                APPLICATION.isLogin = false;
                                AppShared.getInstance(context).cleanUserInfoEntity();
                                aCache.clear();
                                finish();
                            }
                        });
                alert.show();
                //清空登录账号
                break;
        }
    }
}

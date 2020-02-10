package com.tianzhi.shop520.callback;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.annotation.Nullable;
import android.view.Window;

import com.lzy.okgo.request.BaseRequest;
import com.tianzhi.shop520.ui.diyview.dialog.LMToast;
import com.tianzhi.shop520.util.NetworkUtil;


/**
 * Created by wisdomnt on 2017/3/30.
 * 不统一解析code
 */
public abstract class DialogJsonCallback<T> extends JsonCallback2<T> {

    private ProgressDialog dialog;
    private Activity activity;
    private void initDialog(Activity activity) {
        dialog = new ProgressDialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("请求网络中...");
    }

    public DialogJsonCallback(Activity activity, boolean isture) {
        super();
        this.activity =activity;
        if (isture) {
            initDialog(activity);
        }
    }

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        if (!NetworkUtil.isConnected(activity)) {
            LMToast.showToast("网络连接已断开");
            return;
        }
        //网络请求前显示对话框
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void onAfter(@Nullable T t, @Nullable Exception e) {
        super.onAfter(t, e);
        //网络请求结束后关闭对话框
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}


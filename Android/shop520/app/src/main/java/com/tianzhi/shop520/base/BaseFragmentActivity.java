package com.tianzhi.shop520.base;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tianzhi.shop520.APPLICATION;
import com.tianzhi.shop520.AtyMgr;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.ui.activity.login.LoginAct;
import com.tianzhi.shop520.ui.diyview.dialog.LMToast;
import com.tianzhi.shop520.ui.diyview.dialog.LoadingDialog;
import com.tianzhi.shop520.util.XPermissionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wisdomnt on 2017/2/24.
 */
public abstract class BaseFragmentActivity extends FragmentActivity {

    @BindView(R.id.iv_title_bg)
    ImageView ivTitleBg;
    @BindView(R.id.tv_baseLeft)
    TextView tvBaseLeft;
    @BindView(R.id.leftBtn)
    protected Button leftBtn;//左上角按钮
    @BindView(R.id.rightBtn)
    protected Button rightBtn;//右上角按钮
    @BindView(R.id.tv_rightBtnTips)
    TextView tvRightBtnTips;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.title)
    RelativeLayout titleLayout;
    @BindView(R.id.warn_container)
    RelativeLayout warnContainer;
    @BindView(R.id.root)
    RelativeLayout root;
    //    @BindView(R.id.righIvtBtn)
    public ImageButton righIvtBtn;
    @BindView(R.id.base_container)
    LinearLayout baseContainer;
    @BindView(R.id.et_title_search)
    protected EditText etTitleSearch;
    /**
     * 网络检查设置
     */
    private RelativeLayout warnContainerLayout;
    protected LoadingDialog mLoadingDialog;
    private Dialog loadingDialog = null;
    protected Context context = null;
    protected Bundle paras = null; // 页面参数
    protected Intent intent = null;
    //定义标题栏上的按钮
    private ImageButton titleBtn;
    private Dialog dialog;
    @SuppressLint({"InlinedApi", "NewApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AtyMgr.getAppManager().addActivity(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        Log.e("msg", "操作系统版本：" + Build.VERSION.SDK_INT);
        setContentView(R.layout.act_base);
//        ButterKnife.bind(this);
        context = this;
        intent = getIntent();
        paras = intent.getExtras();// 页面参数
        if (paras == null)
            paras = new Bundle();
        baseContainer = (LinearLayout) findViewById(R.id.base_container);
        righIvtBtn = (ImageButton) findViewById(R.id.righIvtBtn);
//        setTranslucentStatus(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        dismissLoading();
        mLoadingDialog = null;
        super.onDestroy();
    }

    /**
     * 绑定控件ID
     */
//    protected abstract void findViewById();

    /**
     * 初始化监听器
     */
//    protected abstract void initListener();

    /**
     * 初始化UI
     */
    protected abstract void initView();

    /**
     * 设置Activity页面
     *
     * @param layoutResID 界面的ID
     */
    protected void setContentLayout(int layoutResID) {
        LayoutInflater inflater = getLayoutInflater();
        View childrenView = inflater.inflate(layoutResID, null);
        childrenView.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        baseContainer.addView(childrenView);
        ButterKnife.bind(this);
//        findViewById();
//        initListener();
//        initView();
    }

    /**
     * 设置标题
     *
     * @param title
     */
    @SuppressLint("NewApi")
    protected void setActivityTitle(CharSequence title) {

        if (title != null) {
            tvTitle.setText(title);
        }
    }
/*隐藏标题*/
    protected  void setShowTitle(boolean flag){
        if(flag){
            root.setVisibility(View.GONE);
        }
    }

    /**
     * 设置标题
     *
     * @param title
     */
    protected void setActivityTitle(int title) {
        tvTitle.setText(title);
        leftBtn.setVisibility(View.VISIBLE);
    }

    /*
    * 设置搜索框是否显示
    * */
    protected void setActivityShowSearch(boolean isShow) {
        if (isShow){
            etTitleSearch.setVisibility(View.VISIBLE);
            tvTitle.setVisibility(View.GONE);
        }else {
            etTitleSearch.setVisibility(View.GONE);
            tvTitle.setVisibility(View.VISIBLE);
        }

    }
    /**
     * 设置TitleLayout显示与否
     *
     * @param isShow
     */
    protected void setTitleLayoutShow(boolean isShow) {
        if (!isShow)
            titleLayout.setVisibility(View.GONE);
    }

    /**
     * 头部右侧菜单是否显示
     */
    protected void setRightMenuShow(boolean isShow) {
        if (isShow)
            righIvtBtn.setVisibility(View.VISIBLE);
    }

    /**
     * 设置WarnLayout显示与否
     *
     * @param isShow
     */
    protected void setWarnLayoutShow(boolean isShow) {
        if (isShow)
            warnContainerLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 设置左边按钮显示与否
     *
     * @param isShow
     */
    protected void setLeftButtonShow(boolean isShow) {
        if (!isShow)
            leftBtn.setVisibility(View.INVISIBLE);
    }

    /**
     * 设置右边按钮显示与否
     *
     * @param isShow
     */
    protected void setRightButtonShow(boolean isShow) {
        if (isShow)
            rightBtn.setVisibility(View.VISIBLE);
    }
    /**
     * 设置右边按钮显示与否
     *
     * @param isShow
     */
    protected void setRightButtonName(CharSequence isShow) {
        if (!TextUtils.isEmpty(isShow))
            rightBtn.setText(isShow);
    }


    /**
     * 设置右边按钮tips展示与否
     *
     * @param isShow
     */
    protected void setRightButtonTipsShow(boolean isShow) {
        if (!isShow) {
            APPLICATION.isReceiveNum = false;
        } else {
            APPLICATION.isReceiveNum = true;
        }
    }

    /**
     * 显示加载进度框
     */
    public void showLoadingDialog() {
        try {
            if (mLoadingDialog == null) {
                mLoadingDialog = new LoadingDialog(this);
            }
            mLoadingDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示加载进度框
     *
     * @param cancelable 对话框是否可取消
     */
    public void showLoading(boolean cancelable) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this);
        }
        mLoadingDialog.setCancelable(cancelable);
        mLoadingDialog.show();
    }

    /**
     * 显示加载进度框
     */
    public void showLoading(final AsyncTask<?, ?, ?> task) {
        showLoading();
        mLoadingDialog.setOnCancelListener(new OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                if (task != null) {
                    task.cancel(true);
                }
            }
        });
    }

    /**
     * 隐藏加载框
     *
     * @param task
     * @param finishAct 如果值为true，则隐藏加载框并结束当前的activity
     */
    public void showLoading(final AsyncTask<?, ?, ?> task,
                            final boolean finishAct) {
        showLoading();
        mLoadingDialog.setOnCancelListener(new OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                if (task != null) {
                    task.cancel(true);
                    if (finishAct) {
                        finish();
                    }

                }
            }
        });
    }

    /**
     * 隐藏加载进度框
     *
     * @return 加载框由显示转为隐藏状态则返回true, 反之false
     */
    public boolean cancelLoadingDialog() {
        try {
            if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
                mLoadingDialog.dismiss();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 显示toast
     *
     * @param text
     */
    public void toast(String text) {
        LMToast.showToast(text);
    }

    /**
     * 显示toast
     *
     * @param
     */
    public void toast(int textId) {
        LMToast.showToast(textId);
    }
//
//    @Override
//    public void onClick(View v) {
//        if(CommonUtils.isFastDoubleClick()){
//            return;
//        }
//    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        try {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void showLoading() {
        if (loadingDialog == null || !loadingDialog.isShowing()) {
            View v = getLayoutInflater().inflate(R.layout.loading_progress, null);// 得到加载view
            LinearLayout layout = (LinearLayout) v
                    .findViewById(R.id.dialog_view);// 加载布局
            // main.xml中的ImageView
            ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
            TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
            // 加载动画
            Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
                    this, R.anim.loading_animation);
            // 使用ImageView显示动画
            spaceshipImage.startAnimation(hyperspaceJumpAnimation);
            tipTextView.setText("");// 设置加载信息
            tipTextView.setVisibility(View.VISIBLE);

            loadingDialog = new Dialog(this, R.style.loadingProgressDialog);// 创建自定义样式dialog

            // loadingDialog.setCancelable(false);// 不可以用“返回键”取消
            loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));// 设置布局
            if (!this.isFinishing()) {
                loadingDialog.setCancelable(false);
                loadingDialog.show();
            }
        }
    }

    /**
     * 隐藏登录加载框.
     */
    public void dismissLoading() {
        try {
            if (loadingDialog != null && loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Activity跳转，无参数,父Activity不销毁
     *
     * @param to 目的Activity
     */
    public void goNext(Class<?> to) {
        goNext(to, null, null, false);
    }

    /**
     * Activity跳转，无参数
     *
     * @param to       目的Activity
     * @param finished 父Activity是否销毁，finished=true销毁
     */
    public void goNext(Class<?> to, boolean finished) {
        goNext(to, null, null, finished);
    }

    /**
     * Activity跳转，未指定参数名，参数名为paras
     *
     * @param to         目的Activity
     * @param paraValues 参数
     * @param finished   父Activity是否销毁，finished=true销毁
     */
    public void goNext(Class<?> to, Object paraValues, boolean finished) {
        goNext(to, null, paraValues, finished);
    }

    /**
     * Activity跳转，如果是多个参数，建议用map<String, object>
     *
     * @param to
     * @param paraName
     * @param paraValues
     * @param finished
     */
    public void goNext(Class<?> to, String paraName, Object paraValues,
                       boolean finished) {
        Intent intent = new Intent(context, to);
        if (paraValues != null)
            initParas(intent, paraName, paraValues);
        startActivity(intent);
        if (finished)
            finish();
    }

    /**
     * 调用另一Activity处理，处理完成后可以返回值，实现类中需要重载方法：onActivityResult(int requestCode,
     * int resultCode, Intent data)。
     *
     * @param to          另一Activity
     * @param requestCode request code
     */
    public void invokeActivity(Class<?> to, int requestCode) {
        invokeActivity(to, null, null, requestCode);
    }

    /**
     * 调用另一Activity处理，处理完成后可以返回值，实现类中需要重载方法：onActivityResult(int requestCode,
     * int resultCode, Intent data)。
     *
     * @param to          另一Activity
     * @param paraName    参数名
     * @param paraValues  参数值
     * @param requestCode request code
     */
    public void invokeActivity(Class<?> to, String paraName, Object paraValues,
                               int requestCode) {
        Intent intent = new Intent();
        intent.setClass(context, to);
        if (paraValues != null)
            initParas(intent, paraName, paraValues);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 用于activity里面的list的adapter里面的控件调用setResult回传值
     *
     * @param data
     * @param rsp_code
     */
    public void setActivityResult(Intent data, int rsp_code) {
        setResult(rsp_code, data);
        finish();
    }

    /**
     * 把activity之间的参数保存在intent中，如果是多个参数，建议用map<String, object>.
     *
     * @param intent
     * @param parasName 参数名
     * @param value     参数值
     */
    @SuppressWarnings("unchecked")
    protected void initParas(Intent intent, String parasName, Object value) {
        if (value == null) {
            return;
        }
        if (parasName == null)
            parasName = "paras";
        if (value instanceof Integer) {
            intent.putExtra(parasName, (Integer) value);
        } else if (value instanceof String) {
            intent.putExtra(parasName, (String) value);
        } else if (value instanceof Boolean) {
            intent.putExtra(parasName, (Boolean) value);
        } else if (value instanceof Character) {
            intent.putExtra(parasName, (Character) value);
        } else if (value instanceof Byte) {
            intent.putExtra(parasName, (Byte) value);
        } else if (value instanceof Double) {
            intent.putExtra(parasName, (Double) value);
        } else if (value instanceof Float) {
            intent.putExtra(parasName, (Float) value);
        } else if (value instanceof Long) {
            intent.putExtra(parasName, (Long) value);
        } else if (value instanceof StringBuffer) {
            intent.putExtra(parasName, ((StringBuffer) value).toString());
        } else if (value instanceof Bundle) {
            intent.putExtras((Bundle) value);
        } else if (value instanceof ArrayList) {
            @SuppressWarnings("rawtypes")
            ArrayList al = (ArrayList) value;
            if (al == null || al.size() == 0)
                return;

            Object v = al.get(al.size() - 1);
            if (v instanceof Integer)
                intent.putIntegerArrayListExtra(parasName, al);
            else
                intent.putStringArrayListExtra(parasName,
                        (ArrayList<String>) value);
        } else if (value instanceof Map) {
            // 传递过来的是map
            Map<String, Object> p = (Map<String, Object>) value;
            @SuppressWarnings("rawtypes")
            Iterator<String> iter = ((Map) value).keySet()
                    .iterator();
            Bundle bundle = new Bundle();
            while (iter.hasNext()) {
                String key = iter.next();
                Object val = (Object) p.get(key);
                if (val instanceof Integer) {
                    bundle.putInt(key, (Integer) val);
                }
                if (val instanceof String) {
                    bundle.putString(key, (String) val);
                }
                if (val instanceof Boolean) {
                    bundle.putBoolean(key, (Boolean) val);
                }
                if (val instanceof Byte) {
                    bundle.putByte(key, (Byte) val);
                }
                if (val instanceof Double) {
                    bundle.putDouble(key, (Double) val);
                }
                if (val instanceof Float) {
                    bundle.putFloat(key, (Float) val);
                }
                if (val instanceof Long) {
                    bundle.putLong(key, (Long) val);
                }
                if (val instanceof StringBuffer) {
                    bundle.putString(key, ((StringBuffer) val).toString());
                }
                if (val instanceof ArrayList) {
                    @SuppressWarnings("rawtypes")
                    ArrayList al = (ArrayList) val;
                    if (al == null || al.size() == 0)
                        return;

                    Object v = al.get(al.size() - 1);
                    if (v instanceof Integer)
                        bundle.putIntegerArrayList(key, al);
                    else
                        bundle.putStringArrayList(key,
                                (ArrayList<String>) value);
                }
            }
            intent.putExtras(bundle);
        }
    }

    @OnClick(R.id.leftBtn)
    public void onClick() {
        finish();
    }
    /**
     * 登录信息提示
     */
    protected void loginDialog() {
        if (dialog != null) {
            dialog.show();
            return;
        }
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dialog_please_login);
        dialog.show();
        Button btn_next = (Button) dialog.getWindow().findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
            }
        });
        Button btn_login = (Button) dialog.getWindow().findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
                invokeActivity(LoginAct.class, null, paras, 0);
            }
        });
    }
//    @OnClick({R.id.leftBtn, R.id.rightBtn, R.id.righIvtBtn, R.id.warn_container})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.leftBtn:
//                finish();
//                break;
//            case R.id.rightBtn:
//                break;
//            case R.id.righIvtBtn:
//                titlePopup.show(view);
//                break;
//            case R.id.warn_container:
//                Intent intent = new Intent(Settings.ACTION_SETTINGS);
//                startActivity(intent);
//                AtyMgr.getAppManager().finishActivity();
//                break;
//        }
//    }


//    @OnClick({R.id.leftBtn, R.id.righIvtBtn})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.leftBtn:
//                finish();
//                break;
//            case R.id.righIvtBtn:
//                titlePopup.show(view);
//                break;
//        }
//    }

//    @OnClick(R.id.righIvtBtn)
//    public void onClick() {
//        titlePopup.show(righIvtBtn);
//    }
@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
        grantResults) {
    XPermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
}
}

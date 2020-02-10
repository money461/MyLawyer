package com.tianzhi.shop520.base;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tianzhi.shop520.R;
import com.tianzhi.shop520.ui.activity.login.LoginAct;

/**
 *
 */
public abstract class BaseLazyFragment extends Fragment {
    private static final String TAG = BaseLazyFragment.class.getSimpleName();
    private boolean isPrepared;
    protected Bundle paras;
    protected Context context = null;
    protected Intent intent = null;
    private Dialog loadingDialog = null;
    /**
     * 第一次onResume中的调用onUserVisible避免操作与onFirstUserVisible操作重复
     */
    private boolean isFirstResume = true;
    private boolean isFirstVisible = true;
    private boolean isFirstInvisible = true;
    private Dialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    /**
     * 用于fragment刷新bundle参数
     *
     * @param mybundle
     */
    public void refreshData(Bundle mybundle) {
        this.paras = mybundle;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        isViewInitiated = true;
//        prepareFetchData();

        initPrepare();
        context = this.getActivity();
        intent = this.getActivity().getIntent();
        paras = intent.getExtras();// 页面参数
        if ( paras == null )
            paras = new Bundle();
    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        this.isVisibleToUser = isVisibleToUser;
//        prepareFetchData();
//    }

//    public abstract void fetchData();
//
//    public boolean prepareFetchData() {
//        return prepareFetchData(false);
//    }
//
//    public boolean prepareFetchData(boolean forceUpdate) {
//        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
//            fetchData();
//            isDataInitiated = true;
//            return true;
//        }
//        return false;
//    }


    @Override
    public void onResume() {
        super.onResume();
        if (isFirstResume) {
            isFirstResume = false;
            return;
        }
        if (getUserVisibleHint()) {
            onUserVisible();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint()) {
            onUserInvisible();
        }
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isFirstVisible) {
                isFirstVisible = false;
                initPrepare();
            } else {
                onUserVisible();
            }
        } else {
            if (isFirstInvisible) {
                isFirstInvisible = false;
                onFirstUserInvisible();
            } else {
                onUserInvisible();
            }
        }
    }

    public synchronized void initPrepare() {
        if (isPrepared) {
            onFirstUserVisible();
        } else {
            isPrepared = true;
        }
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

    public void showLoading() {
        if (loadingDialog == null || !loadingDialog.isShowing()) {
            View v = this.getActivity().getLayoutInflater().inflate(R.layout.loading_progress, null);// 得到加载view
            LinearLayout layout = (LinearLayout) v
                    .findViewById(R.id.dialog_view);// 加载布局
            // main.xml中的ImageView
            ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
            TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
            // 加载动画
            Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
                    this.getActivity(), R.anim.loading_animation);
            // 使用ImageView显示动画
            spaceshipImage.startAnimation(hyperspaceJumpAnimation);
            tipTextView.setText("");// 设置加载信息
            tipTextView.setVisibility(View.VISIBLE);

            loadingDialog = new Dialog(this.getActivity(), R.style.loadingProgressDialog);// 创建自定义样式dialog

            // loadingDialog.setCancelable(false);// 不可以用“返回键”取消
            loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));// 设置布局
            if (!this.getActivity().isFinishing()) {
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
     * 第一次fragment可见（进行初始化工作）
     */
    public abstract void onFirstUserVisible();

    /**
     * fragment可见（切换回来或者onResume）
     */
    public abstract void onUserVisible();

    /**
     * 第一次fragment不可见（不建议在此处理事件）
     */
    public abstract void onFirstUserInvisible();

    /**
     * fragment不可见（切换掉或者onPause）
     */
    public abstract void onUserInvisible();
    /**
     * Activity跳转，无参数,父Activity不销毁
     *
     * @param to 目的Activity
     */
    public void goNext( Class<?> to ) {
        goNext( to, null, null, false );
    }
    /**
     * Activity跳转，无参数
     *
     * @param to       目的Activity
     * @param finished 父Activity是否销毁，finished=true销毁
     */
    public void goNext(Class<?> to, boolean finished ) {
        goNext( to, null, null, finished );
    }

    /**
     * Activity跳转，未指定参数名，参数名为paras
     *
     * @param to         目的Activity
     * @param paraValues 参数
     * @param finished   父Activity是否销毁，finished=true销毁
     */
    public void goNext(Class<?> to, Object paraValues, boolean finished ) {
        goNext( to, null, paraValues, finished );
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
                       boolean finished ) {
        Intent intent = new Intent( this.getContext(), to );
        if ( paraValues != null )
            initParas( intent, paraName, paraValues );
        startActivity( intent );
        if ( finished )
            this.getActivity().finish();
    }
    /**
     * 把activity之间的参数保存在intent中，如果是多个参数，建议用map<String, object>.
     *
     * @param intent
     * @param parasName 参数名
     * @param value     参数值
     */
    @SuppressWarnings( "unchecked" )
    protected void initParas(Intent intent, String parasName, Object value ) {
        if ( value == null ) {
            return;
        }
        if ( parasName == null )
            parasName = "paras";
        if ( value instanceof Integer) {
            intent.putExtra( parasName, (Integer) value );
        } else if ( value instanceof String) {
            intent.putExtra( parasName, (String) value );
        } else if ( value instanceof Boolean) {
            intent.putExtra( parasName, (Boolean) value );
        } else if ( value instanceof Character) {
            intent.putExtra( parasName, (Character) value );
        } else if ( value instanceof Byte) {
            intent.putExtra( parasName, (Byte) value );
        } else if ( value instanceof Double) {
            intent.putExtra( parasName, (Double) value );
        } else if ( value instanceof Float) {
            intent.putExtra( parasName, (Float) value );
        } else if ( value instanceof Long) {
            intent.putExtra( parasName, (Long) value );
        } else if ( value instanceof StringBuffer) {
            intent.putExtra( parasName, ( (StringBuffer) value ).toString() );
        } else if ( value instanceof Bundle) {
            intent.putExtras( (Bundle) value );
        } else if ( value instanceof java.util.ArrayList ) {
            @SuppressWarnings( "rawtypes" )
            java.util.ArrayList al = (java.util.ArrayList) value;
            if ( al == null || al.size() == 0 )
                return;

            Object v = al.get( al.size() - 1 );
            if ( v instanceof Integer)
                intent.putIntegerArrayListExtra( parasName, al );
            else
                intent.putStringArrayListExtra( parasName,
                        (java.util.ArrayList<String>) value );
        } else if ( value instanceof java.util.Map ) {
            // 传递过来的是map
            java.util.Map<String, Object> p = (java.util.Map<String, Object>) value;
            @SuppressWarnings( "rawtypes" )
            java.util.Iterator<String> iter = ( (java.util.Map) value ).keySet()
                    .iterator();
            Bundle bundle = new Bundle();
            while ( iter.hasNext() ) {
                String key = iter.next();
                Object val = (Object) p.get( key );
                if ( val instanceof Integer) {
                    bundle.putInt( key, (Integer) val );
                }
                if ( val instanceof String) {
                    bundle.putString( key, (String) val );
                }
                if ( val instanceof Boolean) {
                    bundle.putBoolean( key, (Boolean) val );
                }
                if ( val instanceof Byte) {
                    bundle.putByte( key, (Byte) val );
                }
                if ( val instanceof Double) {
                    bundle.putDouble( key, (Double) val );
                }
                if ( val instanceof Float) {
                    bundle.putFloat( key, (Float) val );
                }
                if ( val instanceof Long) {
                    bundle.putLong( key, (Long) val );
                }
                if ( val instanceof StringBuffer) {
                    bundle.putString( key, ( (StringBuffer) val ).toString() );
                }
                if ( val instanceof java.util.ArrayList ) {
                    @SuppressWarnings( "rawtypes" )
                    java.util.ArrayList al = (java.util.ArrayList) val;
                    if ( al == null || al.size() == 0 )
                        return;

                    Object v = al.get( al.size() - 1 );
                    if ( v instanceof Integer)
                        bundle.putIntegerArrayList( key, al );
                    else
                        bundle.putStringArrayList( key,
                                (java.util.ArrayList<String>) value );
                }
            }
            intent.putExtras( bundle );
        }
    }
}
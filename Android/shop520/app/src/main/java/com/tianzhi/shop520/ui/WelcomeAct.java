package com.tianzhi.shop520.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.util.EventBusManager;
import com.tianzhi.shop520.util.ExitEvent;
import com.tianzhi.shop520.util.NetworkUtil;
import com.tianzhi.shop520.util.UpdateAppManager;
import com.tianzhi.shop520.util.XPermissionUtils;

/**
 * Created by thinkpad on 2017/10/25.
 * 欢迎页
 */

public class WelcomeAct extends BaseFragmentActivity{
    private Context mContext;

    Handler handler = new Handler() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentLayout(R.layout.act_welcome);
        setShowTitle(true);
        getPermission();

//        checkNewVersion();
    }

    @Override
    protected void initView() {


    }
    // 检查新版本
    private void checkNewVersion() {
        if (NetworkUtil.isConnected(this)) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    new UpdateAppManager.Builder(mContext,WelcomeAct.this).isAutoInstall(true)
                            .isHintNewVersion(true).build()
                            .check();
                }
            });
        }
    }
    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.CALL_PHONE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
/*获取权限*/
    private void getPermission() {
//            Toast.makeText(context,"为了带给您更好的体验，请您允许开启以下权限",Toast.LENGTH_LONG).show();
        XPermissionUtils.requestPermissions(this, 1, new String[]{
                Manifest.permission.CALL_PHONE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,}, new XPermissionUtils.OnPermissionListener() {
            @Override
            public void onPermissionGranted() {
                checkNewVersion();
            }

            @Override
            public void onPermissionDenied() {
                AlertDialog alert;
                alert = new AlertDialog.Builder(context).create();
                alert.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
                alert.setMessage("您拒绝了权限，即将退出App？");//BUTTON_NEGATIVE
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "退出",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //弹出提示
                                EventBusManager.post(new ExitEvent());
                                //弹出权限被禁用的提示框
                            }
                        });
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "去设置",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                gotoMiuiPermission();
                            }
                        });
                alert.show();


            }
        });
    }
    /**
     * 跳转到miui的权限管理页面
     */
    private void gotoMiuiPermission() {
        Intent i = new Intent("miui.intent.action.APP_PERM_EDITOR");
        ComponentName componentName = new ComponentName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        i.setComponent(componentName);
        i.putExtra("extra_pkgname", getPackageName());
        try {
            startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
            gotoMeizuPermission();
        }
    }
    /**
     * 跳转到魅族的权限管理系统
     */
    private void gotoMeizuPermission() {
        Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra("packageName", getPackageName());
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            gotoHuaweiPermission();
        }
    }
    /**
     * 华为的权限管理页面
     */
    private void gotoHuaweiPermission() {
        try {
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");//华为权限管理
            intent.setComponent(comp);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            startActivity(getAppDetailSettingIntent());
        }

    }
    /**
     * 获取应用详情页面intent
     *
     * @return
     */
    private Intent getAppDetailSettingIntent() {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
        }
        return localIntent;
    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(requestCode==REQUESTPERMISSION){
//            if(permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    if(updataService!=null)
//                        startService(updataService);
//                }else{
//
////提示没有权限，安装不了咯
//                }
//            }
//        }
//    }
}

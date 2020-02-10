package com.tianzhi.shop520.ui.diyview.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.view.Display;
import android.view.WindowManager;


/**
 * Created by wisdomnt on 2017/5/10.
 */
public class DialogUtils {

    private DialogUtils dialogUtils;

    public static DialogUtils getInstance(){
        return new DialogUtils();
    }

    private FragmentActivity fragmentActivity;


    public BaseDialogNew showDialog(Activity fragmentActivity, String title, String msg){
        final Activity activity = fragmentActivity;
        BaseDialogNew mBaseDialog = BaseDialogNew.getDialog(fragmentActivity, "温馨提示", msg,
                "取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                }, "登录", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gotoLogin( activity);
                        dialog.dismiss();
                    }
                });
        WindowManager windowManager = fragmentActivity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = mBaseDialog.getWindow().getAttributes();
        lp.width = (int)(display.getWidth() * 0.9); //设置宽度
        mBaseDialog.getWindow().setAttributes(lp);
        return mBaseDialog;
    }

    private void gotoLogin(Activity activity) {
//        Intent intent = new Intent( activity, LoginAct.class);
//        activity.startActivity(intent);
    }

}

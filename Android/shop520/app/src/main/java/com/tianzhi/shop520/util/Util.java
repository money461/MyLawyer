package com.tianzhi.shop520.util;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by scott on 15/8/17.
 * 功能描述：常量工具类
 */
public class Util {
    private Context mContext;

    public Util(Context context) {
        mContext = context;
    }

    //Dp to Px
    public float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,mContext.getResources().getDisplayMetrics());
    }
    /**
     * 得到设备屏幕的宽度
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 得到设备屏幕的高度
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 得到设备的密度
     */
    public static float getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * 把密度转换为像素
     */
    public static int dip2px(Context context, float px) {
        final float scale = getScreenDensity(context);
        return (int) (px * scale + 0.5);
    }


}

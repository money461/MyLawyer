package com.tianzhi.shop520.ui.diyview.dialog;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by thinkpad on 2017/10/20.
 */

public class CustomViewPager extends ViewPager {
    private boolean isCanScroll = true;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScanScroll(boolean isCanScroll) {
        isCanScroll = isCanScroll;
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return ((isCanScroll) && (super.onInterceptTouchEvent(ev)));
    }

    public boolean onTouchEvent(MotionEvent ev) {
        return ((isCanScroll) && (super.onTouchEvent(ev)));
    }
}


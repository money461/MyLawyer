package com.tianzhi.shop520.ui.diyview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * Created by thinkpad on 2017/11/25.
 */

public class MyWebview extends WebView {
    public MyWebview(Context context) {
        super(context);
    }

    public MyWebview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyWebview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }
}
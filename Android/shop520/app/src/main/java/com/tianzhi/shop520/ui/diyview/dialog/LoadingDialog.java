package com.tianzhi.shop520.ui.diyview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.tianzhi.shop520.R;


/**
 * 加载提示框
 * 
 * @author ymx
 * 
 */
public class LoadingDialog extends Dialog {
	private View mContentView;

	public LoadingDialog(Context context) {
		this(context, R.style.LoadingDialogStyle);
	}

	public LoadingDialog(Context context, int theme) {
		super(context, theme);
		mContentView = getLayoutInflater().inflate(R.layout.view_loading,
				null);
		setContentView(mContentView);
		setCanceledOnTouchOutside(false);
	}

	public void setMessage(String message) {
		TextView messageTxt = (TextView) mContentView
				.findViewById(R.id.message);
		messageTxt.setText(message);
	}

	public void setMessage(int resId) {
		TextView messageTxt = (TextView) mContentView
				.findViewById(R.id.message);
		messageTxt.setText(resId);
	}
}

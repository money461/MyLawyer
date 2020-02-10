package com.tianzhi.shop520.ui.diyview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tianzhi.shop520.R;


public class BaseDialogNew extends Dialog implements
		View.OnClickListener {
	private Context mContext;// 上下文
	private TextView titleView;
	private TextView mMsg;
	private Button mBtnButton1;// 底部按钮1
	private Button mBtnButton2;// 底部按钮2
	private OnClickListener mOnClickListener1;// 按钮1的单击监听事件
	private OnClickListener mOnClickListener2;// 按钮2的单击监听事件
	private static BaseDialogNew mBaseDialog;//
	private LinearLayout mLayoutBottom;
	private LinearLayout mLayoutContent;// 内

	public BaseDialogNew(Context context) {
		super(context, R.style.Theme_Light_FullScreenDialogAct);
		// TODO Auto-generated constructor stub
		this.mContext = context;
		setContentView(R.layout.dialog_util_new);
		initViews();
		initEvents();
		setCancelable(true);
		setCanceledOnTouchOutside(true);
	}

	private void initEvents() {
		// TODO Auto-generated method stub
		mBtnButton1.setOnClickListener(this);
		mBtnButton2.setOnClickListener(this);
	}

	private void initViews() {
		titleView = (TextView) findViewById(R.id.dialog_title_new);
		mMsg = (TextView) findViewById(R.id.dialog_msg_new);
		mLayoutBottom = (LinearLayout) findViewById(R.id.dialog_layout_bottom_new);
		mBtnButton1 = (Button) findViewById(R.id.btn_cancel_new);
		mBtnButton2 = (Button) findViewById(R.id.btn_positive_new);
		mLayoutContent = (LinearLayout) findViewById(R.id.dialog_layout_content_new);
	}

	/**
	 * 填充新布局到内容布局
	 * 
	 * @param resource
	 */
	public void setDialogContentView(int resource) {
		View v = LayoutInflater.from(mContext).inflate(resource, null);
		if (mLayoutContent.getChildCount() > 0) {
			mLayoutContent.removeAllViews();
		}
		mLayoutContent.addView(v);
	}

	/**
	 * 填充新布局到内容布局
	 * 
	 * @param resource
	 * @param params
	 */
	public void setDialogContentView(int resource,
			LinearLayout.LayoutParams params) {
		View v = LayoutInflater.from(mContext).inflate(resource, null);
		if (mLayoutContent.getChildCount() > 0) {
			mLayoutContent.removeAllViews();
		}
		mLayoutContent.addView(v, params);
	}

	public static BaseDialogNew getDialog(Context context, CharSequence title,
                                          CharSequence message) {
		return getDialog(context, title, message, null, null, null, null);
	}

	public static BaseDialogNew getDialog(Context context, CharSequence title,
                                          CharSequence message, CharSequence button1,
                                          OnClickListener listener1) {
		return getDialog(context, title, message, button1, listener1, null,
				null);
	}

	public static BaseDialogNew getDialog(Context context, CharSequence title,
                                          CharSequence message, CharSequence button1,
                                          OnClickListener listener1, CharSequence button2,
                                          OnClickListener listener2) {
		mBaseDialog = new BaseDialogNew(context);
		if (mBaseDialog.titleAndMessageIsExist(title, message)) {
			mBaseDialog.setTitle(title);
			mBaseDialog.setMessage(message);
		}
		if (mBaseDialog.buttonIsExist(button1, listener1, button2, listener2)) {
			mBaseDialog.setButton1(button1, listener1);
			mBaseDialog.setButton2(button2, listener2);
		}
		mBaseDialog.setCancelable(true);
		mBaseDialog.setCanceledOnTouchOutside(false);
		return mBaseDialog;
	}

	public boolean buttonIsExist(CharSequence button1,
			OnClickListener listener1, CharSequence button2,
			OnClickListener listener2) {
		if ((button1 != null && listener1 != null)
				|| (button2 != null && listener2 != null)) {
			mLayoutBottom.setVisibility(View.VISIBLE);
			return true;
		} else {
			mLayoutBottom.setVisibility(View.GONE);
			return false;
		}
	}

	public boolean titleAndMessageIsExist(CharSequence title,
			CharSequence message) {
		if (title == null && message == null) {
			return false;
		} else {
			return true;
		}
	}

	public void setTitle(CharSequence text) {
		if (text != null) {
			titleView.setText(text);
		} else {
			titleView.setVisibility(View.GONE);
		}
	}

	public void setMessage(CharSequence text) {
		if (text != null) {
			mMsg.setVisibility(View.VISIBLE);
			mMsg.setText(text);
		} else {
			mMsg.setVisibility(View.GONE);
		}
	}

	public void setButton1(CharSequence text,
			OnClickListener listener) {
		if (text != null && listener != null) {
			mLayoutContent.setBackgroundResource(R.color.transparent_white);
			mLayoutBottom.setVisibility(View.VISIBLE);
			mBtnButton1.setVisibility(View.VISIBLE);
			mBtnButton1.setText(text);
			mOnClickListener1 = listener;
		} else {
			mBtnButton1.setVisibility(View.GONE);
		}
	}

	public void setButton2(CharSequence text,
			OnClickListener listener) {
		if (text != null && listener != null) {
			mLayoutContent.setBackgroundResource(R.color.transparent_white);
			mLayoutBottom.setVisibility(View.VISIBLE);
			mBtnButton2.setVisibility(View.VISIBLE);
			mBtnButton2.setText(text);
			mOnClickListener2 = listener;
		} else {
			mBtnButton2.setVisibility(View.GONE);
		}
	}

	public void setButton1Background(int id) {
		mBtnButton1.setBackgroundResource(id);
	}

	public void setButton2Background(int id) {
		mBtnButton2.setBackgroundResource(id);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cancel_new:
			if (mOnClickListener1 != null) {
				mOnClickListener1.onClick(mBaseDialog, 0);
			}
			break;

		case R.id.btn_positive_new:
			if (mOnClickListener2 != null) {
				mOnClickListener2.onClick(mBaseDialog, 1);
			}
			break;
		default:
			break;
		}
	}
}

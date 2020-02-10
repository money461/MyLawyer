package com.tianzhi.shop520.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Field;

public class ViewUtil {

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public static void setBackgroundOfVersion(View view, Drawable drawable) {
		if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
			view.setBackground(drawable);
		else
			view.setBackgroundDrawable(drawable);
	}

	public static void setListViewHeightBasedOnChildren(ListView listView , int px) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
			LogUtils.w( "LXZ - TAG:ViewUtil ", "setListViewHeightBasedOnChildren Line 40 -_-! : " + totalHeight + "    " + listItem.getMeasuredHeight());
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1))+px;
		listView.setLayoutParams( params );
	}

	public static void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
			LogUtils.w( "LXZ - TAG:ViewUtil ", "setListViewHeightBasedOnChildren Line 40 -_-! : " + totalHeight + "    " + listItem.getMeasuredHeight());
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

	public static void setGridViewHeightBasedOnChildren(GridView gridView) {
		ListAdapter listAdapter = gridView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int rows;
		int columns=0;
		int horizontalBorderHeight=0;
		Class<?> clazz=gridView.getClass();
		try {
			Field column=clazz.getDeclaredField("mRequestedNumColumns");
			column.setAccessible(true);
			columns=(Integer)column.get(gridView);
			Field horizontalSpacing=clazz.getDeclaredField("mRequestedHorizontalSpacing");
			horizontalSpacing.setAccessible(true);
			horizontalBorderHeight=(Integer)horizontalSpacing.get(gridView);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(listAdapter.getCount()%columns>0){
			rows=listAdapter.getCount()/columns+1;
		}else {
			rows=listAdapter.getCount()/columns;
		}
		int totalHeight = 0;
		for (int i = 0; i < rows; i++) {
			View listItem = listAdapter.getView(i, null, gridView);
			listItem.measure(0, 0);
//			totalHeight += listItem.getMeasuredHeight();
			totalHeight += listItem.getLayoutParams().height;
		}
		ViewGroup.LayoutParams params = gridView.getLayoutParams();
		params.height = totalHeight+horizontalBorderHeight*(rows-1);
		gridView.setLayoutParams(params);
	}

	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	/**
	 * 金额输入框长度最长为8位，小数点后最多有2位
	 *
	 * @param editText
	 */
	public static void setEditMoneyListener(EditText editText) {
		final boolean isFlag = false;
		editText.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable edt) {
				setEditMoneyStantard(edt, isFlag);

			}

			public void beforeTextChanged(CharSequence arg0, int arg1,
										  int arg2, int arg3) {
			}

			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
									  int arg3) {
			}
		});
	}

	/**
	 * 规范输入框金额
	 *
	 * @param edt
	 * @param isFlag
	 */
	public static void setEditMoneyStantard(Editable edt, boolean isFlag) {
		boolean isChanged = isFlag;
		if (isChanged) {// 如果字符未改变则返回
			return;
		}
		String cuttedStr = edt.toString();
		isChanged = true;
		int i = cuttedStr.length();
		if (i <= 0) {
			isChanged = false;
			return;
		}
		// 非0开头的7个数字小数点后只有2个小数
		if (!cuttedStr
				.matches("^(([1-9]{1}\\d{0,6})|([0]{1}))(\\.(\\d){0,2})?$")) {
			if ("0".equals(cuttedStr.substring(0, 1))) {
				if (".".equals(cuttedStr.substring(1, 2))) {
					edt.delete(i - 1, i);
				} else {
					edt.delete(0, 1);
				}
			} else {
				edt.delete(i - 1, i);
			}
		}
		isChanged = false;
	}

	public static TimeCount initBtnCode(Button btn_code) {
		TimeCount timeCount = new TimeCount(Constants.BASE_SEND_CODE_TIME,
				1000);
		timeCount.setmBtnCode(btn_code);
		return timeCount;
	}

	public static TimeCount initBtnCode(Button btn_code, int time) {
		TimeCount timeCount = new TimeCount(time, 1000);
		timeCount.setmBtnCode(btn_code);
		return timeCount;
	}

	public static class TimeCount extends CountDownTimer {
		private TextView mBtnCode;

		public void setmBtnCode(TextView mBtnCode) {
			this.mBtnCode = mBtnCode;
		}

		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
		}

		@Override
		public void onFinish() {// 计时完毕时触发
			mBtnCode.setText("重新发送");
			mBtnCode.setClickable(true);
//			mBtnCode.setBackgroundResource(R.drawable.button_cfan_220px);
		}

		@Override
		public void onTick(long millisUntilFinished) {// 计时过程显示
			mBtnCode.setClickable(false);
			mBtnCode.setText(millisUntilFinished / 1000 + "秒");
		}

	}

	/**
	 * 获取控件宽
	 *
	 * @param view
	 *            控件
	 * @return
	 */
	public static int getViewWidth(View view) {
		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		view.measure(w, h);
		return (view.getMeasuredWidth());
	}

	/**
	 * 获取控件高
	 *
	 * @param view
	 *            控件
	 * @return
	 */
	public static int getViewHeight(View view) {
		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		view.measure(w, h);
		return (view.getMeasuredHeight());
	}
	
	
	/**
	 * 防止 按钮对此点击
	 * */
	private static long lastClickTime;
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();   
        if ( time - lastClickTime < 500) {   
            return true;   
        }   
        lastClickTime = time;   
        return false;   
    }
//    /**
//	 * 添加水平分割线
//	 * */
//    public static View addDividerView(Context context){
//    	View view=new View(context);
//    	LayoutParams layoutParams=new LayoutParams(LayoutParams.MATCH_PARENT,2);
//    	layoutParams.setMargins(DisplayUtils.dip2px(context, 15), DisplayUtils.dip2px(context, 10), DisplayUtils.dip2px(context, 15), 0);
//    	view.setLayoutParams(layoutParams);
//    	view.setBackground(context.getDrawable(R.drawable.divider));
//		return view;
//    }
}

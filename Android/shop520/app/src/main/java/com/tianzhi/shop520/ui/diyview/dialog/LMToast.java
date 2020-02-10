package com.tianzhi.shop520.ui.diyview.dialog;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tianzhi.shop520.APPLICATION;
import com.tianzhi.shop520.R;


public class LMToast {

	/**
	 * 
	 * @param message
	 * @param picId
	 */
	public static void showToast(String message, int picId) {
		Toast toast = new Toast(APPLICATION.getContext());
		View toastView = LayoutInflater.from(APPLICATION.getContext()).inflate(
				R.layout.view_toast_msg_pic, null);
		((ImageView) toastView.findViewById(R.id.tips_icon))
				.setImageResource(picId);
		((TextView) toastView.findViewById(R.id.tips_msg)).setText(message);
		toast.setView(toastView);
		toast.setGravity(Gravity.NO_GRAVITY, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.show();
	}

	/**
	 * 系统资源文件引用Toast
	 * 
	 * @param resId
	 *            文字
	 * @param picId
	 *            图片
	 */
	public static void showToast(int resId, int picId) {
		Toast toast = new Toast(APPLICATION.getContext());
		View toastView = LayoutInflater.from(APPLICATION.getContext()).inflate(
				R.layout.view_toast_msg_pic, null);
		((ImageView) toastView.findViewById(R.id.tips_icon))
				.setImageResource(picId);
		((TextView) toastView.findViewById(R.id.tips_msg)).setText(APPLICATION
				.getContext().getString(resId));
		toast.setView(toastView);
		toast.setGravity(Gravity.NO_GRAVITY, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.show();
	}

	public static void showToast(String message) {
		Toast toast = new Toast(APPLICATION.getContext());
		View toastView = LayoutInflater.from(APPLICATION.getContext()).inflate(
				R.layout.view_toast_msg, null);
		((TextView) toastView.findViewById(R.id.tips_msg)).setText(message);
		toast.setView(toastView);
		toast.setGravity(Gravity.BOTTOM, 0, 200);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.show();
	}

	public static void showToast(int resId) {
		Toast toast = new Toast(APPLICATION.getContext());
		View toastView = LayoutInflater.from(APPLICATION.getContext()).inflate(
				R.layout.view_toast_msg, null);
		((TextView) toastView.findViewById(R.id.tips_msg)).setText(APPLICATION
				.getContext().getString(resId));
		toast.setView(toastView);
		toast.setGravity(Gravity.BOTTOM, 0, 200);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.show();
	}
}

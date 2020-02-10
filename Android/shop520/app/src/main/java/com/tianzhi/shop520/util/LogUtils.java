package com.tianzhi.shop520.util;

import android.util.Log;

public class LogUtils {
	private static boolean isDebug = false;

	public static <T> void d(String tag, T msg) {
		if (isDebug) {
			Log.d(tag, String.valueOf(msg));
		}
	}

	public static <T> void e(String tag, T msg) {
		if (isDebug) {
			Log.e("    "+tag, String.valueOf(msg));
		}
	}

	public static <T> void e(String tag, T msg, Throwable tr) {
		if (isDebug) {
			Log.e(tag, String.valueOf(msg), tr);
		}
	}

	public static <T> void i(String tag, T msg) {
		if (isDebug) {
			Log.i(tag, String.valueOf(msg));
		}
	}

	public static <T> void v(String tag, T msg) {
		if (isDebug) {
			Log.v(tag, String.valueOf(msg));
		}
	}

	public static <T> void w(String tag, T msg) {
		if (isDebug) {
			Log.w(tag, String.valueOf(msg));
		}
	}
}
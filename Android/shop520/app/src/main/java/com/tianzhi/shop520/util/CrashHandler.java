package com.tianzhi.shop520.util;

import android.content.Context;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;

/**
 * 当程序发生异常时，设置APP友好退出
 * @author Tony
 *
 */
public class CrashHandler implements UncaughtExceptionHandler {

//	private final String mType = android.os.Build.MODEL;
//	private final String mTyb = android.os.Build.BRAND;
//	private final String mOsVersion = android.os.Build.VERSION.RELEASE;
	String mAppVersion;
	@SuppressWarnings("unused")
	private UncaughtExceptionHandler defaultExHandler;
	@SuppressWarnings("unused")
	private Context context;
	private static CrashHandler instance;

	public CrashHandler() {
		defaultExHandler = Thread.getDefaultUncaughtExceptionHandler();
	}

	/**
	 * 同步方法，以免单例多线程环境下出现异常
	 * 
	 * @return
	 */
	public synchronized static CrashHandler getInstance() {
		if (instance == null) {
			instance = new CrashHandler();
		}
		return instance;
	}

	/**
	 * 初始化
	 */
	public void init(Context ctx) {
		Thread.setDefaultUncaughtExceptionHandler(this);
		this.context = ctx;
	}

	/**
	 * 处理可截获异常
	 * @param thread
	 * @param ex
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		Log.e("异常信息： ", getErrorInfo(ex));
		android.os.Process.killProcess(android.os.Process.myPid());		//设置友好退出
		System.exit(0);
	}

	/**
	 * 获取异常的具体信息
	 * 
	 * @param arg1
	 * @return
	 */
	private String getErrorInfo(Throwable arg1) {
		Writer writer = new StringWriter();
		PrintWriter pw = new PrintWriter(writer);
		arg1.printStackTrace(pw);
		pw.close();
		String error = writer.toString();
		return error;
	}
}

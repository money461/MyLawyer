package com.tianzhi.shop520;

import android.app.Application;
import android.content.Context;

import com.mob.MobSDK;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tianzhi.shop520.ui.diyview.dialog.LMToast;
import com.tianzhi.shop520.util.Constants;
import com.tianzhi.shop520.util.CrashHandler;

import java.util.ArrayList;


public class APPLICATION extends Application {//
	/** Application context */
	private static Context context;
	/** Application object */
	public static APPLICATION application;
	/** 双击退出计时 */
	private long exitTime = 0;

	private static String[][] cityArrs;
	
	public static boolean isReceiveNum = true;
	public static int receiveNum = 0;
	public static int isOneHundred = 0;
	public static boolean isTried = false;
	public static ArrayList<String> appCodeList = null;
	public static String wxOid = null;
	public static String wxWebOrder = null;
	public static boolean isRefreshTimeShow = false;
	// IWXAPI 是第三方app和微信通信的openapi接口
	private IWXAPI api;
	public static boolean isLogin = false;
	@Override
	public void onCreate() {
		super.onCreate();
		APPLICATION.context = this;
		CrashHandler handler = CrashHandler.getInstance();
		handler.init(getApplicationContext());
		MobSDK.init(context,"21b8fadfeae90","723a66a845963ec1dcf52fb6631c3462");
// 通过WXAPIFactory工厂，获取IWXAPI的实例
		api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, false);
// 将该app注册到微信
		api.registerApp(Constants.APP_ID);
	}

	/**
	 * 获取App Application object
	 * 
	 * @return
	 */
	public static APPLICATION getInstance() {
		if (null == application) {
			application = new APPLICATION();
		}
		return application;
	}

	/**
	 * 获取App Context
	 * 
	 * @return
	 */
	public static Context getContext() {
		return context;
	}

	/**
	 * 双击返回键退出
	 */
	public void exitBy2Click() {
		if ((System.currentTimeMillis() - exitTime) > 2000) {
			LMToast.showToast(R.string.exit_app);
			exitTime = System.currentTimeMillis();
		} else {
			AtyMgr.getAppManager().AppExit(this);
		}
	}

	public static String[][] getCityArrs() {
		return cityArrs;
	}

	public static void setCityArrs(String[][] cityArrs) {
		APPLICATION.cityArrs = cityArrs;
	}

	public static int getIsOneHundred() {
		return isOneHundred;
	}

	public static void setIsOneHundred(int isOneHundred) {
		APPLICATION.isOneHundred = isOneHundred;
	}


	@Override
	public void onTerminate() {
		super.onTerminate();
	}
}

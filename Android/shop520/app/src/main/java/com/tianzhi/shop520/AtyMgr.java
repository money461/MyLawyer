package com.tianzhi.shop520;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.util.Stack;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 * 
 * @author ymx
 */
public class AtyMgr {

	private static Stack<Activity> activityStack;
	private static AtyMgr instance;

	private AtyMgr() {
	}

	/**
	 * 单一实例
	 */
	public static AtyMgr getAppManager() {
		if (instance == null) {
			instance = new AtyMgr();
		}
		return instance;
	}

	/**
	 * 添加Activity到堆栈
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	public int getAtyStackSize(){
		Log.e("msg","stackSize: " + (activityStack == null ? 0 : activityStack.size()));
		return activityStack == null ? 0 : activityStack.size();
	}

	/**
	 * 获取当前Activity（堆栈中最后一个压入的）
	 */
	public Activity currentActivity() {
		Activity activity = activityStack.lastElement();
		return activity;
	}

	/**
	 * 结束当前Activity（堆栈中最后一个压入的）
	 */
	public void finishActivity() {
		try {
			Activity activity = activityStack.lastElement();
			finishActivity(activity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 结束指定的Activity
	 */
	public void finishActivity(Activity activity) {
		if (activity != null) {
			activityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}

	/**
	 * 结束指定类名的Activity
	 */
	public void finishActivity(Class<?> cls) {
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(cls)) {
				finishActivity(activity);
			}
		}
	}

	/**
	 * 结束所有Activity
	 */
	public void finishAllActivity() {
		if(activityStack == null){
			return;
		}
		for (int i = 0, size = activityStack.size(); i < size; i++) {
			if (null != activityStack.get(i)) {
				activityStack.get(i).finish();
			}
		}
		activityStack.clear();
	}

	/**
	 * 退出应用程序
	 */
	public void AppExit(Context context) {
		try {
			finishAllActivity();
			ActivityManager activityMgr = (ActivityManager) context
					.getSystemService(Context.ACTIVITY_SERVICE);
			activityMgr.killBackgroundProcesses(context.getPackageName());
			System.exit(0);
		} catch (Exception e) {
		}
	}
	
	// 退出栈顶Activity
		public void popActivity(Activity activity) {
			if (activity != null) {
				activity.finish();
				activityStack.remove(activity);
				activity = null;
			}
		}
		
		// 退出栈中所有Activity
		public void popAllActivityExceptOne() {
			while (true) {
				Activity activity = currentActivity();
				if (activity == null) {
					break;
				}
				popActivity(activity);
			}
		}
}
package com.tianzhi.shop520.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.callback.DialogCallback;
import com.tianzhi.shop520.entity.AppInfo;
import com.tianzhi.shop520.entity.BaseResponse;
import com.tianzhi.shop520.ui.MainActivity;
import com.tianzhi.shop520.ui.StartGuideActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;


/**
 * Update app manager Usage:
 * 
 * <pre>
 * new UpdateManager.Builder(this).isHintNewVersion(true).isAutoInstall(true)
 * 		.build().check();
 * </pre>
 * 
 * @author markmjw
 * @date 2015-01-07
 */
@SuppressWarnings("deprecation")
public class UpdateAppManager implements Handler.Callback {
	private static final String TAG = UpdateAppManager.class.getSimpleName();

	private Context mContext;
    private Activity activity;
	private boolean isAutoInstall;//是否自动更新
	private boolean isHintNewVersion;//是否提示新版本

	private NotificationManager mNotificationManager;
	private NotificationCompat.Builder mNotificationBuilder;

	private static final int UPDATE_NOTIFICATION_PROGRESS = 0x1;//通知更新进度
	private static final int COMPLETE_DOWNLOAD_APK = 0x2;//下载完成
	private static final int DOWNLOAD_NOTIFICATION_ID = 0x3;//下载通知ID

	private static final String SUFFIX = ".apk";//后缀名
	private static final String APK_PATH = "APK_PATH";//apk地址
	private static final String APP_NAME = "APP_NAME";//apk名字

	private static final String TYPE_DIALOG = "dialog";//弹出框类型
	private static final String TYPE_NOTIFY = "notify";//通知类型

	private HashMap<String, String> mCache = new HashMap<String, String>();

	private Handler mHandler;

	private ProgressDialog mProgressDialog;

//	private APP6110401 app6110401 = new APP6110401();//app版本检查接口
	private String versionCode;//版本号
    PackageManager manager;
	File apkFile;
    PackageInfo info = null;
	AppInfo appInfo;
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			anima();
		}
	};
	private UpdateAppManager(Builder builder) {
		this.mContext = builder.context;
		this.isAutoInstall = builder.isAutoInstall;
		this.isHintNewVersion = builder.isHintNewVersion;
        this.activity = builder.activity;
		mNotificationManager = (NotificationManager) mContext
				.getSystemService(Context.NOTIFICATION_SERVICE);
		mHandler = new Handler(this);
		getAppVersion();
	}

	/**
	 * 获取版本号
	 * */
	private void getAppVersion() {
		try {
			PackageManager pm = mContext.getPackageManager();
			PackageInfo pinfo = pm.getPackageInfo(mContext.getPackageName(),
					PackageManager.GET_CONFIGURATIONS);
			versionCode = pinfo.versionCode+"";
		} catch (NameNotFoundException e) {
			versionCode = "1.0.0";
		}
	}

	/**
	 * 接收handle消息处理执行下一步操作
	 * */
	@Override
	public boolean handleMessage(Message msg) {
		String type = (String) msg.obj;
		switch (msg.what) {
		case UPDATE_NOTIFICATION_PROGRESS:
			if (TYPE_DIALOG.equals(type)) {
				showProgress("正在下载，已完成：" + msg.arg1 + "%", false);
			} else {
				showDownloadNotification(msg.arg1);
			}
			break;

		case COMPLETE_DOWNLOAD_APK:
			if (TYPE_DIALOG.equals(type)) {
				dismissProgress();

				autoInstallApk();
				EventBusManager.post(new ExitEvent());
			} else {
				installApk();
			}
			break;
		}
		return true;
	}

	public void check() {
		if (!isHintNewVersion) {
			shortToast("正在检查更新...");
		}
        OkGo.get(BaseConstant.TestUrl+BaseConstant.APPVERISON)
//				.execute(new StringCallback() {
//					@Override
//					public void onSuccess(String s, Call call, Response response) {
//					LogUtils.e("版本检查",s);
//					}
//				});
                .execute(new DialogCallback<BaseResponse<AppInfo>>(activity,false) {
                    @Override
                    public void onSuccess(BaseResponse<AppInfo> appInfoBaseResponse, Call call, Response response) {
                        super.onSuccess(appInfoBaseResponse, call, response);
                        appInfo = appInfoBaseResponse.data;
                        manager = mContext.getPackageManager();
                        try {
                            info = manager.getPackageInfo(mContext.getPackageName(), 0);
                        } catch (NameNotFoundException e) {
                            e.printStackTrace();
                        }
                        if(info.versionCode< appInfo.sort){
//							appInfo.isForce = "1";//强制跟新
                            boolean isForce = "1"
                                    .equals(appInfo.isForce);
                            String note =appInfo.updatedContent;
                            String url = appInfo.down;
							LogUtils.e("更新信息",note);
                            showUpdateDialog(note, url, isForce);//如果upd_flag等于0为有新版本，而且must_upd等于1为必须更新就调用更新对话框
                        }else {
							handler.sendEmptyMessageDelayed(0, 500);
						}
                    }
                });
	}
	/**
	 * 跳转动画
	 */
	public void anima() {
		Intent intent = new Intent();
		intent.putExtra("page", "0");
		if (isFirst()) {
			intent.setClass(mContext,StartGuideActivity.class);
		} else {
			intent.setClass(mContext,MainActivity.class);
		}
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.fade, R.anim.hold);// 进入和出去的动画效果
		activity.finish();
	}
	/**
	 * 判断是否是首次启动
	 */
	private Boolean isFirst() {
		int count =  AppShared.getInstance(mContext).getInt( "isFirst");

		// 如果是首次登录则进入引导页
		if (count == 0) {
			AppShared.getInstance(mContext).saveInt( "isFirst", 1);
			return true;
		} else {
			AppShared.getInstance(mContext).saveInt( "isFirst", 2);
			return false;
		}
	}
	/**
	 * 显示更新对话框
	 * message 版本类容
	 * url 下载地址
	 * isForce 是否必须更新
	 * */
	private void showUpdateDialog(String message, final String url,
			final boolean isForce) {
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setTitle("发现新版本");
		if(!TextUtils.isEmpty(message)){
			builder.setMessage(message.replace("|","\n"));
		}

//		builder.setCancelable(false);

		builder.setPositiveButton(isForce ? "退出程序" : "暂不更新",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						if (isForce) {
							EventBusManager.post(new ExitEvent());
						}
					}
				});

		builder.setNegativeButton("立即更新",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						if (NetworkUtil.isConnected(mContext)) {
							NetworkUtil.NetworkState state = NetworkUtil
									.getNetworkState(mContext);
							 if (NetworkUtil.NetworkState.MOBILE == state) {
							 showNetDialog(url, isForce);
							 } else {
							new DownloadTask(isForce ? TYPE_DIALOG
									: TYPE_NOTIFY).execute(url);
							 }
						}
					}
				});

		AlertDialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(false);
		dialog.setCancelable(false);//设置dialog不可被取消，强制更新或退出程序
		dialog.show();
	}

	private void showNetDialog(final String url, final boolean isForce) {
		AlertDialog.Builder netBuilder = new AlertDialog.Builder(mContext);
		netBuilder.setTitle("下载提示");
		netBuilder.setMessage("您在目前的网络环境下继续下载将可能会消耗手机流量，请确认是否继续下载？");
		netBuilder.setPositiveButton("取消下载",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		netBuilder.setNegativeButton("继续下载",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						new DownloadTask(isForce ? TYPE_DIALOG : TYPE_NOTIFY)
								.execute(url);
					}
				});
		AlertDialog netDialog = netBuilder.create();
		netDialog.setCanceledOnTouchOutside(false);
		netDialog.show();
	}

	private void showDownloadNotification(final int progress) {
		if (mContext != null) {
			String contentText = progress + "%";
			PendingIntent contentIntent = PendingIntent.getActivity(mContext,
					0, new Intent(), PendingIntent.FLAG_CANCEL_CURRENT);

			if (mNotificationBuilder == null) {
				mNotificationBuilder = new NotificationCompat.Builder(mContext)
						.setSmallIcon(mContext.getApplicationInfo().icon)
						.setTicker("开始下载...").setContentTitle(getAppName())
						.setContentIntent(contentIntent);
			}

			mNotificationBuilder.setContentText(contentText);
			mNotificationBuilder.setProgress(100, progress, false);
			mNotificationManager.notify(DOWNLOAD_NOTIFICATION_ID,
					mNotificationBuilder.build());
		}
	}

	private String getAppName() {
		return mContext.getString(R.string.app_name);
	}

	private PackageInfo getPackageInfo() {
		PackageInfo info = null;
		if (mContext != null) {
			try {
				info = mContext.getPackageManager().getPackageInfo(
						mContext.getPackageName(), 0);
			} catch (NameNotFoundException e) {
				e.printStackTrace();
				info = new PackageInfo();
				info.packageName = mContext.getPackageName();
				info.versionName = "1.0";
			}
		}
		return info;
	}

	private void installApk() {
		if (UpdateAppManager.this.isAutoInstall) {
			autoInstallApk();
		} else {
			if (mNotificationBuilder == null) {
				mNotificationBuilder = new NotificationCompat.Builder(mContext);
			}
			mNotificationBuilder
					.setSmallIcon(mContext.getApplicationInfo().icon)
					.setContentTitle(getAppName()).setContentText("下载完成，点击安装")
					.setTicker("任务下载完成");

			Intent intent = getFileIntent(mContext,apkFile);
			PendingIntent pendingIntent = PendingIntent.getActivity(mContext,
					0, intent, 0);
			mNotificationBuilder.setContentIntent(pendingIntent);
			mNotificationManager.notify(DOWNLOAD_NOTIFICATION_ID,
					mNotificationBuilder.build());
		}
	}
	/**
	 * 获得调用系统应用打开文件的intent
	 *
	 * @param context
	 * @param file
	 */
	public static Intent getFileIntent(Context context, File file) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_GRANT_READ_URI_PERMISSION);
		Uri contentUri;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			//因为注册的时候authorities填的是包名...
			String authority = context.getPackageName()+".fileProvider";
			//7.0上必须这样获得Intent
			contentUri = FileProvider.getUriForFile(context, authority, file);
		} else {
			//7.0以下使用旧版本的方式
			contentUri = Uri.fromFile(file);
		}
		String MimeType = context.getContentResolver().getType(contentUri);
		intent.setDataAndType(contentUri, MimeType);
		return intent;
	}
	private void autoInstallApk() {

        if(Build.VERSION.SDK_INT>=24) {//判读版本是否在7.0以上
            Uri apkUri = FileProvider.getUriForFile(mContext, "com.tianzhi.shop520.fileProvider", apkFile);//在AndroidManifest中的android:authorities值
			LogUtils.e("安装路径",apkUri.toString());
            Intent install = new Intent(Intent.ACTION_VIEW);
            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			install.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);//增加读写权限
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//添加这一句表示对目标应用临时授权该Uri所代表的文件
            install.setDataAndType(apkUri, "application/vnd.android.package-archive");
            mContext.startActivity(install);
            mNotificationManager.cancel(DOWNLOAD_NOTIFICATION_ID);
        } else{
			LogUtils.e("安装路径",Uri.parse("file://" + mCache.get(APK_PATH)));
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setDataAndType(Uri.parse("file://" + mCache.get(APK_PATH)), "application/vnd.android.package-archive");
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			mContext.startActivity(i);
			mNotificationManager.cancel(DOWNLOAD_NOTIFICATION_ID);
        }
	}

	private void shortToast(String content) {
		Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
	}

	private void shortToast(int content) {
		Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
	}

	private void dismissProgress() {
		if (null != mProgressDialog && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
	}

	public void showProgress(String message, boolean cancelAble) {
		if (TextUtils.isEmpty(message)) {
			return;
		}

		if (null == mProgressDialog) {
			mProgressDialog = new ProgressDialog(mContext);
			mProgressDialog.setCanceledOnTouchOutside(false);
		}

		mProgressDialog.setCancelable(cancelAble);
		mProgressDialog.setMessage(message);
		if (!mProgressDialog.isShowing()) {
			mProgressDialog.show();
		}
	}

	private class DownloadTask extends AsyncTask<String, Integer, Boolean> {
		private String mProgressType;

		public DownloadTask(String progressType) {
			mProgressType = progressType;
		}

		@SuppressWarnings("deprecation")
		@Override
		protected Boolean doInBackground(String... params) {
			@SuppressWarnings("deprecation")
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(params[0]);
			try {
				HttpResponse response = httpClient.execute(httpGet);
				if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
					LogUtils.e( TAG, "APK路径出错，请检查服务端配置接口。" );
					return false;
				} else {
					HttpEntity entity = response.getEntity();
					InputStream inputStream = entity.getContent();
					long total = entity.getContentLength();
					PackageInfo info = getPackageInfo();
					String name = info.packageName + "-v" + info.versionName
							+ SUFFIX;
					String path = FileUtil
							.getPathByType(FileUtil.DIR_TYPE_DOWNLOAD)
							+ "/"
							+ name;
					LogUtils.e("下载路径",path);
					mCache.put(APP_NAME, getAppName());
					mCache.put(APK_PATH, path);
					 apkFile = new File(path);
					if (apkFile.exists()) {
						apkFile.delete();
					}
//					boolean mkdirs=apkFile.mkdirs();
//					LogUtils.e(tag,"----"+mkdirs+"----");
					FileOutputStream fos = new FileOutputStream(apkFile);
					byte[] buf = new byte[1024];
					int count = 0;
					int length;
					while ((length = inputStream.read(buf)) != -1) {
						fos.write(buf, 0, length);
						count += length;
						int progress = (int) ((count / (float) total) * 100);
						if (progress % 5 == 0) {
							Message message = new Message();
							message.what = UPDATE_NOTIFICATION_PROGRESS;
							message.arg1 = progress;
							message.obj = mProgressType;
							mHandler.sendMessage(message);
						}
					}
					inputStream.close();
					fos.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}

		@Override
		protected void onPostExecute(Boolean flag) {
			if (flag) {
				Message message = new Message();
				message.what = COMPLETE_DOWNLOAD_APK;
				message.obj = mProgressType;
				mHandler.sendMessage(message);
			} else {
				LogUtils.e( TAG, "下载失败" );
			}
		}
	}

	public static class Builder {
		private Context context;
        private Activity activity;
		private boolean isAutoInstall = true;
		private boolean isHintNewVersion = true;

		public Builder(Context ctx,Activity activity) {
			this.context = ctx;
            this.activity = activity;
		}

		public Builder isAutoInstall(boolean isAuto) {
			this.isAutoInstall = isAuto;
			return this;
		}

		public Builder isHintNewVersion(boolean isHint) {
			this.isHintNewVersion = isHint;
			return this;
		}

		public UpdateAppManager build() {
			return new UpdateAppManager(this);
		}
	}
}
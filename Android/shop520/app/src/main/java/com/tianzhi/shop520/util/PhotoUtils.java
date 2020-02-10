package com.tianzhi.shop520.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.util.UUID;

public class PhotoUtils {
	/**
	 * 相册的RequestCode
	 * 
	 */
	public static final int INTENT_REQUEST_CODE_ALBUM = 0;
	/**
	 * 照相的RequestCode
	 * 
	 */
	public static final int INTENT_REQUEST_CODE_CAMERA = 1;
	/**
	 * 裁剪照片的RequestCode
	 * 
	 */
	public static final int INTENT_REQUEST_CODE_CROP = 2;

	/**
	 * 通过手机相册获取图片
	 * 
	 * @param activity
	 */
	public static void selectPhoto(Activity activity) {
		Intent intent = new Intent(Intent.ACTION_PICK, null);
		intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				"image/*");
		activity.startActivityForResult(intent, INTENT_REQUEST_CODE_ALBUM);
	}

	/**
	 * 通过手机照相获取图片
	 * 
	 * @param activity
	 * @return 照相后图片的路径
	 */
	public static String takePicture(Activity activity,Context context) {
		AppUtil.createDirFile(PathManager.DOWNLOAD_FILES_DIR);
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		String path = PathManager.DOWNLOAD_FILES_DIR
				+ UUID.randomUUID().toString() ;
		File file = AppUtil.createNewFile(path);
		if (file != null) {
			Uri imageUri;
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//Android7.0 适配
				String authority = context.getPackageName() + ".fileProvider";
				imageUri = FileProvider.getUriForFile(context, authority,file);
			} else {
				imageUri = Uri.fromFile(file);
			}
			intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		}
		activity.startActivityForResult(intent, INTENT_REQUEST_CODE_CAMERA);
		return path;
	}

	/**
	 * 裁剪图片
	 * 
	 * @param context
	 * @param activity
	 *
	 *            需要裁剪的图片路径
	 */
	public static void cropPhoto(Context context, Activity activity, Uri mUri) {
		Intent intent = new Intent();
		intent.setAction("com.android.camera.action.CROP");
		intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
		intent.setDataAndType(mUri, "image/*");// mUri是已经选择的图片Uri
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);// 裁剪框比例
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 150);// 输出图片大小
		intent.putExtra("outputY", 150);
		intent.putExtra("return-data", true);
		activity.startActivityForResult(intent, INTENT_REQUEST_CODE_CROP);
	}
	/**
	 * 裁剪图片
	 *
	 * @param context
	 * @param activity
	 *            需要裁剪的图片路径
	 */
	public static void cropPhotoDeful(Context context, Activity activity, Uri mUri) {
		Intent intent = new Intent();

		intent.setAction("com.android.camera.action.CROP");
		intent.setDataAndType(mUri, "image/*");// mUri是已经选择的图片Uri
		intent.putExtra("crop", "true");
//		intent.putExtra("aspectX", 1);// 裁剪框比例
//		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 325);// 输出图片大小
		intent.putExtra("outputY", 250);
		intent.putExtra("return-data", true);
		activity.startActivityForResult(intent, INTENT_REQUEST_CODE_CROP);
	}
}

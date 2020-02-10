package com.tianzhi.shop520.util;

import android.os.Environment;

import java.io.File;

public class PathManager {
//	public static final String APP_ROOT_DIR =  "/data/data/com.tianzhi.shop520/cache";
	public static final String APP_ROOT_DIR =   Environment.getExternalStorageDirectory()+"/love520";
	public static final String UPLOAD_FILES_DIR =getSdCardRootPath2() + "uploadfile/";
	public static final String SPEECH_FILES_DIR =getSdCardRootPath2() + "speech/";
	public static final String DOWNLOAD_FILES_DIR = getSdCardRootPath2() + "picture/";
	public static final String IMAGES_CACHE_DIR = getSdCardRootPath();
	public static final String SAVE_DIR = getSdCardRootPath2();
	public static final String APK_DIR = getSdCardRootPath2() + "newapk/";
	public static final String DOWN_DIR = getSdCardRootPath2() + "save/";
	public static final String WELCOME_IMG_NAME="welcome.jpg";
	
	/**
	 * 获取缓存图片目录路径
	 * 
	 * @return
	 */
	public static String getSdCardRootPath() {
		if (isSdCardExit()) {
			return Environment.getExternalStorageDirectory().getPath() + "/love520/images/icon/";
		}else{
			return APP_ROOT_DIR+"/images/";
		}
	}
	
	/**
	 * 判断SD卡是否存在，并且是否具有读写权限
	 * @return
	 */
	public static boolean isSdCardExit() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}
	
	/**
	 * 获取SD卡中流流顺文件夹
	 */
	public static String getSdCardRootPath2() {
		if (isSdCardExit()) {
			return Environment.getExternalStorageDirectory().getPath() + "/love520/";
		}else{
			return  PathManager.APP_ROOT_DIR+"/images/";
		}
	}
	
	public static String getSdCardRootPath3() {
		if (isSdCardExit()) {
			return Environment.getExternalStorageDirectory().getPath();
		}else{
			return  PathManager.APP_ROOT_DIR;
		}
	}
	
	
	public static void checkPath() {
		File dir = new File(IMAGES_CACHE_DIR);
		if (!dir.exists()) {
			dir.mkdirs();
			System.out.println("dirmk");
		}
		dir = new File(SAVE_DIR);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		dir = new File(UPLOAD_FILES_DIR);
		if (!dir.exists()) {
			 dir.mkdirs();
		}
		
		dir = new File(APK_DIR);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}
	
}


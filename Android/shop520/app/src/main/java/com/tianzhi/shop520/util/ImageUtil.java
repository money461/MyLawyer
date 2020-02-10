package com.tianzhi.shop520.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//import android.os.Environment;

public class ImageUtil {
	/**
	 * 截屏
	 * 
	 * @param activity
	 * @return
	 */
	public static Bitmap shotPic(Activity activity, int catHeight) {
		// View是你需要截图的View
		View view = activity.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap b1 = view.getDrawingCache();
		// 获取状态栏高度
		Rect frame = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;
		Log.i("TAG", "" + statusBarHeight);
		// 获取屏幕长和高
//		int width = activity.getWindowManager().getDefaultDisplay().getWidth();////
//		int height = activity.getWindowManager().getDefaultDisplay().getHeight();/////
		// 去掉标题栏
//		Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight+catHeight,
//				width, height - statusBarHeight-catHeight);///////定位
		Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight+catHeight,
				b1.getWidth(), b1.getHeight()-statusBarHeight-catHeight);///////修改
		view.destroyDrawingCache();
		return b;
	}

	/**
	 * Save Bitmap to a file.保存图片到SD卡。
	 * 
	 * @param
	 * @param
	 * @return error message if the saving is failed. null if the saving is
	 *         successful.
	 * @throws IOException
	 */
	public static String savePicture(Bitmap bitmap, String path, String fileName) {

		String sdStatus = Environment.getExternalStorageState();
			if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
			LogUtils.e("TestFile", "SD card is not avaiable/writeable right now.");
			return null;
		}
		
		FileOutputStream fos = null;
		File file = new File(path);
		file.mkdirs();// 创建文件夹
		String filePath = path + fileName + ".jpg";

		try {
			fos = new FileOutputStream(filePath);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);// 把数据写入文件
			return filePath;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if(null != fos){
					fos.flush();
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * * 获取圆形图片方法
	 * 
	 * @param
	 * @param
	 * @return Bitmap
	 * @author tony
	 */
	public static Bitmap getCircleBitmap(Bitmap bitmap) {
		Log.e("msg", "bitmap: " + bitmap);
		if(bitmap == null){
			return bitmap;
		}
		Paint paint = new Paint();
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		int x = bitmap.getWidth();

		canvas.drawCircle(x / 2, x / 2, x / 2, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}
}

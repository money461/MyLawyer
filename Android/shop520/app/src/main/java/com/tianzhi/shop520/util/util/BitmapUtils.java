/**
 *
 */
package com.tianzhi.shop520.util.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * ClassName: BitmapUtils <br/>
 * Function: ADD FUNCTION. <br/>
 * Reason: ADD REASON(可选). <br/>
 * date: 2014-4-11 上午9:37:09 <br/>
 *
 * @author yanxiaogang
 * @since JDK 1.6
 */
@SuppressLint( "SdCardPath" )
public class BitmapUtils {

    private static final String TAG = "PicUtil";

    /**
     * 根据一个网络连接(URL)获取bitmapDrawable图像
     *
     * @param imageUri
     * @return
     */
    @SuppressWarnings( "deprecation" )
    public static BitmapDrawable getfriendicon( URL imageUri ) {

        BitmapDrawable icon = null;
        try {
            HttpURLConnection hp = (HttpURLConnection) imageUri.openConnection();
            icon = new BitmapDrawable( hp.getInputStream() );// 将输入流转换成bitmap
            hp.disconnect();// 关闭连接
        } catch ( Exception e ) {
        }
        return icon;
    }

    /**
     * 根据一个网络连接(String)获取bitmapDrawable图像
     *
     * @param imageUri
     * @return
     */
    @SuppressWarnings( "deprecation" )
    public static BitmapDrawable getcontentPic( String imageUri ) {
        URL imgUrl = null;
        try {
            imgUrl = new URL( imageUri );
        } catch ( MalformedURLException e1 ) {
            e1.printStackTrace();
        }
        BitmapDrawable icon = null;
        try {
            HttpURLConnection hp = (HttpURLConnection) imgUrl.openConnection();
            icon = new BitmapDrawable( hp.getInputStream() );// 将输入流转换成bitmap
            hp.disconnect();// 关闭连接
        } catch ( Exception e ) {
        }
        return icon;
    }

    /**
     * 根据一个网络连接(URL)获取bitmap图像
     *
     * @param imageUri
     * @return
     */
    public static Bitmap getusericon( URL imageUri ) {
        // 显示网络上的图片
        URL myFileUrl = imageUri;
        Bitmap bitmap = null;
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput( true );
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream( is );
            is.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        return bitmap;
    }




    public static boolean downpic( String picName, Bitmap bitmap ) {
        boolean nowbol = false;
        try {
            File saveFile = new File( "/mnt/sdcard/download/weibopic/" + picName + ".png" );
            if ( !saveFile.exists() ) {
                saveFile.createNewFile();
            }
            FileOutputStream saveFileOutputStream;
            saveFileOutputStream = new FileOutputStream( saveFile );
            nowbol = bitmap.compress( Bitmap.CompressFormat.PNG, 100, saveFileOutputStream );
            saveFileOutputStream.close();
        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return nowbol;
    }

    public static void writeTofiles( Context context, Bitmap bitmap, String filename ) {
        BufferedOutputStream outputStream = null;
        try {
            outputStream = new BufferedOutputStream( context.openFileOutput( filename, Context.MODE_PRIVATE ) );
            bitmap.compress( Bitmap.CompressFormat.PNG, 100, outputStream );
        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
        }
    }

    /**
     * 将文件写入缓存系统中
     *
     * @param filename
     * @param is
     * @return
     */
    public static String writefile( Context context, String filename, InputStream is ) {
        BufferedInputStream inputStream = null;
        BufferedOutputStream outputStream = null;
        try {
            inputStream = new BufferedInputStream( is );
            outputStream = new BufferedOutputStream( context.openFileOutput( filename, Context.MODE_PRIVATE ) );
            byte[] buffer = new byte[ 1024 ];
            int length;
            while ( ( length = inputStream.read( buffer ) ) != -1 ) {
                outputStream.write( buffer, 0, length );
            }
        } catch ( Exception e ) {
        } finally {
            if ( inputStream != null ) {
                try {
                    inputStream.close();
                } catch ( IOException e ) {
                    e.printStackTrace();
                }
            }
            if ( outputStream != null ) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch ( IOException e ) {
                    e.printStackTrace();
                }
            }
        }
        return context.getFilesDir() + "/" + filename + ".jpg";
    }

    // 放大缩小图片
    public static Bitmap zoomBitmap( Bitmap bitmap, int w, int h ) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidht = ( (float) w / width );
        float scaleHeight = ( (float) h / height );
        matrix.postScale( scaleWidht, scaleHeight );
        Bitmap newbmp = Bitmap.createBitmap( bitmap, 0, 0, width, height, matrix, true );
        return newbmp;
    }

    /**
     * 读取图片属性：旋转的角度
     *
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    public static int readPictureDegree( String path ) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface( path );
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL );
            switch ( orientation ) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        return degree;
    }

    // 将Drawable转化为Bitmap
    public static Bitmap drawableToBitmap( Drawable drawable ) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap( width, height,
                drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888 : Config.RGB_565 );
        Canvas canvas = new Canvas( bitmap );
        drawable.setBounds( 0, 0, width, height );
        drawable.draw( canvas );
        return bitmap;

    }

    // 获得圆角图片的方法
    public static Bitmap getRoundedCornerBitmap( Bitmap bitmap, float roundPx ) {
        if ( bitmap == null ) {
            return null;
        }

        Bitmap output = Bitmap.createBitmap( bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888 );
        Canvas canvas = new Canvas( output );

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect( 0, 0, bitmap.getWidth(), bitmap.getHeight() );
        final RectF rectF = new RectF( rect );

        paint.setAntiAlias( true );
        canvas.drawARGB( 0, 0, 0, 0 );
        paint.setColor( color );
        canvas.drawRoundRect( rectF, roundPx, roundPx, paint );

        paint.setXfermode( new PorterDuffXfermode( Mode.SRC_IN ) );
        canvas.drawBitmap( bitmap, rect, rect, paint );
        return output;
    }

    /**
     * 用于制作圆形bitmap图像
     *
     * @param bitmap bitmap资源
     * @return
     */

    public static Bitmap getRoundedCornerBitmap( Bitmap bitmap ) {
        Bitmap output = Bitmap.createBitmap( bitmap.getWidth(),
                bitmap.getHeight(), Config.ARGB_8888 );
        Canvas canvas = new Canvas( output );

        final Paint paint = new Paint();
        Rect rect;
        float roundPx;
        int cc;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
//		System.out.println(bitmap.getHeight() + "," + bitmap.getWidth());
        if ( width > height ) {
            cc = ( width - height ) / 2;
            roundPx = height / 2;
            rect = new Rect( cc, 0, width-cc, height );
        } else {
            roundPx = width / 2;
            cc = ( height - width ) / 2;
            rect = new Rect( 0, cc, width, height );
        }
        final RectF rectF = new RectF( rect );

        paint.setAntiAlias( true );
        canvas.drawARGB( 0, 0, 0, 0 );
        canvas.drawRoundRect( rectF, roundPx, roundPx, paint );

        paint.setXfermode( new PorterDuffXfermode( Mode.SRC_IN ) );
        canvas.drawBitmap( bitmap, rect, rect, paint );
        Log.e( "LXZ - TAG:BitmapUtils ", "getRoundedCornerBitmap Line 432 -_-! : " + output );
        return output;
    }

    // 获得带倒影的图片方法
    public static Bitmap createReflectionImageWithOrigin( Bitmap bitmap ) {
        final int reflectionGap = 4;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.preScale( 1, -1 );

        Bitmap reflectionImage = Bitmap.createBitmap( bitmap, 0, height / 2, width, height / 2, matrix, false );

        Bitmap bitmapWithReflection = Bitmap.createBitmap( width, ( height + height / 2 ), Config.ARGB_8888 );

        Canvas canvas = new Canvas( bitmapWithReflection );
        canvas.drawBitmap( bitmap, 0, 0, null );
        Paint deafalutPaint = new Paint();
        canvas.drawRect( 0, height, width, height + reflectionGap, deafalutPaint );

        canvas.drawBitmap( reflectionImage, 0, height + reflectionGap, null );

        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient( 0, bitmap.getHeight(), 0, bitmapWithReflection.getHeight()
                + reflectionGap, 0x70ffffff, 0x00ffffff, TileMode.CLAMP );
        paint.setShader( shader );
        // Set the Transfer mode to be porter duff and destination in
        paint.setXfermode( new PorterDuffXfermode( Mode.DST_IN ) );
        // Draw a rectangle using the paint with our linear gradient
        canvas.drawRect( 0, height, width, bitmapWithReflection.getHeight() + reflectionGap, paint );

        return bitmapWithReflection;
    }

    /**
     * bitmap2Drawable:(这里用一句话描述这个方法的作用). <br/>
     *
     * @param bitmap
     * @return
     * @author yanxiaogang
     * @since JDK 1.6
     */
    public static Drawable bitmap2Drawable( Bitmap bitmap ) {
        BitmapDrawable bd = new BitmapDrawable( bitmap );
        Drawable d = bd;
        return d;
    }

    /**
     * 缩放图片
     *
     * @param bitmap
     * @param zf
     * @return
     */
    public static Bitmap zoom( Bitmap bitmap, float zf ) {
        Matrix matrix = new Matrix();
        matrix.postScale( zf, zf );
        return Bitmap.createBitmap( bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true );
    }

    /**
     * 缩放图片
     *
     * @param bitmap
     * @param hf
     * @return
     */
    public static Bitmap zoom( Bitmap bitmap, float wf, float hf ) {
        Matrix matrix = new Matrix();
        matrix.postScale( wf, hf );
        return Bitmap.createBitmap( bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true );
    }

    /**
     * 图片圆角处理
     *
     * @param bitmap
     * @param roundPX
     * @return
     */
    public static Bitmap getRCB( Bitmap bitmap, float roundPX ) {
        // RCB means
        // Rounded
        // Corner Bitmap
        Bitmap dstbmp = Bitmap.createBitmap( bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888 );
        Canvas canvas = new Canvas( dstbmp );

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect( 0, 0, bitmap.getWidth(), bitmap.getHeight() );
        final RectF rectF = new RectF( rect );
        paint.setAntiAlias( true );
        canvas.drawARGB( 0, 0, 0, 0 );
        paint.setColor( color );
        canvas.drawRoundRect( rectF, roundPX, roundPX, paint );
        paint.setXfermode( new PorterDuffXfermode( Mode.SRC_IN ) );
        canvas.drawBitmap( bitmap, rect, rect, paint );
        return dstbmp;
    }

    public static Bitmap getSquareBitmap( Bitmap bitmap ) {
        if ( bitmap == null )
            return null;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        final Paint paint = new Paint();
        Rect rect = null;
        int sidePx = 0;
        int dif = Math.abs( width - height ) / 2;
        sidePx = Math.min( width, height );
        Bitmap output = Bitmap.createBitmap( sidePx, sidePx, Config.ARGB_8888 );
        Canvas canvas = new Canvas( output );
        // if ( width - height > 0 )
        // rect = new Rect( 0 + dif, 0, sidePx + dif, sidePx );
        // if ( height - width > 0 )
        // rect = new Rect( 0, 0 + dif, sidePx, sidePx + dif );
        rect = new Rect( 0, 0, sidePx, sidePx );
        final RectF rectF = new RectF( rect );

        paint.setAntiAlias( true );
        canvas.drawARGB( 0, 0, 0, 0 );
        canvas.drawRect( rectF, paint );

        paint.setXfermode( new PorterDuffXfermode( Mode.SRC_IN ) );
        canvas.drawBitmap( bitmap, rect, rect, paint );
        return output;
    }

    public static Bitmap compressImage( Bitmap image ) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress( Bitmap.CompressFormat.JPEG, 100, baos );// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 90;
        while ( baos.toByteArray().length / 1024 > 10 ) { // 循环判断如果压缩后图片是否大于10kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            image.compress( Bitmap.CompressFormat.JPEG, options, baos );// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream( baos.toByteArray() );// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream( isBm, null, null );// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }

}

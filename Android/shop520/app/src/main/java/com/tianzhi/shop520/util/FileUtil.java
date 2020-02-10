package com.tianzhi.shop520.util;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import java.io.File;

/**
 * File util.
 *
 * @author markmjw
 * @date 2014-10-28
 */
public class FileUtil {
    public static final String EXTERNAL_STORAGE = Environment.getExternalStorageDirectory()
            .toString();

    private static String DIR_HOME = EXTERNAL_STORAGE + "/love520";
    private static String DIR_IMAGE = DIR_HOME + "/image";
    private static String DIR_CACHE = DIR_HOME + "/cache";
    private static String DIR_LOG = DIR_HOME + "/log";
    private static String DIR_DOWNLOAD = DIR_HOME + "/download";

    public static final int DIR_TYPE_CACHE = 0x01;
    public static final int DIR_TYPE_IMAGE = DIR_TYPE_CACHE << 1;
    public static final int DIR_TYPE_LOG = DIR_TYPE_IMAGE << 1;
    public static final int DIR_TYPE_DOWNLOAD = DIR_TYPE_LOG << 1;

    /** 默认最小需要的空间 */
    public static final long MIN_SPACE = 10 * 1024 * 1024;

    /**
     * 通过类型获取目录路径
     *
     * @param type
     * @return
     */
    public static String getPathByType(int type) {
        String dir = "/";
        String filePath;

        switch (type) {
            case DIR_TYPE_CACHE:
                filePath = DIR_CACHE;
                break;

            case DIR_TYPE_IMAGE:
                filePath = DIR_IMAGE;
                break;

            case DIR_TYPE_LOG:
                filePath = DIR_LOG;
                break;

            case DIR_TYPE_DOWNLOAD:
                filePath = DIR_DOWNLOAD;
                break;

            default:
                filePath = "";
                break;
        }

        File file = new File(filePath);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }

        if (file.exists()) {
            if (file.isDirectory()) {
                dir = file.getPath();
            }
        } else {
            // 文件没创建成功，可能是sd卡不存在，但是还是把路径返回
            dir = filePath;
        }

        return dir + "/";
    }

    /**
     * SdCard是否存在
     *
     * @return
     */
    public static boolean isSDCardExist() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 判断存储空间是否足够,默认需要 {@link FileUtil#MIN_SPACE}
     *
     * @return
     */
    public static boolean hasEnoughSpace() {
        return hasEnoughSpace(MIN_SPACE);
    }

    /**
     * 判断存储空间是否足够
     *
     * @param needSize
     * @return
     */
    @SuppressLint("NewApi")
	public static boolean hasEnoughSpace(float needSize) {
        if (isSDCardExist()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs sf = new StatFs(path.getPath());

            long blockSize;
            long availCount;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                blockSize = sf.getBlockSizeLong();
                availCount = sf.getAvailableBlocksLong();
            } else {
                blockSize = sf.getBlockSize();
                availCount = sf.getAvailableBlocks();
            }

            long restSize = availCount * blockSize;
            if (restSize > needSize) {
                return true;
            }
        }
        return false;
    }

    /**
     * Delete file or folder.
     *
     * @param filePath
     * @return
     */
    public static boolean deleteFile(String filePath) {
        if (!TextUtils.isEmpty(filePath)) {
            File file = new File(filePath);

            if (file.isDirectory()) {
                // 处理目录
                File files[] = file.listFiles();
                if (null != files && files.length > 0) {
                    for (File f : files) {
                        deleteFile(f.getAbsolutePath());
                    }
                    return true;
                } else {
                    // 目录下没有文件或者目录，删除
                    return file.delete();
                }

            } else {
                // 如果是文件，删除
                return file.delete();
            }
        }
        return false;
    }

    /**
     * Delete file or folder.
     *
     * @param deleteFile
     * @return
     */
    public static boolean deleteFile(File deleteFile) {
        if (deleteFile != null) {
            if (!deleteFile.exists()) {
                return true;
            }

            if (deleteFile.isDirectory()) {
                // 处理目录
                File[] files = deleteFile.listFiles();
                if (null != files) {
                    for (File file : files) {
                        deleteFile(file.getAbsolutePath());
                    }
                }
            }

            if (!deleteFile.isDirectory()) {
                // 如果是文件，删除
                return deleteFile.delete();

            } else {
                // 目录下没有文件或者目录，删除
                File[] files = deleteFile.listFiles();
                if (null != files && files.length == 0) {
                    return deleteFile.delete();
                }
            }
        }

        return false;
    }
}

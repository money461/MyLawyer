package com.tianzhi.shop520.entity.personal;

import java.io.Serializable;

/**
 * Created by thinkpad on 2017/10/27.
 * 上传头像
 */

public class UploadHead implements Serializable{
    public String flag;
    public String msg;
    public String data;

    @Override
    public String toString() {
        return "UploadHead{" +
                "flag='" + flag + '\'' +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}

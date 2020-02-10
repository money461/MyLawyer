package com.tianzhi.shop520.entity;

import java.io.Serializable;

/**
 * Created by thinkpad on 2017/10/30.
 */

public class BaseResponseData implements Serializable{
    public String flag;
    public  String msg;
    public String data;

    @Override
    public String toString() {
        return "BaseResponseData{" +
                "flag='" + flag + '\'' +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}

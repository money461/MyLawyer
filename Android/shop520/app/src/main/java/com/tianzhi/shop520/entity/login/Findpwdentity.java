package com.tianzhi.shop520.entity.login;

import java.io.Serializable;

/**
 * Created by thinkpad on 2017/10/30.
 */

public class Findpwdentity implements Serializable {
    public String flag;
    public String msg;
    public UserInfo data;

    @Override
    public String toString() {
        return "Findpwdentity{" +
                "flag='" + flag + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}


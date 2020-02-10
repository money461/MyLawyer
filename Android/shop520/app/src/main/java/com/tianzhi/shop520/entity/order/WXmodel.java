package com.tianzhi.shop520.entity.order;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wisdomnt on 2017/5/19.
 */
public class WXmodel {
    public String appid;
    public String partnerid;
    public String prepayid;
    @SerializedName("package")
    public String packagestr;
    public String signType;
    public String noncestr;
    public String timestamp;
    public String paySign;

    @Override
    public String toString() {
        return "WXmodel{" +
                "appid='" + appid + '\'' +
                ", partnerid='" + partnerid + '\'' +
                ", prepayid='" + prepayid + '\'' +
                ", packagestr='" + packagestr + '\'' +
                ", signType='" + signType + '\'' +
                ", noncestr='" + noncestr + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", paySign='" + paySign + '\'' +
                '}';
    }
}

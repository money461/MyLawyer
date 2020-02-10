package com.tianzhi.shop520.entity;

import java.io.Serializable;

/**
 * Created by thinkpad on 2017/11/24.
 * app信息
 */

public class AppInfo implements Serializable{
    public int id;
    public String name;//app名称
    public String down;//下载地址
    public String version;//版本号v1.0.0
    public String createdTime;
    public String updatedTime;
    public String isForce;//是否强制更新 1：是，0：否
    public String operater;
    public String updatedContent;//更新内容
    public int sort;//版本号

    @Override
    public String toString() {
        return "AppInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", down='" + down + '\'' +
                ", version='" + version + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", updatedTime='" + updatedTime + '\'' +
                ", isForce='" + isForce + '\'' +
                ", operater='" + operater + '\'' +
                ", updatedContent='" + updatedContent + '\'' +
                ", sort='" + sort + '\'' +
                '}';
    }

}

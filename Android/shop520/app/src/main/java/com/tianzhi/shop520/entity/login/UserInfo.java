package com.tianzhi.shop520.entity.login;

import java.io.Serializable;

/**
 * Created by thinkpad on 2017/10/26.
 */

public class UserInfo implements Serializable{
    private static final long serialVersionUID = 2421263553592651152L;
    public String id;//用户ID
    public String userName;
    public String userNick;
    public String userSex;
    public String password;
    public String phone;
    public String email;
    public String code;
    public String headUrl;//头像图片地址
    public String isMember;
    public String createdTime;//创建时间
    public String lastUpload;//
    public String updatedTime;//
    public String status;//账户状态    冻结  正常  等
    public String operater;
    public String usertype;
    public String recommendedCode;//推荐二维码
    public String qrCode;//用户二维码
    public String type;//用户类型 0：普通用户 1：普通会员 2：爱心会员 3：爱心大师
    public String userToken;////用户token
    public String userMallId;//商城id
    public String loveSurplus;////剩余的爱心值
    public String recommendTotal;//推荐的用户数

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", userNick='" + userNick + '\'' +
                ", userSex='" + userSex + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", code='" + code + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", isMember='" + isMember + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", lastUpload='" + lastUpload + '\'' +
                ", updatedTime='" + updatedTime + '\'' +
                ", status='" + status + '\'' +
                ", operater='" + operater + '\'' +
                ", usertype='" + usertype + '\'' +
                ", recommendedCode='" + recommendedCode + '\'' +
                ", qrCode='" + qrCode + '\'' +
                ", type='" + type + '\'' +
                ", userToken='" + userToken + '\'' +
                ", userMallId='" + userMallId + '\'' +
                ", loveSurplus='" + loveSurplus + '\'' +
                ", recommendTotal='" + recommendTotal + '\'' +
                '}';
    }
}

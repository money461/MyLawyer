package com.tianzhi.shop520.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.tianzhi.shop520.APPLICATION;
import com.tianzhi.shop520.entity.login.UserInfo;

/**
 * Created by wisdomnt on 2017/3/16.
 */
public class AppShared {
    private SharedPreferences sharedPreferences;
    private Context mContext;
    private String YHB_CACHE = "yhb_cache";
    public static final String TOKEN = "token";
    public static final String ID = "id";
    public static final String HEADURL = "headurl";
    public static final String QRCODE = "QRcode";
    public static final String LOVESURPLUS = "loveSurplus";//剩余爱心值
    public static final String USERNICK ="userNick";
    public static final String USERSEX ="userSex";
    public static final String RECOMMENDEDCODE = "recommendedCode";
    public static final String TYPE = "type";
    public static final String recommendTotal = "recommendTotal";
    public static final String USER_INFO_ENTITY = "userInfoEntity";

    public AppShared(Context context) {
        this.mContext = context;
        sharedPreferences = APPLICATION.getContext().getSharedPreferences(YHB_CACHE,
                Context.MODE_PRIVATE);
    }

    public static AppShared getInstance(Context context) {
        AppShared uniqueInstance = new AppShared(context);
        return uniqueInstance;
    }


    /**
     * 获取登录信息
     * @param userEntity
     */
    public void saveLoginUserInfo(UserInfo userEntity) {
        Editor editor = sharedPreferences.edit();
        editor.putString(ID, userEntity.id);
        editor.putString(TOKEN, userEntity.userToken);
        editor.putString(QRCODE,userEntity.qrCode);
        editor.putString(LOVESURPLUS,userEntity.loveSurplus);
        editor.putString(USERNICK,userEntity.userNick);
        editor.putString(USERSEX,userEntity.userSex);
        editor.putString(RECOMMENDEDCODE,userEntity.recommendedCode);
        editor.putString(TYPE,userEntity.type);
        editor.putString(recommendTotal,userEntity.recommendTotal);
//        editor.putString(NAME,
//                ToolsUtil.encode(BaseConstant.DES_KEY, userEntity.getName()));
        editor.commit();
        editor.clear();
    }
    /**
     * 获取登录信息
     * @return
     */

    public UserInfo getLoginInfo() {
        UserInfo userEntity = new UserInfo();
        userEntity.id  = sharedPreferences.getString(ID, "");
        userEntity.userToken  = sharedPreferences.getString(TOKEN, "");
        userEntity.qrCode =sharedPreferences.getString(QRCODE,"");
        userEntity.loveSurplus = sharedPreferences.getString(LOVESURPLUS,"");
        userEntity.headUrl = sharedPreferences.getString(HEADURL,"");
        userEntity.recommendedCode = sharedPreferences.getString(RECOMMENDEDCODE,"");
        userEntity.userNick = sharedPreferences.getString(USERNICK,"");
        userEntity.userSex = sharedPreferences.getString(USERSEX,"");
        userEntity.type = sharedPreferences.getString(TYPE,"");
        userEntity.recommendTotal = sharedPreferences.getString(recommendTotal,"");
        return userEntity;
    }
    /**
     * 保存图片地址
     * @param headurl
     */
    public void saveHeadUrl(String  headurl) {
        Editor editor = sharedPreferences.edit();
        editor.putString(HEADURL, headurl);
//        editor.putString(NAME,
//                ToolsUtil.encode(BaseConstant.DES_KEY, userEntity.getName()));
        editor.commit();
        editor.clear();
    }
    /**
     * 获取头像地址
     * @return
     */

    public String  getHeadUrl() {
        String  headurl = null ;
        headurl = sharedPreferences.getString(HEADURL, "");
        return headurl;
    }

    /**
     * 清除登录信息
     */
    public void cleanUserInfoEntity() {
        Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN, "");
        editor.putString(ID, "");
        editor.putString(QRCODE,"");
        editor.putString(LOVESURPLUS,"");
        editor.commit();
        editor.clear();
    }

    /**
     * 缓存信息
     *
     * @param key
     * @param value
     */
    public  void saveInt( String key, int value ) {
        Editor editor = sharedPreferences.edit();
        if ( key == null || "".equals( key ) )
            return;
        try {
            editor.putInt( key.toLowerCase(), value ).commit();
        } catch ( Exception e ) {
            LogUtils.i( "SharePreStore", "save " + key + ": " + e.getMessage() );
            e.printStackTrace();
        }
    }
    /**
     * 获取缓存信息
     *
     * @param key
     * @return
     */
    public  int getInt( String key ) {
        if ( sharedPreferences.contains( key.toLowerCase() ) )
            return sharedPreferences.getInt( key.toLowerCase(), 0 );
        else
            return 0;
    }
}

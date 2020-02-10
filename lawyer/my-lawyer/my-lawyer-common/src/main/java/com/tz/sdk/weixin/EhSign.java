package com.tz.sdk.weixin;
import java.util.Map;
import java.util.UUID;

import com.google.zxing.datamatrix.encoder.SymbolShapeHint;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Formatter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;  
/**
 * 微信三方登录
 * @author menglin 2018年1月26日15:07:11
 *
 */
public class EhSign {
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    public static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
    
    //获取登录的的access_token
    public static AccessTokenPojo getAccess_token(String code){
    	String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		url = url.replace("APPID",EHWeChatPayConfig.appid);
		url = url.replace("APPSECRET",EHWeChatPayConfig.AppSecret);
		JSONObject object = JSONObject.fromObject(WXPayUtil.httpRequest(url, "GET", null));
		AccessTokenPojo accessTokenPojo = new AccessTokenPojo();
		accessTokenPojo.setAccess_token(object.getString("access_token"));
		accessTokenPojo.setOpenid(object.getString("openid"));
		accessTokenPojo.setExpires_in(object.getLong("expires_in"));
		accessTokenPojo.setRefresh_token(object.getString("refresh_token"));
		return  accessTokenPojo;
    } 
    
    //access_token是否有效的验证
    public static boolean  isAccessTokenIsInvalid(String accessToken,String openID){
    	String url = "https://api.weixin.qq.com/sns/auth?access_token=accessToken&openid=openID";
		url = url.replace("accessToken",accessToken);
		url = url.replace("openID",openID);
		JSONObject object = JSONObject.fromObject(WXPayUtil.httpRequest(url, "GET", null));
		int errorCode = object.getInt("errcode");
		System.out.println(object);
        if (errorCode == 0) {
              return true;
         }
		return  false;
    }
    
    //access_token是否有效的验证
    public static AccessTokenPojo  refreshAccessToken(String refreshToken,String openID){
    	String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=openID&grant_type=refresh_token&refresh_token=refreshToken";
		url = url.replace("refreshToken",refreshToken);
		url = url.replace("openID",openID);
		JSONObject object = JSONObject.fromObject(WXPayUtil.httpRequest(url, "GET", null));
		AccessTokenPojo accessTokenPojo = new AccessTokenPojo();
		accessTokenPojo.setAccess_token(object.getString("access_token"));
		accessTokenPojo.setOpenid(object.getString("openid"));
		accessTokenPojo.setExpires_in(object.getLong("expires_in"));
		accessTokenPojo.setRefresh_token(object.getString("refresh_token"));
		return  accessTokenPojo;
    }
      
    public static void main(String[] args) {
    	
    	  System.out.println(isAccessTokenIsInvalid("ssdsad","sdasd3132"));
    	 	  
	}
}

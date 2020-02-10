package com.tz.util;

import org.apache.commons.lang.StringUtils;

import com.tz.res.AppMsgResult;
import com.tz.sdk.weixin.WXPayUtil;

import net.sf.json.JSONObject;

/**
 * 环信接口封装
 * 
 * @author menglin 2018年1月25日10:19:15
 */
public class Hxchat {

	private static final String APP_NAME = "mylawyer";
	private static final String ORG_NAME = "1129180102178087";
	private static final String CLIENT_ID = "YXA6Hqg5QO-bEeeqhAW_14kb2Q";
	private static final String CLIENT_SECRET = "YXA6PmeykIsgIKVQLzNZ2lplQxfrsHU";

	/**
	 * 注册环信用户
	 * 
	 * @param params String params = "{" +" \"username\":\"fang\"," + " \"password\":\"123\"," + " \"nickname\":\"兔子\"" + " }";
	 * @return
	 */
	public static AppMsgResult adduser(String username,String password,String usernick) {
		AppMsgResult msgResult = null;
		if (StringUtils.isBlank(username)) {
			return msgResult = AppMsgResult.result(404, "请传递参数:username", null);
		}
		if (StringUtils.isBlank(password)) {
			return msgResult = AppMsgResult.result(404, "请传递参数:password", null);
		}
		if (StringUtils.isBlank(usernick)) {
			return msgResult = AppMsgResult.result(404, "请传递参数:usernick", null);
		}
		String params = "{" +" \"username\":\""+username+"\"," + " \"password\":\""+password+"\"," + " \"nickname\":\""+usernick+"\"" + " }";
		try {
			JSONObject object = JSONObject.fromObject(WXPayUtil.httpsRequest2(
					"https://a1.easemob.com/" + ORG_NAME + "/" + APP_NAME + "/users", "POST", params, null));
			msgResult = AppMsgResult.result(200, "success", object.get("entities".toString()));
		} catch (Exception e) {
			// TODO: handle exception
			return msgResult = AppMsgResult.result(5000, e.getMessage(), null);
		}
		return msgResult;
	}

	/**
	 * 获取管理员token
	 * 
	 * @param params
	 * @return
	 */
	public static AppMsgResult token() {
		AppMsgResult msgResult = null;
		String params = "{" + " \"grant_type\":\"client_credentials\"," + " \"client_id\":\"" + CLIENT_ID + "\","
				+ " \"client_secret\":\"" + CLIENT_SECRET + "\"" + " }";
		try {
			JSONObject object = JSONObject.fromObject(WXPayUtil.httpsRequest2(
					"https://a1.easemob.com/" + ORG_NAME + "/" + APP_NAME + "/token", "POST", params, null));
			String token = object.get("access_token").toString();
			msgResult = AppMsgResult.result(200, "success", token);
		} catch (Exception e) {
			// TODO: handle exception
			return msgResult = AppMsgResult.result(5000, e.getMessage(), null);
		}
		return msgResult;
	}

	/**
	 * 获取用户信息
	 * @param username 环信用户名
	 * @return
	 */
	public static AppMsgResult getuser(String username) {
		AppMsgResult msgResult = null;
		if (StringUtils.isBlank(username)) {
			return msgResult = AppMsgResult.result(404, "请传递参数:username", null);
		}
		try {
			AppMsgResult result = token();
			if (200 != (int) result.getFlag()) {
				return msgResult = AppMsgResult.result(5000, "获取token失败！", null);
			}
			JSONObject object = JSONObject.fromObject(WXPayUtil.httpsRequest2(
					"https://a1.easemob.com/" + ORG_NAME + "/" + APP_NAME + "/users/" + username, "GET", null,
					"Bearer " + result.getData()));
			
			msgResult = AppMsgResult.result(200, "success", object);
		} catch (Exception e) {
			// TODO: handle exception
			return msgResult = AppMsgResult.result(5000, e.getMessage(), null);
		}
		return msgResult;
	}
	
	/**
	 * 删除环信账号
	 * @param username 环信用户名
	 * @return
	 */
	public static AppMsgResult deleteuser(String username) {
		AppMsgResult msgResult = null;
		if (StringUtils.isBlank(username)) {
			return msgResult = AppMsgResult.result(404, "请传递参数:username", null);
		}
		try {
			AppMsgResult result = token();
			if (200 != (int) result.getFlag()) {
				return msgResult = AppMsgResult.result(5000, "获取token失败！", null);
			}
			JSONObject object = JSONObject.fromObject(WXPayUtil.httpsRequest2(
					"https://a1.easemob.com/" + ORG_NAME + "/" + APP_NAME + "/users/" + username, "DELETE", null,
					"Bearer " + result.getData()));
			msgResult = AppMsgResult.result(200, "success", object);
		} catch (Exception e) {
			// TODO: handle exception
			return msgResult = AppMsgResult.result(5000, e.getMessage(), null);
		}
		return msgResult;
	}
	/**
	 * 修改环信账号昵称
	 * @param username
	 * @param nickName
	 * @return
	 */
	public static AppMsgResult updateuser(String username,String nickName) {
		AppMsgResult msgResult = null;
		if (StringUtils.isBlank(username)) {
			return msgResult = AppMsgResult.result(404, "请传递参数:username", null);
		}
		if (StringUtils.isBlank(nickName)) {
			return msgResult = AppMsgResult.result(404, "请传递参数:nickName", null);
		}
		try {
			AppMsgResult result = token();
			if (200 != (int) result.getFlag()) {
				return msgResult = AppMsgResult.result(5000, "获取token失败！", null);
			}
			String params = "{" +" \"nickname\":\""+nickName+"\"" + " }";
			JSONObject object = JSONObject.fromObject(WXPayUtil.httpsRequest2(
					"https://a1.easemob.com/" + ORG_NAME + "/" + APP_NAME + "/users/" + username, "PUT", params,
					"Bearer " + result.getData()));
			msgResult = AppMsgResult.result(200, "success", object);
		} catch (Exception e) {
			// TODO: handle exception
			return msgResult = AppMsgResult.result(5000, e.getMessage(), null);
		}
		return msgResult;
	}
	/**
	 * 创建聊天室
	 * @param owner 拥有者
	 * @param members 加入者
	 * @return
	 */
	public static AppMsgResult addchatrooms(String owner,String members) {
		AppMsgResult msgResult = null;
		if (StringUtils.isBlank(owner)) {
			return msgResult = AppMsgResult.result(404, "请传递参数:owner", null);
		}
		try {
			AppMsgResult result = token();
			if (200 != (int) result.getFlag()) {
				return msgResult = AppMsgResult.result(5000, "获取token失败！", null);
			}
			String params = "{" + " \"name\":\"我的律师聊天室\"," + " \"description\":\"我的律师聊天室\","
					+ " \"maxusers\":\"300\","+"\"owner\":\""+owner+"\","+"\"members\":\""+members+"\" " + " }";
			
			JSONObject object = JSONObject.fromObject(WXPayUtil.httpsRequest2(
					"https://a1.easemob.com/" + ORG_NAME + "/" + APP_NAME + "/chatrooms/", "POST", params,
					"Bearer " + result.getData()));
			msgResult = AppMsgResult.result(200, "success", object);
		} catch (Exception e) {
			// TODO: handle exception
			return msgResult = AppMsgResult.result(5000, e.getMessage(), null);
		}
		return msgResult;
	}
	/**
	 * 获取加入的聊天室
	 * @param owner 拥有者

	 * @return
	 */
	public static AppMsgResult joined_chatrooms(String username) {
		AppMsgResult msgResult = null;
		if (StringUtils.isBlank(username)) {
			return msgResult = AppMsgResult.result(404, "请传递参数:username", null);
		}
		try {
			AppMsgResult result = token();
			if (200 != (int) result.getFlag()) {
				return msgResult = AppMsgResult.result(5000, "获取token失败！", null);
			}
			JSONObject object = JSONObject.fromObject(WXPayUtil.httpsRequest2(
					"https://a1.easemob.com/" + ORG_NAME + "/" + APP_NAME +"/"+username+"/joined_chatrooms/", "GET", null,
					"Bearer " + result.getData()));
			msgResult = AppMsgResult.result(200, "success", object);
		} catch (Exception e) {
			// TODO: handle exception
			return msgResult = AppMsgResult.result(5000, e.getMessage(), null);
		}
		return msgResult;
	}
	/**
	 * 环信用户强制下线
	 * @param username
	 * @return
	 */
	public static AppMsgResult disconnect(String username) {
		AppMsgResult msgResult = null;
		if (StringUtils.isBlank(username)) {
			return msgResult = AppMsgResult.result(404, "请传递参数:username", null);
		}
		try {
			AppMsgResult result = token();
			if (200 != (int) result.getFlag()) {
				return msgResult = AppMsgResult.result(5000, "获取token失败！", null);
			}
			JSONObject object = JSONObject.fromObject(WXPayUtil.httpsRequest2(
					"https://a1.easemob.com/" + ORG_NAME + "/" + APP_NAME +"/users/"+username+"/status/", "GET", null,
					"Bearer " + result.getData()));
			if(object.get("data").toString().contains("online")){
				AppMsgResult result2 = token();
				if (200 != (int) result2.getFlag()) {
					return msgResult = AppMsgResult.result(5000, "获取token失败！", null);
				}
				System.out.println(result2.getData());
				WXPayUtil.httpsRequest2(
						"https://a1.easemob.com/" + ORG_NAME + "/" + APP_NAME +"/users/"+username+"/disconnect/", "GET", null,
						"Bearer " + result2.getData());
			}
		} catch (Exception e) {
			// TODO: handle exception
			return msgResult = AppMsgResult.result(5000, e.getMessage(), null);
		}
		return msgResult;
	}
	//环信用户状态
	public static AppMsgResult status(String username) {
		AppMsgResult msgResult = null;
		if (StringUtils.isBlank(username)) {
			return msgResult = AppMsgResult.result(404, "请传递参数:username", null);
		}
		try {
			AppMsgResult result = token();
			if (200 != (int) result.getFlag()) {
				return msgResult = AppMsgResult.result(5000, "获取token失败！", null);
			}
			JSONObject object = JSONObject.fromObject(WXPayUtil.httpsRequest2(
					"https://a1.easemob.com/" + ORG_NAME + "/" + APP_NAME +"/users/"+username+"/status/", "GET", null,
					"Bearer " + result.getData()));
			msgResult = AppMsgResult.result(200, "success", object);
		} catch (Exception e) {
			// TODO: handle exception
			return msgResult = AppMsgResult.result(5000, e.getMessage(), null);
		}
		return msgResult;
	}
	public static void main(String[] args) {
		/*System.out.println(adduser("eh_7c9302aab0124b53ae42f209549cb933","123456","芳"));*/
		/*System.out.println(disconnect("eh_bt5sdsdsd9083423jhdf234"));*/
		System.out.println(getuser("eh_7c9302aab0124b53ae42f209549cb933"));
		
	}
}

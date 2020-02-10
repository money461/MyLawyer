package com.tz.config;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.tz.sdk.weixin.WXPayUtil;

import net.sf.json.JSONObject;

public class requestTest {

	public static final String DEF_CHATSET = "UTF-8";
	public static final int DEF_CONN_TIMEOUT = 30000;
	public static final int DEF_READ_TIMEOUT = 30000;
	public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

	/**
	 *
	 * @param strUrl
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param method
	 *            请求方法
	 * @return 网络请求字符串
	 * @throws Exception
	 */
	public static String net(String strUrl, Map params, String method) throws Exception {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		String rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			if (method == null || method.equals("GET")) {
				strUrl = strUrl + "?" + urlencode(params);
			}
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			if (method == null || method.equals("GET")) {
				conn.setRequestMethod("GET");
			} else {
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
			}
			conn.setRequestProperty("User-agent", userAgent);
			conn.setUseCaches(false);
			conn.setConnectTimeout(DEF_CONN_TIMEOUT);
			conn.setReadTimeout(DEF_READ_TIMEOUT);
			conn.setInstanceFollowRedirects(false);
			conn.connect();
			if (params != null && method.equals("POST")) {
				try {
					DataOutputStream out = new DataOutputStream(conn.getOutputStream());
					out.writeBytes(urlencode(params));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			InputStream is = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sb.append(strRead);
			}
			rs = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return rs;
	}

	// 将map型转为请求参数型
	public static String urlencode(Map<String, Object> data) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry i : data.entrySet()) {
			try {
				sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		/*
		 * Map params = new HashMap();//请求参数 params.put("username","芳");//需要查询的快递公司编号
		 * params.put("password","123");//需要查询的订单号
		 * params.put("nickname","兔子");//应用APPKEY(应用详细页查询) try { String res =
		 * net("https://a1.easemob.com/1129180102178087/mylawyer/users",params,"POST");
		 * System.out.println(res); } catch (Exception e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */

		/*
		 * String
		 * s=httpsRequest("https://a1.easemob.com/1129180102178087/mylawyer/users",
		 * "POST",null); System.out.println(s);
		 */
		/*
		 * String params = "{" +" \"username\":\"fang\"," + " \"password\":\"123\"," +
		 * " \"nickname\":\"兔子\"" + " }"; JSONObject object =
		 * JSONObject.fromObject(WXPayUtil.httpRequest(
		 * "https://a1.easemob.com/1129180102178087/mylawyer/users", "POST", params));
		 */
		/*String params = "{" + " \"grant_type\":\"client_credentials\","
				+ " \"client_id\":\"YXA6Hqg5QO-bEeeqhAW_14kb2Q\","
				+ " \"client_secret\":\"YXA6PmeykIsgIKVQLzNZ2lplQxfrsHU\"" + " }";
		
			/*
		 * JSONObject object = JSONObject.fromObject(WXPayUtil.httpsRequest2(
		 * "https://a1.easemob.com/1129180102178087/mylawyer/token", "POST",
		 * params,null));
		 */
		String params = "{" + " \"access_token\":\"YWMtTPJFYgF0Eei0cJcQV-8bvAAAAAAAAAAAAAAAAAAAAAEeqDlA75sR56qEBb_XiRvZAgMAAAFhKxCgLgBPGgDaYil5mmJwgAhsCaBQEZR--zizDb-9e9_GDrBTU0NgJQ\","
		+ " \"application\":\"1ea83940-ef9b-11e7-aa84-05bfd7891bd9\"" + " }";
		JSONObject object = JSONObject.fromObject(WXPayUtil.httpsRequest2(
				"https://a1.easemob.com/1129180102178087/mylawyer/users/fang", "DELETE", null,"Bearer YWMtTPJFYgF0Eei0cJcQV-8bvAAAAAAAAAAAAAAAAAAAAAEeqDlA75sR56qEBb_XiRvZAgMAAAFhKxCgLgBPGgDaYil5mmJwgAhsCaBQEZR--zizDb-9e9_GDrBTU0NgJQ")); 
		object.get("access_token").toString();
		System.out.println(object);

	}

	/*
	 * 处理https GET/POST请求 请求地址、请求方法、参数
	 */
	public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		StringBuffer buffer = null;
		try {
			// 创建SSLContext
			SSLContext sslContext = SSLContext.getInstance("SSL");
			TrustManager[] tm = { new MyX509TrustManager() };
			// 初始化
			sslContext.init(null, tm, new java.security.SecureRandom());
			;
			// 获取SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod(requestMethod);
			// 设置当前实例使用的SSLSoctetFactory
			conn.setSSLSocketFactory(ssf);
			conn.connect();
			// 往服务器端写内容
			if (null != outputStr) {
				OutputStream os = conn.getOutputStream();
				os.write(outputStr.getBytes("utf-8"));
				os.close();
			}
			// 读取服务器端返回的内容
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			buffer = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
}

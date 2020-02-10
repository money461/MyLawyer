package com.tz.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.tz.pojo.ZaComAuthentication;
import com.tz.remote.CompanyRemote;
import com.tz.remote.PayRemote;
import com.tz.res.AppMsgResult;
import com.tz.sdk.weixin.WXPayUtil;

import net.sf.json.JSONObject;

/**
 * 企业类
 * 
 * @author menglin 2017年12月28日10:02:56
 *
 */
@RestController
@RequestMapping("/user/api")
public class PayContrller {

	@Autowired
	PayRemote payRemote;

	/**
	 * 支付宝支付接口
	 * 
	 * @param userId
	 *            用户id
	 * @param token
	 *            登录token
	 * @param type
	 *            类型 // type 1 充值， 2 企业认证，3 打赏，4 悬赏。
	 * @param amount
	 *            金额
	 * @param userIdTo
	 *            被打赏的用户id
	 * @param orderId
	 *            悬赏的订单id
	 * @return
	 */
	@PostMapping("/zfbAppPay")
	public AppMsgResult zfbAppPay(String userId, String token, String type, BigDecimal amount, String userIdTo,
			String orderId,String userType,String giftId,int giftNum) {
		return payRemote.zfbAppPay(userId, token, type, amount, userIdTo, orderId,userType,giftId,giftNum);
	}

	/**
	 * 微信支付接口
	 * 
	 * @param userId
	 *            用户id
	 * @param token
	 *            登录token
	 * @param type
	 *            类型 // type 1 充值， 2 企业认证，3 打赏，4 悬赏。
	 * @param amount
	 *            金额
	 * @param userIdTo
	 *            被打赏的用户id
	 * @param orderId
	 *            悬赏的订单id
	 * @return
	 */
	@PostMapping("/wechatAppPay")
	public AppMsgResult wechatAppPay( String userId, String token, String type,
			BigDecimal amount, String userIdTo, String orderId,String userType,String giftId,int giftNum) {
		return payRemote.wechatAppPay( userId, token, type, amount, userIdTo, orderId,userType,giftId,giftNum);
	}
	
	/**
	 * 支付宝支付回调
	 * @param request
	 * @param response
	 */
	@PostMapping("/zfbAppNotify")
	public void zfbAppNotify() {
		payRemote.zfbAppNotify();
	}
	/**
	 * 微信支付回调
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@PostMapping("/wechatAppNotify")
	public void wechatAppNotify(HttpServletRequest request) throws IOException {
		try {
			 //读取参数  
	        InputStream inputStream ;  
	        StringBuffer sb = new StringBuffer();  
	        inputStream = request.getInputStream();  
	        String s ;  
	        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));  
	        while ((s = in.readLine()) != null){  
	            sb.append(s);  
	        }  
	        in.close();  
	        inputStream.close();  
	        //解析xml成map  
	        Map<String, String> m = new HashMap<String, String>();  
	        
	        try {
				m = WXPayUtil.xmlToMap(sb.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	        for(Object keyValue : m.keySet()){
	            System.out.println(keyValue+"="+m.get(keyValue));
	        }
	        //过滤空 设置 TreeMap  
	        SortedMap<String,String> packageParams = new TreeMap<String,String>();        
	        Iterator it = m.keySet().iterator();  
	        while (it.hasNext()) {  
	            String parameter = (String) it.next();  
	            String parameterValue = m.get(parameter);  

	            String v = "";  
	            if(null != parameterValue) {  
	                v = parameterValue.trim();  
	            }  
	            packageParams.put(parameter, v);  
	        }    
			JSONObject jsonObject = JSONObject.fromObject(packageParams);  
	        payRemote.wechatAppNotify(jsonObject.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	
}

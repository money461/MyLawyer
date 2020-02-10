package com.tz.config;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bouncycastle.jcajce.provider.symmetric.IDEA.Mac;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import sun.misc.BASE64Decoder;
import org.apache.commons.codec.binary.Base64;

/**
 * pre 前置过滤器 --- 请求接口验证
 * @author menglin
 *
 */
public class AccessFilter extends ZuulFilter {
	private final Logger _logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		ctx.setSendZuulResponse(true);// 会进行路由，也就是会调用api服务提供者
		ctx.setResponseStatusCode(200);
		ctx.set("isOK", true);// 可以把一些值放到ctx中，便于后面的filter获取使用
		
	/*	
		String paramter = "";
		Map<String, String[]> params = request.getParameterMap();
		params.entrySet();
		try {
			Map<String, String> returnParams = RSAtest.getParameterStringMap(params);
			paramter = RSAtest.formatUrlMap(returnParams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_logger.info(request.getMethod() + " " + request.getRequestURL() + " " + paramter);
		boolean status = false;
		try {
			// 处理参数
			if (paramter.indexOf("&sign=") != -1) {
				String signVal = paramter.substring(paramter.indexOf("&sign=") + 6, paramter.length());
				signVal = signVal.replace(' ','+');
				_logger.info("传入的参数信息  开始前：----"+paramter);
				paramter = paramter.substring(0, paramter.indexOf("&sign="));
				_logger.info("传入的参数信息  处理后：----"+paramter);
				byte[] signature2 = Base64.decodeBase64(signVal);
				System.out.println("传入的签名：" + signVal);
				if (RSAtest.verify(paramter.getBytes("UTF-8"), signature2)) {
					_logger.info("签名成功");
					status = true ;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 如果用户名和密码都正确，则继续执行下一个filter
		if (status) {
			ctx.setSendZuulResponse(true);// 会进行路由，也就是会调用api服务提供者
			ctx.setResponseStatusCode(200);
			ctx.set("isOK", true);// 可以把一些值放到ctx中，便于后面的filter获取使用
		} else {
			ctx.setSendZuulResponse(false);// 不需要进行路由，也就是不会调用api服务提供者
			ctx.setResponseStatusCode(401);
			ctx.set("no", false);// 可以把一些值放到ctx中，便于后面的filter获取使用
			// 返回内容给客户端
			ctx.setResponseBody("{\"flag\":\"1000\",\"msg\":\"签名验证失败！\",\"data\":\"null\"}");// 返回错误内容

			HttpServletResponse response = ctx.getResponse();
			response.addHeader("Access-Control-Allow-Origin", "*");
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.setLocale(new java.util.Locale("zh", "CN"));
		}*/
		return null;
	}
}
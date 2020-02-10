package com.tz.config;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bouncycastle.jcajce.provider.symmetric.IDEA.Mac;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import sun.misc.BASE64Decoder;
import org.apache.commons.codec.binary.Base64;
/**
 *post 后置过滤器 ----------用于接口验证服务端接口验证
 * @author menglin
 * 2018年2月5日16:41:28
 *
 */
@Component
public class ErrorFilter extends ZuulFilter {
	private final Logger _logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public String filterType() {
		return "error";
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
		  
		_logger.info("进入异常过滤器");  
		ctx.setResponseBody("{\"flag\":\"5000\",\"msg\":\"服务器繁忙，请稍后重试！\",\"data\":\"null\"}");// 返回错误内容
		HttpServletResponse response = ctx.getResponse();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setLocale(new java.util.Locale("zh", "CN"));
  
		return null;
	}
}
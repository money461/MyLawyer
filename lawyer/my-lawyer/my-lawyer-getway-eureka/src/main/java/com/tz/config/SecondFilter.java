package com.tz.config;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bouncycastle.jcajce.provider.symmetric.IDEA.Mac;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.tz.cache.RedisCache;
import com.tz.res.Constant;

import sun.misc.BASE64Decoder;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ArrayUtils;
/**
 *pre 前置过滤器 -----------用于接口权限验证--后端管理接口权限  
 * @author menglin
 * 	2018年2月5日16:41:37
 *
 */
@Component
public class SecondFilter extends ZuulFilter {
	private final Logger _logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RedisCache cache;
	
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();  
        HttpServletRequest request = ctx.getRequest();  
        _logger.info("second过滤器");  
		Map<String, String[]> params = request.getParameterMap();
		params.entrySet();
		_logger.info(request.getMethod() + " " + request.getRequestURI() + " " + params.toString());
		String uri = request.getRequestURI();
		if(!Constant.WHITE_lIST.equals(uri)) {
			if(request.getRequestURL().toString().contains("admin")) {
				 _logger.info("后端方法");
				 try {
					// map集合
					Map<String, String> returnParams = RSAtest.getParameterStringMap(params);
					String userId = returnParams.get("userId"); 
					if(!"1".equals(userId)) {
						_logger.info("userId:-------------"+userId);
						String cache_key_auth = RedisCache.CAHCEADMIN + RedisCache.CAHCENAME_AUTH+ userId;
						String result_cache = cache.getCache(cache_key_auth, String.class);
						boolean flag = false; 
						//缓存判断是否存在
						if(null != result_cache) {
							_logger.info("result_cache:-------------"+result_cache);
							if(ArrayUtils.contains(result_cache.split(","), request.getRequestURI())) {
								flag = true;
							}	
						}
						if(!flag) {
							ctx.setSendZuulResponse(false);// 不需要进行路由，也就是不会调用api服务提供者
							ctx.setResponseStatusCode(401);
							ctx.set("no", false);// 可以把一些值放到ctx中，便于后面的filter获取使用
							// 返回内容给客户端
							ctx.setResponseBody("{\"flag\":\"10000\",\"msg\":\"没有访问的权限！\",\"data\":\"null\"}");// 返回错误内容
							HttpServletResponse response = ctx.getResponse();
							response.addHeader("Access-Control-Allow-Origin", "*");
							response.setContentType("application/json");
							response.setCharacterEncoding("UTF-8");
							response.setLocale(new java.util.Locale("zh", "CN"));
						}
					}
					//		
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
        return null;  
	}
}
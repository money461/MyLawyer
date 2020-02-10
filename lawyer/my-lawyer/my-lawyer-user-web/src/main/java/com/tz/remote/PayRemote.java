package com.tz.remote;

import java.math.BigDecimal;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.config.FeignConfiguration;
import com.tz.pojo.ZaComAuthentication;
import com.tz.res.AppMsgResult;

/**
 * 支付远程服务调用
 * 
 * @author menglin 2017年12月28日09:58:43
 *
 */
@FeignClient(name = "my-lawyer-user-service",configuration=FeignConfiguration.class, fallback = PayRemoteHystrix.class)
public interface PayRemote {

	// 支付宝支付
	@PostMapping("pay/api/zfbAppPay")
	public AppMsgResult zfbAppPay(@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "amount", required = false) BigDecimal amount,
			@RequestParam(value = "userIdTo", required = false) String userIdTo,
			@RequestParam(value = "orderId", required = false) String orderId,
			@RequestParam(value = "userType", required = true) String userType,
			@RequestParam(value = "giftId", required = false) String giftId,
			@RequestParam(value = "giftNum", required = false) int giftNum);

	// 微信支付
	@PostMapping("pay/api/wechatAppPay")
	public AppMsgResult wechatAppPay(@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "amount", required = false) BigDecimal amount,
			@RequestParam(value = "userIdTo", required = false) String userIdTo,
			@RequestParam(value = "orderId", required = false) String orderId,
			@RequestParam(value = "userType", required = true) String userType,
			@RequestParam(value = "giftId", required = false) String giftId,
			@RequestParam(value = "giftNum", required = false) int giftNum);

	// 支付宝支付回调
	@PostMapping("pay/api/zfbAppNotify")
	public AppMsgResult zfbAppNotify();

	// 微信支付回调
	@PostMapping("pay/api/wechatAppNotify")
	public AppMsgResult wechatAppNotify(@RequestParam(value = "packageParamsStr", required = true) String packageParamsStr);

}

package com.tz.controller;

import java.math.BigDecimal;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tz.pojo.ZaComAuthentication;
import com.tz.res.AppMsgResult;
import com.tz.service.CompanyService;
import com.tz.service.PayService;

/**
 * 支付类
 * 
 * @author menglin 2017年12月28日09:49:05
 */

@RestController
@RequestMapping("pay/api")
public class PayComtroller {

	// 支付接口
	@Autowired
	private PayService payService;

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
	public AppMsgResult zfbAppPay(@RequestParam(required = true) String userId,
			@RequestParam(required = true) String token, @RequestParam(required = true) String type,
			@RequestParam(required = false) BigDecimal amount, @RequestParam(required = false) String userIdTo,
			@RequestParam(required = false) String orderId, @RequestParam(required = true) String userType,
			@RequestParam(required = false) String giftId, @RequestParam(required = false) int giftNum) {
		return payService.zfbAppPay(userId, token, type, amount, userIdTo, orderId, userType, giftId, giftNum);
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
	public AppMsgResult wechatAppPay(HttpServletRequest request, @RequestParam(required = true) String userId,
			@RequestParam(required = true) String token, @RequestParam(required = true) String type,
			@RequestParam(required = true) BigDecimal amount, @RequestParam(required = false) String userIdTo,
			@RequestParam(required = false) String orderId, @RequestParam(required = true) String userType,
			@RequestParam(required = false) String giftId, @RequestParam(required = false) int giftNum) {
		return payService.wechatAppPay(request, userId, token, type, amount, userIdTo, orderId, userType, giftId,
				giftNum);
	}

	/**
	 * 支付宝支付回调
	 * 
	 * @param request
	 * @param response
	 */
	@PostMapping("/zfbAppNotify")
	public void zfbAppNotify(HttpServletRequest request) {
		payService.zfbAppNotify(request);
	}

	/**
	 * 微信支付回调
	 * 
	 * @param request
	 * @param response
	 */
	@PostMapping("/wechatAppNotify")
	public void wechatAppNotify(@RequestParam(required = true) String packageParamsStr, HttpServletResponse response) {
		payService.wechatAppNotify(packageParamsStr, response);
	}

}

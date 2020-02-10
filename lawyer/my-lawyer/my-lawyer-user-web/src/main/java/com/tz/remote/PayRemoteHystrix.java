package com.tz.remote;

import java.math.BigDecimal;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.pojo.ZaComAuthentication;
import com.tz.res.AppMsgResult;

/**
 * 支付服务熔断类
 * @author menglin 2017年12月28日09:59:39
 *
 */
@Component
public class PayRemoteHystrix implements PayRemote{

	@Override
	public AppMsgResult zfbAppPay(String userId, String token, String type, BigDecimal amount, String userIdTo,
			String orderId,String userType, String giftId,int giftNum) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult wechatAppPay(String userId, String token, String type,
			BigDecimal amount, String userIdTo, String orderId,String userType, String giftId,int giftNum) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult zfbAppNotify() {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult wechatAppNotify(String packageParamsStr) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	


   
}

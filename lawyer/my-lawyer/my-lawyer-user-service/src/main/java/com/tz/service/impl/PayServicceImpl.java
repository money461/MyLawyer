package com.tz.service.impl;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.aliyuncs.exceptions.ClientException;
import com.tz.cache.RedisCache;
import com.tz.id.IDUtils;
import com.tz.mapper.ZaComAuthenticationMapper;
import com.tz.mapper.ZaGiftMapper;
import com.tz.mapper.ZaLawyerAuthenticationMapper;
import com.tz.mapper.ZaUserAwardRecordMapper;
import com.tz.mapper.ZaUserDealLogMapper;
import com.tz.mapper.ZaUserIncomeRecordMapper;
import com.tz.mapper.ZaUserMapper;
import com.tz.mapper.ZaUserProfitMapper;
import com.tz.mapper.ZaUserPublishCaseMapper;
import com.tz.mapper.ZaUserPurchaseRecordMapper;
import com.tz.mapper.vo.ZaComAuthenticationMapperVo;
import com.tz.mapper.vo.ZaLawyerAuthenticationMapperVo;
import com.tz.pojo.ZaComAuthentication;
import com.tz.pojo.ZaComAuthenticationExample;
import com.tz.pojo.ZaGift;
import com.tz.pojo.ZaGiftExample;
import com.tz.pojo.ZaLawyerAchievementsExample;
import com.tz.pojo.ZaLawyerAuthentication;
import com.tz.pojo.ZaLawyerAuthenticationExample;
import com.tz.pojo.ZaUser;
import com.tz.pojo.ZaUserAwardRecord;
import com.tz.pojo.ZaUserAwardRecordExample;
import com.tz.pojo.ZaUserDealLog;
import com.tz.pojo.ZaUserDealLogExample;
import com.tz.pojo.ZaUserExample;
import com.tz.pojo.ZaUserIncomeRecord;
import com.tz.pojo.ZaUserIncomeRecordExample;
import com.tz.pojo.ZaUserProfit;
import com.tz.pojo.ZaUserProfitExample;
import com.tz.pojo.ZaUserPublishCase;
import com.tz.pojo.ZaUserPublishCaseExample;
import com.tz.pojo.ZaUserPurchaseRecord;
import com.tz.pojo.ZaUserPurchaseRecordExample;
import com.tz.pojo.vo.CategoryNames;
/*import com.tz.pojo.vo.CategoryNames;*/
import com.tz.pojo.vo.ZaUserVo;
import com.tz.res.AppMsgResult;
import com.tz.res.Constant;
import com.tz.sdk.weixin.WXPayConstants;
import com.tz.sdk.weixin.WXPayUtil;
import com.tz.sdk.weixin.WeChatPayConfig;
import com.tz.sdk.zfb.AlipayConfig;
import com.tz.service.PayService;
import com.tz.service.UserService;
import com.tz.sms.ALiDaYuUtil;
import com.tz.validate.ValidateUtil;

/**
 * 用户接口实现类
 * 
 * @author menglin 2017年12月26日17:37:55
 */
@Service
public class PayServicceImpl implements PayService {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	// 用户类
	@Autowired
	ZaUserMapper userMapper;
	private ZaUserExample example;
	private ZaUserExample.Criteria criteria;

	// 支付订单日志
	@Autowired
	ZaUserDealLogMapper dealLogMapper;
	private ZaUserDealLogExample dealLogExample;
	private ZaUserDealLogExample.Criteria criteria2;

	// 用户记录收入信息
	@Autowired
	ZaUserIncomeRecordMapper userIncomeRecordMapper;
	private ZaUserIncomeRecordExample userIncomeRecordExample;
	private ZaUserDealLogExample.Criteria criteria3;

	// 用户总收益充值信息
	@Autowired
	ZaUserProfitMapper userProfitMapper;
	private ZaUserProfitExample userProfitExample;
	private ZaUserProfitExample.Criteria criteria4;

	// 用户记录支出信息
	@Autowired
	ZaUserPurchaseRecordMapper userPurchaseRecordMapper;
	private ZaUserPurchaseRecordExample userPurchaseRecordExample;
	private ZaUserDealLogExample.Criteria criteria5;

	// 企业认证类
	@Autowired
	ZaComAuthenticationMapper comAuthenticationMapper;
	private ZaComAuthenticationExample comAuthenticationExample;
	private ZaComAuthenticationExample.Criteria criteria6;

	// 律师认证
	@Autowired
	ZaLawyerAuthenticationMapper lawyerAuthenticationMapper;
	private ZaLawyerAuthenticationExample lawyerAuthenticationExample;
	private ZaLawyerAuthenticationExample.Criteria criteria7;

	// 发布悬赏委托
	@Autowired
	ZaUserPublishCaseMapper userPublishCaseMapper;
	private ZaUserPublishCaseExample userPublishCaseExample;
	private ZaUserPublishCaseExample.Criteria criteria8;

	// 礼物
	@Autowired
	ZaGiftMapper zaGiftMapper;
	private ZaGiftExample zaGiftExample;
	private ZaGiftExample.Criteria criteria10;

	// 礼物明细
	@Autowired
	ZaUserAwardRecordMapper zaUserAwardRecordMapper;
	private ZaUserAwardRecordExample zaUserAwardRecordExample;
	private ZaUserAwardRecordExample.Criteria criteria9;

	@Autowired
	private RedisCache cache;

	// 用户接口类
	@Autowired
	UserService userService;

	@Transactional
	@Override
	public AppMsgResult zfbAppPay(String userId, String token, String type, BigDecimal amount, String userIdTo,
			String orderId, String userType, String giftId, int giftNum) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null;
		// 1、登录判断
		msgResult = userService.validateUserLogin(userId, token);
		if (200 != (int) msgResult.getFlag()) {
			return msgResult;
		}
		// 实例化客户端 --正式
		/*
		 * AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.GATEWAYURL,
		 * AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json",
		 * AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, "RSA2");
		 */ // 实例化客户端 --测试
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.CS_GATEWAYURL, AlipayConfig.CS_APP_ID,
				AlipayConfig.CS_APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.CS_ALIPAY_PUBLIC_KEY,
				"RSA2");
		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		// type 1 充值， 2 企业认证，3 打赏，4 悬赏。
		if (StringUtils.isNotBlank(type)) {
			// 支付日志表
			msgResult = payPublic(userId,type,amount,userIdTo,orderId,userType,giftId,giftNum);
			if (200 != (int) msgResult.getFlag()) {
				return msgResult;
			}
			ZaUserDealLog userDealLog = (ZaUserDealLog) msgResult.getData();
			boolean flag = true;
			// 充值
			if ("1".equals(type)) {
				userDealLog.setHeadline("账户充值");
				model.setSubject("账户充值");
			} else if ("2".equals(type)) {
				userDealLog.setHeadline("企业认证");
				model.setSubject("企业认证");
			} else if ("3".equals(type)) {
				// 被打赏用户id
				if (StringUtils.isNotBlank(userIdTo)) {
					ZaUserAwardRecord awardRecord = new ZaUserAwardRecord();
					// 金额判断是否存在
					if (amount.compareTo(new BigDecimal(0)) != 1) {
						// 查询礼物
						if (StringUtils.isBlank(giftId)) {
							return AppMsgResult.result(564, "金额id不见啦！", null);
						}
						ZaGift zaGift = zaGiftMapper.selectByPrimaryKey(giftId);
						if (null == zaGift) {
							return AppMsgResult.result(564, "选择金额信息不见啦,请选择其他！", null);
						}
						awardRecord.setPrice(zaGift.getPrice());
					}
					userDealLog.setHeadline("账户打赏");
					model.setSubject("账户打赏");
					String orderId_new = IDUtils.genId();
					awardRecord.setId(orderId_new);
					awardRecord.setUserId(userId);
					awardRecord.setAwardeeId(userIdTo);
					awardRecord.setCreatedTime(new Date());
					awardRecord.setUpdatedTime(new Date());
					awardRecord.setGiftId(giftId);
					awardRecord.setGiftNum(giftNum);
					awardRecord.setReward(userDealLog.getPayCash().setScale(2, BigDecimal.ROUND_HALF_UP));
					awardRecord.setType(3);
					awardRecord.setAwardStatus(0);
					userDealLog.setOrderId(orderId_new);
					addUserAwardRecord(awardRecord);
				} else {
					flag = false;
					msgResult = AppMsgResult.result(564, "被打赏用户id不能为空", null);
				}
			} else if ("4".equals(type)) {
				// 悬赏订单id
				if (StringUtils.isNotBlank(orderId)) {
					userDealLog.setHeadline("发布委托");
					model.setSubject("发布委托");
					userDealLog.setOrderId(orderId);
				} else {
					flag = false;
					msgResult = AppMsgResult.result(565, "发布委托订单id不能为空", null);
				}
				// 代写文书
			} else if ("7".equals(type)) {
				// 悬赏订单id
				if (StringUtils.isNotBlank(orderId)) {
					userDealLog.setHeadline("代写文书");
					model.setSubject("代写文书");
					userDealLog.setOrderId(orderId);
				} else {
					flag = false;
					msgResult = AppMsgResult.result(565, "代写文书订单id不能为空", null);
				}
			} else {
				flag = false;
				msgResult = AppMsgResult.result(563, "支付类型参数错误！", null);
			}
			// 条件判断
			if (flag) {
				int res = dealLogMapper.insertSelective(userDealLog);
				if (res == 1) {
					model.setBody("我的个人律师");
					model.setOutTradeNo(userDealLog.getId());
					model.setTimeoutExpress("30m");
					// 测试
					/* model.setTotalAmount("0.01"); */
					// 正式
					model.setTotalAmount("" + userDealLog.getPayCash() + "");
					model.setProductCode("QUICK_MSECURITY_PAY");
					request.setBizModel(model);
					// 正式
					/*
					 * request.setNotifyUrl(AlipayConfig.APPnotify);
					 */ // 测试
					request.setNotifyUrl(AlipayConfig.CS_APPnotify);
					try {
						// 这里和普通的接口调用不同，使用的是sdkExecute
						AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
						System.out.println(response.getBody());// 就是orderString 可以直接给客户端请求，无需再做处理。
						msgResult = AppMsgResult.result(200, "ok", response.getBody());
					} catch (AlipayApiException e) {
						e.printStackTrace();
						msgResult = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
					}
				} else {
					msgResult = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
				}
			}
		} else {
			msgResult = AppMsgResult.result(562, "支付类型不能为空！", null);
		}
		return msgResult;
	}

	@Transactional
	@Override
	public AppMsgResult wechatAppPay(HttpServletRequest request, String userId, String token, String type,
			BigDecimal amount, String userIdTo, String orderId, String userType, String giftId, int giftNum) {
		// TODO Auto-generated method stub

		System.out.println(WXPayUtil.getIpAddress(request));
		AppMsgResult msgResult = null;
		// 1、登录判断
		msgResult = userService.validateUserLogin(userId, token);
		if (200 != (int) msgResult.getFlag()) {
			return msgResult;
		}
		// type 1 充值， 2 企业认证，3 打赏，4 悬赏。
		if (StringUtils.isNotBlank(type)) {
			// 支付日志表
			msgResult = payPublic(userId,type,amount,userIdTo,orderId,userType,giftId,giftNum);
			if (200 != (int) msgResult.getFlag()) {
				return msgResult;
			}
			ZaUserDealLog userDealLog = (ZaUserDealLog) msgResult.getData();
			boolean flag = true;
			// 准备发起支付的参数
			LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
			// 充值
			if ("1".equals(type)) {
				userDealLog.setHeadline("账户充值");

				parameters.put("body", "账户充值"); // 描述
			} else if ("2".equals(type)) {
				userDealLog.setHeadline("企业认证");
				parameters.put("body", "企业认证"); // 描述
			} else if ("3".equals(type)) {
				// 被打赏用户id
				if (StringUtils.isNotBlank(userIdTo)) {
					ZaUserAwardRecord awardRecord = new ZaUserAwardRecord();
					// 金额判断是否存在
					if (amount.compareTo(new BigDecimal(0)) != 1) {
						// 查询礼物
						if (StringUtils.isBlank(giftId)) {
							return AppMsgResult.result(564, "金额id不见啦！", null);
						}
						ZaGift zaGift = zaGiftMapper.selectByPrimaryKey(giftId);
						if (null == zaGift) {
							return AppMsgResult.result(564, "选择金额信息不见啦,请选择其他！", null);
						}
						awardRecord.setPrice(zaGift.getPrice());
					}
					userDealLog.setHeadline("账户打赏");
					parameters.put("body", "账户打赏"); // 描述
					String orderId_new = IDUtils.genId();
					awardRecord.setId(orderId_new);
					awardRecord.setUserId(userId);
					awardRecord.setAwardeeId(userIdTo);
					awardRecord.setCreatedTime(new Date());
					awardRecord.setUpdatedTime(new Date());
					awardRecord.setGiftId(giftId);
					awardRecord.setGiftNum(giftNum);
					awardRecord.setReward(userDealLog.getPayCash().setScale(2, BigDecimal.ROUND_HALF_UP));
					awardRecord.setType(3);
					awardRecord.setAwardStatus(0);
					userDealLog.setOrderId(orderId_new);
					addUserAwardRecord(awardRecord);
				} else {
					flag = false;
					msgResult = AppMsgResult.result(564, "被打赏用户id不能为空", null);
				}
			} else if ("4".equals(type)) {
				// 悬赏订单id
				if (StringUtils.isNotBlank(orderId)) {
					userDealLog.setHeadline("发布委托");
					parameters.put("body", "发布委托"); // 描述
					userDealLog.setOrderId(orderId);
				} else {
					flag = false;
					msgResult = AppMsgResult.result(565, "发布委托订单id不能为空", null);
				}
			}else if ("7".equals(type)) {
				// 悬赏订单id
				if (StringUtils.isNotBlank(orderId)) {
					userDealLog.setHeadline("代写文书");
					parameters.put("body", "代写文书"); // 描述
					userDealLog.setOrderId(orderId);
				} else {
					flag = false;
					msgResult = AppMsgResult.result(565, "代写文书订单id不能为空", null);
				}
			}else {
				flag = false;
				msgResult = AppMsgResult.result(563, "支付类型参数错误！", null);
			}
			// 条件判断
			if (flag) {
				int res = dealLogMapper.insertSelective(userDealLog);
				if (res == 1) {
					// 发起支付请求
					// 微信支付操作
					// 生成订单
					Map<String, String> result = new HashMap<>();

					parameters.put("appid", WeChatPayConfig.appid); // 应用ID
					parameters.put("mch_id", WeChatPayConfig.app_mchId); // 商户号
					/*
					 * parameters.put("device_info", WeChatPayConfig.DEVICE_INFO); // 设备号
					 */ /* String nonce_str = IDUtils.genId(); */
					parameters.put("nonce_str", IDUtils.genId()); // 随机字符串

					parameters.put("out_trade_no", userDealLog.getId()); // 商户订单号
					// 测试
					parameters.put("total_fee", "1");
					// 订单总金额，单位为分
					// 正式
					parameters.put("total_fee", userDealLog.getPayCash().multiply(new
							  BigDecimal(100)).intValue()+""); // 订单总金额，单位为分
					/*
					 * parameters.put("total_fee", order.getPayment().multiply(new
					 * BigDecimal(100)).intValue()+""); // 订单总金额，单位为分
					 */ parameters.put("spbill_create_ip", WXPayUtil.getIpAddress(request));
					// 用户端实际ip
					/*
					 * parameters.put("notify_url", WeChatPayConfig.APPnotify); // 支付成功后，回调地址 正式
					 */ parameters.put("notify_url", WeChatPayConfig.cs_APPnotify); // 支付成功后，回调地址 测试
					parameters.put("trade_type", WeChatPayConfig.TRADE_TYPE_APP); // 支付类型(JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付、MICROPAY--刷卡支付)
					/* parameters.put("openid", openid); */ // trade_type=JSAPI，此参数必传
					try {
						String sign = WXPayUtil.generateSignature(parameters, WeChatPayConfig.app_key);
						System.out.println("参与签名的参数：" + parameters);
						parameters.put("sign", sign);// 根据微信签名规则，生成签名
						System.out.println("---------------------------------------------------------");
						System.out.println(WXPayUtil.getIpAddress(request));
						// 将请求参数转换为xml格式的string
						String requestXML = WXPayUtil.generateSignedXml(parameters, WeChatPayConfig.app_key);

						System.out.println("---------------------------------------------------------");
						System.out.println(sign);
						System.out.println("---------------------------------------------------------");
						System.out.println(requestXML);
						System.out.println("---------------------------------------------------------");

						// 发送请求
						String xmlData = WXPayUtil.httpsRequest2(
								WXPayConstants.DOMAIN_API + WXPayConstants.UNIFIEDORDER_URL_SUFFIX, "POST", requestXML,
								null);

						System.out.println(xmlData);
						System.out.println("**************************");
						System.out.println(new String(xmlData.getBytes("UTF-8")));
						// 解析XML
						Map<String, String> map = WXPayUtil.xmlToMap(xmlData);
						String prepay_id = (String) map.get("prepay_id");// 获取prepay_id

						result.put("appid", (String) map.get("appid"));
						result.put("noncestr", UUID.randomUUID().toString().replaceAll("-", ""));
						result.put("package", "Sign=WXPay");
						result.put("partnerid", (String) map.get("mch_id"));
						result.put("prepayid", prepay_id);
						result.put("timestamp", System.currentTimeMillis() / 1000 + "");
						String paySign = WXPayUtil.generateSignature(result, WeChatPayConfig.app_key);
						result.put("signType", "MD5");
						result.put("paySign", paySign);
						msgResult = AppMsgResult.result("200", "success", result);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						msgResult = AppMsgResult.result("568", "支付失败", null);
						e.printStackTrace();
					}
				} else {
					msgResult = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
				}
			}
		} else {
			msgResult = AppMsgResult.result(562, "支付类型不能为空！", null);
		}

		return msgResult;
	}

	@Override
	public void zfbAppNotify(HttpServletRequest request) {
		// 获取支付宝POST过来反馈信息
		HttpServletResponse response = null;
		LOG.info("invoke----------/zfbAPPnotify");
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。
			/*
			 * try { valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8"); }
			 * catch (UnsupportedEncodingException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); }
			 */
			params.put(name, valueStr);
		}
		// 切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
		// boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String
		// publicKey, String charset, String sign_type)
		try {
			// 线上
			/*
			 * boolean flag =
			 * AlipaySignature.rsaCheckV1(params,AlipayConfig.ALIPAY_PUBLIC_KEY,"UTF-8",
			 * "RSA2");
			 */
			// 测试
			boolean flag = AlipaySignature.rsaCheckV1(params, AlipayConfig.CS_ALIPAY_PUBLIC_KEY, "UTF-8", "RSA2");

			if (flag) {// 验证成功
				if ("TRADE_SUCCESS".equals(params.get("trade_status"))) {
					// 用户支付的账号
					String buyer_logon_id = params.get("buyer_logon_id");
					LOG.info("buyer_logon_id----------/buyer_logon_id" + buyer_logon_id);
					// 付款金额
					String amount = params.get("buyer_pay_amount");
					LOG.info("amount----------/amount" + amount);
					// 商户订单号
					String out_trade_no = params.get("out_trade_no");
					LOG.info("out_trade_no----------/amount" + out_trade_no);
					// //支付宝交易号
					String trade_no = params.get("trade_no");
					LOG.info("trade_no----------/amount" + trade_no);
					// 交易支付时间
					String gmt_payment = params.get("gmt_payment");
					LOG.info("gmt_payment----------/amount" + gmt_payment);
					// 根据不同类型来进行操作
					// 查询 支付订单
					ZaUserDealLog userDealLog = dealLogMapper.selectByPrimaryKey(out_trade_no);
					// 未支付成功
					if (userDealLog.getStatus() != 1) {
						payPublicNotify(userDealLog, buyer_logon_id, amount, 1);
						// 错误的回调请求
					} else {
						LOG.info("已经修改了订单信息！");
					}
				}
			} else {
				LOG.info("buyer_logon_id----------/flag--" + flag);
			}
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Transactional
	@Override
	public void wechatAppNotify(String packageParamsStr, HttpServletResponse response) {
		// 判断签名是否正确
		String resXml = "";
		try {
			Map packageParams = JSON.parseObject(packageParamsStr);
			LOG.info("微信支付回调------------------------------------");
			if (WXPayUtil.isSignatureValid(packageParams, WeChatPayConfig.app_key)) {
				if ("SUCCESS".equals((String) packageParams.get("result_code"))) {
					// 这里是支付成功
					////////// 执行自己的业务逻辑////////////////
					String mch_id = (String) packageParams.get("mch_id"); // 商户号
					String openid = (String) packageParams.get("openid"); // 用户标识
					String out_trade_no = (String) packageParams.get("out_trade_no"); // 商户订单号
					String total_fee = (String) packageParams.get("total_fee");
					String transaction_id = (String) packageParams.get("transaction_id"); // 微信支付订单号
					// 查询支付订单
					ZaUserDealLog userDealLog = dealLogMapper.selectByPrimaryKey(out_trade_no);

					if (!WeChatPayConfig.app_mchId.equals(mch_id) || userDealLog == null) {
						LOG.info("支付失败,错误信息：" + "参数错误");
						resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
								+ "<return_msg><![CDATA[参数错误]]></return_msg>" + "</xml> ";
					} else {
						// 判断是否处理
						if (userDealLog.getStatus() != 1) {// 支付的价格
							// 订单状态的修改。根据实际业务逻辑执行
							resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
									+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
							// 处理逻辑
							payPublicNotify(userDealLog, openid, total_fee, 2);

						} else {
							resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
									+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
							LOG.info("订单已处理");
						}

					}
				} else {
					LOG.info("支付失败,错误信息：" + packageParams.get("err_code"));
					resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
							+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
				}
			} else {
				resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
						+ "<return_msg><![CDATA[通知签名验证失败]]></return_msg>" + "</xml> ";
				LOG.info("通知签名验证失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ------------------------------
		// 处理业务完毕
		// ------------------------------
		BufferedOutputStream out;
		try {
			out = new BufferedOutputStream(response.getOutputStream());
			out.write(resXml.getBytes());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		System.out.println(new BigDecimal(0).compareTo(new BigDecimal(0)));
		/* "75468049402BD2F7FF592DA8D5574591" */

		/* WXPayUtil.isSignatureValid(packageParams, WeChatPayConfig.app_key); */
	}

	@Override
	public AppMsgResult userIncomeRecord(ZaUserIncomeRecord incomeRecord) {
		// TODO Auto-generated method stub
		userIncomeRecordMapper.insertSelective(incomeRecord);
		return null;
	}

	@Override
	public AppMsgResult userPurchaseRecord(ZaUserPurchaseRecord purchaseRecord) {
		// TODO Auto-generated method stub
		userPurchaseRecordMapper.insertSelective(purchaseRecord);
		return null;

	}

	@Override
	public AppMsgResult addOrUpdateUserProfit(String userId, BigDecimal bd, Integer userType) {
		// TODO Auto-generated method stub
		// 账户充值总金额
		ZaUserProfit userProfit = new ZaUserProfit();
		userProfitExample = new ZaUserProfitExample();
		criteria4 = userProfitExample.createCriteria();
		criteria4.andUserIdEqualTo(userId);
		List<ZaUserProfit> lists = userProfitMapper.selectByExample(userProfitExample);
		userProfit.setUserId(userId);
		userProfit.setUserType(userType);
		if (lists.size() > 0) {
			userProfit = lists.get(0);
			userProfit.setCoinConsumTotal(userProfit.getCoinConsumTotal().add(bd));
			userProfit.setCoinSurplus(userProfit.getCoinSurplus().add(bd));
			userProfit.setLastUseTime(new Date());
			userProfitMapper.updateByPrimaryKeySelective(userProfit);
		} else {
			userProfit.setId(IDUtils.genId());
			userProfit.setCoinConsumTotal(bd);
			userProfit.setCoinSurplus(bd);
			userProfit.setStatus(1);
			/* userProfit.setObType(1); */
			userProfit.setCreatedTime(new Date());
			userProfit.setUpdatedTime(new Date());
			userProfit.setLastUseTime(new Date());
			userProfitMapper.insertSelective(userProfit);
		}
		return null;
	}

	@Transactional
	@Override
	public AppMsgResult payPublicNotify(ZaUserDealLog userDealLog, String openid, String amount, Integer payType) {

		String out_trade_no = userDealLog.getId();

		String userId = userDealLog.getUserId();
		// 订单类型
		String dealPurpose = userDealLog.getDealPurpose();
		// 操作用户类型
		int userType = userDealLog.getUserType();
		// 操作
		// 修改订单日志状态
		userDealLog.setStatus(1);
		userDealLog.setDealTime(new Date());
		userDealLog.setPayAccount(openid);
		dealLogMapper.updateByPrimaryKeySelective(userDealLog);
		// 账户充值
		if ("1".equals(dealPurpose)) {
			// 个人用户存在充值
			if (userType == 1) {
				// 账户充值记录
				ZaUserIncomeRecord userIncomeRecord = new ZaUserIncomeRecord();
				userIncomeRecord.setId(IDUtils.genId());
				userIncomeRecord.setUserId(userId);
				userIncomeRecord.setDealCode(out_trade_no);
				BigDecimal bd = new BigDecimal(amount);
				// 设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)
				bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
				userIncomeRecord.setInCash(bd);
				userIncomeRecord.setInStatus(1);
				userIncomeRecord.setInType(4);
				userIncomeRecord.setInOut(1);
				userIncomeRecord.setEventDesc(userDealLog.getHeadline());
				userIncomeRecord.setUserAccount(openid);
				userIncomeRecord.setPayeeRealName(openid);
				userIncomeRecord.setIncomeTime(new Date());
				userIncomeRecord.setCreatedTime(new Date());
				userIncomeRecord.setUpdatedTime(new Date());
				// 添加用户充值记录
				userIncomeRecord(userIncomeRecord);
				// 记录收益表
				addOrUpdateUserProfit(userId, bd, userType);
			}
			// 企业认证
		} else if ("2".equals(dealPurpose)) {
			boolean flag_2 = false;
			// 个人用户
			if (userType == 1) {
				flag_2 = true;
				// 律师用户
			} else if (userType == 2) {
				// 查询律师下的绑定的用户id
				ZaLawyerAuthentication lawyerAuthentication = lawyerAuthenticationMapper.selectByPrimaryKey(userId);
				userId = lawyerAuthentication.getUserId();
				flag_2 = true;
				// 企业用户
			} else if (userType == 3) {
				LOG.info("ERROR:  企业认证------：" + out_trade_no + " ---用户类型错误" + userType);
				// 其他
			} else {
				LOG.info("ERROR:  企业认证------：" + out_trade_no + " ---用户类型错误" + userType);
			}
			if (flag_2) {
				// 1.用户id--- 查询企业认证信息
				comAuthenticationExample = new ZaComAuthenticationExample();
				criteria6 = comAuthenticationExample.createCriteria();
				criteria6.andUserIdEqualTo(userId);
				List<ZaComAuthentication> authentications = comAuthenticationMapper
						.selectByExample(comAuthenticationExample);
				// 2.修改认证信息为开始认证
				if (authentications.size() > 0) {
					ZaComAuthentication authentication = authentications.get(0);
					// 修改状态为5，审核中
					authentication.setComStatus(5);
					comAuthenticationMapper.updateByPrimaryKeySelective(authentication);
				} else {
					LOG.info("ERROR: 企业认证------用户id为：" + out_trade_no + " ---用户企业认证不存在");
				}
			}
			// 账户打赏
		} else if ("3".equals(dealPurpose)) {
			// 订单号 -- 打赏明细
			String orderId = userDealLog.getOrderId();
			String userIdTo = userDealLog.getUserIdTo();
			
			ZaUserAwardRecord awardRecord = new ZaUserAwardRecord();
			awardRecord.setId(orderId);
			awardRecord.setAwardStatus(1);
			zaUserAwardRecordMapper.updateByPrimaryKeySelective(awardRecord);
			
			// 1. 记录打赏用户记录
			ZaUserPurchaseRecord purchaseRecord = new ZaUserPurchaseRecord();
			purchaseRecord.setId(IDUtils.genId());
			purchaseRecord.setUserId(userId);
			purchaseRecord.setDealCode(out_trade_no);
			BigDecimal bd = new BigDecimal(amount);
			// 设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)
			bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
			purchaseRecord.setPayCash(bd);
			// 支付类型 1 支付宝
			purchaseRecord.setPayType(payType);
			// 状态
			purchaseRecord.setPayStatus(1);
			purchaseRecord.setConsumType(1);
			purchaseRecord.setInOut(1);
			purchaseRecord.setEventDesc("账户打赏");
			purchaseRecord.setConsumTime(new Date());
			purchaseRecord.setCreatedTime(new Date());
			purchaseRecord.setUpdatedTime(new Date());
			// 记录用户消费
			userPurchaseRecord(purchaseRecord);

			// 2. 记录打赏律师收入记录
			ZaUserIncomeRecord userIncomeRecord = new ZaUserIncomeRecord();
			userIncomeRecord.setId(IDUtils.genId());
			userIncomeRecord.setUserId(userIdTo);
			userIncomeRecord.setDealCode(out_trade_no);
			// 设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)
			bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
			userIncomeRecord.setInCash(bd);
			userIncomeRecord.setInStatus(1);
			userIncomeRecord.setInType(6);
			userIncomeRecord.setInOut(0);
			userIncomeRecord.setEventDesc(userDealLog.getHeadline());
			userIncomeRecord.setUserAccount(openid);
			userIncomeRecord.setPayeeRealName(openid);
			userIncomeRecord.setIncomeTime(new Date());
			userIncomeRecord.setCreatedTime(new Date());
			userIncomeRecord.setUpdatedTime(new Date());
			// 记录律师用户打赏收益
			userIncomeRecord(userIncomeRecord);

			// 3、增加被打赏用户记录 --- 针对律师
			// 添加收益记录
			addOrUpdateUserProfit(userIdTo, bd, 2);
			// 发布悬赏
		} else if ("4".equals(dealPurpose)) {
			// 订单号 -- 发布悬赏委托存在
			String orderId = userDealLog.getOrderId();
			
			//打赏明细
			ZaUserAwardRecord awardRecord = new ZaUserAwardRecord();
			awardRecord.setId(orderId);
			awardRecord.setAwardStatus(1);
			zaUserAwardRecordMapper.updateByPrimaryKeySelective(awardRecord);
			
			// 1、发布悬赏委托的订单id修改状态
			// userPublishCaseMapper;
			ZaUserPublishCase userPublishCase = new ZaUserPublishCase();
			userPublishCase.setId(orderId);

			// 2. 记录悬赏消费记录
			ZaUserPurchaseRecord purchaseRecord = new ZaUserPurchaseRecord();
			purchaseRecord.setId(IDUtils.genId());
			purchaseRecord.setUserId(userId);
			purchaseRecord.setDealCode(out_trade_no);
			BigDecimal bd = new BigDecimal(amount);
			// 设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)
			bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
			purchaseRecord.setPayCash(bd);
			// 支付类型 1 支付宝
			purchaseRecord.setPayType(payType);
			// 状态
			purchaseRecord.setPayStatus(1);
			// 悬赏
			purchaseRecord.setConsumType(2);
			purchaseRecord.setInOut(1);
			purchaseRecord.setEventDesc("账户打赏");
			purchaseRecord.setConsumTime(new Date());
			purchaseRecord.setCreatedTime(new Date());
			purchaseRecord.setUpdatedTime(new Date());
			// 记录用户消费
			userPurchaseRecord(purchaseRecord);

			// 支付成功 等待解决。
			userPublishCase.setStatus(1);
			userPublishCase.setUpdatedTime(new Date());
			userPublishCaseMapper.updateByPrimaryKeySelective(userPublishCase);
			// 类型错误
		//代写文书	
		}else if ("7".equals(dealPurpose)) {
			// 订单号 -- 发布悬赏委托存在
			String orderId = userDealLog.getOrderId();
			
			//打赏明细
			ZaUserAwardRecord awardRecord = new ZaUserAwardRecord();
			awardRecord.setId(orderId);
			awardRecord.setAwardStatus(1);
			zaUserAwardRecordMapper.updateByPrimaryKeySelective(awardRecord);
			// 1、发布悬赏委托的订单id修改状态
			// userPublishCaseMapper;
			ZaUserPublishCase userPublishCase = new ZaUserPublishCase();
			userPublishCase.setId(orderId);

			// 2. 记录悬赏消费记录
			ZaUserPurchaseRecord purchaseRecord = new ZaUserPurchaseRecord();
			purchaseRecord.setId(IDUtils.genId());
			purchaseRecord.setUserId(userId);
			purchaseRecord.setDealCode(out_trade_no);
			BigDecimal bd = new BigDecimal(amount);
			// 设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)
			bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
			purchaseRecord.setPayCash(bd);
			// 支付类型 1 支付宝
			purchaseRecord.setPayType(payType);
			// 状态
			purchaseRecord.setPayStatus(1);
			// 悬赏
			purchaseRecord.setConsumType(2);
			purchaseRecord.setInOut(1);
			purchaseRecord.setEventDesc("账户打赏");
			purchaseRecord.setConsumTime(new Date());
			purchaseRecord.setCreatedTime(new Date());
			purchaseRecord.setUpdatedTime(new Date());
			// 记录用户消费
			userPurchaseRecord(purchaseRecord);

			// 支付成功 等待解决。
			userPublishCase.setStatus(1);
			userPublishCase.setUpdatedTime(new Date());
			userPublishCaseMapper.updateByPrimaryKeySelective(userPublishCase);
			// 类型错误
		}else {
			LOG.info("数据类型错误！");
		}
		return null;
	}

	@Override
	public AppMsgResult payPublic(String userId, String type, BigDecimal amount, String userIdTo, String orderId,
			String userType, String giftId, int giftNum) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null;
		if (StringUtils.isNotBlank(type)) {
			// 支付日志表
			ZaUserDealLog userDealLog = new ZaUserDealLog();
			String id = IDUtils.genId();
			userDealLog.setId(id);
			userDealLog.setUserType(Integer.parseInt(userType));
			userDealLog.setCreatedTime(new Date());
			userDealLog.setUpdatedTime(new Date());
			userDealLog.setPayType(1);
			userDealLog.setDealPurpose(type);
			userDealLog.setPayCash(amount);
			userDealLog.setUserId(userId);
			userDealLog.setStatus(0);
			// 判断是其他操作
			if (!"1".equals(type)) {
				// 金额判断是否存在
				if (amount.compareTo(new BigDecimal(0)) != 1) {
					// 查询礼物
					if (StringUtils.isBlank(giftId)) {
						return AppMsgResult.result(564, "金额id不见啦！", null);
					}
					ZaGift zaGift = zaGiftMapper.selectByPrimaryKey(giftId);
					if (null == zaGift) {
						return AppMsgResult.result(564, "选择金额信息不见啦,请选择其他！", null);
					}
					giftNum = giftNum <= 0 ? 1 : giftNum;
					userDealLog.setPayCash(zaGift.getPrice().multiply(new BigDecimal(giftNum)));
				}
			}
			msgResult = AppMsgResult.result(200, "success", userDealLog);
		} else {
			msgResult = AppMsgResult.result(562, "支付类型不能为空！", null);
		}
		return msgResult;
	}

	@Override
	public int addUserAwardRecord(ZaUserAwardRecord awardRecord) {
		// TODO Auto-generated method stub
		return zaUserAwardRecordMapper.insertSelective(awardRecord);
	}

}

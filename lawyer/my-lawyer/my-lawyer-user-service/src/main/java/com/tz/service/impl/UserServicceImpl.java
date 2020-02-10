package com.tz.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.aliyuncs.exceptions.ClientException;
import com.tz.cache.RedisCache;
import com.tz.id.IDUtils;
import com.tz.mapper.ZaComAuthenticationMapper;
import com.tz.mapper.ZaLawyerAuthenticationMapper;
import com.tz.mapper.ZaUserEscrowMapper;
import com.tz.mapper.ZaUserMapper;
import com.tz.mapper.ZaUserProfitMapper;
import com.tz.mapper.ZaUserPurchaseRecordMapper;
import com.tz.mapper.vo.ZaComAuthenticationMapperVo;
import com.tz.mapper.vo.ZaLawyerAuthenticationMapperVo;
import com.tz.pojo.ZaComAuthentication;
import com.tz.pojo.ZaComAuthenticationExample;
import com.tz.pojo.ZaLawyerAuthentication;
import com.tz.pojo.ZaLawyerAuthenticationExample;
import com.tz.pojo.ZaUser;
import com.tz.pojo.ZaUserDealLogExample;
import com.tz.pojo.ZaUserEscrow;
import com.tz.pojo.ZaUserEscrowExample;
import com.tz.pojo.ZaUserExample;
import com.tz.pojo.ZaUserProfit;
import com.tz.pojo.ZaUserProfitExample;
import com.tz.pojo.ZaUserPurchaseRecord;
import com.tz.pojo.ZaUserPurchaseRecordExample;
import com.tz.pojo.vo.CategoryNames;
/*import com.tz.pojo.vo.CategoryNames;*/
import com.tz.pojo.vo.ZaUserVo;
import com.tz.res.AppMsgResult;
import com.tz.res.Constant;
import com.tz.sdk.zfb.AlipayConfig;
import com.tz.service.HxChatService;
import com.tz.service.UserService;
import com.tz.sms.ALiDaYuUtil;
import com.tz.util.Hxchat;
import com.tz.validate.ValidateUtil;

/**
 * 用户接口实现类
 * 
 * @author menglin 2017年12月26日17:37:55
 */
@Service
public class UserServicceImpl implements UserService {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	// 用户类
	@Autowired
	ZaUserMapper userMapper;
	private ZaUserExample example;
	private ZaUserExample.Criteria criteria;

	// 用户类
	/*
	 * @Autowired TzUserMapper userMapper2; private TzUserExample example2; private
	 * TzUserExample.Criteria criteria2;
	 */

	// 企业认证类
	@Autowired
	ZaComAuthenticationMapper comAuthenticationMapper;
	private ZaComAuthenticationExample comAuthenticationExample;
	private ZaComAuthenticationExample.Criteria criteria3;

	// 企业认证扩展接口类
	@Autowired
	ZaComAuthenticationMapperVo comAuthenticationMappervo;

	// 律师认证类
	@Autowired
	ZaLawyerAuthenticationMapper lawyerAuthenticationMapper;
	private ZaLawyerAuthenticationExample lawyerAuthenticationExample;
	private ZaLawyerAuthenticationExample.Criteria criteria4;

	// 律师认证扩展接口类
	@Autowired
	ZaLawyerAuthenticationMapperVo lawyerAuthenticationMappervo;

	// 个人总收益充值信息
	@Autowired
	ZaUserProfitMapper userProfitMapper;
	private ZaUserProfitExample userProfitExample;
	private ZaUserProfitExample.Criteria criteria5;

	// 用户记录支出信息
	@Autowired
	ZaUserPurchaseRecordMapper userPurchaseRecordMapper;
	private ZaUserPurchaseRecordExample userPurchaseRecordExample;
	private ZaUserDealLogExample.Criteria criteria6;

	// 环信接口
	@Autowired
	private HxChatService hxChatService;

	// 三方登录接口
	@Autowired
	ZaUserEscrowMapper zaUserEscrowMapper;
	private ZaUserEscrowExample userEscrowExample;
	private ZaUserEscrowExample.Criteria criteria2;

	@Autowired
	private RedisCache cache;

	@Override
	public AppMsgResult validateUserLogin(String userId, String token) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null;
		if (StringUtils.isBlank(userId)) {
			return AppMsgResult.result(538, "登录用户的id不能为空！", null);
		}
		if (StringUtils.isBlank(token)) {
			return AppMsgResult.result(7000, "登录token不能为空！", null);
		}
		// 获取后台管理用户的登录 token
		String cache_key = RedisCache.CAHCENAME + "|getUserToken|" + userId;
		String result_cache = cache.getCache(cache_key, String.class);
		// 是否存在
		if (null == result_cache) {
			return AppMsgResult.result(6000, "用户未登录！", null);
		}
		// 验证
		if (!result_cache.equals(token)) {
			return AppMsgResult.result(6000, "用户未登录！", null);
		}
		// 重置userToken有效时间
		cache.putCacheWithExpireTime(cache_key, result_cache, RedisCache.USERCAHCETIME);
		msgResult = AppMsgResult.result(200, "success", null);
		return msgResult;
	}

	@Override
	public AppMsgResult getUserList(Integer curPage, Integer rows) {
		AppMsgResult msgResult = null;
		/*
		 * String cache_key = RedisCache.CAHCENAME + "|getUserList|" + curPage + "|" +
		 * rows; // 先去缓存中取 List<TzUser> result_cache = cache.getListCache(cache_key,
		 * TzUser.class); if (result_cache != null) { LOG.info("get cache with key:" +
		 * cache_key); } else { // 缓存中没有再去数据库取，并插入缓存（缓存时间为60秒） rows = rows ==
		 * null?10:rows; curPage = curPage example2 = new TzUserExample(); // 分页处理
		 * PageHelper.startPage(curPage, rows); criteria2 = example2.createCriteria();
		 * result_cache = userMapper2.selectByExample(example2);
		 * System.out.println(result_cache); cache.putListCacheWithExpireTime(cache_key,
		 * result_cache, RedisCache.CAHCETIME); LOG.info("put cache with key:" +
		 * cache_key); } msgResult = AppMsgResult.result(200, "success", result_cache);
		 */
		return msgResult;
	}

	@Transactional
	@Override
	public AppMsgResult regist(String userPhone, String userPassword, String code) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null;
		// 判断空
		if (StringUtils.isNotBlank(userPhone)) {
			if (StringUtils.isNotBlank(userPassword)) {
				if (StringUtils.isNotBlank(code)) {
					// 手机号码判断
					if (ValidateUtil.phoneValidate(userPhone)) {
						// 注册
						example = new ZaUserExample();
						criteria = example.createCriteria();
						criteria.andUserPhoneEqualTo(userPhone);
						List<ZaUser> users = userMapper.selectByExample(example);
						int count = users.size();
						// 判断是否注册
						if (count > 0) {
							msgResult = AppMsgResult.result(520, "手机号码已经被使用！", "");
						} else {
							// 测试
							String cache_key2 = RedisCache.CAHCENAME + "|getTelCode|regist|" + userPhone;
							cache.putCacheWithExpireTime(cache_key2, "123456", RedisCache.CODECAHCETIME);

							String cache_key = RedisCache.CAHCENAME + "|getTelCode|regist|" + userPhone;
							// 验证码
							String result_cache = cache.getCache(cache_key, String.class);
							System.out.println(result_cache);
							// 验证码判断
							if (StringUtils.isNotBlank(result_cache)) {
								if (code.equals(result_cache)) {
									// 密码判断
									if (ValidateUtil.passWordValidate(userPassword)) {
										ZaUser user = new ZaUser();
										String id = IDUtils.genId();
										user.setUpdatedTime(new Date());
										// 验证成功 添加用户数据
										user.setCreatedTime(new Date());
										user.setLastLoginTime(new Date());
										user.setUserPhone(userPhone);
										user.setId(IDUtils.genId());
										user.setHeadUrl(Constant.DFHEAD2);
										user.setUserAccount(userPhone);
										String ucode = IDUtils.getCode();
										user.setCode(ucode);
										user.setId(id);
										// 默认在线
										user.setState(0);
										// 默认不冻结
										user.setStatus(1);
										// 默认男士
										user.setUserGender(1);
										String name = "lawyer_" + IDUtils.phoneCode();
										user.setUserNick(name);
										user.setUserPassword(IDUtils.md5(userPassword + ucode));
										int res = userMapper.insertSelective(user);
										if (res == 1) {
											// 注册自动登录
											String login_key = RedisCache.CAHCENAME + "|getUserToken|" + id;
											String token = IDUtils.md5(id + System.currentTimeMillis());
											cache.putCacheWithExpireTime(login_key, token, RedisCache.USERCAHCETIME);

											// 封装用户数据 注册按照个人用户处理
											ZaUserVo userVo = new ZaUserVo();
											userVo.setId(id);
											userVo.setUserAccount(userPhone);
											userVo.setName(name);
											userVo.setUserPhone(userPhone);
											userVo.setHeadUrl(Constant.FILESERVER_URL_TEST + Constant.DFHEAD2);
											userVo.setUserType(1);
											userVo.setStatus(1);
											userVo.setToken(token);
											hxChatService.adduser("eh_" + id, "123456", name);
											userVo.setHxusername("eh_" + id);
											userVo.setHxpassword("123456");
											userVo.setHxusernicke(name);

											msgResult = AppMsgResult.result(200, "success", userVo);
										} else {
											msgResult = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
										}
									} else {
										msgResult = AppMsgResult.result(521, "密码必须为6-19位数字、字母组合！", null);
									}
								} else {
									msgResult = AppMsgResult.result(522, "验证码错误！", null);
								}
								// code判断
							} else {
								msgResult = AppMsgResult.result(522, "验证码错误！", null);
							}
						}
					} else {
						msgResult = AppMsgResult.result(523, "手机号码格式错误！", null);
					}
				} else {
					msgResult = AppMsgResult.result(524, "验证码不能为空！", null);
				}
			} else {
				msgResult = AppMsgResult.result(525, "密码不能为空！", null);
			}
		} else {
			msgResult = AppMsgResult.result(526, "手机号码不能为空！", null);
		}
		return msgResult;
	}

	@Override
	public AppMsgResult getUserPhoneCode(String userPhone, String type) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null;
		// 判断空
		if (StringUtils.isNotBlank(userPhone)) {
			// 格式校验
			if (ValidateUtil.phoneValidate(userPhone)) {
				example = new ZaUserExample();
				criteria = example.createCriteria();
				criteria.andUserPhoneEqualTo(userPhone);
				List<ZaUser> users = userMapper.selectByExample(example);
				int count = users.size();
				// 注册获取验证码
				if ("regist".equals(type)) {
					// 账号已经注册
					if (count > 0) {
						msgResult = AppMsgResult.result(520, "手机号码已经被使用！", null);
					} else {
						// 缓存用户验证码手机号码
						String cache_key = RedisCache.CAHCENAME + "|getTelCode|" + type + "|" + userPhone;
						// 操作
						String result_cache = IDUtils.phoneCode();
						// 判断操作
						String res = "";
						try {
							res = ALiDaYuUtil.sendMessage("爱心520商城", "SMS_105250418", userPhone, result_cache);
						} catch (ClientException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							res = "";
						}
						if ("OK".equals(res)) {
							cache.putCacheWithExpireTime(cache_key, result_cache, RedisCache.CODECAHCETIME);
							msgResult = AppMsgResult.result(200, "success", null);
						} else {
							msgResult = AppMsgResult.result(527, "发送验证码失败，请稍后重试！", null);
						}
					}
					// 忘记密码获取验证码
				} else if ("forget".equals(type)) {
					if (count < 1) {
						msgResult = AppMsgResult.result(528, "手机号码未注册！", null);
					} else {
						// 操作
						// 判断操作
						// 缓存用户验证码手机号码
						String cache_key = RedisCache.CAHCENAME + "|getTelCode|" + type + "|" + userPhone;
						// 操作
						String result_cache = IDUtils.phoneCode();
						// 判断操作
						String res = "";
						try {
							res = ALiDaYuUtil.sendMessage("爱心520商城", "SMS_105250417", userPhone, result_cache);
						} catch (ClientException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							res = "";
						}
						if ("OK".equals(res)) {
							cache.putCacheWithExpireTime(cache_key, result_cache, RedisCache.CODECAHCETIME);
							msgResult = AppMsgResult.result(200, "success", null);
						} else {
							msgResult = AppMsgResult.result(527, "发送验证码失败，请稍后重试！", null);
						}
					}
				} else {
					msgResult = AppMsgResult.result(529, "type类型参数错误！", null);
				}
			} else {
				msgResult = AppMsgResult.result(523, "手机号码格式错误！", null);
			}
		} else {
			msgResult = AppMsgResult.result(526, "手机号码不能为空！", null);
		}
		return msgResult;
	}

	@Override
	public AppMsgResult forgetPassWord(String userPhone, String userPassword, String code) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null;
		// 判断空
		if (StringUtils.isNotBlank(userPhone)) {
			if (StringUtils.isNotBlank(userPassword)) {
				if (StringUtils.isNotBlank(code)) {
					// 手机号码判断
					if (ValidateUtil.phoneValidate(userPhone)) {
						example = new ZaUserExample();
						criteria = example.createCriteria();
						criteria.andUserPhoneEqualTo(userPhone);
						List<ZaUser> users = userMapper.selectByExample(example);
						int count = users.size();
						// 判断是否注册
						if (count < 1) {
							msgResult = AppMsgResult.result(528, "手机号码未注册！", null);
						} else {
							String cache_key = RedisCache.CAHCENAME + "|getTelCode|forget|" + userPhone;
							// 验证码
							String result_cache = cache.getCache(cache_key, String.class);
							System.out.println(result_cache);
							// 验证码判断
							if (StringUtils.isNotBlank(result_cache)) {
								if (code.equals(result_cache)) {
									// 密码判断
									if (ValidateUtil.passWordValidate(userPassword)) {
										ZaUser user = users.get(0);
										user.setUserPassword(IDUtils.md5(userPassword + user.getCode()));
										int res = userMapper.updateByPrimaryKeySelective(user);
										if (res == 1) {
											msgResult = AppMsgResult.result(200, "success", null);
										} else {
											msgResult = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
										}
									} else {
										msgResult = AppMsgResult.result(521, "密码必须为6-19位数字、字母组合！", null);
									}
								} else {
									msgResult = AppMsgResult.result(522, "验证码错误", null);
								}
								// code判断
							} else {
								msgResult = AppMsgResult.result(522, "验证码错误！", null);
							}
						}
					} else {
						msgResult = AppMsgResult.result(523, "手机号码格式错误！", null);
					}
				} else {
					msgResult = AppMsgResult.result(524, "验证码不能为空！", null);
				}
			} else {
				msgResult = AppMsgResult.result(525, "密码不能为空！", null);
			}
		} else {
			msgResult = AppMsgResult.result(526, "手机号码不能为空！", null);
		}
		return msgResult;
	}

	@Override
	public AppMsgResult login(String userPhone, String userPassword, String userType) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null;
		// 判断空
		ZaUserVo userVo = new ZaUserVo();
		String new_userId = "";
		boolean validateFlag = false;
		if (StringUtils.isNotBlank(userPhone)) {
			if (StringUtils.isNotBlank(userPassword)) {
				if (StringUtils.isNotBlank(userType)) {
					// 手机号码判断
					if (ValidateUtil.phoneValidate(userPhone)) {
						// 账号是否存在
						example = new ZaUserExample();
						criteria = example.createCriteria();
						criteria.andUserPhoneEqualTo(userPhone);
						List<ZaUser> users = userMapper.selectByExample(example);
						if (users.size() > 0) {
							ZaUser user = users.get(0);
							String code = user.getCode();
							String id = user.getId();
							Integer Status = user.getStatus();
							if (user.getUserPassword().equals(IDUtils.md5(userPassword + code))) {
								// 个人用户
								if ("1".equals(userType)) {
									if (Status == 1) {
										// 单点登录
										String userId = id;
										new_userId = id;
										String cache_key = RedisCache.CAHCENAME + "|getUserToken|" + userId;

										String token = IDUtils.md5(userId + new Date());
										cache.putCacheWithExpireTime(cache_key, token, RedisCache.USERCAHCETIME);
										String result_cache = cache.getCache(cache_key, String.class);
										System.out.println("-----------------result_cache:" + result_cache);
										user.setLastLoginTime(new Date());
										userMapper.updateByPrimaryKeySelective(user);
										/* user.setHeadUrl(Constant.FILESERVER_URL + user.getHeadUrl()); */

										// 封装用户数据 注册按照个人用户处理
										userVo.setId(userId);
										userVo.setUserEmail(user.getUserEmail());
										userVo.setUserAccount(user.getUserAccount());
										userVo.setName(user.getUserNick());
										userVo.setUserPhone(user.getUserPhone());
										userVo.setHeadUrl(Constant.FILESERVER_URL_TEST + user.getHeadUrl());
										userVo.setUserType(1);
										userVo.setStatus(1);
										userVo.setToken(token);
										userVo.setState(user.getState());
										// 个人用户余额查询
										userProfitExample = new ZaUserProfitExample();
										criteria5 = userProfitExample.createCriteria();
										criteria5.andUserIdEqualTo(userId);
										List<ZaUserProfit> lists = userProfitMapper.selectByExample(userProfitExample);
										if (lists.size() > 0) {
											ZaUserProfit userProfit = lists.get(0);
											userVo.setCoinSurplus(userProfit.getCoinSurplus());
										}
										validateFlag = true;
									} else {
										msgResult = AppMsgResult.result(531, "用户账号已被冻结，请联系客服！", null);
									}
									// 律师用户
								} else if ("2".equals(userType)) {
									//
									lawyerAuthenticationExample = new ZaLawyerAuthenticationExample();
									criteria4 = lawyerAuthenticationExample.createCriteria();
									criteria4.andUserIdEqualTo(id);
									List<ZaLawyerAuthentication> lawyerAuthentications = lawyerAuthenticationMapper
											.selectByExample(lawyerAuthenticationExample);
									// 是否认证律师
									if (lawyerAuthentications.size() > 0) {
										ZaLawyerAuthentication lawyerAuthentication = lawyerAuthentications.get(0);
										Status = lawyerAuthentication.getLawStatus();
										if (Status == 1) {
											// 单点登录
											String userId = lawyerAuthentication.getId();
											new_userId = userId;
											String cache_key = RedisCache.CAHCENAME + "|getUserToken|" + userId;

											String token = IDUtils.md5(userId + new Date());
											cache.putCacheWithExpireTime(cache_key, token, RedisCache.USERCAHCETIME);
											String result_cache = cache.getCache(cache_key, String.class);
											System.out.println("-----------------result_cache:" + result_cache);
											lawyerAuthentication.setUpdatedTime(new Date());
											lawyerAuthenticationMapper
													.updateByPrimaryKeySelective(lawyerAuthentication);

											// 封装用户数据
											userVo.setId(userId);
											userVo.setUserAccount(userPhone);
											// 案件类型
											List<CategoryNames> comCategoryNames = lawyerAuthenticationMappervo
													.selectLawyerCategoryNames(lawyerAuthentication.getId());
											if (comCategoryNames.size() > 0) {
												StringBuilder sb = new StringBuilder();
												for (CategoryNames CategoryName : comCategoryNames) {
													sb.append(CategoryName.getCategoryName() + ",");
												}
												sb.substring(0, sb.length() - 1);
												userVo.setCategoryNames(sb.toString());
											}
											// 律师事务所
											userVo.setLawOffice(lawyerAuthentication.getLawOffice());
											// 星级
											userVo.setGrade(lawyerAuthentication.getGrade());
											// 律师姓名
											userVo.setName(lawyerAuthentication.getRealName() + "律师");
											userVo.setUserPhone(userPhone);
											userVo.setHeadUrl(
													Constant.FILESERVER_URL_TEST + lawyerAuthentication.getLawLogo());
											userVo.setUserType(2);
											userVo.setStatus(1);
											userVo.setState(lawyerAuthentication.getState());
											userVo.setToken(token);
											validateFlag = true;
										} else {
											if (Status > 1) {
												msgResult = AppMsgResult.result(554, "律师账号已被冻结，请联系客服！", null);
											} else {
												msgResult = AppMsgResult.result(552, "律师信息正在认证中，请耐心等待！", null);
											}
										}
									} else {
										msgResult = AppMsgResult.result(8000, "律师信息未认证！", null);
									}
									// 企业用户
								} else if ("3".equals(userType)) {
									//
									comAuthenticationExample = new ZaComAuthenticationExample();
									criteria3 = comAuthenticationExample.createCriteria();
									criteria3.andUserIdEqualTo(id);
									List<ZaComAuthentication> authentications = comAuthenticationMapper
											.selectByExample(comAuthenticationExample);
									// 是否认证企业
									if (authentications.size() > 0) {
										ZaComAuthentication authentication = authentications.get(0);
										Status = authentication.getComStatus();
										if (Status == 1) {
											// 单点登录
											String userId = authentication.getId();
											new_userId = userId;
											String cache_key = RedisCache.CAHCENAME + "|getUserToken|" + userId;

											String token = IDUtils.md5(userId + new Date());
											cache.putCacheWithExpireTime(cache_key, token, RedisCache.USERCAHCETIME);
											String result_cache = cache.getCache(cache_key, String.class);
											System.out.println("-----------------result_cache:" + result_cache);
											authentication.setUpdatedTime(new Date());
											comAuthenticationMapper.updateByPrimaryKeySelective(authentication);

											// 封装用户数据
											userVo.setId(userId);
											userVo.setUserAccount(userPhone);
											// 公司简称
											userVo.setName(authentication.getComNickname());
											userVo.setUserPhone(userPhone);
											userVo.setHeadUrl(
													Constant.FILESERVER_URL_TEST + authentication.getComLogo());
											// 类别------------
											List<CategoryNames> comCategoryNames = comAuthenticationMappervo
													.selectComCategoryNames(authentication.getId());
											if (comCategoryNames.size() > 0) {
												StringBuilder sb = new StringBuilder();
												for (CategoryNames CategoryName : comCategoryNames) {
													sb.append(CategoryName.getCategoryName() + ",");
												}
												sb.substring(0, sb.length() - 1);
												userVo.setCategoryNames(sb.toString());
											}
											userVo.setUserType(3);
											userVo.setStatus(1);
											userVo.setState(authentication.getState());
											userVo.setToken(token);
											validateFlag = true;

										} else {
											if (Status > 1) {
												msgResult = AppMsgResult.result(555, "企业账号已被冻结，请联系客服！", null);
											} else {
												msgResult = AppMsgResult.result(551, "企业信息正在认证中，请耐心等待！", null);
											}
										}
									} else {
										msgResult = AppMsgResult.result(9000, "企业信息未认证！", null);
									}
								} else {
									msgResult = AppMsgResult.result(549, "请传入登录用户类型！", null);
								}
							} else {
								msgResult = AppMsgResult.result(530, "手机号码或者密码错误！", null);
							}
						} else {
							msgResult = AppMsgResult.result(528, "手机号码未注册！", null);
						}
					} else {
						msgResult = AppMsgResult.result(523, "手机号码格式错误！", null);
					}
				} else {
					msgResult = AppMsgResult.result(549, "请传入登录用户类型！", null);
				}
			} else {
				msgResult = AppMsgResult.result(525, "密码不能为空！", null);
			}
		} else {
			msgResult = AppMsgResult.result(526, "手机号码不能为空！", null);
		}
		if (validateFlag) {
			ZaUserEscrow userEscrow = zaUserEscrowMapper.selectByPrimaryKey("eh_" + new_userId);
			if (null != userEscrow) {
				// 下线
				Hxchat.disconnect(userEscrow.getUsername());
				userVo.setHxusername(userEscrow.getUsername());
				userVo.setHxpassword("123456");
				userVo.setHxusernicke(userEscrow.getUsernick());
			}
			msgResult = AppMsgResult.result(200, "success", userVo);
		}
		return msgResult;
	}

	@Override
	public AppMsgResult updatePassWord(String userId, String oldUserPassword, String userPassword, String token) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null;
		// 判断登录
		msgResult = validateUserLogin(userId, token);
		if (200 != (int) msgResult.getFlag()) {
			return msgResult;
		}
		// 原密码
		if (StringUtils.isNotBlank(oldUserPassword)) {
			if (StringUtils.isNotBlank(userPassword)) {
				// 密码格式判断
				if (ValidateUtil.passWordValidate(userPassword)) {
					ZaUser zaUser = userMapper.selectByPrimaryKey(userId);
					if (zaUser != null) {
						String sqloldPassword = zaUser.getUserPassword();
						String code = zaUser.getCode();
						// 原密码比较
						if (sqloldPassword.equals(IDUtils.md5(oldUserPassword + code))) {
							zaUser.setUserPassword(IDUtils.md5(userPassword + code));
							zaUser.setUpdatedTime(new Date());
							int res = userMapper.updateByPrimaryKeySelective(zaUser);

							if (res == 1) {
								msgResult = AppMsgResult.result(200, "success", null);
							} else {
								msgResult = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
							}
						} else {
							msgResult = AppMsgResult.result(558, "原密码错误！", null);
						}
					} else {
						msgResult = AppMsgResult.result(559, "用户账号不存在！", null);
					}
				} else {
					msgResult = AppMsgResult.result(521, "密码必须为6-19位数字、字母组合！", null);
				}
			} else {
				msgResult = AppMsgResult.result(560, "新密码不能为空！", null);
			}
		} else {
			msgResult = AppMsgResult.result(561, "原密码不能为空！", null);
		}
		return msgResult;
	}

	@Override
	public AppMsgResult updateUser(String userId, String token, String userNick, Integer userGender, String userEmail) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null;
		// 判断登录
		msgResult = validateUserLogin(userId, token);
		if (200 != (int) msgResult.getFlag()) {
			return msgResult;
		}
		ZaUser user = new ZaUser();
		user.setId(userId);
		if (StringUtils.isNotBlank(userNick))
			user.setUserNick(userNick);
		if (userGender > 0 && userGender < 3)
			user.setUserGender(userGender);
		if (StringUtils.isNotBlank(userEmail))
			user.setUserEmail(userEmail);
		int res = userMapper.updateByPrimaryKeySelective(user);

		ZaUser zaUser = userMapper.selectByPrimaryKey(userId);
		// 封装用户数据 注册按照个人用户处理
		ZaUserVo userVo = new ZaUserVo();
		userVo.setUserEmail(zaUser.getUserEmail());
		userVo.setId(zaUser.getId());
		userVo.setUserAccount(zaUser.getUserAccount());
		userVo.setName(zaUser.getUserNick());
		userVo.setUserPhone(zaUser.getUserPhone());
		userVo.setHeadUrl(Constant.FILESERVER_URL + user.getHeadUrl());
		userVo.setUserType(1);
		userVo.setStatus(1);
		userVo.setToken(token);
		msgResult = AppMsgResult.result(200, "success", userVo);

		if (res == 1) {
			msgResult = AppMsgResult.result(200, "success", null);
		} else {
			msgResult = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
		}
		return msgResult;
	}

	@Override
	public AppMsgResult switchUser(String userId, String token, String userPhone, String currentType, String type) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null;
		// 判断登录
		msgResult = validateUserLogin(userId, token);
		if (200 != (int) msgResult.getFlag()) {
			return msgResult;
		}
		boolean validateFlag = false;
		boolean flag = false;
		String new_userId = "";
		ZaUserVo userVo = new ZaUserVo();
		// 个人用户
		if ("1".equals(type)) {
			// 查询个人用户下是否存在律师
			if ("1".equals(currentType)) {
				msgResult = AppMsgResult.result(200, "success", null);
				// 当前身份律师
			} else if ("2".equals(currentType)) {
				ZaLawyerAuthentication lawyerAuthentication = lawyerAuthenticationMapper.selectByPrimaryKey(userId);
				// 查询律师下的个人用户信息
				if (lawyerAuthentication != null) {
					flag = true;
					new_userId = lawyerAuthentication.getUserId();
					lawyerAuthentication.setState(1);
					updateLawyerState(lawyerAuthentication);
				} else {
					msgResult = AppMsgResult.result(531, "用户账号已被冻结，请联系客服！", null);
				}
				// 当前身份企业
			} else if ("3".equals(currentType)) {
				ZaComAuthentication comAuthentication = comAuthenticationMapper.selectByPrimaryKey(userId);
				// 查询企业下的个人用户信息
				if (comAuthentication != null) {
					flag = true;
					new_userId = comAuthentication.getUserId();
				} else {
					msgResult = AppMsgResult.result(531, "用户账号已被冻结，请联系客服！", null);
				}
			} else {
				msgResult = AppMsgResult.result(529, "当前登录用户类型参数错误！", null);
			}
			// 是否存在用户
			if (flag) {
				ZaUser user = userMapper.selectByPrimaryKey(new_userId);
				int status = user.getStatus();
				if (status == 1) {
					// 单点登录
					String new_cache_key = RedisCache.CAHCENAME + "|getUserToken|" + new_userId;

					String new_token = IDUtils.md5(new_userId + new Date());
					cache.putCacheWithExpireTime(new_cache_key, new_token, RedisCache.USERCAHCETIME);
					String new_result_cache = cache.getCache(new_cache_key, String.class);
					System.out.println("-----------------new_result_cache:" + new_result_cache);
					user.setLastLoginTime(new Date());
					userMapper.updateByPrimaryKeySelective(user);
					/* user.setHeadUrl(Constant.FILESERVER_URL + user.getHeadUrl()); */

					// 封装用户数据 注册按照个人用户处理
					userVo.setId(new_userId);
					userVo.setUserEmail(user.getUserEmail());
					userVo.setUserAccount(user.getUserAccount());
					userVo.setName(user.getUserNick());
					userVo.setUserPhone(user.getUserPhone());
					userVo.setHeadUrl(Constant.FILESERVER_URL + user.getHeadUrl());
					userVo.setUserType(1);
					userVo.setStatus(1);
					userVo.setToken(new_token);
					// 个人用户余额查询
					userProfitExample = new ZaUserProfitExample();
					criteria5 = userProfitExample.createCriteria();
					criteria5.andUserIdEqualTo(userId);
					List<ZaUserProfit> lists = userProfitMapper.selectByExample(userProfitExample);
					if (lists.size() > 0) {
						ZaUserProfit userProfit = lists.get(0);
						userVo.setCoinSurplus(userProfit.getCoinSurplus());
					}
					validateFlag = true;
				} else {
					msgResult = AppMsgResult.result(531, "用户账号已被冻结，请联系客服！", null);
				}
			}
			// 开始写律师切换企业身份
		} else if ("2".equals(type)) {
			// 当前身份为个人
			if ("1".equals(currentType)) {
				flag = true;
				new_userId = userId;
				// 当前身份为律师
			} else if ("2".equals(currentType)) {
				msgResult = AppMsgResult.result(200, "success", null);
				// 当前身份为企业
			} else if ("3".equals(currentType)) {
				ZaComAuthentication comAuthentication = comAuthenticationMapper.selectByPrimaryKey(userId);
				if (comAuthentication != null) {
					flag = true;
					new_userId = comAuthentication.getUserId();
				} else {
					msgResult = AppMsgResult.result(531, "用户账号已被冻结，请联系客服！", null);
				}
			} else {
				msgResult = AppMsgResult.result(529, "当前登录用户类型参数错误！", null);
			}
			//
			if (flag) {
				lawyerAuthenticationExample = new ZaLawyerAuthenticationExample();
				criteria4 = lawyerAuthenticationExample.createCriteria();
				criteria4.andUserIdEqualTo(new_userId);
				List<ZaLawyerAuthentication> lists = lawyerAuthenticationMapper
						.selectByExample(lawyerAuthenticationExample);
				if (lists.size() > 0) {
					ZaLawyerAuthentication lawyerAuthentication = lists.get(0);
					int status = lawyerAuthentication.getLawStatus();
					if (status == 1) {
						String lawId = lawyerAuthentication.getId();
						// 单点登录
						String new_cache_key = RedisCache.CAHCENAME + "|getUserToken|" + lawId;

						String new_token = IDUtils.md5(lawId + new Date());
						cache.putCacheWithExpireTime(new_cache_key, new_token, RedisCache.USERCAHCETIME);
						String new_result_cache = cache.getCache(new_cache_key, String.class);
						System.out.println("-----------------new_result_cache:" + new_result_cache);
						lawyerAuthentication.setUpdatedTime(new Date());
						// 在线状态
						lawyerAuthentication.setState(0);
						lawyerAuthenticationMapper.updateByPrimaryKeySelective(lawyerAuthentication);

						// 封装用户数据
						userVo.setId(lawyerAuthentication.getId());
						userVo.setUserAccount(userPhone);
						// 案件类型
						List<CategoryNames> comCategoryNames = lawyerAuthenticationMappervo
								.selectLawyerCategoryNames(lawyerAuthentication.getId());
						if (comCategoryNames.size() > 0) {
							StringBuilder sb = new StringBuilder();
							for (CategoryNames CategoryName : comCategoryNames) {
								sb.append(CategoryName.getCategoryName() + ",");
							}
							sb.substring(0, sb.length() - 1);
							userVo.setCategoryNames(sb.toString());
						}
						// 律师事务所
						userVo.setLawOffice(lawyerAuthentication.getLawOffice());
						// 星级
						userVo.setGrade(lawyerAuthentication.getGrade());
						// 律师姓名
						userVo.setName(lawyerAuthentication.getRealName() + "律师");
						userVo.setUserPhone(userPhone);
						userVo.setHeadUrl(Constant.FILESERVER_URL + lawyerAuthentication.getLawLogo());
						userVo.setUserType(2);
						userVo.setStatus(1);
						userVo.setToken(new_token);

						validateFlag = true;
					} else {
						if (status > 1) {
							msgResult = AppMsgResult.result(554, "律师账号已被冻结，请联系客服！", null);
						} else {
							msgResult = AppMsgResult.result(552, "律师信息正在认证中，请耐心等待！", null);
						}
					}
				} else {
					msgResult = AppMsgResult.result(8000, "律师信息未认证！", null);
				}
			}
			// 切换企业身份
		} else if ("3".equals(type)) {
			// 当前身份为个人
			if ("1".equals(currentType)) {
				flag = true;
				new_userId = userId;
				// 当前身份为律师
			} else if ("2".equals(currentType)) {
				ZaLawyerAuthentication lawyerAuthentication = lawyerAuthenticationMapper.selectByPrimaryKey(userId);
				if (lawyerAuthentication != null) {
					flag = true;
					new_userId = lawyerAuthentication.getUserId();
					lawyerAuthentication.setState(1);
					updateLawyerState(lawyerAuthentication);
				} else {
					msgResult = AppMsgResult.result(531, "用户账号已被冻结，请联系客服！", null);
				}
				// 当前身份为企业
			} else if ("3".equals(currentType)) {
				msgResult = AppMsgResult.result(200, "success", null);
			} else {
				msgResult = AppMsgResult.result(529, "当前登录用户类型参数错误！", null);
			}
			//
			if (flag) {
				comAuthenticationExample = new ZaComAuthenticationExample();
				criteria3 = comAuthenticationExample.createCriteria();
				criteria3.andUserIdEqualTo(new_userId);
				List<ZaComAuthentication> authentications = comAuthenticationMapper
						.selectByExample(comAuthenticationExample);
				// 是否认证律师
				if (authentications.size() > 0) {
					ZaComAuthentication authentication = authentications.get(0);
					int status = authentication.getComStatus();
					if (status == 1) {
						String comId = authentication.getId();
						// 单点登录
						String new_cache_key = RedisCache.CAHCENAME + "|getUserToken|" + comId;

						String new_token = IDUtils.md5(comId + new Date());
						cache.putCacheWithExpireTime(new_cache_key, new_token, RedisCache.USERCAHCETIME);
						String new_result_cache = cache.getCache(new_cache_key, String.class);
						System.out.println("-----------------new_result_cache:" + new_result_cache);
						authentication.setUpdatedTime(new Date());
						comAuthenticationMapper.updateByPrimaryKeySelective(authentication);

						// 封装用户数据
						userVo.setId(authentication.getId());
						userVo.setUserAccount(userPhone);
						// 公司简称
						userVo.setName(authentication.getComNickname());
						userVo.setUserPhone(userPhone);
						userVo.setHeadUrl(Constant.FILESERVER_URL + authentication.getComLogo());
						// 类别------------
						List<CategoryNames> comCategoryNames = comAuthenticationMappervo
								.selectComCategoryNames(authentication.getId());
						if (comCategoryNames.size() > 0) {
							StringBuilder sb = new StringBuilder();
							for (CategoryNames CategoryName : comCategoryNames) {
								sb.append(CategoryName.getCategoryName() + ",");
							}
							sb.substring(0, sb.length() - 1);
							userVo.setCategoryNames(sb.toString());
						}
						userVo.setUserType(3);
						userVo.setStatus(1);
						userVo.setToken(new_token);
						validateFlag = true;
					} else {
						if (status > 1) {
							msgResult = AppMsgResult.result(555, "企业账号已被冻结，请联系客服！", null);
						} else {
							msgResult = AppMsgResult.result(551, "企业信息正在认证中，请耐心等待！", null);
						}
					}
				} else {
					msgResult = AppMsgResult.result(9000, "企业信息未认证！", null);
				}
			}

		} else {
			msgResult = AppMsgResult.result(529, "用户类型参数错误！", null);
		}
		if (validateFlag) {
			ZaUserEscrow userEscrow = zaUserEscrowMapper.selectByPrimaryKey("eh_" + new_userId);
			if (null != userEscrow) {
				// 下线
				Hxchat.disconnect(userEscrow.getUsername());
				userVo.setHxusername(userEscrow.getUsername());
				userVo.setHxpassword("123456");
				userVo.setHxusernicke(userEscrow.getUsernick());

			}
			msgResult = AppMsgResult.result(200, "success", userVo);
		}
		return msgResult;
	}

	@Override
	public AppMsgResult updateLawyerState(ZaLawyerAuthentication lawyerAuthentication) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null;
		int res = lawyerAuthenticationMapper.updateByPrimaryKeySelective(lawyerAuthentication);
		if (res == 1) {
			msgResult = AppMsgResult.result(200, "success", null);
		} else {
			msgResult = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
		}
		return msgResult;
	}

	@Override
	public AppMsgResult showMoney(ZaUserPurchaseRecord userPurchaseRecord, String token, String userPassword) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null;
		// 判断空
		if (null != userPurchaseRecord) {
			// 用户id
			String userId = userPurchaseRecord.getUserId();
			// 提现账号
			String userAccount = userPurchaseRecord.getUserAccount();
			// 提现真实姓名
			String payeeRealName = userPurchaseRecord.getPayeeRealName();
			// 提现金额
			BigDecimal pay_cash = userPurchaseRecord.getPayCash();
			/*
			 * //提现类型 Integer type = userLoveShow.getType();
			 */
			// 判断登录
			msgResult = validateUserLogin(userId, token);
			if (200 != (int) msgResult.getFlag()) {
				return msgResult;
			}
			if (StringUtils.isNotBlank(payeeRealName)) {
				if (StringUtils.isNotBlank(userAccount)) {
					if (StringUtils.isNotBlank(userPassword)) {
						// 至少提现10元
						int res = pay_cash.compareTo(new BigDecimal(10));
						if (res != -1) {
							// 查询律师
							ZaLawyerAuthentication lawyerAuthentication = lawyerAuthenticationMapper
									.selectByPrimaryKey(userId);
							if (null != lawyerAuthentication) {
								// 律师账号情况
								int lawStatus = lawyerAuthentication.getLawStatus();
								if (lawStatus == 1) {
									// 用户id 验证登录密码
									String new_userId = lawyerAuthentication.getUserId();
									// 查询管理的用户
									ZaUser user = null;
									user = userMapper.selectByPrimaryKey(new_userId);
									String code = user.getCode();
									// 判断用户是否存在
									if (null != user) {
										if (user.getUserPassword().equals(IDUtils.md5(userPassword + code))) {
											// 查询用户可提现金额
											userProfitExample = new ZaUserProfitExample();
											criteria5 = userProfitExample.createCriteria();
											criteria5.andUserIdEqualTo(userId);
											List<ZaUserProfit> userProfits = userProfitMapper
													.selectByExample(userProfitExample);
											if (userProfits.size() > 0) {
												ZaUserProfit userProfit = userProfits.get(0);
												// 剩余的金额
												BigDecimal db = userProfit.getCoinSurplus();
												if (db.compareTo(pay_cash) != -1) {
													// 添加提现
													String id = IDUtils.genId();
													userPurchaseRecord.setId(id);
													userPurchaseRecord.setCreatedTime(new Date());
													userPurchaseRecord.setUpdatedTime(new Date());
													// 提现消费
													userPurchaseRecord.setConsumType(3);
													// 支出
													userPurchaseRecord.setInOut(1);

													BigDecimal fee = pay_cash.multiply(new BigDecimal(5))
															.divide(new BigDecimal(1000));
													fee = fee.setScale(2, BigDecimal.ROUND_HALF_UP);
													BigDecimal new_fee = new BigDecimal(0);
													// 添加消费记录
													if (new BigDecimal(1).compareTo(fee) == 1) {
														new_fee.add(new BigDecimal(1));
													} else if (fee.compareTo(new BigDecimal(25)) == -1) {
														new_fee.add(new BigDecimal(25));
													} else {
														new_fee.add(fee);
													}
													// 实际到账 ---去掉手续费
													BigDecimal new_pay_cash = pay_cash.subtract(new_fee);
													// 实例化客户端 --正式
													AlipayClient alipayClient = new DefaultAlipayClient(
															AlipayConfig.GATEWAYURL, AlipayConfig.APP_ID,
															AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET,
															AlipayConfig.ALIPAY_PUBLIC_KEY, "RSA2");
													// 实例化客户端 --测试
													/*
													 * AlipayClient alipayClient = new
													 * DefaultAlipayClient(AlipayConfig.CS_GATEWAYURL,
													 * AlipayConfig.CS_APP_ID, AlipayConfig.CS_APP_PRIVATE_KEY, "json",
													 * AlipayConfig.CHARSET, AlipayConfig.CS_ALIPAY_PUBLIC_KEY, "RSA2");
													 */ AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
													request.setBizContent("{" + "\"out_biz_no\":\"" + id + "\","
															+ "\"payee_type\":\"ALIPAY_LOGONID\","
															+ "\"payee_account\":\"" + userAccount + "\","
															+ "\"amount\":\"0.1\"," +
															/* "\"amount\":\""+new_pay_cash+"\"," + */
															/* "\"payer_show_name\":\"上海交通卡退款\"," + */
															"\"payer_show_name\":\"我的个人律师\"," +
															"\"payee_real_name\":\"" + payeeRealName + "\","
															+ "\"remark\":\"我的个人律师账户余额提取\"" + "  }");
													try {
														AlipayFundTransToaccountTransferResponse response = alipayClient
																.execute(request);
														if (response.isSuccess()) {
															System.out.println("调用成功");
															userProfit.setLastUseTime(new Date());
															userProfit.setCoinInTotal(
																	userProfit.getCoinConsumTotal().add(pay_cash));
															userProfit.setCoinSurplus(
																	userProfit.getCoinSurplus().subtract(pay_cash));
															// 减少用户的总剩余值
															userProfitMapper.updateByPrimaryKeySelective(userProfit);

															ZaUserPurchaseRecord purchaseRecord = new ZaUserPurchaseRecord();
															purchaseRecord.setId(IDUtils.genId());
															purchaseRecord.setUserId(userId);
															/*
															 * purchaseRecord.setDealCode(out_trade_no);
															 */
															purchaseRecord.setPayCash(new_pay_cash);
															// 支付类型 1 支付宝
															purchaseRecord.setPayType(3);
															// 状态
															purchaseRecord.setPayStatus(1);
															purchaseRecord.setConsumType(3);
															purchaseRecord.setInOut(1);
															purchaseRecord.setEventDesc("支付宝提现！");
															purchaseRecord.setConsumTime(new Date());
															purchaseRecord.setCreatedTime(new Date());
															purchaseRecord.setUpdatedTime(new Date());
															userPurchaseRecordMapper.insertSelective(purchaseRecord);
															// 手续费
															purchaseRecord.setId(IDUtils.genId());
															purchaseRecord.setPayCash(new_fee);
															purchaseRecord.setEventDesc("支付宝提现手续费！");
															userPurchaseRecordMapper.insertSelective(purchaseRecord);

															msgResult = AppMsgResult.result(200, "success", null);
														} else {
															System.out.println("调用失败");
															msgResult = AppMsgResult.result(570,
																	"服务器繁忙或账号和姓名不匹配，请仔细检查或者稍后重试！", null);
														}
													} catch (AlipayApiException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
														msgResult = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
													}
												} else {
													msgResult = AppMsgResult.result(569, "亲，没有这么多金额可提取！", null);
												}
											} else {
												msgResult = AppMsgResult.result(568, "提现出现异常，请稍后重试！", null);
											}
										} else {
											msgResult = AppMsgResult.result(565, "登录密码错误！", null);
										}
									}
								} else {
									msgResult = AppMsgResult.result(554, "律师账号已被冻结，请联系客服！", null);
								}
							} else {
								/// ?????????????
								msgResult = AppMsgResult.result(8000, "律师账号未认证！", null);
							}
							// 金额输入错误
						} else {
							msgResult = AppMsgResult.result(557, "提现至少在10(包含10)以上！", null);
						}
					} else {
						msgResult = AppMsgResult.result(525, "密码不能为空！", null);
					}
				} else {
					msgResult = AppMsgResult.result(567, "支付宝账号不能为空！", null);
				}
			} else {
				msgResult = AppMsgResult.result(566, "支付宝真实收款人姓名不能为空！", null);
			}
		}
		return msgResult;
	}

	public static void main(String[] args) {
		
		
/*		BigDecimal bd = new BigDecimal(300.5);
		int res = bd.compareTo(new BigDecimal(56));
		System.out.println("bd:" + bd + ",res:" + res);

		BigDecimal fee = bd.multiply(new BigDecimal(5)).divide(new BigDecimal(1000));
		fee = fee.setScale(2, BigDecimal.ROUND_HALF_UP);
		System.out.println(fee);*/
		
		// 实例化客户端 --正式
		AlipayClient alipayClient = new DefaultAlipayClient(
				AlipayConfig.GATEWAYURL, AlipayConfig.APP_ID,
				AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET,
				AlipayConfig.ALIPAY_PUBLIC_KEY, "RSA2");
		// 实例化客户端 --测试
		
/*		 AlipayClient alipayClient = new
		  DefaultAlipayClient(AlipayConfig.CS_GATEWAYURL,
		  AlipayConfig.CS_APP_ID, AlipayConfig.CS_APP_PRIVATE_KEY, "json",
		  AlipayConfig.CHARSET, AlipayConfig.CS_ALIPAY_PUBLIC_KEY, "RSA2");*/
		  AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
		  request.setBizContent("{" + "\"out_biz_no\":\"" + System.currentTimeMillis()+ "\","
				+ "\"payee_type\":\"ALIPAY_LOGONID\","
				+ "\"payee_account\":\"18398369370\","
				+ "\"amount\":\"0.1\"," +
				/* "\"amount\":\""+new_pay_cash+"\"," + */
				"\"payer_show_name\":\"我的个人律师\"," +
				"\" payee_real_name\":\"蒙林\","
				+ "\"remark\":\"我的个人律师账户余额提取\"" + "  }");
		try {
			AlipayFundTransToaccountTransferResponse response = alipayClient
					.execute(request);
			System.out.println(response);
			if (response.isSuccess()) { 
				System.out.println("调用成功");
			
			} else {
				System.out.println("调用失败");
			}
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("调用失败sss");
		}
		/*
		 * if (fee == 0) { fee = 1; } else if (fee > 25) { fee = 25; }
		 */
	}

	@Override
	public AppMsgResult getNewUserinfo(String userId, String token, String userType) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null;
		// 判断登录
		msgResult = validateUserLogin(userId, token);
		if (200 != (int) msgResult.getFlag()) {
			return msgResult;
		}
		if (StringUtils.isBlank(userType)) {
			return AppMsgResult.result(549, "请传入登录用户类型！", null);
		}
		// 判断空
		ZaUserVo userVo = new ZaUserVo();
		boolean validateFlag = false;
		// 个人用户
		if ("1".equals(userType)) {
			ZaUser user = userMapper.selectByPrimaryKey(userId);
			if (null == user) {
				return AppMsgResult.result(589, "账户信息查询失败！", null);
			}
			int status = user.getStatus();
			if (status == 1) {
				// 单点登录
				String cache_key = RedisCache.CAHCENAME + "|getUserToken|" + userId;

				String new_token = IDUtils.md5(userId + new Date());
				cache.putCacheWithExpireTime(cache_key, new_token, RedisCache.USERCAHCETIME);
				String result_cache = cache.getCache(cache_key, String.class);
				System.out.println("-----------------result_cache:" + result_cache);
				user.setLastLoginTime(new Date());
				userMapper.updateByPrimaryKeySelective(user);
				/* user.setHeadUrl(Constant.FILESERVER_URL + user.getHeadUrl()); */

				// 封装用户数据 注册按照个人用户处理
				userVo.setId(userId);
				userVo.setUserEmail(user.getUserEmail());
				userVo.setUserAccount(user.getUserAccount());
				userVo.setName(user.getUserNick());
				userVo.setUserPhone(user.getUserPhone());
				userVo.setHeadUrl(Constant.FILESERVER_URL_TEST + user.getHeadUrl());
				userVo.setUserType(1);
				userVo.setStatus(1);
				userVo.setToken(new_token);
				userVo.setState(user.getState());
				// 个人用户余额查询
				userProfitExample = new ZaUserProfitExample();
				criteria5 = userProfitExample.createCriteria();
				criteria5.andUserIdEqualTo(userId);
				List<ZaUserProfit> lists = userProfitMapper.selectByExample(userProfitExample);
				if (lists.size() > 0) {
					ZaUserProfit userProfit = lists.get(0);
					userVo.setCoinSurplus(userProfit.getCoinSurplus());
				}
				validateFlag = true;
			} else {
				msgResult = AppMsgResult.result(531, "用户账号已被冻结，请联系客服！", null);
			}
			// 律师用户
		} else if ("2".equals(userType)) {
			// 查询律师用户信息
			ZaLawyerAuthentication lawyerAuthentication = lawyerAuthenticationMapper.selectByPrimaryKey(userId);
			if (null == lawyerAuthentication) {
				return AppMsgResult.result(589, "账户信息查询失败！", null);
			}
			int status = lawyerAuthentication.getLawStatus();
			if (status == 1) {
				// 单点登录
				String cache_key = RedisCache.CAHCENAME + "|getUserToken|" + userId;

				String new_token = IDUtils.md5(userId + new Date());
				cache.putCacheWithExpireTime(cache_key, new_token, RedisCache.USERCAHCETIME);
				String result_cache = cache.getCache(cache_key, String.class);
				System.out.println("-----------------result_cache:" + result_cache);
				lawyerAuthentication.setUpdatedTime(new Date());
				lawyerAuthenticationMapper.updateByPrimaryKeySelective(lawyerAuthentication);

				ZaUser user = userMapper.selectByPrimaryKey(lawyerAuthentication.getUserId());
				if (null != user) {
					userVo.setUserAccount(user.getUserAccount());
					userVo.setUserPhone(user.getUserPhone());
				}
				// 封装用户数据
				userVo.setId(userId);
				// 案件类型
				List<CategoryNames> comCategoryNames = lawyerAuthenticationMappervo
						.selectLawyerCategoryNames(lawyerAuthentication.getId());
				if (comCategoryNames.size() > 0) {
					StringBuilder sb = new StringBuilder();
					for (CategoryNames CategoryName : comCategoryNames) {
						sb.append(CategoryName + ",");
					}
					sb.substring(0, sb.length() - 1);
					userVo.setCategoryNames(sb.toString());
				}
				// 律师事务所
				userVo.setLawOffice(lawyerAuthentication.getLawOffice());
				// 星级
				userVo.setGrade(lawyerAuthentication.getGrade());
				// 律师姓名
				userVo.setName(lawyerAuthentication.getRealName() + "律师");
				userVo.setHeadUrl(Constant.FILESERVER_URL_TEST + lawyerAuthentication.getLawLogo());
				userVo.setUserType(2);
				userVo.setStatus(1);
				userVo.setState(lawyerAuthentication.getState());
				userVo.setToken(new_token);
				validateFlag = true;
			} else {
				if (status > 1) {
					msgResult = AppMsgResult.result(554, "律师账号已被冻结，请联系客服！", null);
				} else {
					msgResult = AppMsgResult.result(552, "律师信息正在认证中，请耐心等待！", null);
				}
			}
			// 企业用户
		} else if ("3".equals(userType)) {
			//
			ZaComAuthentication authentication = comAuthenticationMapper.selectByPrimaryKey(userId);
			// 是否认证企业
			if (null == authentication) {
				return AppMsgResult.result(589, "账户信息查询失败！", null);
			}
			int status = authentication.getComStatus();
			if (status == 1) {
				// 单点登录
				String cache_key = RedisCache.CAHCENAME + "|getUserToken|" + userId;

				String new_token = IDUtils.md5(userId + new Date());
				cache.putCacheWithExpireTime(cache_key, new_token, RedisCache.USERCAHCETIME);
				String result_cache = cache.getCache(cache_key, String.class);
				System.out.println("-----------------result_cache:" + result_cache);
				authentication.setUpdatedTime(new Date());
				comAuthenticationMapper.updateByPrimaryKeySelective(authentication);

				ZaUser user = userMapper.selectByPrimaryKey(authentication.getUserId());
				if (null != user) {
					userVo.setUserAccount(user.getUserAccount());
					userVo.setUserPhone(user.getUserPhone());
				}
				// 封装用户数据
				userVo.setId(userId);
				// 公司简称
				userVo.setName(authentication.getComNickname());
				userVo.setHeadUrl(Constant.FILESERVER_URL_TEST + authentication.getComLogo());
				// 类别------------
				List<CategoryNames> comCategoryNames = comAuthenticationMappervo
						.selectComCategoryNames(authentication.getId());
				if (comCategoryNames.size() > 0) {
					StringBuilder sb = new StringBuilder();
					for (CategoryNames CategoryName : comCategoryNames) {
						sb.append(CategoryName + ",");
					}
					sb.substring(0, sb.length() - 1);
					userVo.setCategoryNames(sb.toString());
				}
				userVo.setUserType(3);
				userVo.setStatus(1);
				userVo.setState(authentication.getState());
				userVo.setToken(new_token);
				validateFlag = true;
			} else {
				if (status > 1) {
					msgResult = AppMsgResult.result(555, "企业账号已被冻结，请联系客服！", null);
				} else {
					msgResult = AppMsgResult.result(551, "企业信息正在认证中，请耐心等待！", null);
				}
			}
		} else {
			msgResult = AppMsgResult.result(549, "请传入登录用户类型！", null);
		}
		if (validateFlag) {
			ZaUserEscrow userEscrow = zaUserEscrowMapper.selectByPrimaryKey("eh_" +userId);
			if (null != userEscrow) {
				// 下线
				Hxchat.disconnect(userEscrow.getUsername());
				userVo.setHxusername(userEscrow.getUsername());
				userVo.setHxpassword("123456");
				userVo.setHxusernicke(userEscrow.getUsernick());
			}
			msgResult = AppMsgResult.result(200, "success", userVo);
		}
		return msgResult;
	}
}

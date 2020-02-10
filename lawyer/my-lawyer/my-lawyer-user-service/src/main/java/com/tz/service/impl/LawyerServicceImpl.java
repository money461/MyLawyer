package com.tz.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tz.cache.RedisCache;
import com.tz.id.IDUtils;
import com.tz.mapper.ZaLawyerAchievementsMapper;
import com.tz.mapper.ZaLawyerAuthenticationMapper;
import com.tz.mapper.ZaLawyerCaseMapper;
import com.tz.mapper.vo.ZaLawyerAuthenticationMapperVo;
import com.tz.pojo.ZaLawyerAchievements;
import com.tz.pojo.ZaLawyerAchievementsExample;
import com.tz.pojo.ZaLawyerAuthentication;
import com.tz.pojo.ZaLawyerAuthenticationExample;
import com.tz.pojo.ZaLawyerCase;
import com.tz.pojo.ZaLawyerCaseExample;

import com.tz.pojo.vo.LawyerAchievementsVo;
import com.tz.res.AppMsgResult;
import com.tz.res.Constant;
import com.tz.service.LawyerService;
import com.tz.service.UserService;
import com.tz.util.AddressToGpsUtil;

@Service
public class LawyerServicceImpl implements LawyerService {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	// 律师认证类
	@Autowired
	ZaLawyerAuthenticationMapper lawyerAuthenticationMapper;
	private ZaLawyerAuthenticationExample example;
	private ZaLawyerAuthenticationExample.Criteria criteria;

	// 律师分类中间类
	@Autowired
	ZaLawyerCaseMapper lawyerCaseMapper;
	private ZaLawyerCaseExample lawyerCaseExample;
	private ZaLawyerCaseExample.Criteria criteria2;

	// 律师案例类
	@Autowired
	ZaLawyerAchievementsMapper achievementsMapper;
	private ZaLawyerAchievementsExample achievementsExample;
	private ZaLawyerAchievementsExample.Criteria criteria3;

	// 律师认证扩展接口类
	@Autowired
	ZaLawyerAuthenticationMapperVo lawyerAuthenticationMappervo;

	// redis缓存
	@Autowired
	private RedisCache cache;

	// 用户接口类
	@Autowired
	UserService userService;
	
	@Override
	public AppMsgResult lawyerAuth(ZaLawyerAuthentication lawyerAuth, String caseIds, String token) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null;
		// 关联用户账号的id
		if(null == lawyerAuth) {
			return AppMsgResult.result(583, "参数错误！", null);
		}
		String userId = lawyerAuth.getUserId();
		// 1、登录判断
		msgResult = userService.validateUserLogin(userId, token);
		if (200 != (int) msgResult.getFlag()) {
			return msgResult;
		}
		// 律师证页内照
		String qualificationUrl = lawyerAuth.getQualificationUrl();
		if (StringUtils.isNotBlank(qualificationUrl)) {
			// 手持身份证
			String licenceUrl = lawyerAuth.getLicenceUrl();
			if (StringUtils.isNotBlank(licenceUrl)) {
				// 律师真实姓名
				String realName = lawyerAuth.getRealName();
				if (StringUtils.isNotBlank(realName)) {
					// 律师工作年限
					Integer ageLimit = lawyerAuth.getAgeLimit();
					if (ageLimit > 0) {
						// 律师地址
						String lawAddress = lawyerAuth.getLawAddress();
						if (StringUtils.isNotBlank(lawAddress)) {
							// 律师事务所
							String lawOffice = lawyerAuth.getLawOffice();
							if (StringUtils.isNotBlank(lawOffice)) {
								// 添加数据
								String id = IDUtils.genId();
								lawyerAuth.setId(id);
								lawyerAuth.setCreatedTime(new Date());
								lawyerAuth.setUpdatedTime(new Date());
								//封装经纬度
								Map<String,Double> map = new HashMap<String,Double>();
								map = AddressToGpsUtil.getGeocoderLatitude(lawAddress);
								lawyerAuth.setComLon(map.get("lon"));
								lawyerAuth.setComLat(map.get("lat"));
								
								
								// 默认审核中
								lawyerAuth.setLawStatus(0);
								int res = lawyerAuthenticationMapper.insertSelective(lawyerAuth);
								// 律师分类中间表 多条数据
								ZaLawyerCase lawyerCase = new ZaLawyerCase();
								// 分割id
								String[] caseId = caseIds.split(",");
								lawyerCase.setLawId(id);
								lawyerCase.setCreatedTime(new Date());
								lawyerCase.setUpdatedTime(new Date());
								for (String str : caseId) {
									lawyerCase.setId(IDUtils.genId());
									lawyerCase.setCaseId(Integer.parseInt(str));
									// 添加律师分类中间表
									lawyerCaseMapper.insertSelective(lawyerCase);
								}
								if (res == 1) {
									msgResult = AppMsgResult.result(200, "success", lawyerAuth);
								} else {
									msgResult = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
								}
							} else {
								msgResult = AppMsgResult.result(532, "请填写律师事务所！", null);
							}
						} else {
							msgResult = AppMsgResult.result(533, "请填写律师工作地址！", null);
						}
					} else {
						msgResult = AppMsgResult.result(534, "请填写工作年限！", null);
					}
				} else {
					msgResult = AppMsgResult.result(535, "请填写律师真实姓名！", null);
				}
			} else {
				msgResult = AppMsgResult.result(536, "请上传手持身份证照！", null);
			}
		} else {
			msgResult = AppMsgResult.result(537, "请上传律师业内照！", null);
		}
				
		return msgResult;
	}

	@Override
	public AppMsgResult addAchievements(ZaLawyerAchievements lawyerAchievements, String userId, String token) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null;
		// 1、登录判断
		msgResult = userService.validateUserLogin(userId, token);
		if (200 != (int) msgResult.getFlag()) {
			return msgResult;
		}
		Integer caseId = lawyerAchievements.getCaseId();
		if (caseId != null) {
			// 案件起始时间
			Date caseBegin = lawyerAchievements.getCaseBegin();
			if (caseBegin != null) {
				// 案件结束时间
				Date caseEnd = lawyerAchievements.getCaseEnd();
				if (caseEnd != null) {
					// 案件描述
					String caseDesc = lawyerAchievements.getCaseDesc();
					if (StringUtils.isNotBlank(caseDesc)) {
						// 业务处理
						achievementsExample = new ZaLawyerAchievementsExample();
						criteria3 = achievementsExample.createCriteria();
						criteria3.andLawIdEqualTo(userId);
						int count = achievementsMapper.countByExample(achievementsExample);
						if (count > 3) {
							msgResult = AppMsgResult.result(548, "律师案例最多只能添加三个！", null);
						} else {
							lawyerAchievements.setId(IDUtils.genId());
							lawyerAchievements.setLawId(userId);
							lawyerAchievements.setCreatedTime(new Date());
							lawyerAchievements.setUpdatedTime(new Date());
							int res = achievementsMapper.insertSelective(lawyerAchievements);
							if (res == 1) {
								msgResult = AppMsgResult.result(200, "success", null);
							} else {
								msgResult = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
							}
						}
					} else {
						msgResult = AppMsgResult.result(539, "请填写案例描述信息！", null);
					}
				} else {
					msgResult = AppMsgResult.result(557, "请选择案例结束时间！", null);
				}

			} else {
				msgResult = AppMsgResult.result(540, "请选择案例起始时间", null);
			}
		} else {
			msgResult = AppMsgResult.result(556, "请选择案件分类！", null);
		}
					

		/*
		 * // 律师id String lawId = lawyerAchievements.getLawId(); if
		 * (StringUtils.isNotBlank(lawId)) {
		 * 
		 * } else { msgResult = AppMsgResult.result(538, "登录用户的id不能为空！", null); }
		 */
		return msgResult;
	}

	@Override
	public AppMsgResult deleteAchievements(String id, String userId, String token) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null;
		// 1、登录判断
		msgResult = userService.validateUserLogin(userId, token);
		if (200 != (int) msgResult.getFlag()) {
			return msgResult;
		}
		if (StringUtils.isNotBlank(id)) {
			int res = achievementsMapper.deleteByPrimaryKey(id);
			if (res == 1) {
				msgResult = AppMsgResult.result(200, "success", null);
			} else {
				msgResult = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
			}

		} else {
			msgResult = AppMsgResult.result(541, "id主键参数不能为空！", null);
		}
		

		return msgResult;
	}

	@Override
	public AppMsgResult updateAchievements(ZaLawyerAchievements lawyerAchievements, String userId, String token) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null;
		// 1、登录判断
		msgResult = userService.validateUserLogin(userId, token);
		if (200 != (int) msgResult.getFlag()) {
			return msgResult;
		}
		// 主键
		String id = lawyerAchievements.getId();
		if (StringUtils.isNotBlank(id)) {
			// 案件起始时间
			Date caseBegin = lawyerAchievements.getCaseBegin();
			if (caseBegin != null) {
				Date caseEnd = lawyerAchievements.getCaseEnd();
				if (caseEnd != null) {
					// 案件描述
					String caseDesc = lawyerAchievements.getCaseDesc();
					if (StringUtils.isNotBlank(caseDesc)) {
						// 业务处理
						lawyerAchievements.setUpdatedTime(new Date());
						int res = achievementsMapper.updateByPrimaryKeySelective(lawyerAchievements);
						if (res == 1) {
							msgResult = AppMsgResult.result(200, "success", null);
						} else {
							msgResult = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
						}
					} else {
						msgResult = AppMsgResult.result(539, "请填写案例描述信息！", null);
					}
				} else {
					msgResult = AppMsgResult.result(557, "请选择案例结束时间！", null);
				}
			} else {
				msgResult = AppMsgResult.result(540, "请选择案例起始时间！", null);
			}
		} else {
			msgResult = AppMsgResult.result(541, "id主键参数不能为空！", null);
		}
					
		return msgResult;
	}

	@Override
	public AppMsgResult selectAchievements(String userId, String token) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null;
		// 1、登录判断
		msgResult = userService.validateUserLogin(userId, token);
		if (200 != (int) msgResult.getFlag()) {
			return msgResult;
		}
		// 业务处理
		/*
		 * achievementsExample = new ZaLawyerAchievementsExample(); criteria3 =
		 * achievementsExample.createCriteria(); criteria3.andLawIdEqualTo(userId);
		 * List<ZaLawyerAchievements> lists =
		 * achievementsMapper.selectByExample(achievementsExample);
		 */
		List<LawyerAchievementsVo> lists = lawyerAuthenticationMappervo
				.selectLawyerAchievements(userId);
		msgResult = AppMsgResult.result(200, "success", lists);			
		return msgResult;
	}

	@Override
	public AppMsgResult selectLawyer(String userId, String token,String type) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null;
		// 1、登录判断
		msgResult = userService.validateUserLogin(userId, token);
		if (200 != (int) msgResult.getFlag()) {
			return msgResult;
		}
		// 个人用户
		if ("1".equals(type)) {
			// 查询个人用户下是否存在律师
			example = new ZaLawyerAuthenticationExample();
			criteria = example.createCriteria();
			criteria.andUserIdEqualTo(userId);
			List<ZaLawyerAuthentication> lists = lawyerAuthenticationMapper.selectByExample(example);
			if (lists.size() > 0) {
				// 业务处理
				ZaLawyerAuthentication lawyerAuthentication = lawyerAuthenticationMapper
						.selectByPrimaryKey(lists.get(0).getId());
				lawyerAuthentication.setLicenceUrl(Constant.FILESERVER_URL_TEST+lawyerAuthentication.getLicenceUrl());
				lawyerAuthentication.setQualificationUrl(Constant.FILESERVER_URL_TEST+lawyerAuthentication.getQualificationUrl());
				
				msgResult = AppMsgResult.result(200, "success", lawyerAuthentication);
			} else {
				msgResult = AppMsgResult.result(8000, "律师账号未认证！", null);
			}
		} else if ("2".equals(type)) {
			// 业务处理
			ZaLawyerAuthentication lawyerAuthentication = lawyerAuthenticationMapper
					.selectByPrimaryKey(userId);
			if(lawyerAuthentication != null) {
				msgResult = AppMsgResult.result(200, "success", lawyerAuthentication);	
			} else {
				msgResult = AppMsgResult.result(8000, "律师账号未认证！", null);
			}
			
		} else {
			msgResult = AppMsgResult.result(529, "用户类型参数错误！", null);
		}				
		return msgResult;
	}

}

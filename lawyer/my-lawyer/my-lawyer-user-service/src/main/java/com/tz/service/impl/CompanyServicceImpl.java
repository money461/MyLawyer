package com.tz.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aliyuncs.exceptions.ClientException;
import com.github.pagehelper.PageHelper;
import com.tz.cache.RedisCache;
import com.tz.id.IDUtils;
import com.tz.mapper.ZaComAuthenticationMapper;
import com.tz.mapper.ZaComScopeMapper;
import com.tz.mapper.vo.ZaComAuthenticationMapperVo;
import com.tz.pojo.ZaComAuthentication;
import com.tz.pojo.ZaComAuthenticationExample;
import com.tz.pojo.ZaComScope;
import com.tz.pojo.ZaComScopeExample;
import com.tz.pojo.ZaLawyerAuthentication;
import com.tz.pojo.ZaLawyerAuthenticationExample;
import com.tz.pojo.ZaUser;
import com.tz.pojo.ZaUserExample;
import com.tz.res.AppMsgResult;
import com.tz.res.Constant;
import com.tz.service.CompanyService;
import com.tz.service.UserService;
import com.tz.sms.ALiDaYuUtil;
import com.tz.validate.ValidateUtil;

@Service
public class CompanyServicceImpl implements CompanyService {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	// 企业认证类
	@Autowired
	ZaComAuthenticationMapper comAuthenticationMapper;
	private ZaComAuthenticationExample example;
	private ZaComAuthenticationExample.Criteria criteria;

	// 企业分类中间表类
	@Autowired
	ZaComScopeMapper comScopeMapper;
	private ZaComScopeExample comScopeExample;
	private ZaComScopeExample.Criteria criteria2;

	@Autowired
	private RedisCache cache;

	// 用户接口类
	@Autowired
	UserService userService;

	@Transactional
	@Override
	public AppMsgResult companyAuth(ZaComAuthentication comAuth, String scope, String token) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null;
		// 关联用户账号的id
		if (null == comAuth) {
			return AppMsgResult.result(583, "参数错误！", null);
		}
		String userId = comAuth.getUserId();
		// 1、登录判断
		msgResult = userService.validateUserLogin(userId, token);
		if (200 != (int) msgResult.getFlag()) {
			return msgResult;
		}
		// 三证合一用户营业执照
		String licenceUrl = comAuth.getLicenceUrl();
		if (StringUtils.isNotBlank(licenceUrl)) {
			// 法人手持身份证
			String qualificationUrl = comAuth.getQualificationUrl();
			if (StringUtils.isNotBlank(qualificationUrl)) {
				// 企业详细名称
				String comName = comAuth.getComName();
				if (StringUtils.isNotBlank(comName)) {
					// 企业联系人电话
					String comPhone = comAuth.getComPhone();
					if (StringUtils.isNotBlank(comPhone)) {
						// 企业地址
						String comAddress = comAuth.getComAddress();
						if (StringUtils.isNotBlank(comAddress)) {
							// 企业经营范围
							if (StringUtils.isNotBlank(scope)) {
								// 业务操作
								String id = IDUtils.genId();
								comAuth.setId(id);
								comAuth.setCreatedTime(new Date());
								comAuth.setCreatedTime(new Date());
								// 默认审核中
								comAuth.setComStatus(0);
								int res = comAuthenticationMapper.insertSelective(comAuth);
								// 企业分类中间表
								ZaComScope comScope = new ZaComScope();
								comScope.setId(IDUtils.genId());
								comScope.setComCategoryId(Integer.parseInt(scope));
								comScope.setComId(id);
								comScope.setUpdatedTime(new Date());
								int res2 = comScopeMapper.insert(comScope);
								if (res == 1 && res2 == 1) {
									msgResult = AppMsgResult.result(200, "success", null);
								} else {
									msgResult = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
								}
							} else {
								msgResult = AppMsgResult.result(542, "请选择企业的经营范围！", null);
							}
						} else {
							msgResult = AppMsgResult.result(543, "请填写企业地址！", null);
						}
					} else {
						msgResult = AppMsgResult.result(544, "请填写企业联系人的电话号码！", null);
					}
				} else {
					msgResult = AppMsgResult.result(545, "请填写企业全称！", null);
				}
			} else {
				msgResult = AppMsgResult.result(546, "请上传企业法人手持身份证照！", null);
			}
		} else {
			msgResult = AppMsgResult.result(547, "请上传企业营业执照（三证合一）！", null);
		}

		return msgResult;
	}

	@Override
	public AppMsgResult selectCompany(String userId, String token, String type) {
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
			example = new ZaComAuthenticationExample();
			criteria = example.createCriteria();
			criteria.andUserIdEqualTo(userId);
			List<ZaComAuthentication> lists = comAuthenticationMapper.selectByExample(example);
			if (lists.size() > 0) {
				// 业务处理
				ZaComAuthentication comAuthentication = comAuthenticationMapper.selectByPrimaryKey(userId);
				comAuthentication.setLicenceUrl(Constant.FILESERVER_URL_TEST + comAuthentication.getLicenceUrl());
				comAuthentication
						.setQualificationUrl(Constant.FILESERVER_URL_TEST + comAuthentication.getQualificationUrl());

				msgResult = AppMsgResult.result(200, "success", comAuthentication);
			} else {
				msgResult = AppMsgResult.result(9000, "企业账号未认证！", null);
			}
		} else if ("2".equals(type)) {
			// 业务处理
			ZaComAuthentication comAuthentication = comAuthenticationMapper.selectByPrimaryKey(userId);
			if (null != comAuthentication) {
				msgResult = AppMsgResult.result(200, "success", comAuthentication);
			} else {
				msgResult = AppMsgResult.result(9000, "企业账号未认证！", null);
			}
		} else {
			msgResult = AppMsgResult.result(529, "用户类型参数错误！", null);
		}
		return msgResult;
	}

}

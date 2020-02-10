package com.tz.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.aliyuncs.exceptions.ClientException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tz.cache.RedisCache;
import com.tz.date.DateUtile;
import com.tz.id.IDUtils;
import com.tz.mapper.ZaCaseCategoryMapper;
import com.tz.mapper.ZaComAuthenticationMapper;
import com.tz.mapper.ZaComManagerMapper;
import com.tz.mapper.ZaLawyerAuthenticationMapper;
import com.tz.mapper.ZaLawyerCaseMapper;
import com.tz.mapper.ZaUserMapper;
import com.tz.mapper.ZaUserProfitMapper;
import com.tz.mapper.ZaUserPurchaseRecordMapper;
import com.tz.mapper.vo.ZaCaseCategoryMapperVo;
import com.tz.mapper.vo.ZaComAuthenticationMapperVo;
import com.tz.mapper.vo.ZaLawyerAuthenticationMapperVo;
import com.tz.pojo.ZaCaseCategory;
import com.tz.pojo.ZaCaseCategoryExample;
import com.tz.pojo.ZaComAuthentication;
import com.tz.pojo.ZaComAuthenticationExample;
import com.tz.pojo.ZaComManager;
import com.tz.pojo.ZaComManagerExample;
import com.tz.pojo.ZaLawyerAuthentication;
import com.tz.pojo.ZaLawyerAuthenticationExample;
import com.tz.pojo.ZaLawyerCase;
import com.tz.pojo.ZaLawyerCaseExample;
import com.tz.pojo.ZaUser;
import com.tz.pojo.ZaUserDealLogExample;
import com.tz.pojo.ZaUserExample;
import com.tz.pojo.ZaUserProfit;
import com.tz.pojo.ZaUserProfitExample;
import com.tz.pojo.ZaUserPurchaseRecord;
import com.tz.pojo.ZaUserPurchaseRecordExample;
import com.tz.pojo.vo.CategoryNames;
import com.tz.pojo.vo.ZaAdminUserVo;
import com.tz.pojo.vo.ZaCaseCategoryVo;
import com.tz.pojo.vo.ZaComAuthenticationVo;
import com.tz.pojo.vo.ZaLawyerAuthenticationVo;
import com.tz.pojo.vo.ZaPlatManagerVo;
/*import com.tz.pojo.vo.CategoryNames;*/
import com.tz.pojo.vo.ZaUserVo;
import com.tz.res.AppMsgResult;
import com.tz.res.Constant;
import com.tz.sdk.zfb.AlipayConfig;
import com.tz.service.UserService;
import com.tz.service.ZaCaseCategoryService;
import com.tz.sms.ALiDaYuUtil;
import com.tz.validate.ValidateUtil;

/**
 * 律师分类接口实现
 * 
 * @author menglin 2017年12月26日17:37:55
 */
@Service
public class ZaCaseCategoryServicceImpl implements ZaCaseCategoryService {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	
	// 律师认证类
	@Autowired
	ZaLawyerAuthenticationMapper lawyerAuthenticationMapper;
	private ZaLawyerAuthenticationExample lawyerAuthenticationExample;
	private ZaLawyerAuthenticationExample.Criteria criteria;

	// 律师认证扩展接口类
	@Autowired
	ZaLawyerAuthenticationMapperVo lawyerAuthenticationMappervo;

	// 注入用户接口类
	@Autowired
	private UserService userService;

	// 注入律师分类扩展类
	@Autowired
	private ZaCaseCategoryMapperVo caseCategoryMapperVo;

	// 注入律师分类
	@Autowired
	private ZaCaseCategoryMapper caseCategoryMapper;
	private ZaCaseCategoryExample caseCategoryExample;
	private ZaCaseCategoryExample.Criteria criteria2;
	
	// 注入律师分类中间表
	@Autowired
	private ZaLawyerCaseMapper lawyerCaseMapper;
	private ZaLawyerCaseExample lawyerCaseExample;
	private ZaLawyerCaseExample.Criteria criteria3;
	

	@Autowired
	private RedisCache cache;

	@Override
	public AppMsgResult findCategoryOrSonList(ZaCaseCategoryVo caseCategoryVo, Integer curPage, Integer rows, String type,
			String userId, String token) {
		// TODO Auto-generated method stub
		// 1、登录判断
		AppMsgResult msgResult = null;
		msgResult = userService.validateAdminLogin(userId, token);
		if (200 != (int) msgResult.getFlag()) {
			return msgResult;
		}
		// 类型参数
		if (StringUtils.isBlank(type)) {
			return AppMsgResult.result(529, "type类型参数错误！", null);
		}
		rows = rows == null ? 10 : rows;
		curPage = curPage == null ? 1 : curPage;
		// 分页处理
		PageHelper.startPage(curPage, rows);
		// 查询父类下所有子类分类列表
		if ("son".equals(type)) {
			Integer pid = caseCategoryVo.getpId();
			if (null != pid) {
				caseCategoryExample = new ZaCaseCategoryExample();
				// 分页处理
				PageHelper.startPage(curPage, rows);
				criteria2 = caseCategoryExample.createCriteria();
				// 修改时间降序
				caseCategoryExample.setOrderByClause("updated_time DESC");
				criteria2.andPIdEqualTo(pid);
				String caseName = caseCategoryVo.getCaseName();
				if (StringUtils.isNotBlank(caseName)) {
					criteria2.andCaseNameLike("%" + caseName + "%");
				}
				List<ZaCaseCategory> lists = caseCategoryMapper.selectByExample(caseCategoryExample);
				// 取记录总条数
				PageInfo<ZaCaseCategory> pageInfo = new PageInfo<>(lists);
				msgResult = AppMsgResult.result(200, "success", pageInfo);
			} else {
				return AppMsgResult.result(567, "请传入父id:pid！", null);
			}
			// 查询二级分类
		} else if ("all".equals(type)) {
			Map<String, Object> map = new HashMap<String, Object>();
			String caseName = caseCategoryVo.getCaseName();
			// 用户名
			if (StringUtils.isNotBlank(caseName)) {
				map.put("caseName", "%" + caseName + "%");
			} else {
				map.put("caseName", null);
			}
			// 时间段
			if (null != caseCategoryVo.getStartTime() && null != caseCategoryVo.getEndTime()) {
				map.put("startTime", DateUtile.pushDays(caseCategoryVo.getStartTime(), 0));
				map.put("endTime", DateUtile.pushDays(caseCategoryVo.getEndTime(), 0));
			} else {
				map.put("startTime", null);
				map.put("endTime", null);
			}
			// 数据查询封装
			List<ZaCaseCategoryVo> lists = caseCategoryMapperVo.selectCategoryAndChSonList(map);
			// 取记录总条数
			PageInfo<ZaCaseCategoryVo> pageInfo = new PageInfo<>(lists);
			msgResult = AppMsgResult.result(true, "success", pageInfo);
		} else {
			return AppMsgResult.result(529, "type类型参数错误！", null);
		}
		return msgResult;
	}

	@Override
	public AppMsgResult addOrUpdateCategory(ZaCaseCategory caseCategory, String type, String userId, String token) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null;
		msgResult = userService.validateAdminLogin(userId, token);
		if (200 != (int) msgResult.getFlag()) {
			return msgResult;
		}
		// 类型参数
		if (StringUtils.isBlank(type)) {
			return AppMsgResult.result(529, "type类型参数错误！", null);
		}
		caseCategory.setOperator(userId);
		caseCategory.setCreatedTime(new Date());
		String caseName = caseCategory.getCaseName();
		Integer pid = caseCategory.getpId();
		// 名称重复判断
		caseCategoryExample = new ZaCaseCategoryExample();
		criteria2 = caseCategoryExample.createCriteria();
		criteria2.andCaseNameEqualTo(caseName);
		List<ZaCaseCategory> lists = caseCategoryMapper.selectByExample(caseCategoryExample);
		// 新增
		if ("add".equals(type)) {
			if (StringUtils.isBlank(caseName)) {
				return AppMsgResult.result(526, "律师分类名称不能为空！", null);
			}
			if (null == pid) {
				return AppMsgResult.result(526, "律师分类名称不能为空！", null);
			}
			// 名称重复判断
			if (lists.size() > 0) {
				return AppMsgResult.result(577, "律师分类名称已经被使用！", null);
			}
			// id自动增长
			caseCategory.setUpdatedTime(new Date());
			// 验证成功 添加用户数据
			int res = caseCategoryMapper.insertSelective(caseCategory);
			if (res == 1) {
				msgResult = AppMsgResult.result(200, "success", null);
			} else {
				msgResult = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
			}
			// 修改
		} else if ("update".equals(type)) {
			Integer id = caseCategory.getId();
			if (id == null) {
				return AppMsgResult.result(541, "id主键参数不能为空！", null);
			}
			// 名称重复判断
			if (lists.size() > 0) {
				if (id != lists.get(0).getId()) {
					return AppMsgResult.result(577, "律师分类名称已经被使用！", null);
				}
			}
			int res = caseCategoryMapper.updateByPrimaryKeySelective(caseCategory);
			if (res == 1) {
				msgResult = AppMsgResult.result(200, "success", null);
			} else {
				msgResult = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
			}
			// 类型错误
		} else {
			msgResult = AppMsgResult.result(529, "type类型参数错误！", null);
		}
		return msgResult;
	}

	@Override
	public AppMsgResult updateStatusById(String id, String caseStatus,String userId, String token) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null;
		msgResult = userService.validateAdminLogin(userId, token);
		if (200 != (int) msgResult.getFlag()) {
			return msgResult;
		}
		// id参数
		if (StringUtils.isBlank(id)) {
			return AppMsgResult.result(541, "id主键参数不能为空！", null);
		}
		// 状态参数
		if (StringUtils.isBlank(caseStatus)) {
			return AppMsgResult.result(578, "状态参数不能为空！", null);
		}
		ZaCaseCategory caseCategory = new ZaCaseCategory();
		caseCategory.setId(Integer.parseInt(id));
		caseCategory.setCaseStatus(Integer.parseInt(caseStatus));
		int res = caseCategoryMapper.updateByPrimaryKeySelective(caseCategory);
		if (res == 1) {
			msgResult = AppMsgResult.result(200, "success", null);
		} else {
			msgResult = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
		}
		return msgResult;
	}

	@Override
	public AppMsgResult deleteById(String id, String userId, String token) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null;
		msgResult = userService.validateAdminLogin(userId, token);
		if (200 != (int) msgResult.getFlag()) {
			return msgResult;
		}
		
		// id参数
		if (StringUtils.isBlank(id)) {
			return AppMsgResult.result(541, "id主键参数不能为空！", null);
		}
		ZaCaseCategory caseCategory = caseCategoryMapper.selectByPrimaryKey(Integer.parseInt(id));
		if(null != caseCategory) {
			Integer pid = caseCategory.getpId();
			// pid 为0 为顶级节点
			if(pid == 0) {
				return AppMsgResult.result(579, "一级律师分类不能删除！", null);
			}else {
				//二级节点是否被使用
				lawyerCaseExample = new ZaLawyerCaseExample();
				criteria3 = lawyerCaseExample.createCriteria();
				criteria3.andCaseIdEqualTo(Integer.parseInt(id));
				List<ZaLawyerCase> lists = lawyerCaseMapper.selectByExample(lawyerCaseExample);
				if(lists.size()>0) {
					return AppMsgResult.result(580, "分类已经被使用，不能删除！", null);
				}
			}
		}
		//删除操作
		int res = caseCategoryMapper.deleteByPrimaryKey(Integer.parseInt(id));
		if (res == 1) {
			msgResult = AppMsgResult.result(200, "success", null);
		} else {
			msgResult = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
		}
		return msgResult;
	}

	@Override
	public AppMsgResult selectById(String id, String userId, String token) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null;
		msgResult = userService.validateAdminLogin(userId, token);
		if (200 != (int) msgResult.getFlag()) {
			return msgResult;
		}
		if(StringUtils.isBlank(id)) {
			return AppMsgResult.result(541, "id主键参数不能为空！", null);
		}
		return AppMsgResult.result(200, "success！", caseCategoryMapper.selectByPrimaryKey(Integer.parseInt(id)));
	}

}

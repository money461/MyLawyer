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


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tz.cache.RedisCache;
import com.tz.date.DateUtile;

import com.tz.mapper.ZaComAuthenticationMapper;
import com.tz.mapper.ZaComCategoryMapper;
import com.tz.mapper.ZaComScopeMapper;

import com.tz.mapper.vo.ZaComAuthenticationMapperVo;
import com.tz.mapper.vo.ZaComCategoryMapperVo;
import com.tz.pojo.ZaComAuthenticationExample;
import com.tz.pojo.ZaComCategory;
import com.tz.pojo.ZaComCategoryExample;
import com.tz.pojo.ZaComScope;
import com.tz.pojo.ZaComScopeExample;
import com.tz.pojo.vo.ZaComCategoryVo;
/*import com.tz.pojo.vo.CategoryNames;*/
import com.tz.res.AppMsgResult;
import com.tz.service.UserService;
import com.tz.service.ZaComCategoryService;

/**
 * 企业分类接口实现
 * 
 * @author menglin 2017年12月26日17:37:55
 */
@Service
public class ZaComCategoryServicceImpl implements ZaComCategoryService {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	// 企业认证类
	@Autowired
	ZaComAuthenticationMapper comAuthenticationMapper;
	private ZaComAuthenticationExample comAuthenticationExample;
	private ZaComAuthenticationExample.Criteria criteria;

	// 企业认证扩展接口类
	@Autowired
	ZaComAuthenticationMapperVo comAuthenticationMappervo;

	// 注入用户接口类
	@Autowired
	private UserService userService;

	// 企业分类
	@Autowired
	ZaComCategoryMapper comCategoryMapper;
	private ZaComCategoryExample comCategoryExample;
	private ZaComCategoryExample.Criteria criteria2;

	// 企业分类扩展接口类
	@Autowired
	ZaComCategoryMapperVo categoryMapperVo;

	// 企业分类中间类
	@Autowired
	ZaComScopeMapper comScopeMapper;
	private ZaComScopeExample comScopeExample;
	private ZaComScopeExample.Criteria criteria3;

	@Autowired
	private RedisCache cache;

	@Override
	public AppMsgResult findCategoryOrSonList(ZaComCategoryVo comCategoryVo, Integer curPage, Integer rows, String type,
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
			Integer pid = comCategoryVo.getpId();
			if (null != pid) {
				comCategoryExample = new ZaComCategoryExample();
				// 分页处理
				PageHelper.startPage(curPage, rows);
				criteria2 = comCategoryExample.createCriteria();
				// 修改时间降序
				comCategoryExample.setOrderByClause("updated_time DESC");
				criteria2.andPIdEqualTo(pid);
				String categoryName = comCategoryVo.getCategoryName();
				if (StringUtils.isNotBlank(categoryName)) {
					criteria2.andCategoryNameLike("%" + categoryName + "%");
				}
				List<ZaComCategory> lists = comCategoryMapper.selectByExample(comCategoryExample);
				// 取记录总条数
				PageInfo<ZaComCategory> pageInfo = new PageInfo<>(lists);
				msgResult = AppMsgResult.result(200, "success", pageInfo);
			} else {
				return AppMsgResult.result(567, "请传入父id:pid！", null);
			}
			// 查询二级分类
		} else if ("all".equals(type)) {
			Map<String, Object> map = new HashMap<String, Object>();
			String caseName = comCategoryVo.getCategoryName();
			// 用户名
			if (StringUtils.isNotBlank(caseName)) {
				map.put("caseName", "%" + caseName + "%");
			} else {
				map.put("caseName", null);
			}
			// 时间段
			if (null != comCategoryVo.getStartTime() && null != comCategoryVo.getEndTime()) {
				map.put("startTime", DateUtile.pushDays(comCategoryVo.getStartTime(), 0));
				map.put("endTime", DateUtile.pushDays(comCategoryVo.getEndTime(), 0));
			} else {
				map.put("startTime", null);
				map.put("endTime", null);
			}
			// 数据查询封装
			List<ZaComCategoryVo> lists = categoryMapperVo.selectComCategoryAndChSonList(map);
			// 取记录总条数
			PageInfo<ZaComCategoryVo> pageInfo = new PageInfo<>(lists);
			msgResult = AppMsgResult.result(true, "success", pageInfo);
		} else {
			return AppMsgResult.result(529, "type类型参数错误！", null);
		}
		return msgResult;
	}

	@Override
	public AppMsgResult addOrUpdateCategory(ZaComCategory comCategory, String type, String userId, String token) {
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
		comCategory.setOperator(userId);
		comCategory.setCreatedTime(new Date());
		String caseName = comCategory.getCategoryName();
		// 名称重复判断
		comCategoryExample = new ZaComCategoryExample();
		criteria2 = comCategoryExample.createCriteria();
		criteria2.andCategoryNameEqualTo(caseName);
		List<ZaComCategory> lists = comCategoryMapper.selectByExample(comCategoryExample);
		// 新增
		if ("add".equals(type)) {
			if (StringUtils.isBlank(caseName)) {
				return AppMsgResult.result(581, "企业分类名称不能为空！", null);
			}
			// 名称重复判断
			if (lists.size() > 0) {
				return AppMsgResult.result(582, "企业分类名称已经被使用！", null);
			}
			// id自动增长
			comCategory.setUpdatedTime(new Date());
			// 验证成功 添加用户数据
			int res = comCategoryMapper.insertSelective(comCategory);
			if (res == 1) {
				msgResult = AppMsgResult.result(200, "success", null);
			} else {
				msgResult = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
			}
			// 修改
		} else if ("update".equals(type)) {
			Integer id = comCategory.getId();
			if (id == null) {
				return AppMsgResult.result(541, "id主键参数不能为空！", null);
			}
			// 名称重复判断
			if (lists.size() > 0) {
				if (id != lists.get(0).getId()) {
					return AppMsgResult.result(582, "律师分类名称已经被使用！", null);
				}
			}
			int res = comCategoryMapper.updateByPrimaryKeySelective(comCategory);
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
	public AppMsgResult updateStatusById(String id, String caseStatus, String userId, String token) {
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
		ZaComCategory comCategory = new ZaComCategory();
		comCategory.setId(Integer.parseInt(id));
		comCategory.setCategoryStatus(Integer.parseInt(caseStatus));
		int res = comCategoryMapper.updateByPrimaryKeySelective(comCategory);
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
		ZaComCategory caseCategory = comCategoryMapper.selectByPrimaryKey(Integer.parseInt(id));
		if (null != caseCategory) {
			Integer pid = caseCategory.getpId();
			// pid 为0 为顶级节点
			if (pid == 0) {
				return AppMsgResult.result(579, "一级律师分类不能删除！", null);
			} else {
				// 二级节点是否被使用
				comScopeExample = new ZaComScopeExample();
				criteria3 = comScopeExample.createCriteria();
				criteria3.andComCategoryIdEqualTo(Integer.parseInt(id));
				List<ZaComScope> lists = comScopeMapper.selectByExample(comScopeExample);
				if (lists.size() > 0) {
					return AppMsgResult.result(580, "分类已经被使用，不能删除！", null);
				}
			}
		}
		// 删除操作
		int res = comScopeMapper.deleteByPrimaryKey(id);
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
		return AppMsgResult.result(200, "success！", comCategoryMapper.selectByPrimaryKey(Integer.parseInt(id)));
	}

}

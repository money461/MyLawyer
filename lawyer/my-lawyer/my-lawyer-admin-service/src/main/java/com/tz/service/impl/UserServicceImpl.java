package com.tz.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
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
import com.tz.mapper.ZaComAuthenticationMapper;
import com.tz.mapper.ZaLawyerAuthenticationMapper;
import com.tz.mapper.ZaPlatManagerMapper;
import com.tz.mapper.ZaUserMapper;
import com.tz.mapper.ZaUserProfitMapper;
import com.tz.mapper.ZaUserPurchaseRecordMapper;
import com.tz.mapper.vo.ZaAdminUserMappperVo;
import com.tz.mapper.vo.ZaComAuthenticationMapperVo;
import com.tz.mapper.vo.ZaLawyerAuthenticationMapperVo;
import com.tz.pojo.ZaComAuthentication;
import com.tz.pojo.ZaComAuthenticationExample;
import com.tz.pojo.ZaLawyerAuthentication;
import com.tz.pojo.ZaLawyerAuthenticationExample;
import com.tz.pojo.ZaPlatAuthority;
import com.tz.pojo.ZaPlatManager;
import com.tz.pojo.ZaPlatManagerExample;
import com.tz.pojo.ZaUser;
import com.tz.pojo.ZaUserDealLogExample;
import com.tz.pojo.ZaUserExample;
import com.tz.pojo.ZaUserProfitExample;
import com.tz.pojo.ZaUserPurchaseRecordExample;
import com.tz.pojo.com.vo.ComAuthenDetailInfo;
import com.tz.pojo.vo.ZaAdminUserVo;
import com.tz.pojo.vo.ZaComAuthenticationVo;
import com.tz.pojo.vo.ZaLawyerAuthenticationVo;
import com.tz.pojo.vo.ZaPlatManagerVo;
import com.tz.res.AppMsgResult;
import com.tz.res.Constant;
import com.tz.service.PlatAuthorityService;
import com.tz.service.UserService;
import com.tz.validate.ValidateUtil;

import scala.collection.mutable.StringBuilder;

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

	// 企业认证类
	@Autowired
	ZaComAuthenticationMapper comAuthenticationMapper;
	private ZaComAuthenticationExample comAuthenticationExample;
	private ZaComAuthenticationExample.Criteria criteria3;

	// 企业认证扩展接口类
	@Autowired
	ZaComAuthenticationMapperVo comAuthenticationMappervo;

	
	//获取被删除的用户信息扩展接口
	@Autowired
	private ZaAdminUserMappperVo zaAdminUserMappperVo;
	
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

	
	// 平台管理员
	@Autowired
	ZaPlatManagerMapper platManagerMapper;
	private ZaPlatManagerExample platManagerExample;
	private ZaPlatManagerExample.Criteria criteria7;
	
	//注入平台权限接口
	@Autowired
	PlatAuthorityService platAuthorityService;
	
	
	@Autowired
	private RedisCache cache;

	@Override
	public AppMsgResult getUserList(ZaAdminUserVo user, Integer curPage, Integer rows, String userId, String token) {
		AppMsgResult msgResult = null;
		msgResult = validateAdminLogin(userId,token);
		if(200 != (int)msgResult.getFlag()){
			return  msgResult;
		}
		rows = rows == null ? 10 : rows;
		curPage = curPage == null ? 1 : curPage;
		example = new ZaUserExample();
		// 分页处理
		PageHelper.startPage(curPage, rows);
		criteria = example.createCriteria();
		// 修改时间降序
		example.setOrderByClause("updated_time DESC");
		criteria.andStatusNotEqualTo(2);
		if (null != user) {
			// 根据用户昵称查询
			String userNick = user.getUserNick();
			if (StringUtils.isNotBlank(userNick)) {
				criteria.andUserNickLike("%" + userNick + "%");
			}
			// 根据电话查询
			String userPhone = user.getUserPhone();
			if (StringUtils.isNotBlank(userPhone)) {
				criteria.andUserPhoneLike("%" + userPhone + "%");
			}
			if (null != user.getStartTime() && null != user.getEndTime()) {
				criteria.andCreatedTimeBetween(DateUtile.pushDays(user.getStartTime(), 0),
						DateUtile.pushDays(user.getEndTime(), 0));
			}
		}
		List<ZaUser> lists = userMapper.selectByExample(example);
		// 取记录总条数
		PageInfo<ZaUser> pageInfo = new PageInfo<>(lists);
		msgResult = AppMsgResult.result(200, "success", pageInfo);
		return msgResult;
	}

	@Override
	public AppMsgResult addOrUpdateUser(ZaUser user, String type, String userId, String token) {
		AppMsgResult msgResult = null;
		msgResult = validateAdminLogin(userId,token);
		if(200 != (int)msgResult.getFlag()){
			return  msgResult;
		}
		// 类型参数
		if (StringUtils.isBlank(type)) {
			return AppMsgResult.result(529, "Type类型参数错误！", null);
		}
		user.setOperator(userId);
		// 新增
		if ("add".equals(type)) {
			String userPhone = user.getUserPhone();
			if (StringUtils.isBlank(userPhone)) {
				return AppMsgResult.result(526, "手机号码不能为空！", null);
			}
			// 手机号码判断
			if (!ValidateUtil.phoneValidate(userPhone)) {
				return AppMsgResult.result(523, "手机号码格式错误！", null);
			}
			String userPassword = user.getUserPassword();
			if (StringUtils.isBlank(userPassword)) {
				return AppMsgResult.result(525, "密码不能为空！", null);
			}
			// 密码格式判断
			if (!ValidateUtil.passWordValidate(userPassword)) {

				return AppMsgResult.result(521, "密码必须为6-19位数字、字母组合！", null);
			}
			String id = IDUtils.genId();
			user.setId(id);
			user.setUpdatedTime(new Date());
			// 验证成功 添加用户数据
			user.setCreatedTime(new Date());
			user.setLastLoginTime(new Date());
			user.setUserPhone(userPhone);
			user.setHeadUrl(Constant.DFHEAD2);
			user.setUserAccount(userPhone);
			String ucode = IDUtils.getCode();
			user.setCode(ucode);
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
				msgResult = AppMsgResult.result(200, "success", null);
			} else {
				msgResult = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
			}
			// 修改
		} else if ("update".equals(type)) {
			user.setUpdatedTime(new Date());
			int res = userMapper.updateByPrimaryKeySelective(user);
			if (res == 1) {
				msgResult = AppMsgResult.result(200, "success", null);
			} else {
				msgResult = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
			}
			// 类型错误
		} else {
			msgResult = AppMsgResult.result(529, "Type类型参数错误！", null);
		}
		return msgResult;
	}

	@Override
	public AppMsgResult deleteUser(ZaUser user, String userId, String token) {
		AppMsgResult msgResult = null;
		msgResult = validateAdminLogin(userId,token);
		if(200 != (int)msgResult.getFlag()){
			return  msgResult;
		}
		// 设置为2 删除状态
		user.setStatus(2);
		int res = userMapper.updateByPrimaryKeySelective(user);
		if (res == 1) {
			msgResult = AppMsgResult.result(200, "success", null);
		} else {
			msgResult = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
		}
		return msgResult;
	}
	
	
	@Override
	public AppMsgResult getLawyerUserList(ZaLawyerAuthenticationVo user, Integer curPage, Integer rows,String type, String userId,
			String token) {
		AppMsgResult msgResult = null;
		msgResult = validateAdminLogin(userId,token);
		if(200 != (int)msgResult.getFlag()){
			return  msgResult;
		}
		if(StringUtils.isBlank(type)) {
			return AppMsgResult.result(529, "Type类型参数错误！", null);
		}
		rows = rows == null ? 10 : rows;
		curPage = curPage == null ? 1 : curPage;
		// 分页处理
		PageHelper.startPage(curPage, rows);
		Map<String, Object> map = new HashMap<String, Object>();
		String userPhone = user.getUserPhone();
		// 手机号码
		if (StringUtils.isNotBlank(userPhone)) {
			map.put("userPhone", "%" + userPhone + "%");
		} else {
			map.put("userPhone", null);
		}
		String userNick = user.getRealName();
		// 用户名
		if (StringUtils.isNotBlank(userNick)) {
			map.put("realName", "%" + userNick + "%");
		} else {
			map.put("realName", null);
		}
		// 时间段
		if (null != user.getStartTime() && null != user.getEndTime()) {
			map.put("startTime", DateUtile.pushDays(user.getStartTime(), 0));
			map.put("endTime", DateUtile.pushDays(user.getEndTime(), 0));
		} else {
			map.put("startTime", null);
			map.put("endTime", null);
		}
		List<ZaLawyerAuthenticationVo> lists = new ArrayList<>();
		if("anth".equals(type)) {
			lists = lawyerAuthenticationMappervo.selectLawyerAndUserAuthList(map);
		}else if("all".equals(type)) {
			lists = lawyerAuthenticationMappervo.selectLawyerAndUserList(map);
		}else {
			return AppMsgResult.result(529, "Type类型参数错误！", null);
		}
		// 取记录总条数
		PageInfo<ZaLawyerAuthenticationVo> pageInfo = new PageInfo<>(lists);
		msgResult = AppMsgResult.result(true, "success", pageInfo);
		return msgResult;
	}
	@Override
	public AppMsgResult updateLawyerUser(ZaLawyerAuthenticationVo lawyerAuthenticationVo, String userId, String token) {
		AppMsgResult msgResult = null;
		//登录验证
		msgResult = validateAdminLogin(userId,token);
		if(200 != (int)msgResult.getFlag()){
			return  msgResult;
		}
		String id = lawyerAuthenticationVo.getId();
		if(StringUtils.isBlank(id)) {
			return AppMsgResult.result(541, "id主键参数不能为空！", null);
		}
		int res = lawyerAuthenticationMapper.updateByPrimaryKeySelective(lawyerAuthenticationVo);
		if (res == 1) {
			msgResult = AppMsgResult.result(200, "success", null);
		} else {
			msgResult = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
		}
		return msgResult;
	}
	@Override
	public AppMsgResult updateUserStatusById(String id, String status,String type,String userId, String token) {
		AppMsgResult msgResult = null;
		//登录验证
		msgResult = validateAdminLogin(userId,token);
		if(200 != (int)msgResult.getFlag()){
			return  msgResult;
		}
		
		if(StringUtils.isBlank(id)) {
			return AppMsgResult.result(541, "id主键参数不能为空！", null);
		}
		if(StringUtils.isBlank(status)) {
			return AppMsgResult.result(574, "修改状态参数不能为空！", null);
		}
		if(StringUtils.isBlank(type)) {
			return AppMsgResult.result(529, "Type类型参数错误！", null);
		}
		int res = 0;
		//会员用户
		if("1".equals(type)) {
			ZaUser user = new ZaUser();
			user.setId(id);
			user.setOperator(userId);
			user.setStatus(Integer.parseInt(status));
			res = userMapper.updateByPrimaryKeySelective(user);
		//律师用户
		}else if("2".equals(type)) {
			ZaLawyerAuthentication lawyerAuthentication =  new ZaLawyerAuthentication();
			lawyerAuthentication.setId(id);
			lawyerAuthentication.setOperator(userId);
			lawyerAuthentication.setLawStatus(Integer.parseInt(status));
			res = lawyerAuthenticationMapper.updateByPrimaryKeySelective(lawyerAuthentication);
		//企业用户
		}else if("3".equals(type)) {
			ZaComAuthentication comAuthentication = new ZaComAuthentication();
			comAuthentication.setId(id);
			comAuthentication.setOperator(userId);
			comAuthentication.setState(Integer.parseInt(status));
			res = comAuthenticationMapper.updateByPrimaryKeySelective(comAuthentication);
		}else {
			return AppMsgResult.result(529, "Type类型参数错误！", null);
		}
		if (res == 1) {
			msgResult = AppMsgResult.result(200, "success", null);
		} else {
			msgResult = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
		}
		return msgResult;
	}
	@Override
	public AppMsgResult getComUserList(ZaComAuthenticationVo user, Integer curPage, Integer rows,String type, String userId, String token) {
		AppMsgResult msgResult = null;
		msgResult = validateAdminLogin(userId,token);
		if(200 != (int)msgResult.getFlag()){
			return  msgResult;
		}
		if(StringUtils.isBlank(type)) {
			return AppMsgResult.result(529, "Type类型参数错误！", null);
		}
		rows = rows == null ? 10 : rows;
		curPage = curPage == null ? 1 : curPage;
		// 分页处理
		PageHelper.startPage(curPage, rows);
		Map<String, Object> map = new HashMap<String, Object>();
		String userPhone = user.getUserPhone();
		// 手机号码
		if (StringUtils.isNotBlank(userPhone)) {
			map.put("userPhone", "%" + userPhone + "%");
		} else {
			map.put("userPhone", null);
		}
		String userNick = user.getComName();
		// 用户名
		if (StringUtils.isNotBlank(userNick)) {
			map.put("realName", "%" + userNick + "%");
		} else {
			map.put("realName", null);
		}
		// 时间段
		if (null != user.getStartTime() && null != user.getEndTime()) {
			map.put("startTime", DateUtile.pushDays(user.getStartTime(), 0));
			map.put("endTime", DateUtile.pushDays(user.getEndTime(), 0));
		} else {
			map.put("startTime", null);
			map.put("endTime", null);
		}
		List<ZaComAuthenticationVo> lists = new ArrayList<>();
		if("anth".equals(type)) {
			lists = comAuthenticationMappervo.selectComAndUserAuthList(map);
		}else if("all".equals(type)) {
			lists = comAuthenticationMappervo.selectComAndUserList(map);
		}else {
			return AppMsgResult.result(529, "Type类型参数错误！", null);
		}
		// 取记录总条数
		PageInfo<ZaComAuthenticationVo> pageInfo = new PageInfo<>(lists);
		msgResult = AppMsgResult.result(true, "success", pageInfo);
		return msgResult;
	}
	
	@Override
	public AppMsgResult adminLogin(String managerAccount, String managerPassword) {
		AppMsgResult msgResult = null;
		if (StringUtils.isBlank(managerAccount)) {
			return AppMsgResult.result(571, "用户名不能为空！", null);
		}
		if (StringUtils.isBlank(managerPassword)) {
			return AppMsgResult.result(572, "密码不能为空！", null);
		}
		platManagerExample = new ZaPlatManagerExample();
		criteria7 = platManagerExample.createCriteria();
		criteria7.andManagerAccountEqualTo(managerAccount);
		
		List<ZaPlatManager> list = platManagerMapper.selectByExample(platManagerExample);
		if (list.size() > 0) {
			// 获取登录账号信息
			ZaPlatManager manager = list.get(0);
			if (manager.getStatus() != 1) {
				return AppMsgResult.result(531, "用户账号已被冻结，请联系客服！", null);
			}
			if (manager.getManagerPassword().equals(IDUtils.md5(managerPassword + manager.getCode()))) {
				String id = manager.getId();
				String cache_key = RedisCache.CAHCEADMIN + "|getPlatManagerToken|" + id;
				String token = IDUtils.md5(id + System.currentTimeMillis());
				// 缓存一个小时
				cache.putCacheWithExpireTime(cache_key, token, RedisCache.ADMINCAHCETIME);
				String result_cache = cache.getCache(cache_key, String.class);
				ZaPlatManagerVo platManagerVo = new ZaPlatManagerVo();

				platManagerVo.setId(id);
				platManagerVo.setManagerName(manager.getManagerName());
				platManagerVo.setManagerAccount(manager.getManagerAccount());
				platManagerVo.setManagerPhone(manager.getManagerPhone());
				platManagerVo.setToken(token);
				System.out.println("-----------------result_cache:" + result_cache);
				//获取管理员所有权限
				List<ZaPlatAuthority> authorities = platAuthorityService.getAllAuthorityByManagerId(id);
			 	if(authorities.size() > 0) {
			 		StringBuilder sb = new StringBuilder();
			 		for(ZaPlatAuthority zaPlatAuthority:authorities) {
			 			sb.append(zaPlatAuthority.getCode()+",");
			 		}
			 		String cache_key_auth = RedisCache.CAHCEADMIN + RedisCache.CAHCENAME_AUTH+ id;
			 		cache.putCacheWithExpireTime(cache_key_auth, sb.toString().substring(0, sb.toString().length()-1), RedisCache.USERCAHCETIME);
			 		
			 		platManagerVo.setAuthorities(authorities);
			 	}
				msgResult = AppMsgResult.result(200, "success", platManagerVo);
			} else {
				msgResult = AppMsgResult.result(573, "用户名或者密码错误！", null);
			}
		} else {
			msgResult = AppMsgResult.result(573, "用户名或者密码错误！", null);
		}
		return msgResult;
	}
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		sb.append("admin/login/test,").append("admin/user/update,").append("admin/user/getList");
		System.out.println(sb.toString().substring(0, sb.length()-1));
		System.out.println(ArrayUtils.contains(sb.toString().split(","), "admin/user/getList"));
	}
	@Override
	public AppMsgResult validateAdminLogin(String userId, String token) {
		AppMsgResult msgResult = null;
		if (StringUtils.isBlank(userId)) {
			return AppMsgResult.result(538, "登录用户的id不能为空！", null);
		}
		if (StringUtils.isBlank(token)) {
			return AppMsgResult.result(7000, "登录token不能为空！", null);
		}
		// 获取后台管理用户的登录 token
		String cache_key = RedisCache.CAHCEADMIN + "|getPlatManagerToken|" + userId;
		String result_cache = cache.getCache(cache_key, String.class);
		// 是否存在
		if (null == result_cache) {
			return AppMsgResult.result(6000, "用户未登录！", null);
		}
		// 验证
		if (!result_cache.equals(token)) {
			return AppMsgResult.result(6000, "用户未登录！", null);
		}
		//重置userToken有效时间
		cache.putCacheWithExpireTime(cache_key, result_cache, RedisCache.ADMINCAHCETIME);
		msgResult = AppMsgResult.result(200, "success", null);
		return msgResult;
	}

	@Override
	public AppMsgResult selectById(String id, String type, String userId, String token) {
		AppMsgResult msgResult = null;
		msgResult = validateAdminLogin(userId,token);
		if(200 != (int)msgResult.getFlag()){
			return  msgResult;
		}
		if(StringUtils.isBlank(type)) {
			return AppMsgResult.result(529, "Type类型参数错误！", null);
		}
		if(StringUtils.isBlank(id)) {
			return AppMsgResult.result(541, "id主键参数不能为空！", null);
		}
		//会员用户
		if("1".equals(type)) {
			ZaUser user = userMapper.selectByPrimaryKey(id);
			return AppMsgResult.result(200, "success！", user);
		//律师用户
		}else if("2".equals(type)) {
			ZaLawyerAuthentication lawyerAuthentication = lawyerAuthenticationMapper.selectByPrimaryKey(id);
			return AppMsgResult.result(200, "success！", lawyerAuthentication);
		//企业用户
		}else if("3".equals(type)) {
			ZaComAuthentication comAuthentication = comAuthenticationMapper.selectByPrimaryKey(id);
			return AppMsgResult.result(200, "success！", comAuthentication);
		}else {
			return AppMsgResult.result(529, "Type类型参数错误！", null);
		}
	}

	/**
	 * 获取所有被删除的会员信息
	 */
	@Override
	public AppMsgResult getDeletedUserList(ZaAdminUserVo zaAdminUserVo,Integer curPage, Integer rows, String userId, String token) {
		// 校验登录
		AppMsgResult msgResult = null;
		msgResult = validateAdminLogin(userId,token);
		if(200 != (int)msgResult.getFlag()){
//			return  msgResult;
		}
		AppMsgResult result= null;
		Integer userType = zaAdminUserVo.getUserType();
		if(userType==null) {
			return AppMsgResult.result(543, "用户类型必须传值！", null);
		}
		if( userType==1 || userType==2 || userType==3) {
			rows = rows == null ? 10 : rows;
			curPage = curPage == null ? 1 : curPage;
			// 分页处理
			PageHelper.startPage(curPage, rows);
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("userType", userType);
			
			Integer status = zaAdminUserVo.getStatus();
			if(userType==1) {
				if(status==null ) {
					map.put("status", 2);  //查询删除状态
				}else {
					map.put("status", status); 
				}
			}else if(userType==2) {
				if(status==null ) {
					map.put("status", 3);  //查询删除状态
				}else {
					map.put("status", status);
				}
			}else if(userType==3) {
				if(status==null ) {
					map.put("status", 3);  //查询删除状态
				}else {
					map.put("status", status);
				}
			}
			
			String userAccount = zaAdminUserVo.getUserAccount();
			if(StringUtils.isNotBlank(userAccount)) {
				map.put("name", "%"+userAccount+"%");
			}else {
				map.put("name",null);
			}
			
			// 时间段
			if (null != zaAdminUserVo.getStartTime() && null != zaAdminUserVo.getEndTime()) {
				map.put("startTime", DateUtile.pushDays(zaAdminUserVo.getStartTime(), 0));
				map.put("endTime", DateUtile.pushDays(zaAdminUserVo.getEndTime(), 0));
			} else {
				map.put("startTime", null);
				map.put("endTime", null);
			}
			
			List<ZaAdminUserVo> ZaAdminUserVoList = zaAdminUserMappperVo.getDeletedUserList(map);
			PageInfo<ZaAdminUserVo> pageInfo = new PageInfo<ZaAdminUserVo>(ZaAdminUserVoList);
			result = AppMsgResult.result(200, "success", pageInfo);
			
			
		}else {
			return AppMsgResult.result(543, "用户类型参数值有误！", null);
		}
		
		return result;
	}

	
	@Override
	public AppMsgResult forceDelOrRecoveryById(String id, String type, Integer status, Integer userType, String userId,
			String token) {
		// 校验登录
		AppMsgResult msgResult = null;
		msgResult = validateAdminLogin(userId,token);
		if(200 != (int)msgResult.getFlag()){
//			return  msgResult;
		}
		
		AppMsgResult result=null;
		if(userType==null) {
			return AppMsgResult.result(543, "用户类型必须传值！", null);
		}
		if(userType==1 || userType==2 || userType==3) {
			if("forceDel".equals(type)) {
				//强制删除
				//会员用户
				if(userType==1) {
					ZaUser user = userMapper.selectByPrimaryKey(id);
					if(user!=null) {
						//删除用户表以及三方登录信息
						zaAdminUserMappperVo.forceDelUserById(id,userType);
					}else {
						return AppMsgResult.result(546,"用户数据没有找到！", null);
					}
					
				//律师用户
				}else if(userType==2) {
					ZaLawyerAuthentication lawyerAuthentication = lawyerAuthenticationMapper.selectByPrimaryKey(id);
					if(lawyerAuthentication!=null) {
						//删除用户表以及三方登录信息
						zaAdminUserMappperVo.forceDelUserById(id,userType);
						
					}else {
						return AppMsgResult.result(546,"用户数据没有找到！", null);
					}
				//企业用户
				}else if(userType==3) {
					ZaComAuthentication comAuthentication = comAuthenticationMapper.selectByPrimaryKey(id);
					if(comAuthentication!=null) {
						//删除用户表以及三方登录信息
						zaAdminUserMappperVo.forceDelUserById(id,userType);
					}else {
						return AppMsgResult.result(546,"用户数据没有找到！", null);
					}
				}
				
				result = AppMsgResult.result(200, "success", null);
				
			}else if("recovery".equals(type)) {
				//恢复
				if(status==null) {
					return AppMsgResult.result(543, "请填写需要恢复的状态！", null);
				}
				if(userType==1) {
					if(status==0|| status==1) {
						ZaUser zaUser = new ZaUser();
						zaUser.setId(id);
						zaUser.setStatus(status);
						zaUser.setUpdatedTime(new Date());
						zaUser.setOperator(userId);
						int i = userMapper.updateByPrimaryKeySelective(zaUser);
						if(i==1) {
							result = AppMsgResult.result(200, "success", null);
						}else {
							result = AppMsgResult.result(500, "操作失败！", null);
						}
					}else {
						return AppMsgResult.result(543, "选择的恢复状态错误！", null);
					}
					
				}else if(userType==2) {
					if(status==0|| status==1 || status==2 || status==4) {
						ZaLawyerAuthentication zaLawyerAuthentication = new ZaLawyerAuthentication();
						zaLawyerAuthentication.setId(id);
						zaLawyerAuthentication.setLawStatus(status);
						zaLawyerAuthentication.setUpdatedTime(new Date());
						zaLawyerAuthentication.setOperator(userId);
						int i = lawyerAuthenticationMapper.updateByPrimaryKeySelective(zaLawyerAuthentication);
						if(i==1) {
							result = AppMsgResult.result(200, "success", null);
						}else {
							result = AppMsgResult.result(500, "操作失败！", null);
						}
						
					}else {
						return AppMsgResult.result(543, "选择的恢复状态错误！", null);
					}
					
				}else if(userType==3) {
					if(status==0|| status==1 || status==2 || status==4) {
							ZaComAuthentication zaComAuthentication = new ZaComAuthentication();
							zaComAuthentication.setId(id);
							zaComAuthentication.setComStatus(status);
							zaComAuthentication.setUpdatedTime(new Date());
							zaComAuthentication.setOperator(userId);
							int i = comAuthenticationMapper.updateByPrimaryKeySelective(zaComAuthentication);
							if(i==1) {
								result = AppMsgResult.result(200, "success", null);
							}else {
								result = AppMsgResult.result(500, "操作失败！", null);
							}
						
					}else {
						return AppMsgResult.result(543, "选择的恢复状态错误！", null);
					}
					
				}
				
			}else {
				return AppMsgResult.result(543, "type类型参数值有误！", null);
			}
			
		}else {
			return AppMsgResult.result(543, "用户类型参数值有误！", null);
		}
			return result;
	}
}

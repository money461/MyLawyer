package com.tz.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tz.cache.RedisCache;
import com.tz.date.DateUtile;
import com.tz.id.IDUtils;
import com.tz.mapper.vo.ZaPlatManagerMapperVo;
import com.tz.mapper.vo.ZaPlatManagerRoleMapperVo;
import com.tz.pojo.ZaPlatManager;
import com.tz.pojo.ZaPlatManagerRole;
import com.tz.pojo.vo.ZaPlatManagerVo;
import com.tz.res.AppMsgResult;
import com.tz.service.PlatManagerService;
import com.tz.validate.ValidateUtil;

@Service
public class PlatManagerServiceImpl implements PlatManagerService {

	@Autowired
	private RedisCache cache;
	
	@Autowired
	private ZaPlatManagerMapperVo zaPlatManagerMapperVo; 
	
	@Autowired
	private ZaPlatManagerRoleMapperVo zaPlatManagerRoleMapperVo;
	
	//校验useruserToken是否过期存在
		public AppMsgResult checkUserToken(String userId,String userToken){
			AppMsgResult msgResult = null; 
				if(StringUtils.isNotEmpty(userToken)){
					//获取用户的登录 token
					String cache_key=RedisCache.CAHCENAME+"|getPlatManagerToken|"+userId;
					String userToken_cache = cache.getCache(cache_key, String.class);
					//是否存在
					if(null != userToken_cache && userToken.equals(userToken_cache)){
						//重置userToken有效时间
						cache.putCacheWithExpireTime(cache_key, userToken_cache, RedisCache.USERCAHCETIME);
						msgResult= AppMsgResult.nodata(true,"success");
					}else{
						msgResult= AppMsgResult.nodata(true, "failure");
					}
				}else{
				msgResult= AppMsgResult.nodata(false, "failure");
			}
			return msgResult;
			
		}
	

	/**
	 * 添加或者更新管理员信息
	 */
	@Override
	public AppMsgResult addOrUpdatePlatManager(ZaPlatManager platManager, String roleIds, String type, String userId, String token) {
		AppMsgResult result=null;
		//判断用户是否登录
		AppMsgResult msgResult = checkUserToken(userId, token);
		if((boolean)msgResult.getFlag()) {
			Date date = new Date();
			if("add".equalsIgnoreCase(type)) {
				//添加操作
				String managerAccount = platManager.getManagerAccount();
				if(StringUtils.isNotBlank(managerAccount)) {
					//校验账号是否重复
					Integer k = zaPlatManagerMapperVo.checkPlatManagerAccount(managerAccount);
					if(k==0) {
						String managerPassword = platManager.getManagerPassword();
						 if(ValidateUtil.passWordValidate(managerPassword)) {
							 if(StringUtils.isNotEmpty(roleIds)) {
								 String managerPhone = platManager.getManagerPhone();
								 System.out.println("---------------------------"+managerPhone);
								 if(ValidateUtil.phoneValidate(platManager.getManagerPhone())) {
									  //创建管理员对象
									 String id = IDUtils.genId();
									 platManager.setId(id);
									 String code = IDUtils.getCode();
									 platManager.setCode(code);
									 platManager.setManagerPassword(IDUtils.md5(managerPassword + code));
									 platManager.setUpdatedTime(date);
									 platManager.setCreatedTime(date);
									 platManager.setStatus(1);//默认正常状态
									 
									 //添加角色
									 String[] roleIdStr = roleIds.split(",");
										List<ZaPlatManagerRole> managerRoleList = new ArrayList<ZaPlatManagerRole>();
										for (String roleId : roleIdStr) {
											ZaPlatManagerRole zaPlatManagerRole = new ZaPlatManagerRole();
											zaPlatManagerRole.setId(IDUtils.genId());
											zaPlatManagerRole.setManagerId(id);
											zaPlatManagerRole.setRoleId(roleId);
											
											//获取当前登录用户
											zaPlatManagerRole.setOperator(userId);
											managerRoleList.add(zaPlatManagerRole);
										}
											//批量保存管理员与角色信息
											zaPlatManagerRoleMapperVo.batchInsertManagerRoleData(managerRoleList);
											//插入管理员信息
											int i = zaPlatManagerMapperVo.insertSelective(platManager);
											if(i==1) {
												result = AppMsgResult.result(200, "success", null);
											}else {
												result = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
											}
									 
								 }else {
									 return AppMsgResult.result(523, "手机号码格式错误！", null);
								 }
							 }else {
								 return AppMsgResult.result(543, "请为该账户设置至少一个角色吧！", null);
							 }
							 
						 }else {
							 return AppMsgResult.result(521, "密码必须为6-19位数字、字母组合！", null);
						 }
						
					}else {
						result = AppMsgResult.result(543, "该账户已存在,请更换新的账户！",null);
					}
				}else {
					result = AppMsgResult.result(543, "用户账户必传值！",null);
				}
				
			}else if("update".equalsIgnoreCase(type)) {
				String id = platManager.getId();
				if(StringUtils.isNotBlank(id)) {
					//更新操作
					Integer status = platManager.getStatus();
					if(status!=null && status!=1 && status!=0) {
						return AppMsgResult.result(543, "角色状态传输有误！",null);
					}
					String managerAccount = platManager.getManagerAccount();
					if(StringUtils.isNotEmpty(managerAccount)) {
						//去数据库校验账户前后是否变化
						ZaPlatManagerVo platManagerVo = zaPlatManagerMapperVo.queryPlatManagerById(id);
						if(platManagerVo==null) {
							return AppMsgResult.result(558, "修改的账户没找到！", null);
						}
						if(managerAccount.equals(platManagerVo.getManagerAccount())) {
							//没有变化不做修改
							platManager.setManagerAccount(null);
						}else {
							//修改变化了，判断是否已存在
							Integer k = zaPlatManagerMapperVo.checkPlatManagerAccount(managerAccount);
							if(k!=0) {
								return AppMsgResult.result(543, "该账户已存在,请更换新的账户！",null);
							}
						}
						
					}else {
						platManager.setManagerAccount(null);
					}
					
					String managerPassword = platManager.getManagerPassword();
					if(StringUtils.isNotEmpty(managerPassword)) {
						String code = IDUtils.getCode();
						platManager.setCode(code);
						platManager.setManagerPassword(IDUtils.md5(managerPassword+code));
					}else {
						platManager.setManagerPassword(null);
					}
					platManager.setOperator(userId);
					platManager.setUpdatedTime(date);
					
					
					if(StringUtils.isNotEmpty(roleIds)){
						//删除中间表该用户的角色信息
						zaPlatManagerRoleMapperVo.deleteManagerRoleByManagerId(id);
						//将角色重新写入中间表
						//添加角色
						 String[] roleIdStr = roleIds.split(",");
							List<ZaPlatManagerRole> managerRoleList = new ArrayList<ZaPlatManagerRole>();
							for (String roleId : roleIdStr) {
								ZaPlatManagerRole zaPlatManagerRole = new ZaPlatManagerRole();
								zaPlatManagerRole.setId(IDUtils.genId());
								zaPlatManagerRole.setManagerId(id);
								zaPlatManagerRole.setRoleId(roleId);
								
								//获取当前登录用户
								zaPlatManagerRole.setOperator(userId);
								managerRoleList.add(zaPlatManagerRole);
							}
								//批量保存管理员与角色信息
								zaPlatManagerRoleMapperVo.batchInsertManagerRoleData(managerRoleList);
					}
					
					//执行更新操作
					Integer i = zaPlatManagerMapperVo.updateSelectiveById(platManager);
					if (i == 1) {
						result = AppMsgResult.result(200, "success", null);
					} else {
						result = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
					}
					
				}else {
					result = AppMsgResult.result(543, "账户id必须传值",null);
				}
			}else {
				result = AppMsgResult.result(543, "type参数传输有误！",null);
			}
			
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		
		return result;
	}

	/**
	 * 分页查询管理员信息
	 */
	@Override
	public AppMsgResult getPlatManagerList(ZaPlatManagerVo zaPlatManagerVo, Integer curPage, Integer rows) {
		AppMsgResult result=null;
		String id = zaPlatManagerVo.getId();
		if(StringUtils.isNotBlank(id)) {
			String token = zaPlatManagerVo.getToken();
			if(StringUtils.isNotBlank(token)) {
				//判断用户是否登录
				AppMsgResult msgResult = checkUserToken(id, token);
				if((boolean)msgResult.getFlag()) {
					rows = rows == null?10:rows;
					curPage = curPage == null?1:curPage;
					//分页处理
			        PageHelper.startPage(curPage, rows);
			        
			        //封装map集合
			        Map<String,Object> map = new HashMap<String, Object>();
			        
			        Integer status = zaPlatManagerVo.getStatus();
			        
			        map.put("status", status);
			        
			        String managerAccount = zaPlatManagerVo.getManagerAccount();
			        if(StringUtils.isNotBlank(managerAccount)){
			        	map.put("name", "%"+managerAccount+"%");
			        }else{
			        	map.put("name", null);
			        }
			        
			        String phone = zaPlatManagerVo.getManagerPhone();
			        if(StringUtils.isNotBlank(phone)){
			        	map.put("phone", "%"+phone+"%");
			        }else{
			        	map.put("phone", null);
			        }
			    	
			        //时间段
			      	if(null != zaPlatManagerVo.getStartTime() && null != zaPlatManagerVo.getEndTime()){
			      		map.put("startTime",DateUtile.pushDays(zaPlatManagerVo.getStartTime(),0) );
			      		map.put("endTime", DateUtile.pushDays(zaPlatManagerVo.getEndTime(),0));
			      	}else{
			      		map.put("startTime",null);
			      		map.put("endTime",null);
			      	}
			      	 //调用mapper分页查询管理员
			        List<ZaPlatManagerVo> ManagerList = zaPlatManagerMapperVo.selectPlatManagerList(map);
			        
			        //获取分页数据结果
			        PageInfo<ZaPlatManagerVo> pageInfo = new PageInfo<ZaPlatManagerVo>(ManagerList);
			        
			        //返回结果
			         result = AppMsgResult.result(200, "success", pageInfo);
					
				}else {
					result = AppMsgResult.result(538, "用户未登录！",null);
				}
				
			}else {
				result= AppMsgResult.result(529, "用户token必传值！", null);
			}
		}else {
			result= AppMsgResult.result(529, "用户id必传值！", null);
		}
		
		return result;
	}

	/**
	 * 通过管理员id查询管理员信息
	 */
	@Override
	public AppMsgResult queryPlatManagerById(String id, String userId, String token) {
		AppMsgResult result=null;
		//判断用户是否登录
		AppMsgResult msgResult = checkUserToken(userId, token);
		if((boolean)msgResult.getFlag()) {
			ZaPlatManagerVo platManagerVo = zaPlatManagerMapperVo.queryPlatManagerById(id);
			result = AppMsgResult.result(200, "success", platManagerVo);
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		return result;
	}


	/**
	 * 通过管理员id删除管理员
	 */
	@Override
	public AppMsgResult delManagerById(String id, String userId, String token) {
		AppMsgResult result=null;
		//判断用户是否登录
		AppMsgResult msgResult = checkUserToken(userId, token);
		if((boolean)msgResult.getFlag()) {
			//删除该角色中间表信息
			ZaPlatManagerVo managerVo = zaPlatManagerMapperVo.queryPlatManagerById(id);
			if(managerVo!=null) {
				zaPlatManagerRoleMapperVo.deleteManagerRoleByManagerId(id);
				zaPlatManagerMapperVo.deletePlatManagerById(id);
				result = AppMsgResult.result(200, "删除成功！",null);
			}else {
				result = AppMsgResult.result(456, "用户未找到！",null);
			}
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		return result;
	}


	/**
	 * 冻解账户
	 */
	@Override
	public AppMsgResult accountFreezeById(String id, Integer status, String userId, String token) {
		AppMsgResult result=null;
		//判断用户是否登录
		AppMsgResult msgResult = checkUserToken(userId, token);
		if((boolean)msgResult.getFlag()) {
			if(status==null ||status!=0 && status!=1) {
				return AppMsgResult.result(543, "账户状态传值有误！",null);
			}
			ZaPlatManager platManager = new ZaPlatManager();
			platManager.setId(id);
			platManager.setStatus(status);
			platManager.setUpdatedTime(new Date());
			platManager.setOperator(userId);
			Integer k = zaPlatManagerMapperVo.updateSelectiveById(platManager);
			if (k == 1) {
				result = AppMsgResult.result(200, "success", null);
			} else {
				result = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
			}
			
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		return result;
	}
	
	
	
	

}

package com.tz.service.impl;

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
import com.tz.mapper.vo.ZaPlatAuthorityMapperVo;
import com.tz.mapper.vo.ZaPlatRoleAuthorityMapperVo;
import com.tz.pojo.ZaPlatAuthority;
import com.tz.pojo.admin.ZaPlatAuthorityVo;
import com.tz.pojo.vo.ZaPlatRoleVo;
import com.tz.res.AppMsgResult;
import com.tz.service.PlatAuthorityService;
@Service
public class PlatAuthorityServiceImpl implements PlatAuthorityService {
	
	@Autowired
	private RedisCache cache;
	
	@Autowired
	private ZaPlatAuthorityMapperVo zaPlatAuthorityMapperVo;
	
	@Autowired
	private ZaPlatRoleAuthorityMapperVo zaPlatRoleAuthorityMapperVo;
	
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

		//查询所有权限信息
		@Override
		public AppMsgResult selectPlatAuthUsable(Integer status, String userId, String token) {
			AppMsgResult result=null;
			//判断用户是否登录
			AppMsgResult msgResult = checkUserToken(userId, token);
			if((boolean)msgResult.getFlag()) {
				if(status!=null && status!=0 && status!=1) {
					return AppMsgResult.result(556, "status参数值有误！", null);
				}
				List<ZaPlatAuthority> platAuthorityList =  zaPlatAuthorityMapperVo.selectPlatAuthUsable(status);
				result = AppMsgResult.result(200, "success", platAuthorityList);
			}else {
				result = AppMsgResult.result(538, "用户未登录！",null);
			}
			return result;
		}


		/**
		 * 添加或者修改权限
		 */
		@Override
		public AppMsgResult addOrUpdateAuth(ZaPlatAuthority zaPlatAuthority, String type, String userId, String token) {
			AppMsgResult result=null;
			//判断用户是否登录
			AppMsgResult msgResult = checkUserToken(userId, token);
			if((boolean)msgResult.getFlag()) {
				Date date = new Date();
				if("add".equalsIgnoreCase(type)) {
					//添加权限
					String pid = zaPlatAuthority.getpId();
					if(StringUtils.isNotBlank(pid)) {
						String authorityName = zaPlatAuthority.getAuthorityName();
						if(StringUtils.isNotBlank(authorityName)) {
							String description = zaPlatAuthority.getDescription();
							if(StringUtils.isNotBlank(description)) {
								if(StringUtils.isNotBlank(zaPlatAuthority.getModule())) {
									String code = zaPlatAuthority.getCode();
									if(StringUtils.isNotBlank(code)) {
										//校验code是否重复
										Integer k = zaPlatAuthorityMapperVo.checkPlatAuthorityByCode(code);
										if(k==0) {
											//创建权限主键
											String id = IDUtils.genId();
											zaPlatAuthority.setId(id);
											zaPlatAuthority.setCreatedTime(date);
											zaPlatAuthority.setUpdatedTime(date);
											zaPlatAuthority.setOperator(userId);
											Integer i = zaPlatAuthorityMapperVo.insertPlatAuthSelective(zaPlatAuthority);
											if(i==1) {
												result = AppMsgResult.result(200, "success", null);
											}else {
												result = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
											}
											
										}else {
											result = AppMsgResult.result(543, "权限code关键字已经存在,请重新编写！",null);
										}
									}else {
										result = AppMsgResult.result(543, "请填写权限code关键字！",null);
									}
								}else {
									result = AppMsgResult.result(543, "请填写权限作用模块！",null);
								}
								
							}else {
								result = AppMsgResult.result(543, "请填写权限描述！",null);
							}
						}else {
							result = AppMsgResult.result(543, "请填写权限名称！",null);
						}
						
					}else {
						result = AppMsgResult.result(543, "请选择一个父节点！",null);
					}
					
				}else if("update".equalsIgnoreCase(type)) {
				   //更新权限
					Integer status = zaPlatAuthority.getStatus();
					if(status!=null && status!=1 && status!=0) {
						return AppMsgResult.result(543, "角色状态传输有误！",null);
					}
					String id = zaPlatAuthority.getId();
					if(StringUtils.isNotBlank(id)) {
						String code = zaPlatAuthority.getCode();
						if(StringUtils.isNotBlank(code)) {
							ZaPlatAuthority platAuthority = zaPlatAuthorityMapperVo.queryAuthorityById(id);
							if(platAuthority==null) {
								return AppMsgResult.result(558, "权限未找到！", null);
							}
							if(code.equals(platAuthority.getCode())) {
								//不做修改
								zaPlatAuthority.setCode(null);
							}else {
								Integer k = zaPlatAuthorityMapperVo.checkPlatAuthorityByCode(code);
								if(k!=0) {
									return AppMsgResult.result(543, "权限code关键字已经存在,请重新编写！",null);
								}
							}
						    
						}else {
							zaPlatAuthority.setCode(null);
						}
						zaPlatAuthority.setUpdatedTime(date);
						zaPlatAuthority.setOperator(userId);
						
						//执行更新
						Integer k = zaPlatAuthorityMapperVo.updateAuthSelective(zaPlatAuthority);
						if(k==1) {
							result= AppMsgResult.result(200, "success", null);
						}else {
							result = AppMsgResult.result(5000, "服务器繁忙，请稍后请求", null);
						}
					
					}else {
						result = AppMsgResult.result(543, "更新必须传递权限主键id！",null);
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
		 * 权限列表信息
		 */
		@Override
		public AppMsgResult getPlatAuthorityList(ZaPlatAuthorityVo zaPlatAuthorityVo, Integer curPage, Integer rows,
				String userId, String token) {
			AppMsgResult result=null;
			//判断用户是否登录
			AppMsgResult msgResult = checkUserToken(userId, token);
			if((boolean)msgResult.getFlag()) {
				rows = rows == null?10:rows;
				curPage = curPage == null?1:curPage;
				//分页处理
		        PageHelper.startPage(curPage, rows);
		        
		        //封装map集合
		        Map<String,Object> map = new HashMap<String, Object>();
		        
		        Integer status = zaPlatAuthorityVo.getStatus();
		        
		        map.put("status", status);
		        String id = zaPlatAuthorityVo.getId();
		        if(id==null) {
		        	map.put("pId", 0);
		        }else {
		        	map.put("pId", id);
		        }
		        
		        
		        String code = zaPlatAuthorityVo.getCode();
		        if(StringUtils.isNotBlank(code)) {
		        	map.put("code", "%"+code+"%");
		        }else {
		        	map.put("code", null);
		        }
		        
		        String AuthName = zaPlatAuthorityVo.getAuthorityName();
		        if(StringUtils.isNoneBlank(AuthName)){
		        	map.put("name", "%"+AuthName+"%");
		        }else{
		        	map.put("name", null);
		        }
		        
		        //时间段
		      	if(null != zaPlatAuthorityVo.getStartTime() && null != zaPlatAuthorityVo.getEndTime()){
		      		map.put("startTime",DateUtile.pushDays(zaPlatAuthorityVo.getStartTime(),0) );
		      		map.put("endTime", DateUtile.pushDays(zaPlatAuthorityVo.getEndTime(),0));
		      	}else{
		      		map.put("startTime",null);
		      		map.put("endTime",null);
		      	}
		      	
		      List<ZaPlatAuthority> platAuthList = zaPlatAuthorityMapperVo.selectPlatAuthorityList(map);
		    //获取分页数据结果
			  PageInfo<ZaPlatAuthority> pageInfo = new PageInfo<ZaPlatAuthority>(platAuthList);
			     //返回结果
			  result = AppMsgResult.result(true, "", pageInfo);
			}else {
				result = AppMsgResult.result(538, "用户未登录！",null);
			}
			return result;
		}

		
		/**
		 * 查询权限
		 */
		@Override
		public AppMsgResult getPlatAuthorityById(String id, String userId, String token) {
			AppMsgResult result=null;
			//判断用户是否登录
			AppMsgResult msgResult = checkUserToken(userId, token);
			if((boolean)msgResult.getFlag()) {
				ZaPlatAuthority zaPlatAuthority = zaPlatAuthorityMapperVo.queryAuthorityById(id);
				result = AppMsgResult.result(200, "success", zaPlatAuthority);
			}else {
				result = AppMsgResult.result(538, "用户未登录！",null);
			}
			return result;
		}

		/**
		 * 删除权限
		 */
		@Override
		public AppMsgResult delPlatAuthorityById(String id, String userId, String token) {
			AppMsgResult result=null;
			//判断用户是否登录
			AppMsgResult msgResult = checkUserToken(userId, token);
			if((boolean)msgResult.getFlag()) {
				ZaPlatAuthority queryAuthority = zaPlatAuthorityMapperVo.queryAuthorityById(id);
				if(queryAuthority!=null) {
					//检测是权限否存在子节点权限
					List<ZaPlatAuthority> platAuthList =  zaPlatAuthorityMapperVo.querySonAuthorityByPid(id);
					if(platAuthList.size()>0) {
						return AppMsgResult.result(568, "该权限存在子节点，不能直接删除", null);
					}
					//删除角色权限中间表数据
					zaPlatRoleAuthorityMapperVo.delPlatRoleAuthByAuthId(id);
					//删除权限
					zaPlatAuthorityMapperVo.delPlatAuthorityById(id);
					result= AppMsgResult.result(200, "success", null);
				}else {
					result = AppMsgResult.result(556, "权限未找到！",null);
				}
				
			}else {
				result = AppMsgResult.result(538, "用户未登录！",null);
			}
			return result;
		}

		/**
		 * 冻结权限
		 */
		@Override
		public AppMsgResult authorityFreezeById(String id, Integer status, String userId, String token) {
			AppMsgResult result=null;
			//判断用户是否登录
			AppMsgResult msgResult = checkUserToken(userId, token);
			if((boolean)msgResult.getFlag()) {
				if(status==null || status!=0 && status!=1) {
					return AppMsgResult.result(538, "权限状态参数传输有误！",null);
				}
				ZaPlatAuthority zaPlatAuthority = new ZaPlatAuthority(); 
				zaPlatAuthority.setId(id);
				zaPlatAuthority.setStatus(status);
				zaPlatAuthority.setOperator(userId);
				zaPlatAuthority.setUpdatedTime(new Date());
				Integer i = zaPlatAuthorityMapperVo.updateAuthSelective(zaPlatAuthority);
				if (i == 1) {
					result = AppMsgResult.result(200, "success", null);
				} else {
					result = AppMsgResult.result(5000, "操作失败！", null);
				}
			}else {
				result = AppMsgResult.result(538, "用户未登录！",null);
			}
			return result;
		}

		@Override
		public List<ZaPlatAuthority> getAllAuthorityByManagerId(String userId) {
			// TODO Auto-generated method stub
			return zaPlatAuthorityMapperVo.queryAllAuthorityByManagerId(userId);
		}
}

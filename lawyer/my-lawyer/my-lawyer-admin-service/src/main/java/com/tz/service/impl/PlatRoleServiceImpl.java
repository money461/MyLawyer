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
import com.tz.mapper.vo.ZaPlatManagerRoleMapperVo;
import com.tz.mapper.vo.ZaPlatRoleAuthorityMapperVo;
import com.tz.mapper.vo.ZaPlatRoleMapperVo;
import com.tz.pojo.ZaPlatRole;
import com.tz.pojo.ZaPlatRoleAuthority;
import com.tz.pojo.vo.ZaPlatRoleVo;
import com.tz.res.AppMsgResult;
import com.tz.service.PlatRoleService;
@Service
public class PlatRoleServiceImpl implements PlatRoleService {

	@Autowired
	private RedisCache cache;
	
	@Autowired
	private ZaPlatRoleMapperVo zaPlatRoleMapperVo;
	
	@Autowired
	private ZaPlatRoleAuthorityMapperVo zaPlatRoleAuthorityMapperVo; 
	
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
	 * 添加或者更新角色信息
	 */
	@Override
	public AppMsgResult addOrUpdaterPlatRole(ZaPlatRole zaPlatRole, String authIds, String type, String userId,
			String token) {
		AppMsgResult result=null;
		//判断用户是否登录
		AppMsgResult msgResult = checkUserToken(userId, token);
		if((boolean)msgResult.getFlag()) {
			Date date = new Date();
			if("add".equalsIgnoreCase(type)) {
				//添加角色
				String pId = zaPlatRole.getpId();
				if(StringUtils.isNotBlank(pId)) {
					String roleName = zaPlatRole.getRoleName();
					if(StringUtils.isNotBlank(roleName)) {
						String code = zaPlatRole.getCode();
						if(StringUtils.isNotBlank(code)) {
							//检验code是否唯一
							Integer k = zaPlatRoleMapperVo.checkRoleCodeByCode(code);
							if(k==0) {
								String description = zaPlatRole.getDescription();
								if(StringUtils.isNotBlank(description)) {
									if(StringUtils.isNotBlank(authIds)) {
										String id = IDUtils.genId();//角色主键id
										//将该角色插入表中
										zaPlatRole.setId(id);
										zaPlatRole.setCreatedTime(date);
										zaPlatRole.setUpdatedTime(date);
										zaPlatRole.setOperator(userId);
										Integer i = zaPlatRoleMapperVo.insertPlatRoleSelective(zaPlatRole);
										if(i==1) {
											//将该角色的权限插入中间表中
											String[] authIdStr = authIds.split(",");
											List<ZaPlatRoleAuthority> platRoleAuthList = new ArrayList<ZaPlatRoleAuthority>();
											for (String auId : authIdStr) {
												//创建角色
												ZaPlatRoleAuthority roleAuthority = new ZaPlatRoleAuthority();
												roleAuthority.setId(IDUtils.genId());
												roleAuthority.setAuthorityId(auId);
												roleAuthority.setRoleId(id);
												roleAuthority.setOperator(userId);
												platRoleAuthList.add(roleAuthority);
											}
											//批量插入角色权限数据信息
											zaPlatRoleAuthorityMapperVo.batchInsertRoleAuthData(platRoleAuthList);
											
											result = AppMsgResult.result(200, "success", null);
										}else {
											result = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
										}
										
									}else {
										result = AppMsgResult.result(543, "请给该角色赋予至少一个权限！",null);
									}
									
								}else {
									result = AppMsgResult.result(543, "请给该角色描述一下吧！",null);
								}
								
							}else {
								result = AppMsgResult.result(543, "角色code已存在,需保证唯一！",null);
							}
							
						}else {
							result = AppMsgResult.result(543, "角色code必须传值！",null);
						}
					}else {
						result = AppMsgResult.result(543, "角色名称必须传值！",null);
					}
				}else {
					result = AppMsgResult.result(543, "请选择一个父节点！",null);
				}
			}else if("update".equalsIgnoreCase(type)) {
				String id = zaPlatRole.getId();
				if(StringUtils.isNotBlank(id)) {
					//更新角色
					String code = zaPlatRole.getCode();
					if(StringUtils.isNotBlank(code)) {
						//查询该code是否已被修改
						ZaPlatRoleVo platRole =  zaPlatRoleMapperVo.queryPlatRoleById(id);
						if(platRole==null) {
							return AppMsgResult.result(558, "角色未找到！", null);
						}
						if(code.equals(platRole.getCode())) {
							zaPlatRole.setCode(null);
						}else {
							//校验是否重复
							Integer k = zaPlatRoleMapperVo.checkRoleCodeByCode(code);
							if(k!=0) {
								return  AppMsgResult.result(543, "code已存在，不能存在相同code！",null);
							}
						}
						
					}else {
						zaPlatRole.setCode(null);
					}
					Integer status = zaPlatRole.getStatus();
					if(status!=null && status!=1 && status!=0) {
						return AppMsgResult.result(543, "角色状态传输有误！",null);
					}
					//判断是否修改角色权限中间表
					if(StringUtils.isNotBlank(authIds)) {
						//删除中间表数据
						zaPlatRoleAuthorityMapperVo.delPlatRoleAuthByRoleId(id);
						//重新写入
						//将该角色的权限插入中间表中
						String[] authIdStr = authIds.split(",");
						List<ZaPlatRoleAuthority> platRoleAuthList = new ArrayList<ZaPlatRoleAuthority>();
						for (String auId : authIdStr) {
							//创建角色
							ZaPlatRoleAuthority roleAuthority = new ZaPlatRoleAuthority();
							roleAuthority.setId(IDUtils.genId());
							roleAuthority.setAuthorityId(auId);
							roleAuthority.setRoleId(id);
							roleAuthority.setOperator(userId);
							platRoleAuthList.add(roleAuthority);
						}
						//批量插入角色权限数据信息
						zaPlatRoleAuthorityMapperVo.batchInsertRoleAuthData(platRoleAuthList);
					}
					
					//执行角色修改
					zaPlatRole.setUpdatedTime(date);
					zaPlatRole.setOperator(userId);
					Integer i = zaPlatRoleMapperVo.updateSelectiveById(zaPlatRole);
					if(i==1) {
						result = AppMsgResult.result(200, "success", null);
					}else {
						result = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
					}
					
					
				}else {
					result = AppMsgResult.result(543, "修改角色id必须传！",null);
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
	 * 查询所有未被冻结的角色信息
	 */
	@Override
	public AppMsgResult selectPlatRoleUsable(Integer status,String userId, String token) {
		AppMsgResult result=null;
		//判断用户是否登录
		AppMsgResult msgResult = checkUserToken(userId, token);
		if((boolean)msgResult.getFlag()) {
			if(status!=null && status!=0 && status!=1) {
				return AppMsgResult.result(556, "status参数值有误！", null);
			}
			List<ZaPlatRole> platRoleList=zaPlatRoleMapperVo.selectPlatRoleUsable(status);
			result = AppMsgResult.result(200, "success", platRoleList);
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		return result;
	}
	
	
	/**
	 * 根据id查询角色信息
	 * @param id
	 * @param userId
	 * @param token
	 * @return
	 */
	@Override
	public AppMsgResult queryPlatRoleById(String id,String userId,String token) {
		AppMsgResult result=null;
		//判断用户是否登录
		AppMsgResult msgResult = checkUserToken(userId, token);
		if((boolean)msgResult.getFlag()) {
			ZaPlatRoleVo zaPlatRole =  zaPlatRoleMapperVo.queryPlatRoleById(id);
			result=AppMsgResult.result(200, "success", zaPlatRole);
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		return result;
	}

	/**
	 * 获取角色列表信息
	 */
	@Override
	public AppMsgResult getPlatRoleList(ZaPlatRoleVo zaPlatRoleVo, Integer curPage, Integer rows, String userId,
			String token) {
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
	        String id = zaPlatRoleVo.getId();
	        if(id==null) {
	        	map.put("pId", 0);
	        }else {
	        	map.put("pId",id);
	        }
	        
	        
	        Integer status = zaPlatRoleVo.getStatus();
	        map.put("status", status);
	        
	        String RoleName = zaPlatRoleVo.getRoleName();
	        if(StringUtils.isNoneBlank(RoleName)){
	        	map.put("name", "%"+RoleName+"%");
	        }else{
	        	map.put("name", null);
	        }
	        
	        //时间段
	      	if(null != zaPlatRoleVo.getStartTime() && null != zaPlatRoleVo.getEndTime()){
	      		map.put("startTime",DateUtile.pushDays(zaPlatRoleVo.getStartTime(),0) );
	      		map.put("endTime", DateUtile.pushDays(zaPlatRoleVo.getEndTime(),0));
	      	}else{
	      		map.put("startTime",null);
	      		map.put("endTime",null);
	      	}
	      	
	      List<ZaPlatRoleVo> platRoleList = zaPlatRoleMapperVo.selectPlatRoleList(map);
	    //获取分页数据结果
		  PageInfo<ZaPlatRoleVo> pageInfo = new PageInfo<ZaPlatRoleVo>(platRoleList);
		     //返回结果
		  result = AppMsgResult.result(true, "", pageInfo);
			
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		
		return result;
	}

	/**
	 * 删除角色
	 */
	@Override
	public AppMsgResult delPlatRoleById(String id, String userId, String token) {
		AppMsgResult result=null;
		//判断用户是否登录
		AppMsgResult msgResult = checkUserToken(userId, token);
		if((boolean)msgResult.getFlag()) {
			//判断该角色是否有子节点
		    List<ZaPlatRole> platRoleList= zaPlatRoleMapperVo.queryPlatSonRoleByPid(id);
		    if(platRoleList.size()>0) {
		    	return AppMsgResult.result(568, "该角色存在子节点数据，不能直接删除！", null);
		    }
		    
			ZaPlatRoleVo platRoleVo = zaPlatRoleMapperVo.queryPlatRoleById(id);
			if(platRoleVo!=null) {
				//删除角色权限中间表数据
				zaPlatRoleAuthorityMapperVo.delPlatRoleAuthByRoleId(id);
				//删除角色管理员中间表
				zaPlatManagerRoleMapperVo.delPlatManagerRoleByRoleId(id);
				zaPlatRoleMapperVo.delPlatRoleById(id);
				
			   result = AppMsgResult.result(200, "success", null);
			}else {
				result = AppMsgResult.result(456, "角色未找到！",null);
			}
			
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		return result;
	}

	
	/**
	 * 冻解角色
	 */
	@Override
	public AppMsgResult roleFreezeById(String id, Integer status, String userId, String token) {
		AppMsgResult result=null;
		//判断用户是否登录
		AppMsgResult msgResult = checkUserToken(userId, token);
		if((boolean)msgResult.getFlag()) {
			if(status==null || status!=0 && status!=1) {
				return AppMsgResult.result(538, "角色状态参数传输有误！",null);
			}
			ZaPlatRole zaPlatRole = new ZaPlatRole();
			zaPlatRole.setStatus(status);
			zaPlatRole.setId(id);
			zaPlatRole.setUpdatedTime(new Date());
			zaPlatRole.setOperator(userId);
			Integer i = zaPlatRoleMapperVo.updateSelectiveById(zaPlatRole);
			if (i == 1) {
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

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
import com.tz.mapper.vo.ContentCategoryMapperAdminVo;
import com.tz.pojo.ZaContentCategory;
import com.tz.pojo.admin.ZaContentCategoryAdminVo;
import com.tz.res.AppMsgResult;
import com.tz.service.ContentCategoryService;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	@Autowired
	private RedisCache cache;
	
	@Autowired
	private ContentCategoryMapperAdminVo contentCategoryMapperAdminVo;
	
	
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
	 * 清除广告内容缓存
	 */
  public void delContentCategoryCache() {
	  String selectModelCache_key = RedisCache.CAHCENAME+"|getSelectionModel|*";
	  cache.deleteCacheWithPattern(selectModelCache_key);
	  String indexContentCache_key = RedisCache.CAHCENAME+"|getIndexContent|*";
	  cache.deleteCacheWithPattern(indexContentCache_key);
	  String personalCenterCache_key =RedisCache.CAHCENAME+"|showPersonalCenter|*";
	  cache.deleteCacheWithPattern(personalCenterCache_key);
  }
		
	/**
	 * 获取广告内容分类数据
	 */
	@Override
	public AppMsgResult getContentCategoryList(ZaContentCategoryAdminVo zaContentCategoryAdminVo,Integer curPage,Integer rows, String userId,
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
	        
	        Integer status = zaContentCategoryAdminVo.getCategoryStatus();
	        map.put("status", status);
	        
	        String CategoryName = zaContentCategoryAdminVo.getCategoryName();
	        if(StringUtils.isNoneBlank(CategoryName)){
	        	map.put("name", "%"+CategoryName+"%");
	        }else{
	        	map.put("name", null);
	        }
	        
	        Integer id = zaContentCategoryAdminVo.getId();
	        if(id==null) {
	        	map.put("pId",0); //默认查询父节点0的数据
	        }else {
	        	map.put("pId", id); 
	        }
	        
	        //时间段
	      	if(null != zaContentCategoryAdminVo.getStartTime() && null != zaContentCategoryAdminVo.getEndTime()){
	      		map.put("startTime",DateUtile.pushDays(zaContentCategoryAdminVo.getStartTime(),0) );
	      		map.put("endTime", DateUtile.pushDays(zaContentCategoryAdminVo.getEndTime(),0));
	      	}else{
	      		map.put("startTime",null);
	      		map.put("endTime",null);
	      	}
			
	      List<ZaContentCategoryAdminVo> contentCategoryList = contentCategoryMapperAdminVo.selectContentCategoryList(map);
	      //获取分页数据结果
		  PageInfo<ZaContentCategoryAdminVo> pageInfo = new PageInfo<ZaContentCategoryAdminVo>(contentCategoryList);
		     //返回结果
		  result = AppMsgResult.result(200, "", pageInfo);
	      	
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		
		return result;
	}

	/**
	 * 添加或者修改广告内容分类
	 */
	@Override
	public AppMsgResult addOrUpdateContentCategory(ZaContentCategory zaContentCategory,String type, String userId, String token) {
		AppMsgResult result=null;
		//判断用户是否登录
		AppMsgResult msgResult = checkUserToken(userId, token);
		if((boolean)msgResult.getFlag()) {
			Date date = new Date();
			if("add".equalsIgnoreCase(type)) {
				String categoryName = zaContentCategory.getCategoryName();
				if(StringUtils.isNotBlank(categoryName)) {
					Integer categoryStatus = zaContentCategory.getCategoryStatus();
					if(categoryStatus==null || categoryStatus!=1 && categoryStatus!=2 ) {
						Integer sortCode = zaContentCategory.getSortCode();
						if(sortCode!=null) {
							//校验code是否重复
							Integer k = contentCategoryMapperAdminVo.checkContentCategorySortCode(sortCode);
							if(k==0) {
								zaContentCategory.setUpdatedTime(date);
								zaContentCategory.setCreatedTime(date);
								zaContentCategory.setOperator(userId);
								Integer i = contentCategoryMapperAdminVo.insertContentCategorySelective(zaContentCategory);
								if(i==1) {
									//清空广告缓存
									this.delContentCategoryCache();
									result = AppMsgResult.result(200, "success", null);
								}else {
									result = AppMsgResult.result(500, "添加失败，请稍后再试", null);
								}
								
							}else {
								result = AppMsgResult.result(543, "code已存在，请重新输入！",null);
							}
							
						}else {
							result = AppMsgResult.result(543, "内容分类code不用为空！",null);
						}
					}else {
						result = AppMsgResult.result(543, "内容分类状态参数值有误！",null);
					}
				}else {
					result = AppMsgResult.result(543, "内容分类名称不能为空！",null);
				}
				
			}else if("update".equalsIgnoreCase(type)) {
				Integer id = zaContentCategory.getId();
				if(id==null) {
					return AppMsgResult.result(543, "内容分类id不能为空！",null);
				}
				
				Integer sortCode = zaContentCategory.getSortCode();
				if(sortCode!=null) {
					Integer k = contentCategoryMapperAdminVo.checkContentCategorySortCode(sortCode);
					if(k!=0) {
						return AppMsgResult.result(543,"排序code已存在,请重新输入",null);
					}
				}
				
				Integer categoryStatus = zaContentCategory.getCategoryStatus();
				if(categoryStatus!=null && categoryStatus!=1 && categoryStatus!=2) {
					result = AppMsgResult.result(543, "内容分类状态参数值有误！",null);
				}
				zaContentCategory.setUpdatedTime(date);
				zaContentCategory.setOperator(userId);
				Integer k = contentCategoryMapperAdminVo.updateContentCategorySelectvice(zaContentCategory);
				if(k==1) {
					//清空广告缓存
					this.delContentCategoryCache();
					result = AppMsgResult.result(200, "success",null);
				}else {
					result = AppMsgResult.result(500, "操作失败！",null);
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
	 * 根据id查询广告内容
	 */
	@Override
	public AppMsgResult queryContentCategoryById(Integer id, String userId, String token) {
		AppMsgResult result=null;
		//判断用户是否登录
		AppMsgResult msgResult = checkUserToken(userId, token);
		if((boolean)msgResult.getFlag()) {
			ZaContentCategoryAdminVo zaContentCategoryAdminVo = contentCategoryMapperAdminVo.queryContentCategoryById(id);
			result = AppMsgResult.result(200, "success",zaContentCategoryAdminVo);
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		return result;
	}

	/**
	 * 删除内容分类
	 */
	@Override
	public AppMsgResult delContentCategoryById(Integer id, String userId, String token) {
		AppMsgResult result=null;
		//判断用户是否登录
		AppMsgResult msgResult = checkUserToken(userId, token);
		if((boolean)msgResult.getFlag()) {
			ZaContentCategoryAdminVo categoryAdminVo = contentCategoryMapperAdminVo.queryContentCategoryById(id);
			if(categoryAdminVo!=null) {
				 //封装map集合
		        Map<String,Object> map = new HashMap<String, Object>();
		        map.put("pId", id);
				List<ZaContentCategoryAdminVo> contentCategoryList = contentCategoryMapperAdminVo.selectContentCategoryList(map);
				if(contentCategoryList.size()>0) {
					result = AppMsgResult.result(457, "该内容存在子节点数据,不能直接删除！",null);
				}else {
					contentCategoryMapperAdminVo.delContentCategoryById(id);
					//清空缓存
					this.delContentCategoryCache();
					result = AppMsgResult.result(200, "success",null);
				}
			}else {
				result = AppMsgResult.result(456, "该内容未找到,操作失败！",null);
			}
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		return result;
	}

	/**
	 * 停用或者启用内容分类
	 */
	@Override
	public AppMsgResult disOrEnableContentCategoryById(Integer id, Integer status, String userId, String token) {
		AppMsgResult result=null;
		//判断用户是否登录
		AppMsgResult msgResult = checkUserToken(userId, token);
		if((boolean)msgResult.getFlag()) {
			if(status==null || status!=1 && status!=2) {
				return AppMsgResult.result(543, "内容状态错误！",null);
			}
			ZaContentCategory zaContentCategory = new ZaContentCategory();
			zaContentCategory.setId(id);
			zaContentCategory.setCategoryStatus(status);
			zaContentCategory.setOperator(userId);
			zaContentCategory.setUpdatedTime(new Date());
			
			Integer k = contentCategoryMapperAdminVo.updateContentCategorySelectvice(zaContentCategory);
			if(k==1) {
				//清空缓存
				this.delContentCategoryCache();
				result = AppMsgResult.result(200, "success",null);
			}else {
				result = AppMsgResult.result(500, "操作失败！请稍后重试",null);
			}
			
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		return result;
	}
	
	
   /**
    * 查询所有的内容分类
    */
	@Override
	public AppMsgResult selectAllContentCategory(Integer status, String userId, String token) {
		AppMsgResult result=null;
		//判断用户是否登录
		AppMsgResult msgResult = checkUserToken(userId, token);
		if((boolean)msgResult.getFlag()) {
			List<ZaContentCategory> contentCategoryList = contentCategoryMapperAdminVo.selectAllContentCategory(status);
			result = AppMsgResult.result(200, "success！",contentCategoryList);
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		return result;
	}

	
	
}

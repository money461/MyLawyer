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
import com.tz.mapper.vo.ZaContentMapperAdminVo;
import com.tz.pojo.ZaContent;
import com.tz.pojo.admin.ZaContentAdminVo;
import com.tz.res.AppMsgResult;
import com.tz.service.ContentService;
import com.tz.service.UserService;
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private RedisCache cache;
	
	// 注入用户接口类
	@Autowired
	private UserService userService;
	
	@Autowired
	private ZaContentMapperAdminVo zaContentMapperAdminVo;
	
	public void delContentCache() {
		//首页内容缓存
		String indexContentCache_key = RedisCache.CAHCENAME+"|getIndexContent|*";
		//用户个人中心缓存
		String personalCenterCache_key =RedisCache.CAHCENAME+"|showPersonalCenter|*";
		cache.deleteCacheWithPattern(indexContentCache_key);
		cache.deleteCacheWithPattern(personalCenterCache_key);
	}
	
	@Override
	public AppMsgResult getContentList(ZaContentAdminVo zaContentAdminVo,Integer curPage,Integer rows, String userId, String token) {
		//校验是否登录
		AppMsgResult msgResult = userService.validateAdminLogin(userId, token);
		if(200 != (int)msgResult.getFlag()){
//			return  msgResult;
		}
		rows = rows == null?10:rows;
		curPage = curPage == null?1:curPage;
		//分页处理
        PageHelper.startPage(curPage, rows);
        
        //封装map集合
        Map<String,Object> map = new HashMap<String, Object>();
        
        //模糊查询内容标题
        String title = zaContentAdminVo.getTitle();
        if(StringUtils.isNotBlank(title)) {
        	map.put("title", "%"+title+"%");
        }else {
        	map.put("title", null);
        }
       
        //查询内容下的子节点
        map.put("contentCategoryId", zaContentAdminVo.getContentCategoryId());
        
        //时间段
      	if(null != zaContentAdminVo.getStartTime() && null != zaContentAdminVo.getEndTime()){
      		map.put("startTime",DateUtile.pushDays(zaContentAdminVo.getStartTime(),0) );
      		map.put("endTime", DateUtile.pushDays(zaContentAdminVo.getEndTime(),0));
      	}else{
      		map.put("startTime",null);
      		map.put("endTime",null);
      	}
      	
      List<ZaContentAdminVo> zaContentAdminList = 	zaContentMapperAdminVo.getContentList(map);
    //获取分页数据结果
	  PageInfo<ZaContentAdminVo> pageInfo = new PageInfo<ZaContentAdminVo>(zaContentAdminList);
	     //返回结果
		return AppMsgResult.result(200, "", pageInfo);
	}

	@Override
	public AppMsgResult addOrUpdateContent(ZaContent zaContent,String type,String userId, String token) {
		//校验是否登录
		AppMsgResult msgResult = userService.validateAdminLogin(userId, token);
		if(200 != (int)msgResult.getFlag()){
//			return  msgResult;
		}
		AppMsgResult result=null;
		Date date = new Date();
		if("add".equalsIgnoreCase(type)) {
			//添加
			Integer contentCategoryId = zaContent.getContentCategoryId();
			if(contentCategoryId!=null) {
				String title = zaContent.getTitle();
				if(StringUtils.isNotBlank(title)) {
					Integer sort = zaContent.getSort();
					if(sort!=null) {
						//校验在同一类别中，排序sort是否重复
						Integer k = zaContentMapperAdminVo.checkContentSort(contentCategoryId,sort);
						if(k!=0) {
							return AppMsgResult.result(516, "该类别排序sort不能重复！", null);
						}
					}
					//向表中插入内容信息
					zaContent.setId(IDUtils.genId());
					zaContent.setOperator(userId);
					zaContent.setUpdatedTime(date);
					zaContent.setCreatedTime(date);
				    Integer k = zaContentMapperAdminVo.insertContentSelective(zaContent);
				    if(k==1) {
				    	//清除内容缓存
						this.delContentCache();
				    	result = AppMsgResult.result(200, "success", null);
				    }else {
				    	result =  AppMsgResult.result(500, "添加失败,请稍后重试！", null);
				    }
					
					
				}else {
					result = AppMsgResult.result(543, "请填写内容标题信息！", null);
				}
				
			}else {
				result = AppMsgResult.result(543, "请选择内容所属分类！", null);
			}
			
		}else if("update".equalsIgnoreCase(type)) {
			//更新内容
			String id = zaContent.getId();
			if(StringUtils.isNotBlank(id)) {
				Integer sort = zaContent.getSort();
				if(sort!=null) {
					Integer contentCategoryId = zaContent.getContentCategoryId();
					  if(contentCategoryId!=null) {
						Integer k = zaContentMapperAdminVo.checkContentSort(contentCategoryId, sort);
							if(k!=0) {
								return AppMsgResult.result(516, "该类别排序sort不能重复！", null);
							}
				
				        }else {
				        	return AppMsgResult.result(543, "内容所属分类id参数必须传值！", null);
					}
					
				}	
				//修改
				zaContent.setOperator(userId);
				zaContent.setUpdatedTime(date);
				Integer i = zaContentMapperAdminVo.updateContentSelective(zaContent);
				if(i==1) {
					//清除内容缓存
					this.delContentCache();
					result = AppMsgResult.result(200, "success", null);
				}else {
					result =  AppMsgResult.result(500, "添加失败,请稍后重试！", null);
				}
				
				
				
			}else {
				result = AppMsgResult.result(543, "主键id参数必须传值！", null);
			}
		}else {
			result = AppMsgResult.result(543, "type参数值有误！", null);
		}
		
		return result;
	}

	@Override
	public AppMsgResult queryContentById(String id, String userId, String token) {
		//校验是否登录
		AppMsgResult msgResult = userService.validateAdminLogin(userId, token);
		if(200 != (int)msgResult.getFlag()){
//			return  msgResult;
		}
		AppMsgResult result=null;
		
		ZaContent zaContent = zaContentMapperAdminVo.queryContentById(id);
		
		result = AppMsgResult.result(200, "success", zaContent);
		return result;
	}

	@Override
	public AppMsgResult delContentById(String id, String userId, String token) {
		//校验是否登录
		AppMsgResult msgResult = userService.validateAdminLogin(userId, token);
		if(200 != (int)msgResult.getFlag()){
//			return  msgResult;
		}
		AppMsgResult result=null;
		ZaContent zaContent = zaContentMapperAdminVo.queryContentById(id);
		if(zaContent!=null) {
			zaContentMapperAdminVo.delContentById(id);
			//清除内容缓存
			this.delContentCache();
			result= AppMsgResult.result(200, "success", null);
		}else {
			result= AppMsgResult.result(563, "操作失败，数据未找到！", null);
		}
		return result;
	}

}

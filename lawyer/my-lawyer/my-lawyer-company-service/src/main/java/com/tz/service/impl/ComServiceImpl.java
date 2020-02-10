package com.tz.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tz.cache.RedisCache;
import com.tz.mapper.com.vo.ZaComAuthenticationMapperComVo;
import com.tz.mapper.com.vo.ZaComCategoryMapperComVo;
import com.tz.pojo.com.vo.ComAuthenDetailInfo;
import com.tz.pojo.com.vo.ZaComAuthenticationComVo;
import com.tz.pojo.com.vo.ZaComCategoryComVo;
import com.tz.res.AppMsgResult;
import com.tz.service.ComService;
@Service
public class ComServiceImpl implements ComService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RedisCache cache;
	
	@Autowired
	
	private ZaComCategoryMapperComVo zaComCategoryMapperVo;

	
	@Autowired
	private ZaComAuthenticationMapperComVo zaComAuthenticationMapperComVo;
	
	
	/**
	 * 查询企业分类信息	
	 */
	@Override
	public AppMsgResult getCompanyCategory() {
		AppMsgResult result =null;
		//判断用户是否登录
			String companyCategoryCache_key =  RedisCache.CAHCENAME+"|getCompanyCategory|";
			List<ZaComCategoryComVo> companyCategoryList = cache.getListCache(companyCategoryCache_key, ZaComCategoryComVo.class);
			if(companyCategoryList!=null) {
				LOG.info("get cache with key:"+companyCategoryCache_key);
			}else {
				companyCategoryList = zaComCategoryMapperVo.getCompanyCategory();
				if(companyCategoryList.size()>0) {
					cache.putListCache(companyCategoryCache_key, companyCategoryList);
					LOG.info("put cache with key:"+companyCategoryCache_key);
				}
			}
			result = AppMsgResult.result(200, "success",companyCategoryList);
			
		return result;
	}

	/**
	 * 企业分类查询展示基本信息
	 */
	@Override
	public AppMsgResult getCompanys(String comName ,String comCategoryName,String categoryId,Integer curPage, Integer rows) {
		AppMsgResult result =null;
			   
			   Map<String,Object> map = new HashMap<String,Object>();
			   if(StringUtils.isNotBlank(comName)) {
				   map.put("comName", "%"+comName+"%");
			   }else {
				   map.put("comName", null);
			   }
			   if(StringUtils.isNotBlank(comCategoryName)) {
				   map.put("comCategoryName", "%"+comCategoryName+"%");
			   }else {
				   map.put("comCategoryName", null);
			   }
			   if(StringUtils.isNotBlank(categoryId)) {
				   map.put("categoryId", categoryId);
			   }else {
				   map.put("categoryId", null);
			   }
			   
			   if(StringUtils.isNotBlank(comName) ||StringUtils.isNotBlank(comCategoryName) || StringUtils.isNotBlank(categoryId) ) {
				   rows = rows == null?10:rows;
				   curPage = curPage == null?1:curPage;
				   //分页处理
				   PageHelper.startPage(curPage, rows);
				   //分页按名称或者企业分类模糊查询
				   List<ZaComAuthenticationComVo> comAuthenticationList = zaComAuthenticationMapperComVo.getCompanys(map);
				   PageInfo<ZaComAuthenticationComVo> zaComAuthenticationList = new PageInfo<ZaComAuthenticationComVo>(comAuthenticationList);
				   result = AppMsgResult.result(200,"success",zaComAuthenticationList);
			   }else {
				   //随机展示10所已认证企业信息
				    List<ZaComAuthenticationComVo> zaComAuthenticationList = zaComAuthenticationMapperComVo.getCompanys(map);
				    result = AppMsgResult.result(200,"success",zaComAuthenticationList);
			   }
			   
		return result;
	}

	/**
	 * 展示企业详情信息
	 */
	@Override
	public AppMsgResult getCompanyDetailInfo(String id,String userId) {
		AppMsgResult result =null;
			String companyDetailInfoCache_key = RedisCache.CAHCENAME +"|getCompanyDetailInfo|"+id;
		    ComAuthenDetailInfo companyDetailInfo = cache.getCache(companyDetailInfoCache_key, ComAuthenDetailInfo.class);
			if(companyDetailInfo!=null) {
				LOG.info("get cache with key:"+companyDetailInfoCache_key);
			}else {
				companyDetailInfo = zaComAuthenticationMapperComVo.getCompanyDetailInfo(id);
				cache.putCacheWithExpireTime(companyDetailInfoCache_key, companyDetailInfo, RedisCache.CAHCETIME);
				LOG.info("put cache with key:"+companyDetailInfoCache_key);
			}
			
			//判断用户是否已收藏
			if(userId==null) {
				companyDetailInfo.setIsCollection(0);
			}else {
				Integer cid =zaComAuthenticationMapperComVo.queryUserCollectionById(userId, id);
				companyDetailInfo.setIsCollection(cid);
			}
			result = AppMsgResult.result(200,"success",companyDetailInfo);
			
		return result;
	}

	
}

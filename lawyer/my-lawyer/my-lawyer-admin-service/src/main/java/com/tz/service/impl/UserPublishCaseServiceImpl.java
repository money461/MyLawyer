package com.tz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spatial4j.core.io.GeohashUtils;
import com.tz.date.DateUtile;
import com.tz.mapper.vo.UserPublishCaseMapperAdminVo;
import com.tz.pojo.ZaCaseCategory;
import com.tz.pojo.admin.ZaUserPublishCaseAdminVo;
import com.tz.res.AppMsgResult;
import com.tz.service.UserPublishCaseService;
import com.tz.service.UserService;
import com.tz.util.AddressToGpsUtil;
@Service
public class UserPublishCaseServiceImpl implements UserPublishCaseService {


	// 注入用户接口类
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserPublishCaseMapperAdminVo userPublishCaseMapperAdminVo;
	
	@Override
	public AppMsgResult getUserPublishCaseList(ZaUserPublishCaseAdminVo zaUserPublishCaseAdminVo, Integer curPage,
			Integer rows, String userId, String token) {
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
        
        Integer status = zaUserPublishCaseAdminVo.getStatus();
        if(status!=null && status!=0 && status!=1 && status!=2 && status!=3 && status!=4 && status!=5 && status!=6) {
        	return AppMsgResult.result(543, "案件状态参数值有误！", null);
        }
        map.put("status", status);
        
        //发布委托 代写文书
        Integer type = zaUserPublishCaseAdminVo.getType();
        map.put("type", type);
        
        String userName = zaUserPublishCaseAdminVo.getUserName();
        if(StringUtils.isNotBlank(userName)) {
        	map.put("userName", "%"+userName+"%");
        }else {
        	map.put("userName", null);
        }
        String lawName = zaUserPublishCaseAdminVo.getLawName();
        if(StringUtils.isNotBlank(lawName)) {
        	map.put("lawName", "%"+lawName+"%");
        }else {
        	map.put("lawName", null);
        	
        }
       
        //检索案件类型
        map.put("caseId", zaUserPublishCaseAdminVo.getCaseId());
        
        //检索案件发布地址
        String caseAddress = zaUserPublishCaseAdminVo.getCaseAddress();
        if(StringUtils.isNotBlank(caseAddress)) {
        	Map<String, Double> json = AddressToGpsUtil.getGeocoderLatitude(caseAddress);
        	//获取用户地址经纬度
			Double lon  = json.get("lon");
			Double lat = json.get("lat");
			String geoCode = GeohashUtils.encodeLatLon(lon, lat,3);
			System.out.println("--------------------"+geoCode); 
			map.put("geoCode", geoCode+"%");
        }else {
        	map.put("geoCode", null);
        }
        //时间段
      	if(null != zaUserPublishCaseAdminVo.getStartTime() && null != zaUserPublishCaseAdminVo.getEndTime()){
      		map.put("startTime",DateUtile.pushDays(zaUserPublishCaseAdminVo.getStartTime(),0) );
      		map.put("endTime", DateUtile.pushDays(zaUserPublishCaseAdminVo.getEndTime(),0));
      	}else{
      		map.put("startTime",null);
      		map.put("endTime",null);
      	}
        
        List<ZaUserPublishCaseAdminVo> userPublishCaseList = userPublishCaseMapperAdminVo.selectUserPublishCaseList(map);
        PageInfo<ZaUserPublishCaseAdminVo> pageInfo = new PageInfo<ZaUserPublishCaseAdminVo>(userPublishCaseList);
        return AppMsgResult.result(200, "success", pageInfo);
	}

	@Override
	public AppMsgResult downUserPublishCase(String id, String userId, String token) {
		//校验是否登录
		AppMsgResult msgResult = userService.validateAdminLogin(userId, token);
		if(200 != (int)msgResult.getFlag()){
//			return  msgResult;
		}
		AppMsgResult result=null;
		Integer k = userPublishCaseMapperAdminVo.checkUserPublishCaseById(id);
		if(k==1) {
			 userPublishCaseMapperAdminVo.downUserPublishCase(id);
			result=AppMsgResult.result(200, "操作成功！", null);
		}else {
			result=AppMsgResult.result(563, "案件未找到！", null);
		}
		
		return result;
	}

	@Override
	public AppMsgResult delUserPublishCase(String id, String userId, String token) {
		//校验是否登录
		AppMsgResult msgResult = userService.validateAdminLogin(userId, token);
		if(200 != (int)msgResult.getFlag()){
//					return  msgResult;
		}
		AppMsgResult result=null;
		Integer k = userPublishCaseMapperAdminVo.checkUserPublishCaseById(id);
		if(k==1) {
			userPublishCaseMapperAdminVo.delUserPublishCase(id);
			result=AppMsgResult.result(200, "删除成功！", null);
		}else {
			result=AppMsgResult.result(563, "案件未找到！", null);
		}
		return result;
	}

	
	@Override
	public AppMsgResult queryUserPublishCaseById(String id, String userId, String token) {
		//校验是否登录
		AppMsgResult msgResult = userService.validateAdminLogin(userId, token);
		if(200 != (int)msgResult.getFlag()){
	//			return  msgResult;
		}
		ZaUserPublishCaseAdminVo zaUserPublishCaseAdminVo = userPublishCaseMapperAdminVo.queryUserPublishCaseById(id);
		return AppMsgResult.result(200, "success",zaUserPublishCaseAdminVo);
	}

	/**
	 * 获取所有的案件类型
	 */
	@Override
	public AppMsgResult getCaseCategoryList(Integer status, String userId, String token) {
		//校验是否登录
		AppMsgResult msgResult = userService.validateAdminLogin(userId, token);
		if(200 != (int)msgResult.getFlag()){
	//			return  msgResult;
		}
		if(status!=null && status!=1 && status!=2) {
			return AppMsgResult.result(543,"状态参数值有误！", null);
		}
		List<ZaCaseCategory> caseCategoryList = userPublishCaseMapperAdminVo.getCaseCategoryList(status);
		return AppMsgResult.result(200,"success", caseCategoryList);
	}

}

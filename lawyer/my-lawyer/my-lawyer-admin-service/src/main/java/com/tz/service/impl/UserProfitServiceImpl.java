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
import com.tz.mapper.vo.ZaUserProfitMapperAdminVo;
import com.tz.pojo.ZaUserProfit;
import com.tz.pojo.admin.ZaContentAdminVo;
import com.tz.pojo.admin.ZaUserProfitVo;
import com.tz.res.AppMsgResult;
import com.tz.service.UserProfitService;
import com.tz.service.UserService;

@Service
public class UserProfitServiceImpl implements UserProfitService {
	
	// 注入用户接口类
	@Autowired
	private UserService userService;
	
	@Autowired
	private ZaUserProfitMapperAdminVo zaUserProfitMapperAdminVo;
	
	
	@Override
	public AppMsgResult getUserProfitList(ZaUserProfitVo zaUserProfitVo,Integer curPage,Integer rows, String userId, String token) {
		//校验是否登录
		AppMsgResult msgResult = userService.validateAdminLogin(userId, token);
		if(200 != (int)msgResult.getFlag()){
//					return  msgResult;
		}
		
		//按照用户类型搜索
		Integer userType = zaUserProfitVo.getUserType();
		if(userType==null || userType!=1 && userType!=2 && userType!=3) {
			return AppMsgResult.result(456, "用户类型参数有误！", null);
		}
				rows = rows == null?10:rows;
				curPage = curPage == null?1:curPage;
				//分页处理
		        PageHelper.startPage(curPage, rows);
		        
		        //封装map集合
		        Map<String,Object> map = new HashMap<String, Object>();
		        
		        map.put("userType", userType);
		        
		        //名称搜索
		        String name = zaUserProfitVo.getName();
		        if(StringUtils.isNotBlank(name)) {
		        	map.put("name", "%"+name+"%");
		        }else {
		        	map.put("name", null);
		        }
		        
		        //电话搜索
		        String phone = zaUserProfitVo.getPhone();
		        if(StringUtils.isNotBlank(phone)) {
		        	map.put("phone", "%"+phone+"%");
		        }else {
		        	map.put("phone", null);
		        }
		        //模糊查询账户
		        String account = zaUserProfitVo.getAccount();
		        if(StringUtils.isNotBlank(account)) {
		        	map.put("account", "%"+account+"%");
		        }else {
		        	map.put("account", null);
		        }
		        
		        //启用或者禁用
		        Integer status = zaUserProfitVo.getStatus();
		        if(status!=null) {
		        	map.put("status", status);
		        }else {
		        	map.put("status", null);
		        }
		        
		        
		        //时间段
		      	if(null != zaUserProfitVo.getStartTime() && null != zaUserProfitVo.getEndTime()){
		      		map.put("startTime",DateUtile.pushDays(zaUserProfitVo.getStartTime(),0) );
		      		map.put("endTime", DateUtile.pushDays(zaUserProfitVo.getEndTime(),0));
		      	}else{
		      		map.put("startTime",null);
		      		map.put("endTime",null);
		      	}
		      	
		      List<ZaUserProfitVo> zaUserProfitList = 	zaUserProfitMapperAdminVo.getZaUserProfitList(map);
		    //获取分页数据结果
			  PageInfo<ZaUserProfitVo> pageInfo = new PageInfo<ZaUserProfitVo>(zaUserProfitList);
		     //返回结果
			return AppMsgResult.result(200, "", pageInfo);
	}

	@Override
	public AppMsgResult UserProfitFreezeById(String id, Integer status, String userId, String token) {
		//校验是否登录
				AppMsgResult msgResult = userService.validateAdminLogin(userId, token);
				if(200 != (int)msgResult.getFlag()){
//					return  msgResult;
				}
				
	 if(status==null || status!=1 && status!=0) {
		 return AppMsgResult.result(543, "状态传值错误！", null);
	 }
	 ZaUserProfit zaUserProfit = new ZaUserProfit();
	 zaUserProfit.setId(id);
	 zaUserProfit.setUpdatedTime(new Date());
	 zaUserProfit.setStatus(status);
	 zaUserProfit.setOperator(userId);
	 Integer k = zaUserProfitMapperAdminVo.updateUserProfitSelective(zaUserProfit);
	 AppMsgResult result=null;
	 if(k==1) {
		 result=AppMsgResult.result(200, "success", null);
	 }else {
		 result=AppMsgResult.result(500, "操作失败，请稍后重试", null);
	 }
	 
	  return result;
	}

}

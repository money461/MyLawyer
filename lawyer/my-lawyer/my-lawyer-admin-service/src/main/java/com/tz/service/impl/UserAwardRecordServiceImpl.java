package com.tz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tz.date.DateUtile;
import com.tz.mapper.vo.UserAwardRecordMapperAdminVo;
import com.tz.pojo.admin.ZaUserAwardRecordAdminVo;
import com.tz.res.AppMsgResult;
import com.tz.service.UserAwardRecordService;
import com.tz.service.UserService;

@Service
public class UserAwardRecordServiceImpl implements UserAwardRecordService {

	// 注入用户接口类
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserAwardRecordMapperAdminVo userAwardRecordMapperAdminVo;
	
	@Override
	public AppMsgResult getUserAwardRecordList(ZaUserAwardRecordAdminVo zaUserAwardRecordAdminVo, Integer curPage,
			Integer rows, String userId, String token) {
		//校验是否登录
		AppMsgResult msgResult = userService.validateAdminLogin(userId, token);
		if(200 != (int)msgResult.getFlag()){
//							return  msgResult;
		}
		rows = rows == null?10:rows;
		curPage = curPage == null?1:curPage;
		//分页处理
        PageHelper.startPage(curPage, rows);
        
        //封装map集合
        Map<String,Object> map = new HashMap<String, Object>();
        
        Integer awardStatus = zaUserAwardRecordAdminVo.getAwardStatus();
        if(awardStatus!=null && awardStatus!=0 && awardStatus!=1 && awardStatus!=2) {
        	return AppMsgResult.result(543, "状态参数错误！", null);
        }
        map.put("awardStatus", awardStatus);
        
        Integer type = zaUserAwardRecordAdminVo.getType();
        if(type!=null && type!=1 && type!=2 && type!=3 && type!=4) {
        	return AppMsgResult.result(543, "打赏类型参数错误！", null);
        }
        map.put("type", type);
        
        
        String userName = zaUserAwardRecordAdminVo.getUserName();
        if(StringUtils.isNotBlank(userName)) {
        	map.put("userName", "%"+userName+"%");
        }else {
        	map.put("userName", null);
        }
        
        String awardeeName = zaUserAwardRecordAdminVo.getAwardeeName();
        if(StringUtils.isNotBlank(awardeeName)) {
        	map.put("awardeeName","%"+awardeeName+"%");
        }else {
        	map.put("awardeeName", null);
        }
       
        String giftName = zaUserAwardRecordAdminVo.getGiftName();
        if(StringUtils.isNotBlank(giftName)) {
        	map.put("giftName", "%"+giftName+"%");
        }else {
        	map.put("giftName", null);
        }
        
        //时间段
      	if(null != zaUserAwardRecordAdminVo.getStartTime() && null != zaUserAwardRecordAdminVo.getEndTime()){
      		map.put("startTime",DateUtile.pushDays(zaUserAwardRecordAdminVo.getStartTime(),0) );
      		map.put("endTime", DateUtile.pushDays(zaUserAwardRecordAdminVo.getEndTime(),0));
      	}else{
      		map.put("startTime",null);
      		map.put("endTime",null);
      	}
        
      	List<ZaUserAwardRecordAdminVo> zaUserAwardRecordAdminVoList =  userAwardRecordMapperAdminVo.selectUserAwardRecordList(map);
      	
      	PageInfo<ZaUserAwardRecordAdminVo> pageInfo = new PageInfo<ZaUserAwardRecordAdminVo>(zaUserAwardRecordAdminVoList);
      	
		return AppMsgResult.result(200, "success", pageInfo);
	}

}

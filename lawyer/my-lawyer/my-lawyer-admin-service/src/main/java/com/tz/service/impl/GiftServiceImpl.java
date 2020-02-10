package com.tz.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tz.cache.RedisCache;
import com.tz.id.IDUtils;
import com.tz.mapper.index.vo.ZaGiftMapperVo;
import com.tz.pojo.ZaGift;
import com.tz.pojo.index.vo.ZaGiftVo;
import com.tz.res.AppMsgResult;
import com.tz.service.GiftService;
import com.tz.service.UserService;
@Service
public class GiftServiceImpl implements GiftService {

	@Autowired
	private RedisCache cache;
	
	// 注入用户接口类
	@Autowired
	private UserService userService;
	
	@Autowired
	private ZaGiftMapperVo zaGiftMapperVo;
	
	/**
	 * 清除礼品缓存
	 */
	public void delGiftCache() {
		String giftCache_key = RedisCache.CAHCENAME+"|getGift|";
		cache.deleteCache(giftCache_key);
	}
	
	@Override
	public AppMsgResult getGiftList(ZaGiftVo zaGiftVo,Integer curPage,Integer rows, String userId, String token) {

		//校验是否登录
		AppMsgResult msgResult = userService.validateAdminLogin(userId, token);
		if(200 != (int)msgResult.getFlag()){
//			return  msgResult;
		}
		AppMsgResult result =null;
		
		rows = rows == null?10:rows;
		curPage = curPage == null?1:curPage;
		//分页处理
        PageHelper.startPage(curPage, rows);
        
        //封装map集合
        Map<String,Object> map = new HashMap<String, Object>();
        
        String giftName = zaGiftVo.getGiftName();
        if(StringUtils.isNotBlank(giftName)) {
        	map.put("name", "%"+giftName+"%");
        }else {
        	map.put("name", null);
        }
        
        map.put("status",zaGiftVo.getStatus());
        
        List<ZaGiftVo> zaGiftList =  zaGiftMapperVo.selectGiftList(map);
        PageInfo<ZaGiftVo> pageInfo = new PageInfo<ZaGiftVo>(zaGiftList);
        result = AppMsgResult.result(200, "success", pageInfo);
        
		return result;
	}

	@Override
	public AppMsgResult addOrUpdateGift(ZaGift zagift, String type, String userId, String token) {
		//校验是否登录
		AppMsgResult msgResult = userService.validateAdminLogin(userId, token);
		if(200 != (int)msgResult.getFlag()){
//			return  msgResult;
		}
		AppMsgResult result=null;
		
		Date date = new Date();
		if("add".equals(type)) {
			//添加
			String giftName = zagift.getGiftName();
			if(StringUtils.isNotBlank(giftName)) {
				BigDecimal price = zagift.getPrice();
				if(price!=null && price.compareTo(new BigDecimal(0))!=-1) {
					if(zagift.getStatus()!=null && zagift.getStatus()!=1 && zagift.getStatus()!=2) {
						return AppMsgResult.result(543, "状态参数值有误！", null);
					}
					//插入礼品数据
					zagift.setId(IDUtils.genId());
					zagift.setCreatedTime(date);
					zagift.setUpdatedTime(date);
					zagift.setOperator(userId);
					Integer k = zaGiftMapperVo.insertGiftSelective(zagift);
					if(k==1) {
						this.delGiftCache();
						result= AppMsgResult.result(200, "success", null);
					}else {
						result= AppMsgResult.result(500, "添加失败，请稍后重试！", null);
					}
					
				}else {
					result= AppMsgResult.result(543, "请填写礼品价格，且大于等于0", null);
				}
				
			}else {
				result= AppMsgResult.result(543, "请填写礼品名称！", null);
			}
			
		}else if("update".equals(type)) {
			//修改
			String id = zagift.getId();
			if(StringUtils.isNotBlank(id)) {
				BigDecimal price = zagift.getPrice();
				if(price!=null && price.compareTo(new BigDecimal(0))==-1) {
					return AppMsgResult.result(543, "请填写礼品价格，且大于等于0", null);
				}
				
				if(zagift.getStatus()!=null && zagift.getStatus()!=1 && zagift.getStatus()!=2) {
					return AppMsgResult.result(543, "状态参数值有误！", null);
				}
				zagift.setUpdatedTime(date);
				zagift.setOperator(userId);
				Integer k = zaGiftMapperVo.updateGiftSelective(zagift);
				if(k==1) {
					this.delGiftCache();
					result= AppMsgResult.result(200, "success", null);
				}else {
					result= AppMsgResult.result(500, "更新失败，请稍后重试！", null);
				}
				
				
			}else {
				result = AppMsgResult.result(543, "必须传递id！", null);
			}
		}else {
			result = AppMsgResult.result(543, "type参数错误！", null);
		}
		
		return result;
		
		
	}
	

	@Override
	public AppMsgResult delGiftById(String id, String userId, String token) {
		//校验是否登录
		AppMsgResult msgResult = userService.validateAdminLogin(userId, token);
		if(200 != (int)msgResult.getFlag()){
//			return  msgResult;
		}
		AppMsgResult result=null;
		ZaGiftVo gift = zaGiftMapperVo.queryGiftById(id);
		if(gift!=null) {
			zaGiftMapperVo.delGiftById(id);
			this.delGiftCache();
			result=AppMsgResult.result(200, "成功删除！",null);
			
		}else {
			result=AppMsgResult.result(543, "未找到礼品信息",null);
		}
		
		return result;
	}

	@Override
	public AppMsgResult giftFreezeById(String id, Integer status, String userId, String token) {
		//校验是否登录
		AppMsgResult msgResult = userService.validateAdminLogin(userId, token);
		if(200 != (int)msgResult.getFlag()){
//			return  msgResult;
		}
		if(status==null || status!=1 && status!=2) {
			return AppMsgResult.result(543, "状态参数值有误！", null);
		}
		AppMsgResult result=null;
		ZaGift zaGift = new ZaGift();
		zaGift.setId(id);
		zaGift.setUpdatedTime(new Date());
		zaGift.setStatus(status);
		zaGift.setOperator(userId);
		Integer k = zaGiftMapperVo.updateGiftSelective(zaGift);
		if(k==1) {
			this.delGiftCache();
			result= AppMsgResult.result(200, "success", null);
		}else {
			result= AppMsgResult.result(500, "更新失败，请稍后重试！", null);
		}
		return result;
	}

	//查询礼品
	@Override
	public AppMsgResult queryGiftById(String id, String userId, String token) {
		//校验是否登录
		AppMsgResult msgResult = userService.validateAdminLogin(userId, token);
		if(200 != (int)msgResult.getFlag()){
//					return  msgResult;
		}
		ZaGiftVo gift = zaGiftMapperVo.queryGiftById(id);
		return AppMsgResult.result(200, "success", gift);
	}

}

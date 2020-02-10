package com.tz.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.aliyuncs.exceptions.ClientException;
import com.tz.cache.RedisCache;
import com.tz.id.IDUtils;
import com.tz.mapper.ZaComAuthenticationMapper;
import com.tz.mapper.ZaLawyerAuthenticationMapper;
import com.tz.mapper.ZaUserMapper;
import com.tz.mapper.ZaUserProfitMapper;
import com.tz.mapper.ZaUserPurchaseRecordMapper;
import com.tz.mapper.vo.ZaComAuthenticationMapperVo;
import com.tz.mapper.vo.ZaLawyerAuthenticationMapperVo;
import com.tz.pojo.ZaComAuthentication;
import com.tz.pojo.ZaComAuthenticationExample;
import com.tz.pojo.ZaLawyerAuthentication;
import com.tz.pojo.ZaLawyerAuthenticationExample;
import com.tz.pojo.ZaUser;
import com.tz.pojo.ZaUserDealLogExample;
import com.tz.pojo.ZaUserExample;
import com.tz.pojo.ZaUserProfit;
import com.tz.pojo.ZaUserProfitExample;
import com.tz.pojo.ZaUserPurchaseRecord;
import com.tz.pojo.ZaUserPurchaseRecordExample;
import com.tz.pojo.vo.CategoryNames;
/*import com.tz.pojo.vo.CategoryNames;*/
import com.tz.pojo.vo.ZaUserVo;
import com.tz.res.AppMsgResult;
import com.tz.res.Constant;
import com.tz.sdk.zfb.AlipayConfig;
import com.tz.service.HxChatService;
import com.tz.service.UserService;
import com.tz.sms.ALiDaYuUtil;
import com.tz.util.Hxchat;
import com.tz.validate.ValidateUtil;

/**
 * 环信接口实现类
 * 
 * @author menglin 2018年1月25日14:03:37
 */
@Service
public class HxChatServicceImpl implements HxChatService {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Override
	public AppMsgResult adduser(String username, String password,String usernick) {
		// TODO Auto-generated method stub
		return Hxchat.adduser(username, password, usernick);
	}

	@Override
	public AppMsgResult getuser(String username) {
		// TODO Auto-generated method stub
		return Hxchat.getuser(username);
	}

	@Override
	public AppMsgResult deleteuser(String username) {
		// TODO Auto-generated method stub
		return Hxchat.deleteuser(username);
	}

	@Override
	public AppMsgResult updateuser(String username, String nickName) {
		// TODO Auto-generated method stub
		return Hxchat.updateuser(username, nickName);
	}

	@Override
	public AppMsgResult addchatrooms(String owner, String members) {
		// TODO Auto-generated method stub
		members = "[" +" \""+members+"\"" + " ]";
		return Hxchat.addchatrooms(owner, members);
	}

	@Override
	public AppMsgResult joined_chatrooms(String username) {
		// TODO Auto-generated method stub
		return Hxchat.joined_chatrooms(username);
	}
	
	

}

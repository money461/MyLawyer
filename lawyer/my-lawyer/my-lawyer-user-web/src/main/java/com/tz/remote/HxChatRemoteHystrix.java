package com.tz.remote;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.pojo.ZaUserPurchaseRecord;
import com.tz.res.AppMsgResult;

/**
 * 用户服务熔断类
 * @author menglin 2017年12月28日10:00:31
 *
 */
@Component
public class HxChatRemoteHystrix implements HxChatRemote{

	@Override
	public AppMsgResult adduser(String username, String password, String usernick) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult deleteuser(String username) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult getuser(String username) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult updateusernick(String username, String nickName) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult addchatrooms(String owner, String members) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult joined_chatrooms(String username) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

    
}

package com.tz.remote;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.pojo.ZaUser;
import com.tz.pojo.vo.ZaAdminUserVo;
import com.tz.pojo.vo.ZaComAuthenticationVo;
import com.tz.pojo.vo.ZaLawyerAuthenticationVo;
import com.tz.res.AppMsgResult;

/**
 * Created by summer on 2017/5/15.
 */
@Component
public class UserRemoteHystrix implements UserRemote{

	@Override
	public AppMsgResult getUserList(ZaAdminUserVo userVo, Integer curPage, Integer rows, String userId, String token) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult addOrUpdateUser(ZaUser user, String userId, String token, String type) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult deleteUser(ZaUser user, String userId, String token) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult getLawyerUserList(ZaLawyerAuthenticationVo user, Integer curPage, Integer rows, String userId,
			String token, String type) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult updateLawyerUser(ZaLawyerAuthenticationVo lawyerAuthenticationVo, String userId, String token) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult updateUserStatusById(String id, String userId, String token, String status, String type) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult getComUserList(ZaComAuthenticationVo user, Integer curPage, Integer rows, String userId,
			String token, String type) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult adminLogin(String managerAccount, String managerPassword) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult selectById(String id, String type, String userId, String token) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult getDeletedUserList(ZaAdminUserVo zaAdminUserVo, Integer curPage, Integer rows, String userId,
			String token) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult forceDelOrRecoveryById(String id, String type, Integer status, Integer userType, String userId,
			String token) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

 

}

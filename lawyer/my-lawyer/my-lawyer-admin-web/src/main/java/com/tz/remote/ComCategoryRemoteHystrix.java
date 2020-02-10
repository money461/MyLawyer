package com.tz.remote;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.pojo.ZaComCategory;
import com.tz.pojo.ZaUser;
import com.tz.pojo.vo.ZaAdminUserVo;
import com.tz.pojo.vo.ZaComAuthenticationVo;
import com.tz.pojo.vo.ZaComCategoryVo;
import com.tz.pojo.vo.ZaLawyerAuthenticationVo;
import com.tz.res.AppMsgResult;

/**
 * Created by summer on 2017/5/15.
 */
@Component
public class ComCategoryRemoteHystrix implements ComCategoryRemote{

	@Override
	public AppMsgResult findCategoryOrSonList(ZaComCategoryVo comCategoryVo, Integer curPage, Integer rows,
			String userId, String token, String type) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult addOrUpdateCategory(ZaComCategory comCategory, String userId, String token, String type) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult deleteById(String id, String userId, String token) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult updateStatusById(String id, String userId, String token, String caseStatus) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}

	@Override
	public AppMsgResult selectById(String id, String userId, String token) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", "");
	}


 

}

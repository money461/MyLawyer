package com.tz.remote;

import org.springframework.stereotype.Component;

import com.tz.pojo.ZaGift;
import com.tz.pojo.index.vo.ZaGiftVo;
import com.tz.res.AppMsgResult;

@Component
public class GiftRemoteHystrix implements GiftRemote {

	@Override
	public AppMsgResult getGiftList(ZaGiftVo zaGiftVo, Integer curPage, Integer rows, String userId, String token) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult addOrUpdateGift(ZaGift zagift, String type, String userId, String token) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult queryGiftById(String id, String userId, String token) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult delGiftById(String id, String userId, String token) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

	@Override
	public AppMsgResult giftFreezeById(String id, Integer status, String userId, String token) {
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}

}

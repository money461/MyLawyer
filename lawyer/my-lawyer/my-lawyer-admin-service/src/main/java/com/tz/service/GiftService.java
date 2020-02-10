package com.tz.service;

import com.tz.pojo.ZaGift;
import com.tz.pojo.index.vo.ZaGiftVo;
import com.tz.res.AppMsgResult;

public interface GiftService {

	AppMsgResult getGiftList(ZaGiftVo zaGiftVo,Integer curPage,Integer rows, String userId, String token);

	AppMsgResult addOrUpdateGift(ZaGift zagift, String type, String userId, String token);

	AppMsgResult delGiftById(String id, String userId, String token);

	AppMsgResult giftFreezeById(String id, Integer status, String userId, String token);

	AppMsgResult queryGiftById(String id, String userId, String token);

}

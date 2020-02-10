package com.tz.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.config.FeignConfiguration;
import com.tz.pojo.ZaGift;
import com.tz.pojo.index.vo.ZaGiftVo;
import com.tz.res.AppMsgResult;

@FeignClient(name = "my-lawyer-admin-service",configuration=FeignConfiguration.class, fallback = GiftRemoteHystrix.class)
public interface GiftRemote {

	@PostMapping("admin/gift/api/getGiftList")
	AppMsgResult getGiftList(@RequestBody ZaGiftVo zaGiftVo,@RequestParam(value="curPage") Integer curPage, @RequestParam(value="rows")Integer rows, @RequestParam(value="userId")String userId, @RequestParam(value="token")String token);

	@PostMapping("admin/gift/api/addOrUpdateGift")
	AppMsgResult addOrUpdateGift(@RequestBody ZaGift zagift, @RequestParam(value="type")String type,@RequestParam(value="userId") String userId,@RequestParam(value="token") String token);

	@GetMapping("admin/gift/api/queryGiftById")
	AppMsgResult queryGiftById(@RequestParam(value="id")String id, @RequestParam(value="userId")String userId, @RequestParam(value="token")String token);

	@PostMapping("admin/gift/api/delGiftById")
	AppMsgResult delGiftById(@RequestParam(value="id")String id, @RequestParam(value="userId")String userId,@RequestParam(value="token") String token);

	@PostMapping("admin/gift/api/giftFreezeById")
	AppMsgResult giftFreezeById(@RequestParam(value="id")String id, @RequestParam(value="status")Integer status, @RequestParam(value="userId")String userId,@RequestParam(value="token") String token);

}

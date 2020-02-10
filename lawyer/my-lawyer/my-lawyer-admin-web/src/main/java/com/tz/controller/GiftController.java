package com.tz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tz.pojo.ZaGift;
import com.tz.pojo.index.vo.ZaGiftVo;
import com.tz.remote.GiftRemote;
import com.tz.res.AppMsgResult;

@RestController
@RequestMapping("admin/gift/api")
public class GiftController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private GiftRemote GiftRemote;
	
	/**
	 * 获取所有的礼品
	 * @param ZaGiftVo
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/getGiftList")
	public AppMsgResult getGiftList( ZaGiftVo ZaGiftVo,Integer curPage,Integer rows,@RequestParam(required=true) String userId,@RequestParam(required=true)String token) {
		LOG.info("invoke-----------getGiftList");
		AppMsgResult result = GiftRemote.getGiftList(ZaGiftVo,curPage,rows,userId,token);
		return result;
	}
	
	/**
	 * 添加或者修改礼品
	 * @param zagift
	 * @param type
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/addOrUpdateGift")
	public AppMsgResult addOrUpdateGift( ZaGift zagift,@RequestParam(required=true)String type,@RequestParam(required=true)String userId,@RequestParam(required=true)String token) {
		LOG.info("invoke-----------addOrUpdateGift");
		AppMsgResult result = GiftRemote.addOrUpdateGift(zagift,type,userId,token);
		return result;
		
	}
	
	/**
	 * 查询礼品
	 * @param id
	 * @param userId
	 * @param token
	 * @return
	 */
	@GetMapping("/queryGiftById")
	public AppMsgResult queryGiftById(@RequestParam(required=true) String id,@RequestParam(required=true)String userId,@RequestParam(required=true)String token) {
		LOG.info("invoke-----------queryGiftById");
		AppMsgResult result = GiftRemote.queryGiftById(id,userId,token);
		return result;
		
	}
	
	
	/**
	 * 
	 * @param id
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/delGiftById")
	public AppMsgResult delGiftById(@RequestParam(required=true) String id,@RequestParam(required=true)String userId,@RequestParam(required=true)String token) {
		LOG.info("invoke-----------delGiftById");
		AppMsgResult result = GiftRemote.delGiftById(id,userId,token);
		return result;
		
	}
	
	
	/**
	 * 冻解礼品
	 * @return
	 */
	@PostMapping("/giftFreezeById")
	public AppMsgResult giftFreezeById(@RequestParam(required=true)Integer status,@RequestParam(required=true) String id,@RequestParam(required=true)String userId,@RequestParam(required=true)String token) {
		LOG.info("invoke-----------giftFreezeById");
		AppMsgResult result = GiftRemote.giftFreezeById(id,status,userId,token);
		return result;
	}
	
}

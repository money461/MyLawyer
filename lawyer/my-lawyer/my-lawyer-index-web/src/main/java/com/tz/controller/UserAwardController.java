package com.tz.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tz.pojo.ZaUserAwardRecord;
import com.tz.remote.UserAwardRemote;
import com.tz.res.AppMsgResult;

/**
 * 用户打赏系统
 * 
 * @author QL
 *
 */
@RestController
@RequestMapping("userAward/api")
public class UserAwardController {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserAwardRemote userAwardRemote;

	/**
	 * 展示奖赏礼品信息
	 * 
	 * @return
	 */
	@GetMapping("/getGift")
	public AppMsgResult getGift(@RequestParam(required = true) String userId,
			@RequestParam(required = true) String userToken) {
		LOG.info("invoke----/getGift");
		AppMsgResult result = userAwardRemote.getGift(userId, userToken);
		return result;

	}

	/**
	 * * 支付事件类型 4 案件委托悬赏支付 7 代写文书悬赏支付  8 直播间打赏,3 普通打赏 
	 * @param paymentType
	 * @param giftId 
	 * @param giftNum
	 * @param awardeeId
	 * @param awardeeName
	 * @param reward  //应支付金额
	 * @param userPublishCaseId //案件悬赏id
	 * @param userId
	 * @param userToken
	 * @return
	 */
	@PostMapping("/rewardOperation")
	public AppMsgResult rewardOperation(@RequestParam(required=true) Integer paymentType, ZaUserAwardRecord zaUserAwardRecord,String awardeeName,String userPublishCaseId, @RequestParam(required=true) String userToken) {
		 LOG.info("invoke----/rewardOperation");
		 AppMsgResult result =userAwardRemote.rewardOperation(paymentType,zaUserAwardRecord,awardeeName,userPublishCaseId,userToken);
		return result;
		
		
	}

}

package com.tz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tz.res.AppMsgResult;
import com.tz.service.NoticeService;

/**
 * 系统通知(用户邀请律师通知、律师回复通知、系统通知) 通知消息的基本展示
 * @author QL
 *
 */
@RestController
@RequestMapping("notice/api")
public class NoticeController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private NoticeService noticeService;
	
	 /**
	  *  用户邀请律师或者删除我的邀请律师
	  * @param lawId 律师id
	  * @param type 1邀请个人律师 2 删除个人律师
	  * @param userId
	  * @param userToken
	  */
	
	@PostMapping("/inviteIOrDelMyLawyer")
	public AppMsgResult inviteIOrDelMyLawyer(@RequestParam(required=true) String lawId,@RequestParam(required=true,defaultValue="1") Integer type,@RequestParam(required=true) String userId,@RequestParam(required=true) String userToken) {
		LOG.info("invoke----/inviteIOrDelMyLawyer");
		AppMsgResult result = noticeService.inviteIOrDelMyLawyer(lawId,type,userId,userToken);
		return result;
	}
	
	
	/**
	 * 律师同意或者拒绝成为某用户的个人邀请律师
	 * id 通知主键id
	 * @param agree //同意接受1 //忽略拒绝0
	 * @param userId
	 * @param userToken
	 * @return
	 */
	@PostMapping("/lawyerAgreeOrRefuse")
	public AppMsgResult lawyerAgreeOrRefuse(@RequestParam(required=true) Long id,@RequestParam(required=true,defaultValue="1") Integer agree,@RequestParam(required=true) String userId,@RequestParam(required=true) String userToken) {
		LOG.info("invoke----/lawyerAgreeOrRefuse");
		AppMsgResult result = noticeService.lawyerAgreeOrRefuse(id,agree,userId,userToken);
		return result;
	}
	
	
	/**
	 * 根据用户id展示系统/悬赏委托、打赏通知消息
	 *消息类型 0其他系统消息 1案件接受消息 2打赏悬赏消息. 3案件完成回复消息.4.邀请律师消息 5系统通知所有人的消息',
	 */
	@PostMapping("/getSystemNotice")
	public AppMsgResult getSystemNotice(@RequestParam(required=true) Integer noticeType,Integer curPage,Integer rows,@RequestParam(required=true) String userId,@RequestParam(required=true) String userToken) {
		LOG.info("invoke----/getSystemNotice");
     	AppMsgResult result = noticeService.getSystemNotice(noticeType,curPage,rows,userId,userToken);
		return result;
	}
	
	
	
	/**
	 *批量删除消息 
	 * @param ids
	 * @param userId
	 * @param userToken
	 * @return
	 */
	@PostMapping("/delSysNotice")
	public AppMsgResult delSysNoticeById(@RequestParam(required=true) String ids,@RequestParam(required=true) String userId,@RequestParam(required=true) String userToken) {
		LOG.info("invoke----/delSysNotice");
     	AppMsgResult result = noticeService.delSysNoticeById(ids,userId,userToken);
		return result;
   }
}

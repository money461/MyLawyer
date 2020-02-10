package com.tz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tz.res.AppMsgResult;
import com.tz.service.CaseHandleService;

/**
 * 
 * @author QL
 *案件处理层
 */
@RestController
@RequestMapping("case/api")
public class CaseHandleController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CaseHandleService caseHandleService;
	
	/**
	 * 帮助他--发出消息队列--写入系统通知数据库--刷新展示委托通知的接口--用户接受律师接单(用户忽略律师接单)
	 */
	
	
	/**
	 *  律师发出帮助请求
	 * @param id  //案件id
	 * @param userId //律师id
	 * @param userToken
	 * @return
	 */
	@PostMapping("/lawHandlingCase")
	 public AppMsgResult lawHandlingCase(@RequestParam(required=true)String id,
			                           @RequestParam(required=true) String userId,@RequestParam(required=true) String userToken) {
		LOG.info("invoke----/lawHandlingCase");
		AppMsgResult result = caseHandleService.lawHandlingCase(id,userId,userToken);
		return result;
	 }
	
	
	/**
	 *  用户允许律师接单(修改案件状态)或者拒绝律师
	 * @param id  系统通知主键id
	 * @param agree  //忽略0还是接受1
	 * @param userId  //用户id
	 * @param userToken
	 * @return
	 */
	 @PostMapping("/userOrderTaking")
	  public AppMsgResult userOrderTaking(@RequestParam(required=true) Long id,@RequestParam(required=true,defaultValue="0") Integer agree,@RequestParam(required=true) String userId,@RequestParam(required=true) String userToken) {
		  LOG.info("invoke----/lawOrderTaking");
		  AppMsgResult result = caseHandleService.userOrderTaking(id,agree,userId,userToken);
		return result;
	  }
	 
	 
		/**
		 * 用户或者律师放弃案例解决 userType =1普通用户 2律师用户
		 * @param id 放弃案例
		 * @param userType
		 * @param userId
		 * @param userToken
		 * @return
		 */
		@PostMapping("/abandonPublishCase")
		public AppMsgResult abandonPublishCase(@RequestParam(required=true) String id,@RequestParam(required=true) Integer userType,@RequestParam(required=true) String userId,@RequestParam(required=true) String userToken) {
			LOG.info("invoke----/abandonPublishCase");
			AppMsgResult result = caseHandleService.abandonPublishCase(id,userType,userId,userToken);
			return result;
			
		}
		
		 
		
		/**
		 * 律师提出方案已经解决完成，请求客户回应
		 * @param id  案件id
		 * @param userId
		 * @param userToken
		 */
		@PostMapping("/lawyerComplete")
		public AppMsgResult lawyerCompleteCase(@RequestParam(required=true) String id,@RequestParam(required=true) String userId,@RequestParam(required=true) String userToken) {
			LOG.info("invoke----/lawyerComplete");
			AppMsgResult result = caseHandleService.lawyerCompleteCase(id,userId,userToken);
			return result;
		}
		 
		
		 /**
		  * 用户对律师提出的通知回复，替换律师、拒绝或者确认服务交易成功
		  * @param id 通知消息id
		  * @param agree 0 拒绝结束 1 认可完成案件 2替换律师重新发布一次？？
		  * @param userId
		  * @param userToken
		  * @return
		  */
		@PostMapping("/userReplyCompleteCase")
		public AppMsgResult userReplyCompleteCase(@RequestParam(required=true) Long id,@RequestParam(required=true) Integer agree,@RequestParam(required=true) String userId,@RequestParam(required=true) String userToken) {
			LOG.info("invoke----/userReplyCompleteCase");
			AppMsgResult result = caseHandleService.userReplyCompleteCase(id,agree,userId,userToken);
			return result;
		}
		
		
		/**
		 * 用户删除或隐藏发布的案件记录
		 * @param id  案件id
		 * @param userId
		 * @param userToken
		 * @return
		 */
		@PostMapping("/deletePublishCase")
		public AppMsgResult deletePublishCase(@RequestParam(required=true) String id,@RequestParam(required=true) String userId,@RequestParam(required=true) String userToken) {
			LOG.info("invoke----/deletePublishCase");
			AppMsgResult result = caseHandleService.deletePublishCase(id,userId,userToken);
			return result;
		}
}

package com.tz.activemq;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tz.mapper.index.vo.ZaUserMapperVo;
import com.tz.pojo.ZaSysNotice;
import com.tz.pojo.ZaUser;
import com.tz.pojo.ZaUserIncomeRecord;
import com.tz.pojo.ZaUserPublishCase;
import com.tz.pojo.admin.ZaUserAwardRecordAdminVo;
import com.tz.pojo.index.vo.UserRewardOperation;
import com.tz.pojo.index.vo.ZaSysNoticeVo;
import com.tz.res.AppMsgResult;
import com.tz.res.Constant;

/**
 * @author QL 
 *
 */
@Service("consumer")
public class Consumer {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private NoticeHandle noticeHandle;
	
	@Autowired
	private ZaUserMapperVo zaUserMapperVo;
	
	// 使用JmsListener配置消费者监听的队列，其中text是接收到的消息  
	/**
	 * 律师发出帮助请求
	 * @param message
	 */
    @JmsListener(destination=Constant.LAWYERHANDLECASE_QUEUE,containerFactory="jmsListenerContainerQueue")  
    public void receiveQueue(String message) {  
    	ZaSysNotice zaSysNotice = JSONObject.parseObject(message, ZaSysNotice.class);
    	System.out.println("Consumer1收到的Queue报文为:"+zaSysNotice.getMessage()); 
    	//将消息写入数据库系统通知表中
    	Integer id = noticeHandle.insertSysNotice(zaSysNotice);
    	zaSysNotice.setId(Integer.valueOf(id).longValue());
    	//调用消息推送端口
    	LOG.info("------------调用消息推送接口-----------------"+id);
    	
        
    }  
    
    /**
     * 用户对律师案件帮助请求的回复通知
     * @param message
     */
    @JmsListener(destination =Constant.USERORDETAKING_QUEUE,containerFactory="jmsListenerContainerQueue")  
    public void receiveQueue2(String message) {  
    	ZaSysNotice zaSysNotice = JSONObject.parseObject(message, ZaSysNotice.class);
    	System.out.println("Consumer2收到的Queue报文为:"+zaSysNotice.getMessage());  
    	//将消息写入数据库系统通知表中
    	Integer id = noticeHandle.insertSysNotice(zaSysNotice);
    	zaSysNotice.setId(Integer.valueOf(id).longValue());
    	
    	LOG.info("------------调用消息推送接口-----------------");
    	
    	
    }  
    
    /**
     * 用户或者律师放弃案件处理，系统通知案件对方。
     * @param message
     */
    @JmsListener(destination =Constant.ABANDDONPUBLISHCASE_QUEUE,containerFactory="jmsListenerContainerQueue")  
    public void receiveQueue3(String message) {  
    	ZaSysNotice zaSysNotice = JSONObject.parseObject(message, ZaSysNotice.class);
    	System.out.println("Consumer3收到的Queue报文为:"+zaSysNotice.getMessage());  
    	//将消息写入数据库系统通知表中
    	Integer id = noticeHandle.insertSysNotice(zaSysNotice);
    	zaSysNotice.setId(Integer.valueOf(id).longValue());
    	LOG.info("------------调用消息推送接口-----------------");
    	
    }  
    
    /**
     * 律师提出案件已经完成，请求结束案件,发出通知请求
     * @param message
     */
    @JmsListener(destination =Constant.LAWYERCOMPLETE_QUEUE,containerFactory="jmsListenerContainerQueue")  
    public void receiveQueue4(String message) {  
    	ZaSysNotice zaSysNotice = JSONObject.parseObject(message, ZaSysNotice.class);
    	System.out.println("Consumer3收到的Queue报文为:"+zaSysNotice.getMessage());  
    	//将消息写入数据库系统通知表中
    	Integer id = noticeHandle.insertSysNotice(zaSysNotice);
    	zaSysNotice.setId(Integer.valueOf(id).longValue());
    	
    	LOG.info("------------调用消息推送接口-----------------");
    	
    }  
    
    /**
     * 用户回复律师提出案件已经完成的请求 拒绝 案件已完成  同意已完成  更换律师通知已经解雇
     * @param message
     */
    @JmsListener(destination =Constant.USERREPLEYCOMPLETECASE_QUEUE,containerFactory="jmsListenerContainerQueue")  
    public void receiveQueue5(String message) {  
    	ZaSysNotice zaSysNotice = JSONObject.parseObject(message, ZaSysNotice.class);
    	System.out.println("Consumer3收到的Queue报文为:"+zaSysNotice.getMessage());  
    	//将消息写入数据库系统通知表中
    	Integer id = noticeHandle.insertSysNotice(zaSysNotice);
    	zaSysNotice.setId(Integer.valueOf(id).longValue());
    	
    	LOG.info("------------调用消息推送接口-----------------");
    }  
    
    /**
     * 用户请求律师成为自己的专属律师
     * @param message
     */
    @JmsListener(destination =Constant.USERINVITELAWYER_QUEUE,containerFactory="jmsListenerContainerQueue")  
    public void receiveQueue6(String message) {  
    	ZaSysNoticeVo sysNoticeVo = JSONObject.parseObject(message, ZaSysNoticeVo.class);
    	System.out.println("Consumer3收到的Queue报文为:"+sysNoticeVo.getMessage());  
    	//将消息写入数据库系统通知表中
    	Integer id = noticeHandle.insertSysNotice(sysNoticeVo);
    	
    	//获取该用户的的昵称
    	ZaUser user = zaUserMapperVo.queryUserById(sysNoticeVo.getFromUserId());
    	sysNoticeVo.setName(user.getUserNick());
    	sysNoticeVo.setHeadUrl(user.getHeadUrl());
    	sysNoticeVo.setId(Integer.valueOf(id).longValue());
    	
    	LOG.info("------------调用消息推送接口-----------------");
    }  
    
    /**
     * 律师回应用户请求律师成为自己的专属律师
     * @param message
     */
    @JmsListener(destination =Constant.LAWYERAGREEORREFUSE_QUEUE,containerFactory="jmsListenerContainerQueue")  
    public void receiveQueue7(String message) {  
    	ZaSysNotice zaSysNotice = JSONObject.parseObject(message, ZaSysNotice.class);
    	System.out.println("Consumer3收到的Queue报文为:"+zaSysNotice.getMessage());  
    	//将消息写入数据库系统通知表中
    	Integer id = noticeHandle.insertSysNotice(zaSysNotice);
    	zaSysNotice.setId(Integer.valueOf(id).longValue());
    	
    	LOG.info("------------调用消息推送接口-----------------");
    }  
    
   /**
    * 用户打赏后通知受赏者
    * @param message
    */
    @JmsListener(destination =Constant.USERREWARDNOTICE_QUEUE,containerFactory="jmsListenerContainerQueue")  
    public void receiveQueue8(String message) {  
    	 UserRewardOperation userRewardOperation = JSONObject.parseObject(message, UserRewardOperation.class);
    	 //获取用户打赏消费信息写入消费表
    	 ZaUserAwardRecordAdminVo zaUserAwardRecordAdminVo = userRewardOperation.getZaUserAwardRecordAdminVo();
    	 AppMsgResult result = noticeHandle.insertUserPurchaseRecord(zaUserAwardRecordAdminVo);
    	 if((Integer)result.getFlag()==200) {
    		 ZaUserIncomeRecord zaUserIncomeRecord = userRewardOperation.getZaUserIncomeRecord();
    		 //向受赏人写入收益信息并计算总收益
    		 noticeHandle.insertUserIncomeRecord(zaUserIncomeRecord);
    		 ZaSysNotice zaSysNotice = userRewardOperation.getZaSysNotice();
    		 System.out.println("Consumer3收到的Queue报文为:"+zaSysNotice.getMessage());  
    		 //将消息写入数据库系统通知表中
    		 Integer id = noticeHandle.insertSysNotice(zaSysNotice);
    		 zaSysNotice.setId(Integer.valueOf(id).longValue());
    		 
    		 LOG.info("------------调用消息推送接口-----------------");
    	 }
    }  
    
    
    /**
     * 用户发布案件付款后向用户发出成功通知并记录消费信息
     * @param message
     */
     @JmsListener(destination =Constant.USERPUBLISHCASESUCCESSNOTICE_QUEUE,containerFactory="jmsListenerContainerQueue")  
     public void receiveQueue9(String message) {  
     	 UserRewardOperation userRewardOperation = JSONObject.parseObject(message, UserRewardOperation.class);
     	  ZaUserAwardRecordAdminVo zaUserAwardRecordAdminVo = userRewardOperation.getZaUserAwardRecordAdminVo();
     	 //向受赏人写入收益信息并计算总收益
     	 AppMsgResult result = noticeHandle.insertUserPurchaseRecord(zaUserAwardRecordAdminVo);
     	 if((Integer)result.getFlag()==200) {
     		 ZaSysNotice zaSysNotice = userRewardOperation.getZaSysNotice();
     		 System.out.println("Consumer3收到的Queue报文为:"+zaSysNotice.getMessage());  
     		 //将消息写入数据库系统通知表中
     		 Integer id = noticeHandle.insertSysNotice(zaSysNotice);
     		 zaSysNotice.setId(Integer.valueOf(id).longValue());
     		 
     		 LOG.info("------------调用消息推送接口-----------------");
     	 }
     }  
    
    /**
     * 案件过期后对案件用户进行通知
     * @param QL
     */
    @JmsListener(destination =Constant.USERPUBLISHCASEOVERDUE_QUEUE,containerFactory="jmsListenerContainerQueue")  
    public void receiveQueue10(String message) {  
    List<ZaUserPublishCase> list = JSONArray.parseArray(message, ZaUserPublishCase.class);
    //遍历进行发送消息并通知
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    for (ZaUserPublishCase zaUserPublishCase : list) {
    	String userId = zaUserPublishCase.getUserId();
    	Date updatedTime = zaUserPublishCase.getUpdatedTime();
		String date = format.format(updatedTime);
    	
    	//发出通知
		String msg = "您于"+date+"发布的一份案件已经过了最后截止时间，您可以进行修改后重新发布！";
		//创建消息实体
		ZaSysNotice zaSysNotice = new ZaSysNotice();
		zaSysNotice.setSystemId(Constant.SYSTEMID);
		zaSysNotice.setToUserId(userId);  //通知对象id
		zaSysNotice.setNoticeType(0);
		zaSysNotice.setHandleState(1);
		zaSysNotice.setMessage(msg);
		//将消息写入数据库系统通知表中
		Integer id = noticeHandle.insertSysNotice(zaSysNotice);
		zaSysNotice.setId(Integer.valueOf(id).longValue());
		System.out.println("---------------------返回通知主键id------"+zaSysNotice.getId());
		LOG.info("------------调用消息推送接口-----------------");
    	
	}
   }  
    
    
    // 使用JmsListener配置消费者监听主题，其中text是接收到的消息  
    @JmsListener(destination = "aa",containerFactory="jmsListenerContainerTopic")  
    public void receiveTopic(String text) {  
    	System.out.println("Consumer3收到的Topic报文为:"+text);  
    }  
    
}

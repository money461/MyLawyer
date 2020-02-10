package com.tz.activemq;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tz.id.IDUtils;
import com.tz.mapper.index.vo.ZaSysNoticeMapperVo;
import com.tz.mapper.index.vo.ZaUserIncomeRecordMapperVo;
import com.tz.mapper.index.vo.ZaUserProfitMapperVo;
import com.tz.mapper.index.vo.ZaUserPurchaseRecordMapperVo;
import com.tz.pojo.ZaSysNotice;
import com.tz.pojo.ZaUserIncomeRecord;
import com.tz.pojo.ZaUserProfit;
import com.tz.pojo.ZaUserPurchaseRecord;
import com.tz.pojo.admin.ZaUserAwardRecordAdminVo;
import com.tz.res.AppMsgResult;
/**
 * 处理消息类
 * @author QL
 *
 */
@Component(value="noticeHandle")
public class NoticeHandle {

	@Autowired
	private ZaSysNoticeMapperVo zaSysNoticeMapperVo;
	
	@Autowired
	private ZaUserProfitMapperVo zaUserProfitMapperVo;
	
	@Autowired
	private ZaUserIncomeRecordMapperVo zaUserIncomeRecordMapperVo;
	
	@Autowired
	private ZaUserPurchaseRecordMapperVo zaUserPurchaseRecordMapperVo;
	   /**
     * 将消息内容写入数据库中
     * 
     */
    public Integer insertSysNotice(ZaSysNotice zaSysNotice) {
	    //将消息写入系统通知数据库表中 返回消息自增主键id
	    Integer nid = zaSysNoticeMapperVo.insertSysNotice(zaSysNotice);
	    return nid;
    }
    
    /**
     * 向收益表中插入数据并在受赏人账户中计算总收益
     * @param QL
     */
    public void insertUserIncomeRecord(ZaUserIncomeRecord zaUserIncomeRecord) {
    	
    	Integer k = zaUserIncomeRecordMapperVo.insertUserIncomeRecord(zaUserIncomeRecord);
    	if(k==1) {
    		String awardeeId = zaUserIncomeRecord.getUserId();
    		BigDecimal reward = zaUserIncomeRecord.getInCash();
    		//修改该受益人用户的收益及总收益金额信息
    		ZaUserProfit userProfit2= zaUserProfitMapperVo.getUserProfitById(awardeeId);
    		if(userProfit2==null) {
    			userProfit2 = new ZaUserProfit();
    			String userProfitId =IDUtils.genId();
    			userProfit2.setId(userProfitId);
    			userProfit2.setUserId(awardeeId);
    			userProfit2.setCoinIncome(reward);
    			userProfit2.setCoinInTotal(reward);
    			userProfit2.setUserType(2);  //收益者类型为律师用户
    			zaUserProfitMapperVo.insertUserProfit(userProfit2);
    		}else {
    			this.addCoinIncome(userProfit2, reward);
    		}
    	}
    	
    }
    
    
    /**
	 * 收益用户获得货币值，添加收益数据，修改数据库值
	 * @param zaUserProfit
	 * @param reward
	 */
	private void addCoinIncome(ZaUserProfit zaUserProfit,BigDecimal reward) {
		zaUserProfit.setCoinIncome(zaUserProfit.getCoinIncome().add(reward).setScale(2, BigDecimal.ROUND_HALF_UP));
		zaUserProfit.setCoinInTotal(zaUserProfit.getCoinInTotal().add(reward).setScale(2, BigDecimal.ROUND_HALF_UP));
		//将修改收益数据
	     zaUserProfitMapperVo.updateUserProfitById(zaUserProfit);
	}
	
	
	/**
	 * 向消费表中插入数据
	 * @param zaUserAwardRecordAdminVo
	 * @return
	 */
	public AppMsgResult insertUserPurchaseRecord(ZaUserAwardRecordAdminVo zaUserAwardRecordAdminVo) {
		//将打赏记录写入到消费信息表中
		String id = zaUserAwardRecordAdminVo.getId();
		Integer type = zaUserAwardRecordAdminVo.getType();
		String userId = zaUserAwardRecordAdminVo.getUserId();
		String awardeeName = zaUserAwardRecordAdminVo.getAwardeeName();
		Integer giftNum = zaUserAwardRecordAdminVo.getGiftNum();
		String giftId = zaUserAwardRecordAdminVo.getGiftId();
		String giftName = zaUserAwardRecordAdminVo.getGiftName();
		BigDecimal reward = zaUserAwardRecordAdminVo.getReward();
		ZaUserPurchaseRecord zaUserPurchaseRecord = new ZaUserPurchaseRecord();
		String recordId = IDUtils.genId();
		zaUserPurchaseRecord.setId(recordId);
		zaUserPurchaseRecord.setUserId(userId);
		zaUserPurchaseRecord.setUserType(1);//普通用户
		zaUserPurchaseRecord.setDealCode(id);
		zaUserPurchaseRecord.setPayCash(reward);
		zaUserPurchaseRecord.setPayType(0);//账户余额支付
		zaUserPurchaseRecord.setPayStatus(1);
		if(type==3||type==4) {		
			zaUserPurchaseRecord.setConsumType(1);//1打赏消费
			if(type==4) {
				//记录用户直播间打赏行为
				if(giftId!=null && giftNum!=null) {
					zaUserPurchaseRecord.setEventDesc("在"+awardeeName+"直播间送出了"+giftNum+"个"+giftName);
				}else {
					zaUserPurchaseRecord.setEventDesc("在"+awardeeName+"直播间送出打赏了"+reward+"元");
				}
			}else if(type==3) {
				//记录用户普通打赏行为
				if(giftId!=null && giftNum!=null) {
					zaUserPurchaseRecord.setEventDesc("向"+awardeeName+"进行一次打赏，送了"+giftNum+"个"+giftName);
				}else {
					zaUserPurchaseRecord.setEventDesc("向"+awardeeName+"进行一次打赏，打赏了"+reward+"元");
				}
			}
			
		}else if(type==1 ||type==2) {
			zaUserPurchaseRecord.setConsumType(2);//2 案件悬赏消费
			if(type==1) {
				//案件发布悬赏
				if(giftId!=null && giftNum!=null) {
					zaUserPurchaseRecord.setEventDesc("发布了一个案件委托,悬赏了"+giftNum+"个"+giftName);
				}else {
					zaUserPurchaseRecord.setEventDesc("发布了一个案件委托,悬赏了"+reward+"元");
				}
			}else if(type==2) {
				//代写文书
				if(giftId!=null && giftNum!=null) {
					zaUserPurchaseRecord.setEventDesc("发布了一个代写文书,悬赏了"+giftNum+"个"+giftName);
				}else {
					zaUserPurchaseRecord.setEventDesc("发布了一个代写文书,悬赏了"+reward+"元");
				}
			}
		}	
		zaUserPurchaseRecord.setInOut(1);
		zaUserPurchaseRecord.setConsumTime(new Date());
		AppMsgResult result=null;
		Integer k = zaUserPurchaseRecordMapperVo.insertUserPurchaseRecord(zaUserPurchaseRecord);
		if(k==1) {
			result =  AppMsgResult.result(200,"true",null);
		}else {
			result =  AppMsgResult.result(500,"系统插入消费记录失败！请稍后重试！",null);
		}
		
		return result;
	}
	
    
}

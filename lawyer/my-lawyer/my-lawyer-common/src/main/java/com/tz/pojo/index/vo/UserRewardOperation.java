package com.tz.pojo.index.vo;

import java.io.Serializable;

import com.tz.pojo.ZaSysNotice;
import com.tz.pojo.ZaUserIncomeRecord;
import com.tz.pojo.admin.ZaUserAwardRecordAdminVo;
/**
 * 案件布成功后向用户发出通知并将消费写入表中。
 * 直播打赏或者普通打赏，向受赏人收益表中写入记录并发出通知
 * @author QL
 *
 */
public class UserRewardOperation implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 812385298107418265L;

	private ZaUserAwardRecordAdminVo zaUserAwardRecordAdminVo;
	
	private ZaUserIncomeRecord zaUserIncomeRecord;
	
	private ZaSysNotice zaSysNotice;

	
	
	public ZaUserAwardRecordAdminVo getZaUserAwardRecordAdminVo() {
		return zaUserAwardRecordAdminVo;
	}

	public void setZaUserAwardRecordAdminVo(ZaUserAwardRecordAdminVo zaUserAwardRecordAdminVo) {
		this.zaUserAwardRecordAdminVo = zaUserAwardRecordAdminVo;
	}

	public ZaUserIncomeRecord getZaUserIncomeRecord() {
		return zaUserIncomeRecord;
	}

	public void setZaUserIncomeRecord(ZaUserIncomeRecord zaUserIncomeRecord) {
		this.zaUserIncomeRecord = zaUserIncomeRecord;
	}

	public ZaSysNotice getZaSysNotice() {
		return zaSysNotice;
	}

	public void setZaSysNotice(ZaSysNotice zaSysNotice) {
		this.zaSysNotice = zaSysNotice;
	}
	
	
	
	
	
}

package com.tz.pojo.index.vo;

import com.tz.pojo.ZaSysNotice;
import com.tz.res.Constant;
/**
 * 系统通知消息展示实体类
 * @author QL
 *
 */
public class ZaSysNoticeVo extends ZaSysNotice {

	private String name; //用户名称
	
	private String headUrl; //用户头像

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeadUrl() {
		return Constant.FILESERVER_URL+headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
	
	
	
}

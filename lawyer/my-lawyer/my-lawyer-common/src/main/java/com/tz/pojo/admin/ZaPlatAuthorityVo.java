package com.tz.pojo.admin;

import java.io.Serializable;
import java.util.Date;

import com.tz.pojo.ZaPlatAuthority;

public class ZaPlatAuthorityVo extends ZaPlatAuthority implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8329968503522217973L;
	//查询开始时间
		private Date startTime;
		//更新结束时间
		private Date endTime;
		
		public Date getStartTime() {
			return startTime;
		}
		public void setStartTime(Date startTime) {
			this.startTime = startTime;
		}
		public Date getEndTime() {
			return endTime;
		}
		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}
		
		
		
}

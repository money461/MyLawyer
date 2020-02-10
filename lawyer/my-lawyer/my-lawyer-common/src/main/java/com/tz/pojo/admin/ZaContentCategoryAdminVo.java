package com.tz.pojo.admin;

import java.io.Serializable;
import java.util.Date;

import com.tz.pojo.ZaContentCategory;
import com.tz.res.Constant;

public class ZaContentCategoryAdminVo extends ZaContentCategory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4701711980010988415L;

	   //查询开始时间
		private Date startTime;
		//更新结束时间
		private Date endTime;
		
		
		//修改分类图标地址
		@Override
		public String getLogoUrl() {
			String logoUrl = super.getLogoUrl();
			if(logoUrl!=null && !"".equals( logoUrl)){
				logoUrl =Constant.FILESERVER_URL+logoUrl;
			}else{
				logoUrl ="";
			}
			return logoUrl;
		}


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

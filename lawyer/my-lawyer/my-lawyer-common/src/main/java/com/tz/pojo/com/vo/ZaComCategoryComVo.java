package com.tz.pojo.com.vo;

import com.tz.pojo.ZaComCategory;
import com.tz.res.Constant;
/**
 * 
 * @author QL
 *
 *企业分类信息实体类封装
 */
public class ZaComCategoryComVo extends ZaComCategory {

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

}

package com.tz.pojo.index.vo;

import com.tz.pojo.ZaContentCategory;
import com.tz.res.Constant;
/**
 * 
 * @author QL
 *首页选项模块实体类
 */
public class ZaContentCategoryVo extends ZaContentCategory {
   
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
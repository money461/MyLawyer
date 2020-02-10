package com.tz.pojo.index.vo;

import com.tz.pojo.ZaGift;
import com.tz.res.Constant;

public class ZaGiftVo extends ZaGift {

	@Override
	public String getLogoUrl() {
		String logoUrl = super.getLogoUrl();
		if(logoUrl!=null && !"".equals(logoUrl)) {
			logoUrl=Constant.FILESERVER_URL+logoUrl;
		}else {
			logoUrl="";
		}
		
		return logoUrl;
	}

	
	
}

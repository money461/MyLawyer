package com.tz.pojo.index.vo;

import com.tz.pojo.ZaContent;
import com.tz.res.Constant;

public class ZaContentVo extends ZaContent {

	@Override
	public String getHomepageUrl() {
		String homepageUrl = super.getHomepageUrl();
		if(homepageUrl!=null && !"".equals(homepageUrl)) {
			homepageUrl = Constant.FILESERVER_URL+homepageUrl;
		}else {
			homepageUrl = "";
		}
		return homepageUrl;
	}


	//获取轮播图片
	public String[] getPrictures(){
		//获取从数据库查询出来的图片名称字符串
		String string = this.getPrictureUrl();
		if(string !=null && !"".equals(string)){
			String[] prictures = string.split(",");
			for(int i=0;i<prictures.length;i++){
				prictures[i] = Constant.FILESERVER_URL+prictures[i];
			}
			return prictures;
		}
		return new String[]{};
	}
    
	
	
}
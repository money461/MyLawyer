package com.tz.pojo.com.vo;

import com.tz.pojo.ZaComAuthentication;
import com.tz.res.Constant;
/**
 * 展示认证企业基本信息实体类
 * @author QL
 *
 */
public class ZaComAuthenticationComVo extends ZaComAuthentication {

	@Override
	public String getComLogo() {
		String comLogo = super.getComLogo();
		if(comLogo!=null && !"".equals(comLogo)) {
			comLogo = Constant.FILESERVER_URL+comLogo;
		}else {
			comLogo = "";
		}
		return comLogo;
	}

	@Override
	public String getLicenceUrl() {
		String licenceUrl = super.getLicenceUrl();
		if(licenceUrl!=null && !"".equals(licenceUrl)) {
			licenceUrl = Constant.FILESERVER_URL+licenceUrl;
		}else {
			licenceUrl = "";
		}
		return licenceUrl;
	}

	@Override
	public String getIdCard() {
		String idCard = super.getIdCard();
		if(idCard!=null && !"".equals(idCard)) {
			idCard = Constant.FILESERVER_URL+idCard;
		}else {
			idCard = "";
		}
		return idCard;
	}

	
	
	
}

package com.tz.pojo.index.vo;

/**
 * 展示个人中心页面数据
 * @author QL
 *
 */
public class ShowPersonalCenter {

	private PersonalInfo personalInfo;  //头像信息
	
	private IndexContentVo indexContentVo;  //设置信息

	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	public IndexContentVo getIndexContentVo() {
		return indexContentVo;
	}

	public void setIndexContentVo(IndexContentVo indexContentVo) {
		this.indexContentVo = indexContentVo;
	}

	
	
	
}

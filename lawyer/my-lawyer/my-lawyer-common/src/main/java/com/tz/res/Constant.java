package com.tz.res;

/**
 * 全局变量设置
 * @author menglin
 *
 */
public class Constant {

	
	public static final String USER_SESSION = "user";
	public static final String FILESERVER_URL_TEST = "http://47.96.181.35/";
	public static final String FILESERVER_URL = "http://47.96.181.35/";
//	public static final String FILESERVER_URL = "http://www.shcmdn.cn/";
	public static final String DFHEAD2 = "group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png";
	public static final String DFHEAD3 = "/group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png";

	
	//系统id
	public static final String SYSTEMID = "sysdfhuiashdkadjkewhfdfjkd";
	
	/**
	 * 消息队列消费者目的名称
	 */
	public static final String LAWYERHANDLECASE_QUEUE = "律师向案件所属用户发出帮助请求";
	public static final String USERORDETAKING_QUEUE = "用户回应律师承接案件的请求";
	public static final String ABANDDONPUBLISHCASE_QUEUE="用户或者律师放弃案件处理通知对方";
	public static final String LAWYERCOMPLETE_QUEUE= "律师向用户发出已完成案件请求";
	public static final String USERREPLEYCOMPLETECASE_QUEUE = "用户回复律师案件已完成的请求";
	public static final String USERINVITELAWYER_QUEUE = "用户邀请律师成为专属律师的请求";
	public static final String LAWYERAGREEORREFUSE_QUEUE = "律师回应用户邀请成为专属律师的请求";
	public static final String USERREWARDNOTICE_QUEUE = "系统向受赏者发出获得打赏通知";
	public static final String USERPUBLISHCASESUCCESSNOTICE_QUEUE = "系统向悬赏案件成功的用户发出通知";
	public static final String USERPUBLISHCASEOVERDUE_QUEUE = "案件过期通知案件所属用户";
	
	//后端接口白名单
	public static final String WHITE_lIST = "/admin/admin/user/api/adminLogin";
	
	
}

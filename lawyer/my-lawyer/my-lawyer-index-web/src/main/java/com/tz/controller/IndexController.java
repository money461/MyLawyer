package com.tz.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tz.pojo.ZaUserPublishCase;
import com.tz.pojo.index.vo.SelectionCriteria;
import com.tz.remote.IndexRemote;
import com.tz.res.AppMsgResult;

@RestController
@RequestMapping("index/api")
public class IndexController {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IndexRemote indexRemote;

	/**
	 * 获取个人普通用户/律师用户/企业用户主页面选择模块数据
	 * 
	 * @param userType
	 *            用户类型 1普通用户 2律师用户 3企业用户
	 * @param userId
	 * @param userToken
	 * @return
	 */
	@GetMapping("/getSelectionModel")
	public AppMsgResult getSelectionModel(@RequestParam(required = true, defaultValue = "1") Integer userType) {
		LOG.info("invoke----/getSelectionModel");
		AppMsgResult result = indexRemote.getSelectionModel(userType);
		return result;
	}

	
	/**
	 * 获取首页轮播图，法律咨询及内容
	 * 
	 * @param userType
	 * @param userId
	 * @param userToken
	 * @return
	 */
	@GetMapping("/getIndexContent")
	public AppMsgResult getIndexContent(@RequestParam(required = true, defaultValue = "1") Integer userType) {
		LOG.info("invoke----/getIndexContent");
		AppMsgResult result = indexRemote.getIndexContent(userType);
		return result;
	}

	/**
	  * 用户获取推荐律师或者咨询律师或者自主寻找律师信息
	 * @param sortCode 	推荐律师7  咨询律师3  自主寻找4 推荐委托8 代写文书9  个人用户附近 15 律师用户附近19 （必须传值）
	 * @param lon 用户当前位置经度 （必须传值）
	 * @param lat 用户当前位置纬度 （必须传值）
	 * @param userType 用户类型 （必须传值）
	 * @param isSort 是否按照距离排序 传 参数1=降序或null
	 * @param area 按照区域查询
	 * @param caseId 按照案件类型查询
	 * @param isTime 按照时间查询排序 参数1=降序 2=升序 或null
	 * @param isReward 按照赏金大小排序 参数1=降序 或 2=升序  或者null
	 * @param curPage 当前页码
	 * @param rows 每页记录数
	 * @param userId
	 * @param userToken
	 * @return
	 */
	@PostMapping("/getRecommendOrConsultingService")
	public AppMsgResult getRecommendOrConsultingService(SelectionCriteria selectionCriteria) {
		LOG.info("invoke----/getRecommendOrConsultingService");
		AppMsgResult result = indexRemote.getRecommendOrConsultingService(selectionCriteria);
		return result;
	}

	/**
	 * 获取案件类型
	 * 
	 * @param userId
	 * @param userToken
	 * @return
	 */
	@GetMapping("/getCaseCategory")
	public AppMsgResult getCaseCategory() {
		LOG.info("invoke----/getCaseCategory");
		AppMsgResult result = indexRemote.getCaseCategory();
		return result;
	}

	/**
	 * 发布委托代写文书 type=1 案件委托  type=2 代写文书
	 * 必须传参数：userId userToken caseId contentDesc province city  type=1/2    赏金reward endTime
	 * 注意： 1、可填写参数 礼品id giftId 数量giftNum
	 *      2、 手写赏金reward
	 * 
	 * 修改案件并重新发布案件
	 *   1、当案件还未支付时 status==7 可以修改案件所有信息 必须传案件id
	 *   2、当案件还未被律师承接时或者律师放弃解决案件时候（status==0 && status==5），只可以可以修改案件的基本信息 (修改案件内容、案件类型、省、市、截止时间) 不可修改悬赏金额。
	 *   3、修改重新发布必传参数 案件id
	 */
	@PostMapping("/publishCaseOrAllograph")
	public AppMsgResult publishCaseOrAllograph(ZaUserPublishCase zaUserPublishCase,BigDecimal reward,String giftId,Integer giftNum, @RequestParam(required=true) String userToken){
		 LOG.info("invoke----/publishCaseOrAllograph");
		 AppMsgResult result = indexRemote.publishCaseOrAllograph(zaUserPublishCase,reward,giftId,giftNum,userToken);
		return result;
	}

	/**
	 * 获取律师详情信息
	 * 
	 * @param id
	 *            //律师id
	 * @return
	 */
	@GetMapping("/getLawyerDetail")
	public AppMsgResult getLawyerDetail(@RequestParam(required = true) String id,
			  String userId, String userToken) {
		LOG.info("invoke----/getLawyerDetail");
		AppMsgResult result = indexRemote.getLawyerDetail(id, userId, userToken);
		return result;

	}

	/**
	 * 用户收藏操作
	 * 
	 * @param id
	 *            被收藏对象id
	 * @param obType
	 *            obtype = 1 用户收藏律师 2 用户收藏企业 3 用户收藏商品 4用户收藏案例
	 * @param userId
	 * @param userToken
	 * @return
	 */
	@PostMapping("/userCollection")
	public AppMsgResult userCollection(@RequestParam(required = true) String id,
			@RequestParam(required = true) Integer obType, @RequestParam(required = true) String userId,
			@RequestParam(required = true) String userToken) {
		LOG.info("invoke----/userCollection");
		AppMsgResult result = indexRemote.userCollection(id, obType, userId, userToken);
		return result;

	}

	/**
	 * 用户个人中心页面展示
	 * 
	 * @param userType
	 *            用户类型 1普通用户 2律师用户 3企业用户
	 * @param userId
	 * @param userToken
	 * @return
	 */
	@GetMapping("/showPersonalCenter")
	public AppMsgResult showPersonalCenter(@RequestParam(required = true) Integer userType, String userId) {
		LOG.info("invoke----/ShowPersonalCenter");
		AppMsgResult result = indexRemote.showPersonalCenter(userType, userId);
		return result;
	}

	/**
	 * 修改用户离线在线状态
	 * 
	 * @param userType
	 * @param state
	 *            需要修改的状态
	 * @param userId
	 * @param userToken
	 * @return
	 */
	@PostMapping("/updateOnlineState")
	public AppMsgResult updateOnlineState(@RequestParam(required = true) Integer userType,
			@RequestParam(required = true) Integer state, @RequestParam(required = true) String userId,
			@RequestParam(required = true) String userToken) {
		LOG.info("invoke----/updateOnlineState");
		AppMsgResult result = indexRemote.updateOnlineState(userType, state, userId, userToken);
		return result;
	}

	/**
	 * 查看用户个人中心 我的律师数据
	 * 
	 * @param userType
	 * @param userId
	 * @param userToken
	 * @return
	 */
	@GetMapping("/getMyLawyer")
	public AppMsgResult getMyLawyer(@RequestParam(required = true) String userAddress, Integer userType,
			@RequestParam(required = true) String userId, @RequestParam(required = true) String userToken) {
		LOG.info("invoke----/getMyLawyer");
		AppMsgResult result = indexRemote.getMyLawyer(userAddress, userType, userId, userToken);
		return result;

	}

	/**
	 * 查看我的委托
	 * 
	 * @param userId
	 * @param userToken
	 * @return
	 */
	@GetMapping("/getMyPublishCase")
	public AppMsgResult getMyPublishCaseById(@RequestParam(required = true) String userId,
			@RequestParam(required = true) String userToken, Integer curPage, Integer rows) {
		LOG.info("invoke----/getMyPublishCase");
		AppMsgResult result = indexRemote.getMyPublishCaseById(userId, userToken, curPage, rows);
		return result;

	}

	/**
	 * 查看我的收藏
	 * 
	 * @param obType
	 *            1律师 2 企业 3 商品 4 案件
	 * @param lon
	 *            经度
	 * @param lat
	 *            纬度
	 * @param userId
	 * @param userToken
	 * @return
	 */
	@GetMapping("/getMyCollectionById")
	public AppMsgResult getMyCollectionById(@RequestParam(required = true, defaultValue = "1") Integer obType,
			Double lon, Double lat, @RequestParam(required = true) String userId,
			@RequestParam(required = true) String userToken) {
		LOG.info("invoke----/getMyCollectionById");
		AppMsgResult result = indexRemote.getMyCollectionById(obType, lon, lat, userId, userToken);
		return result;
	}

	/**
	 * 取消收藏
	 * 
	 * @param id
	 *            //被收藏对象id
	 * @param userId
	 * @param userToken
	 * @return
	 */
	@PostMapping("/cancelCollection")
	public AppMsgResult cancelCollection(@RequestParam(required = true) String id,
			@RequestParam(required = true) String userId, @RequestParam(required = true) String userToken) {
		LOG.info("invoke----/cancelCollection");
		AppMsgResult result = indexRemote.cancelCollection(id, userId, userToken);
		return result;

	}

	/**
	 * 查看用户余额消费值信息
	 */
	@GetMapping("/getUserProfitById")
	public AppMsgResult getUserProfit(@RequestParam(required = true) String userId,
			@RequestParam(required = true) String userToken) {
		LOG.info("invoke----/getUserProfit");
		AppMsgResult result = indexRemote.getUserProfitById(userId, userToken);
		return result;

	}

	/**
	 * 获取用户交易明细
	 * 
	 * @param userId
	 * @param userToken
	 * @return
	 */
	@GetMapping("/getDealDetail")
	public AppMsgResult getDealDetail(@RequestParam(required = true) String userId,
			@RequestParam(required = true) String userToken, Integer curPage, Integer rows) {
		LOG.info("invoke----/getDealDetail");
		AppMsgResult result = indexRemote.getDealDetail(userId, userToken, curPage, rows);
		return result;
	}

	/**
	 * 律师获取我的客户信息
	 * 
	 * @param lon
	 * @param lat
	 * @return
	 */
	@GetMapping("/getMyCustomer")
	public AppMsgResult getMyCustomer(@RequestParam(required = true) Double lon,
			@RequestParam(required = true) Double lat, @RequestParam(required = true) String userId,
			@RequestParam(required = true) String userToken) {
		LOG.info("invoke----/getMyCustomer");
		AppMsgResult result = indexRemote.getMyCustomer(lon, lat, userId, userToken);
		return result;

	}

	/**
	 * 获取律师用户承接的案件委托信息
	 * 
	 * @param userId
	 * @param userToken
	 * @return
	 */
	@GetMapping("/getPublishCaseByLawId")
	public AppMsgResult getPublishCaseByLawId(@RequestParam(required = true) String userId,
			@RequestParam(required = true) String userToken) {
		LOG.info("invoke----/getMyCustomer");
		AppMsgResult result = indexRemote.getPublishCaseByLawId(userId, userToken);
		return result;
	}

}

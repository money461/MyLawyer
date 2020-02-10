package com.tz.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.distance.DistanceUtils;
import com.spatial4j.core.io.GeohashUtils;
import com.tz.cache.RedisCache;
import com.tz.comparator.DistanceComparator;
import com.tz.id.IDUtils;
import com.tz.mapper.index.vo.ZaCaseCategoryMapperIndexVo;
import com.tz.mapper.index.vo.ZaContentCategoryMapperVo;
import com.tz.mapper.index.vo.ZaGiftMapperVo;
import com.tz.mapper.index.vo.ZaLawyerAuthenticationMapperIndexVo;
import com.tz.mapper.index.vo.ZaUserAwardRecordMapperVo;
import com.tz.mapper.index.vo.ZaUserCollectionMapperVo;
import com.tz.mapper.index.vo.ZaUserDealDetailMapperVo;
import com.tz.mapper.index.vo.ZaUserMapperVo;
import com.tz.mapper.index.vo.ZaUserProfitMapperVo;
import com.tz.mapper.index.vo.ZaUserPublishCaseMapperVo;
import com.tz.pojo.ZaUserAwardRecord;
import com.tz.pojo.ZaUserCollection;
import com.tz.pojo.ZaUserProfit;
import com.tz.pojo.ZaUserPublishCase;
import com.tz.pojo.index.vo.SelectionCriteria;
import com.tz.pojo.index.vo.DealDetail;
import com.tz.pojo.index.vo.IndexContentVo;
import com.tz.pojo.index.vo.LawyerDetailInfo;
import com.tz.pojo.index.vo.MyCustomer;
import com.tz.pojo.index.vo.PersonalInfo;
import com.tz.pojo.index.vo.RecomPublishCaseVo;
import com.tz.pojo.index.vo.RecomSelectionLawyerVo;
import com.tz.pojo.index.vo.ShowPersonalCenter;
import com.tz.pojo.index.vo.ZaCaseCategoryIndexVo;
import com.tz.pojo.index.vo.ZaComAuthenticationIndexVo;
import com.tz.pojo.index.vo.ZaContentCategoryVo;
import com.tz.pojo.index.vo.ZaGiftVo;
import com.tz.pojo.index.vo.ZaLawyerAuthenticationIndexVo;
import com.tz.pojo.index.vo.ZaUserPublishCaseVo;
import com.tz.res.AppMsgResult;
import com.tz.service.IndexService;
import com.tz.util.AddressToGpsUtil;


@Service("indexService")
public class indexServiceImpl implements IndexService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ZaContentCategoryMapperVo zaContentCategoryMapperVo;
	
	@Autowired
	private ZaLawyerAuthenticationMapperIndexVo zaLawyerAuthenticationMapperVo;
	
	@Autowired
	private RedisCache cache;
	
	@Autowired
	private ZaCaseCategoryMapperIndexVo zaCaseCategoryMapperVo;
	
	@Autowired
	private ZaUserPublishCaseMapperVo zaUserPublishCaseMapperVo;
	
	@Autowired
	private ZaUserMapperVo zaUserMapperVo;
	
	@Autowired
	private ZaUserCollectionMapperVo zaUserCollectionMapperVo;
	
	@Autowired
	private ZaUserAwardRecordMapperVo zaUserAwardRecordMapperVo;
	
	@Autowired
	private ZaUserProfitMapperVo zaUserProfitMapperVo;
	
	@Autowired
	private ZaUserDealDetailMapperVo zaUserDealDetailMapperVo;
	
	@Autowired
	private ZaGiftMapperVo zaGiftMapperVo;
	
	//校验useruserToken是否过期存在
	public AppMsgResult checkUserToken(String userId,String userToken){
		AppMsgResult msgResult = null; 
			if(StringUtils.isNotEmpty(userToken)){
				//获取用户的登录 token
				String cache_key=RedisCache.CAHCENAME+"|getUserToken|"+userId;
				String userToken_cache = cache.getCache(cache_key, String.class);
				//是否存在
				if(null != userToken_cache && userToken.equals(userToken_cache)){
					//重置userToken有效时间
					cache.putCacheWithExpireTime(cache_key, userToken_cache, RedisCache.USERCAHCETIME);
					msgResult= AppMsgResult.nodata(true,"success");
				}else{
					msgResult= AppMsgResult.nodata(true, "failure");
				}
			}else{
			msgResult= AppMsgResult.nodata(false, "failure");
		}
		return msgResult;
		
	}


	/**
	 * 计算用户与律师距离并排序
	 */
	public List<RecomSelectionLawyerVo> HandlerComputeRangeAndSort(List<RecomSelectionLawyerVo> RecommendLawInfoList,Double lon,Double lat,Integer isSort){
		for (RecomSelectionLawyerVo recomSelectionLawyerVo : RecommendLawInfoList) {
			List<ZaLawyerAuthenticationIndexVo> lawyerAuthenticationList = recomSelectionLawyerVo.getRecommendLawInfoList();
			for (ZaLawyerAuthenticationIndexVo zaLawyerAuthenticationVo : lawyerAuthenticationList) {
				Double comLon = zaLawyerAuthenticationVo.getComLon();
				Double comLat = zaLawyerAuthenticationVo.getComLat();
				SpatialContext geo = SpatialContext.GEO;
				double distance = geo.calcDistance(geo.makePoint(lon, lat), geo.makePoint(comLon, comLat))*DistanceUtils.DEG_TO_KM;
				zaLawyerAuthenticationVo.setDistance(String.valueOf(new Double(distance).intValue())+"KM");
				
			}
			if(isSort!=null && isSort==1) {
				//根据距离排序
				Collections.sort(lawyerAuthenticationList, new DistanceComparator());
			}
		}
		return RecommendLawInfoList;
		
	}

	
	/**
	 * 获取普通个人用户或者企业用户选择模块
	 * userType 用户类型 1普通用户 2律师用户 3企业用户
	 */
	@Override
	public AppMsgResult getSelectionModel(Integer userType) {
		AppMsgResult result =null;
			//校验用户类型
			if(userType==1 || userType==2 || userType==3 && userType!=null) {
				List<ZaContentCategoryVo> selectionModeList =null;
					//普通用户
					//向缓存中获取数据
					String selectModelCache_key = RedisCache.CAHCENAME+"|getSelectionModel|"+userType;
						selectionModeList = cache.getListCache(selectModelCache_key, ZaContentCategoryVo.class);
					if(selectionModeList!=null) {
						LOG.info("get cache with key:"+selectModelCache_key);
					}else {
						selectionModeList = zaContentCategoryMapperVo.getSelectionModel(userType);
						if(selectionModeList.size()!=0) {
							//存入缓存
							cache.putListCache(selectModelCache_key, selectionModeList);
							LOG.info("put cache with key:"+selectModelCache_key);
						}else {
							return AppMsgResult.result(401,"数据没找到！","");
						}
					}
					result = AppMsgResult.result(200, "success",selectionModeList);
				
			}else {
				result = AppMsgResult.result(543, "用户类型userType传输错误！",null);
			}
			
		return result;
	}
  
	/**
	 * 获取用户主页面轮播图和法律资讯内容
	 */
	@Override
	public AppMsgResult getIndexContent(Integer userType) {
		AppMsgResult result =null;
			//用户已登录
			//校验用户类型
			if(userType==1 || userType==2 || userType==3 && userType!=null) {
				List<IndexContentVo> indexContentList =null;
					//普通用户
					//向缓存中获取数据
					String indexContentCache_key = RedisCache.CAHCENAME+"|getIndexContent|"+userType;
					indexContentList = cache.getListCache(indexContentCache_key, IndexContentVo.class);
					if(indexContentList!=null) {
						LOG.info("get cache with key:"+indexContentCache_key);
					}else {
						indexContentList = zaContentCategoryMapperVo.getIndexContent(userType);
						if(indexContentList.size()>0) {
							//存入缓存
							cache.putListCache(indexContentCache_key, indexContentList);
							LOG.info("put cache with key:"+indexContentCache_key);
						}else {
							return AppMsgResult.result(401,"数据没找到！","");
						}
					}
					result = AppMsgResult.result(200, "success",indexContentList);
				
			}else {
				result = AppMsgResult.result(543, "用户类型userType传输错误！",null);
			}
			
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
	 * @param isReward 按照赏金大小排序 参数1 降序 或 2升序  或者null
	 * @param curPage 当前页码
	 * @param rows 每页记录数
	 * @param userId
	 * @param userToken
	 * @return
     */
	@Override
	public AppMsgResult getRecommendOrConsultingService(SelectionCriteria selectionCriteria) {
		AppMsgResult result =null;
		/*String userAddress = selectionCriteria.getUserAddress();
		if(StringUtils.isEmpty(userAddress)) {
			return AppMsgResult.result(543, "userAddress必须传值！",null);
		}*/
		Double lon = selectionCriteria.getLon();
		if(lon==null) {
			return AppMsgResult.result(543, "用户经度必须传值！",null);
		}
		Double lat = selectionCriteria.getLat();
		if(lat==null) {
			return AppMsgResult.result(543, "用户纬度必须传值！",null);
		}
		
		Integer sortCode = selectionCriteria.getSortCode();
		if(sortCode==null || sortCode!=7 && sortCode!=3 && sortCode !=4 && sortCode!=5 && sortCode !=6  && sortCode!=8 && sortCode!=9 && sortCode!=15 && sortCode!=19 ) {
			return AppMsgResult.result(543, "sortCode传值有误！",null);
		}
		Integer userType = selectionCriteria.getUserType();
		String area = selectionCriteria.getArea();
		Integer isSort = selectionCriteria.getIsSort();
	     Integer caseId = selectionCriteria.getCaseId();
		Integer curPage = selectionCriteria.getCurPage();
		Integer rows = selectionCriteria.getRows();
		
		System.out.println("userType--------------:"+userType);
//		System.out.println("userAddress-------------:"+userAddress);
		System.out.println("当前用户经度-------------:"+lon);
		System.out.println("当前用户纬度-------------:"+lat);
		System.out.println("area-----------------:"+area);
		System.out.println("sortCode 展示类别--------------:"+sortCode);
		System.out.println("isSort是否排序----------------:"+isSort);
		System.out.println("caseId-------------------："+caseId);
		System.out.println("curPage------------------："+curPage);
		System.out.println("rows-----------------------:"+rows);
		
		if(isSort!=null && isSort!=1) {
			return AppMsgResult.result(543, "是否排序参数错误！",null);
		}
		 /**
		  * 分页处理    推荐律师7 推荐委托8 推荐文书 9 不分页处理
		  */
		   if(sortCode!=7 && sortCode!=8 && sortCode!=9) {
			
			   /*放弃验证
			 //判断用户是否登录
				String userId=selectionCriteria.getUserId();
				if(StringUtils.isEmpty(userId)) {
					return AppMsgResult.result(543, "用户id必须传值！",null);
				}
				String userToken = selectionCriteria.getUserToken();
				if(StringUtils.isEmpty(userToken)) {
					return AppMsgResult.result(543, "userToken必须传值！",null);
				}
			   //校验用户是否登录
			   AppMsgResult msgResult = checkUserToken(userId, userToken);
			   if(!(boolean)msgResult.getFlag()) {
				   return  AppMsgResult.result(538, "用户未登录！",null);
				}*/
			   
			   
			   rows = rows == null?10:rows;
			   curPage = curPage == null?1:curPage;
			   //分页处理
			   PageHelper.startPage(curPage, rows);
			   
		   }
		
			//校验用户类型
		   if(userType==null ) {
			   return AppMsgResult.result(543, "请传值用户类型userType！",null);
		   }
			if( userType==1 || userType==2 || userType==3 ) {
				
				//用户已登录获取用户当前位置的经纬度
		/*		Map<String, Double> json = AddressToGpsUtil.getGeocoderLatitude(userAddress);
				//获取用户地址经纬度
				Double lon  = json.get("lon");
				Double lat = json.get("lat");*/
				String geoCode=null;
				if(StringUtils.isEmpty(area)) { //默认状态
					//获取用户地址code  按照该用户的当前位置查询
					if(userType==2) {
						//当为律师用户时，不按照用户地理位置查询，默认情况按照最新发布时间查询展示
						geoCode = null;
					}else {
						//普通用户或企业用户按照用户附近位置查询展示
						geoCode = GeohashUtils.encodeLatLon(lon, lat,3);
					}
				}else {
					//获取地址查询条件
					Map<String, Double> json2 = AddressToGpsUtil.getGeocoderLatitude(area);
					//获取条件地址经纬度
					Double lon2  = json2.get("lon");
					Double lat2 = json2.get("lat");
					geoCode = GeohashUtils.encodeLatLon(lon2, lat2,3);
				}
				System.out.println("模糊查询地址code-------------"+geoCode);
				
				//设置sql语句条件参数
				 Map<String,Object> map = new HashMap<String, Object>();
				 map.put("sortCode", sortCode); //展示分类
				 map.put("geoCode", geoCode);//模糊查询地址code
				 map.put("caseId", caseId); //案件分类
				
				 
				 /**
				  * 根据不同的用户查询展示不同的数据信息
				  */
				 	//查询普通或者律师用户数据
						if(userType==1 || userType==3) {
							//普通用户或者企业类型 主页随机推荐2名附近的律师信息
							List<RecomSelectionLawyerVo> RecommendLawInfoList = zaLawyerAuthenticationMapperVo.getRecommendLawInfo(map);
							//将附近结果查询出来的结果集计算距离(首页默认不排序)
							List<RecomSelectionLawyerVo> handlerComputeRangeAndSort = this.HandlerComputeRangeAndSort(RecommendLawInfoList, lon, lat,isSort);
							//数据分页
					        PageInfo<RecomSelectionLawyerVo> pageInfo = new PageInfo<RecomSelectionLawyerVo>(handlerComputeRangeAndSort);
							//展示分页结果
							result = AppMsgResult.result(200,"success",pageInfo);
						}else if(userType==2) {
							//律师类型
							Integer isTime = selectionCriteria.getIsTime();
							if(isTime!=null && isTime!=1 && isTime!=2) {
								return AppMsgResult.result(543, "是否按照时间排序参数错误！",null);
							}
							Integer isReward = selectionCriteria.getIsReward();
							if(isReward!=null && isReward!=1 && isReward!=2 ) {
								return AppMsgResult.result(543,"是否按照赏金排序参数错误！",null);
							}
							if(isTime!=null && isReward!=null) {
								return AppMsgResult.result(543,"只能按照一种方式排序！",null);
							}
							
							//是否按照时间排序
							map.put("isTime", isTime);
							//是否按照赏金排序
							map.put("isReward", isReward);
							List<RecomPublishCaseVo> userPublishCaseList = zaUserPublishCaseMapperVo.getUserPublishCase(map);
							//数据分页
					        PageInfo<RecomPublishCaseVo> pageInfo = new PageInfo<RecomPublishCaseVo>(userPublishCaseList);
					        //展示分页结果
							result = AppMsgResult.result(200,"success",pageInfo);
						}
						
			}else {
				result = AppMsgResult.result(543, "用户类型userType传输错误！",null);
			}
			
		return result;
		
	}


	/**
	 * 获取案件类型
	 */
	@Override
	public AppMsgResult getCaseCategory() {
		AppMsgResult result =null;
			
			//缓存中获取所有案件类型信息
			List<ZaCaseCategoryIndexVo> caseCategoryList=null;
			String getCaseCategory_key = RedisCache.CAHCENAME+"|getCaseCategory|";
			caseCategoryList = cache.getListCache(getCaseCategory_key, ZaCaseCategoryIndexVo.class);
			if(caseCategoryList!=null) {
				LOG.info("get cache with key:"+getCaseCategory_key);
			}else {
				//获取父目录节点数据
				Integer i=0;
				caseCategoryList = zaCaseCategoryMapperVo.getCaseCategoryBypId(i);
				if(caseCategoryList.size()>0) {
					for (ZaCaseCategoryIndexVo zaCaseCategory : caseCategoryList) {
						Integer getpId = zaCaseCategory.getId();
						List<ZaCaseCategoryIndexVo> list = zaCaseCategoryMapperVo.getCaseCategoryBypId(getpId);
						zaCaseCategory.setCaseCategoryList(list);
					}
					cache.putListCache(getCaseCategory_key, caseCategoryList);
					LOG.info("put cache with key:"+getCaseCategory_key);
				}else {
					return AppMsgResult.result(401,"数据没找到！","");
				}
			}
				
			result = AppMsgResult.result(200, "success",caseCategoryList);
		return result;
	}


	
	
	/**
	 * 	/**
	 * 发布委托代写文书       type=1 案件委托  type=2 代写文书
	 * 必须传参数：userId userToken caseId contentDesc province city  type=1/2    赏金reward endTime
	 * 注意：1、可填写参数 礼品id giftId 数量giftNum
	 *      2、 赏金reward请计算应支付总金额传过来。
	 * 
	 * 修改案件并重新发布案件
	 *   1、当案件还未支付时 status==7 可以修改案件所有信息 必须传案件id
	 *   2、当案件还未被律师承接时或者律师放弃解决案件时候（status==0 && status==5），只可以可以修改案件的基本信息 (修改案件内容、案件类型、省、市、截止时间) 不可修改悬赏金额。
	 *   3、修改重新发布必传参数 案件id
	 *
	 * @param zaUserPublishCase
	 * @param giftId
	 * @param giftNum
	 * @return
	 */
	@Override
	public AppMsgResult publishCaseOrAllograph(ZaUserPublishCase zaUserPublishCase,BigDecimal reward,String giftId,Integer giftNum,String userToken) {
		AppMsgResult result =null;
		//判断用户是否登录
		String userId = zaUserPublishCase.getUserId();
		System.out.println("--------------------------------"+userId);
		if(StringUtils.isEmpty(userId)) {
			return AppMsgResult.result(543, "userId必须传值！", null);
		}
		AppMsgResult msgResult = checkUserToken(userId, userToken);
		if((boolean)msgResult.getFlag()) {
	
		ZaGiftVo gift=null;
		if(StringUtils.isNotBlank(giftId) && giftNum==null) {
			return AppMsgResult.result(543, "礼品与礼品数量必须同时传值！", null);
		}else if(StringUtils.isBlank(giftId) && giftNum!=null) {
			return AppMsgResult.result(543, "礼品与礼品数量必须同时传值！", null);
		}else if(StringUtils.isNotBlank(giftId) && giftNum!=null) {
			//查询礼品
			if(giftNum!=null && giftNum<1) {
				return AppMsgResult.result(543, "礼品数量必须是大于等于1的正整数！",null);
			}
			 gift = zaGiftMapperVo.queryGiftById(giftId);
			 if(gift!=null) {
				 BigDecimal price = gift.getPrice();//礼品价钱
				 //计算所需支付赏金
				 reward = price.multiply(new BigDecimal(giftNum));
			 }else {
				 return AppMsgResult.result(543, "悬赏礼品信息未找到！", null);
			 }
		}
	
			
		String cid = zaUserPublishCase.getId();
		//判断修改重新发布还是发布新的案件
		if(StringUtils.isNotBlank(cid)) {
			//修改重新发布案件
			Integer type = zaUserPublishCase.getType();
			if(type!=null && type!=1 && type!=2) {
				return AppMsgResult.result(543, "发布案件类型传值有误！", null);
			}
			zaUserPublishCase.setStatus(0);//设置为等待解决状态
			//判断当前案件状态
			ZaUserPublishCase publishCase = zaUserPublishCaseMapperVo.queryPublishCaseById(cid);
			if(publishCase==null) {
				 return AppMsgResult.result(543, "要修改的案件不存在！", null);
			}
			Integer now_status= publishCase.getStatus();
			if(now_status==0 || now_status==5 ||now_status==7 ){  
				//处于寻求解决状态时候方可修改
				if(now_status==7) {
					if(reward!=null) {
						//打赏金额必须大于1
						if( reward.compareTo(new BigDecimal(1))==1) {
							//修改悬赏信息  获取悬赏记录主键id
							String awardId = publishCase.getAwardId();
							//删除悬赏记录
							ZaUserAwardRecord rewardRecord = zaUserAwardRecordMapperVo.getRewardRecordById(awardId);
							if(rewardRecord!=null) {
								//删除该悬赏记录
								zaUserAwardRecordMapperVo.deleteUserAwardRecordById(awardId);
							}else {
								return AppMsgResult.result(556, "用户悬赏记录未找到！", null);
							}
							
							//重新写入该悬赏记录
							//生成悬赏记录
							ZaUserAwardRecord zaUserAwardRecord = new ZaUserAwardRecord();
							String rId  = IDUtils.genId();
							zaUserAwardRecord.setId(rId);
							zaUserAwardRecord.setUserId(userId);
							zaUserAwardRecord.setGiftId(giftId);
							if(gift!=null) {
								zaUserAwardRecord.setPrice(gift.getPrice());
							}
							zaUserAwardRecord.setGiftNum(giftNum);
							zaUserAwardRecord.setAwardStatus(0);
							zaUserAwardRecord.setReward(reward.setScale(2, BigDecimal.ROUND_HALF_UP));  //悬赏金额保留2位
							zaUserAwardRecord.setType(type);
							zaUserAwardRecord.setCreatedTime(new Date());
							zaUserAwardRecord.setUpdatedTime(new Date());
							Integer i = zaUserAwardRecordMapperVo.insertUserAwardRecord(zaUserAwardRecord);
							if(i==1) {
								zaUserPublishCase.setAwardId(rId); //更新案件委托表 悬赏记录id
							}else {
								return AppMsgResult.result(500, "悬赏记录插入失败！", null);
							}
						}else if(reward.compareTo(new BigDecimal(1))!=1) {
							return AppMsgResult.result(568, "悬赏金额必须大于1", null);
						}
					}
				}
				
				Integer i = zaUserPublishCaseMapperVo.updatePublishCase(zaUserPublishCase);
				if(i==1) {
					result = AppMsgResult.result(200, "重新发布成功！", null); 
				}else {
					result = AppMsgResult.result(543, "重新发布失败！请稍后再试", null); 
				}
				
			}else if(now_status==1) {
				result = AppMsgResult.result(543,"律师正在解决案件不能随意修改,律师放弃解决后方可修改重新发布!", null);
				
			}else if(now_status==3) {
				result = AppMsgResult.result(543,"用户放弃解决的案件不能再修改了,建议您发布一条新的案件！",null);	
			}else {
				result = AppMsgResult.result(543,"案件处于不能修改的状态",null);
			}
			
		}else {
			//发布案件
			if(reward==null){
				return AppMsgResult.result(543, "请选择悬赏的礼品、或者填写悬赏金额！", null);
			}else if( reward.compareTo(new BigDecimal(1))!=1) {
				return AppMsgResult.result(568, "悬赏金额必须大于1", null);
			}
			
			Integer caseId = zaUserPublishCase.getCaseId();
			if(caseId==null) {
				return AppMsgResult.result(543, "案件类型caseId必须传值！", null);
			}
			String contentDesc = zaUserPublishCase.getContentDesc();
			if(StringUtils.isEmpty(contentDesc)) {
				return AppMsgResult.result(543, "contentDesc必须传值！", null);
			}
			String province = zaUserPublishCase.getProvince();
			if(StringUtils.isEmpty(province)) {
				return AppMsgResult.result(543, "province必须传值！", null);
			}
			String city = zaUserPublishCase.getCity();
			if(StringUtils.isEmpty(city)) {
				return AppMsgResult.result(543, "city必须传值！", null);
			}
			/*String caseAddress = zaUserPublishCase.getCaseAddress();
			if(StringUtils.isEmpty(caseAddress)) {
				return AppMsgResult.result(543, "案件发布物理地址caseAddress必须传值！", null);
			}*/
			Double caseLon = zaUserPublishCase.getCaseLon();
			if(caseLon==null) {
				return AppMsgResult.result(543, "案件发布经度caseLon必须传值！", null);
			}
			Double caseLat = zaUserPublishCase.getCaseLat();
			if(caseLat==null) {
				return AppMsgResult.result(543, "案件发布经度caseLat必须传值！", null);
			}
			
			
			Integer type = zaUserPublishCase.getType();
			if(type==null ||type!=1 && type!=2) {
				return AppMsgResult.result(543, "发布类型传值有误！", null);
			}
			Date endTime = zaUserPublishCase.getEndTime();
			if(endTime==null) {
				return AppMsgResult.result(543, "截止时间必须传值！", null);
			}
			
			
			//生成悬赏记录
			ZaUserAwardRecord zaUserAwardRecord = new ZaUserAwardRecord();
			String rId  = IDUtils.genId();
			zaUserAwardRecord.setId(rId);
			zaUserAwardRecord.setUserId(userId);
			zaUserAwardRecord.setGiftId(giftId);
			if(gift!=null) {
				zaUserAwardRecord.setPrice(gift.getPrice());
			}
			zaUserAwardRecord.setGiftNum(giftNum);
			zaUserAwardRecord.setAwardStatus(0);  //默认未支付案件悬赏
			zaUserAwardRecord.setReward(reward.setScale(2, BigDecimal.ROUND_HALF_UP));  //悬赏金额保留2位
			zaUserAwardRecord.setType(type);
			zaUserAwardRecord.setCreatedTime(new Date());;			
			zaUserAwardRecord.setUpdatedTime(new Date());
			Integer i = zaUserAwardRecordMapperVo.insertUserAwardRecord(zaUserAwardRecord);
			if(i==1) {
				zaUserPublishCase.setAwardId(rId); //更新案件委托表 悬赏记录id
			}else {
				return AppMsgResult.result(500, "悬赏记录插入失败！", null);
			}
			
			//生成案件
			String id = IDUtils.genId();
			zaUserPublishCase.setId(id);
			//用户已登录获取用户当前位置的经纬度
			/*Map<String, Double> json = AddressToGpsUtil.getGeocoderLatitude(zaUserPublishCase.getCaseAddress());
			//获取用户地址经纬度
			Double lon  = json.get("lon");
			Double lat = json.get("lat");
			zaUserPublishCase.setCaseLon(lon);
			zaUserPublishCase.setCaseLat(lat);*/
			String geoCode = GeohashUtils.encodeLatLon(caseLon, caseLat);
			zaUserPublishCase.setGeoCode(geoCode);
			zaUserPublishCase.setStatus(7);  //设置案件状态 默认未悬赏成功案件
			
			Date date = new Date();
			zaUserPublishCase.setCreatedTime(date);
			zaUserPublishCase.setUpdatedTime(date);
			Integer k = zaUserPublishCaseMapperVo.insertUserPublishCase(zaUserPublishCase);
			if(k==1) {
				result = AppMsgResult.result(200, "发布成功！", id);
			}else {
				result = AppMsgResult.result(500, "发布失败，系统繁忙请稍后再试！",null);
			}
	    }
		
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		return result;
	}


	/**
	 * 获取律师详细信息
	 * id 律师id
	 * userId
	 * userToken
	 */
	@Override
	public AppMsgResult getLawyerDetail(String id, String userId,String userToken) {
		AppMsgResult result =null;
		  String lawyerDetailInfoCache_key = RedisCache.CAHCENAME+"|getLawyerDetailInfo|"+id;
		  LawyerDetailInfo lawyerDetailInfo = cache.getCache(lawyerDetailInfoCache_key, LawyerDetailInfo.class);
		  if(lawyerDetailInfo!=null) {
			  LOG.info("get cache with key:"+lawyerDetailInfoCache_key);
		  }else {
			  lawyerDetailInfo = zaLawyerAuthenticationMapperVo.getLawyerDetailById(id);
			  if(lawyerDetailInfo!=null) {
				  //存入缓存
				  cache.putCacheWithExpireTime(lawyerDetailInfoCache_key, lawyerDetailInfo,RedisCache.ACCESSTOKENTIME);
				  LOG.info("put cache with key:"+lawyerDetailInfoCache_key);
			  }else {
				  return AppMsgResult.result(401,"数据没找到！","");
			  }
		  }
		   //检查该用户是否已收藏该律师
		     Integer isCollection = zaUserCollectionMapperVo.queryUserCollectionById(userId,id);
		     System.out.println("是否收藏：--------------"+isCollection);
		     lawyerDetailInfo.setIsCollection(isCollection);
		  
		     result = AppMsgResult.result(200, "success",lawyerDetailInfo);
		 
		return result;
	}


	/**
	 * 用户收藏操作  1 用户收藏律师 2 用户收藏企业 3 用户收藏商品 4用户收藏案例
	 */
	@Override
	public AppMsgResult userCollection(String id,Integer obType, String userId, String userToken) {
		if(obType!=1 && obType!=2 && obType!=3 && obType!=4) {
			return AppMsgResult.result(543, "收藏类型参数错误!", null);
		}
		AppMsgResult result =null;
		//判断用户是否登录
		AppMsgResult msgResult = checkUserToken(userId, userToken);
		if((boolean)msgResult.getFlag()) {
			Integer cid = zaUserCollectionMapperVo.queryUserCollectionById(userId, id);
			if(cid==1) {
				return AppMsgResult.result(559, "已收藏过了！！", null);
			}else if(cid==0) {
				ZaUserCollection zaUserCollection = new ZaUserCollection();
				String collectionId = IDUtils.genId();
				zaUserCollection.setId(collectionId);
				zaUserCollection.setUserId(userId);
				zaUserCollection.setObType(obType);
				zaUserCollection.setCollectionId(id);
				zaUserCollectionMapperVo.insertUserCollection(zaUserCollection);
				result = AppMsgResult.result(200, "已成功收藏！",null);
			}else {
				result = AppMsgResult.result(543,"收藏失败~",null);
			}
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		return result;
	}


	/**
	 * 用户个人中心页面展示
	 * @param userType 用户类型 1普通用户 2律师用户 3企业用户
	 */
	@Override
	public AppMsgResult showPersonalCenter(Integer userType, String userId) {
		if(userType==null || userType!=1 && userType!=2 && userType!=3) {
			return AppMsgResult.result(543, "用户类型参数值有误！",null);
		}
		AppMsgResult result =null;
		ShowPersonalCenter showPersonalCenter = new ShowPersonalCenter();
		//不用判断用户是否登录
			//从缓存中获取个人中心图标
			String personalCenterCache_key =RedisCache.CAHCENAME+"|showPersonalCenter|"+userType;
			IndexContentVo indexContentVo = cache.getCache(personalCenterCache_key, IndexContentVo.class);
			if(indexContentVo!=null) {
				LOG.info("get cache with key:"+personalCenterCache_key);
			}else {
				//数据库获取数据
				indexContentVo = zaContentCategoryMapperVo.getPersonalCenter(userType);
				if(indexContentVo!=null){
					//存入缓存
					cache.putCache(personalCenterCache_key, indexContentVo);
					LOG.info("put cache with key:"+personalCenterCache_key);
				}
			}
			showPersonalCenter.setIndexContentVo(indexContentVo);
			//获取个人信息
			PersonalInfo personalInfo = zaUserMapperVo.getPersonalCenter(userType,userId);
			showPersonalCenter.setPersonalInfo(personalInfo);
			
			result = AppMsgResult.result(200,"success",showPersonalCenter);
			
		return result;
	}

	/**
	 * 修改用户离线在线状态
	 */
	@Override
	public AppMsgResult updateOnlineState(Integer userType, Integer state,String userId, String userToken) {
		if(userType==null || userType!=1 && userType!=2 && userType!=3) {
			return AppMsgResult.result(543, "用户类型参数值有误！",null);
		}
		if(state==null || state!=0 && state!=1) {
			return AppMsgResult.result(543, "在线状态参数值有误！",null);
		}
		AppMsgResult result =null;
		//判断用户是否登录	
		AppMsgResult msgResult = checkUserToken(userId, userToken);
		if((boolean)msgResult.getFlag()) {
			//根据用户类型修改该用户的在线状态
			Integer i = zaLawyerAuthenticationMapperVo.updateOnlineState(userType,userId,state);
			if(i==1) {
				result = AppMsgResult.result(200,"success",null);
			}else {
				result = AppMsgResult.result(558,"操作失败，请稍后再试！",null);
			}
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		return result;
	}

	/**
	 * 查看我的律师数据
	 */
	@Override
	public AppMsgResult getMyLawyer(String userAddress,Integer userType, String userId, String userToken) {
		if(userType==null || userType!=1 && userType!=3) {
			return AppMsgResult.result(543, "用户类型参数值有误！",null);
		}
		AppMsgResult result =null;
		AppMsgResult msgResult = checkUserToken(userId, userToken);
		if((boolean)msgResult.getFlag()) {
			//用户已登录获取用户当前位置的经纬度
			Map<String, Double> json = AddressToGpsUtil.getGeocoderLatitude(userAddress);
			//获取用户地址经纬度
			Double lon  = json.get("lon");
			Double lat = json.get("lat");
			List<ZaLawyerAuthenticationIndexVo>  LawyerAuthenList = zaLawyerAuthenticationMapperVo.getMyLawyer(userId);
			if(LawyerAuthenList.size()>0) {
				//计算距离
				for (ZaLawyerAuthenticationIndexVo zaLawyerAuthenticationVo : LawyerAuthenList) {
					Double comLon = zaLawyerAuthenticationVo.getComLon();
					Double comLat = zaLawyerAuthenticationVo.getComLat();
					if(comLon==null || comLat==null ) {
						zaLawyerAuthenticationVo.setDistance("##KM");
					}else {
						SpatialContext geo = SpatialContext.GEO;
						double distance = geo.calcDistance(geo.makePoint(lon, lat), geo.makePoint(comLon, comLat))*DistanceUtils.DEG_TO_KM;
						zaLawyerAuthenticationVo.setDistance(String.valueOf(new Double(distance).intValue())+"KM");
					}
				}
				
			}
			result = AppMsgResult.result(200,"success",LawyerAuthenList);
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		return result;
	}


	/**
	 * 查看我的委托
	 */
	@Override
	public AppMsgResult getMyPublishCaseById(String userId, String userToken,Integer curPage, Integer rows) {
		AppMsgResult result =null;
		AppMsgResult msgResult = checkUserToken(userId, userToken);
		if((boolean)msgResult.getFlag()) {
			 rows = rows == null?10:rows;
			   curPage = curPage == null?1:curPage;
			   //分页处理
			   PageHelper.startPage(curPage, rows);
			List<ZaUserPublishCaseVo> userPublishCaseList = zaUserPublishCaseMapperVo.getMyPublishCaseById(userId);
			//数据分页
	        PageInfo<ZaUserPublishCaseVo> pageInfo = new PageInfo<ZaUserPublishCaseVo>(userPublishCaseList);
			result = AppMsgResult.result(200,"success",pageInfo);
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		return result;
	}


	/**
	 * 查看我的收藏
	 */
	@Override
	public AppMsgResult getMyCollectionById(Integer obType,Double lon,Double lat,String userId, String userToken) {
		AppMsgResult result =null;
		AppMsgResult msgResult = checkUserToken(userId, userToken);
		if((boolean)msgResult.getFlag()) {
			if(obType==null || obType!=1 && obType!=2 && obType!=3 && obType!=4 ) {
				return AppMsgResult.result(543, "用户类型参数值有误！",null);
			}else {
				if(obType==1) {
					if(lon==null || lat==null) {
						return AppMsgResult.result(543, "用户当前经纬度不能为空",null);
					}
					//查询收藏的律师信息
					List<ZaLawyerAuthenticationIndexVo> collectionLawyerList = zaUserCollectionMapperVo.getMyCollectionLawyerById(userId);
					//计算距离
					if(collectionLawyerList.size()>0) {
						for (ZaLawyerAuthenticationIndexVo zaLawyerAuthenticationVo : collectionLawyerList) {
							Double comLon = zaLawyerAuthenticationVo.getComLon();
							Double comLat = zaLawyerAuthenticationVo.getComLat();
							SpatialContext geo = SpatialContext.GEO;
							double distance = geo.calcDistance(geo.makePoint(lon, lat), geo.makePoint(comLon, comLat))*DistanceUtils.DEG_TO_KM;
							zaLawyerAuthenticationVo.setDistance(String.valueOf(new Double(distance).intValue())+"KM");
						}
					}
					result = AppMsgResult.result(200,"success",collectionLawyerList);
					
				}else if(obType==2) {
					//收藏企业
					List<ZaComAuthenticationIndexVo> collectionComList = zaUserCollectionMapperVo.getMyCollectionComById(userId); 
					
					result = AppMsgResult.result(200,"success",collectionComList);
				}else if(obType==3) {
				   //收藏商品
					result = AppMsgResult.result(200,"success","[]");
				}else if(obType==4){
					//收藏的案例
					result = AppMsgResult.result(200,"success","[]");
				}
				
			}
			
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		return result;
	}

	

	/**
	 * 取消收藏
	 */
	@Override
	public AppMsgResult cancelCollection(String id, String userId, String userToken) {
		AppMsgResult result =null;
		AppMsgResult msgResult = checkUserToken(userId, userToken);
		if((boolean)msgResult.getFlag()) {
			Integer i = zaUserCollectionMapperVo.cancelCollection(id,userId);
			if(i==1) {
				result = AppMsgResult.result(200,"取消收藏成功",null);
			}else {
				result = AppMsgResult.result(559,"操作失败，请稍后再试",null);
			}
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		return result;
	}

	
	/**
	 * 查看用户收益信息
	 */
	@Override
	public AppMsgResult getUserProfitById(String userId, String userToken) {
		AppMsgResult result =null;
		AppMsgResult msgResult = checkUserToken(userId, userToken);
		if((boolean)msgResult.getFlag()) {
			ZaUserProfit zaUserProfit = zaUserProfitMapperVo.getUserProfitById(userId);
			result = AppMsgResult.result(200,"success",zaUserProfit);
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		return result;
	}


	/**
	 * 获取交易明细
	 */
	@Override
	public AppMsgResult getDealDetail(String userId, String userToken,Integer curPage, Integer rows) {
		AppMsgResult result =null;
		AppMsgResult msgResult = checkUserToken(userId, userToken);
		if((boolean)msgResult.getFlag()) {
			 rows = rows == null?10:rows;
			   curPage = curPage == null?1:curPage;
			   //分页处理
			   PageHelper.startPage(curPage, rows);
			//查询交易明细
			List<DealDetail> dealDetailList =  zaUserDealDetailMapperVo.getUserDealDetailById(userId); 
			//数据分页
	        PageInfo<DealDetail> pageInfo = new PageInfo<DealDetail>(dealDetailList);
	        result = AppMsgResult.result(200,"success",pageInfo);
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		return result;
	}


	/**
	 * 律师用户获取我的客户
	 */
	@Override
	public AppMsgResult getMyCustomer(Double lon, Double lat,String userId, String userToken) {
		AppMsgResult result =null;
		AppMsgResult msgResult = checkUserToken(userId, userToken);
		if((boolean)msgResult.getFlag()) {
			
			List<MyCustomer> MyCustomerList =  zaUserMapperVo.getMyCustomert(userId);
			//计算距离
			if(MyCustomerList.size()>0) {
				//用户已登录获取用户当前位置的经纬度
				
				for (MyCustomer myCustomer : MyCustomerList) {
					String address = myCustomer.getUserAddress();
					if(StringUtils.isNotBlank(address)) {
						Map<String, Double> json2 = AddressToGpsUtil.getGeocoderLatitude(address);
						//获取用户地址经纬度
						Double lon2  = json2.get("lon");
						Double lat2 = json2.get("lat");
						SpatialContext geo = SpatialContext.GEO;
						double distance = geo.calcDistance(geo.makePoint(lon, lat), geo.makePoint(lon2, lat2))*DistanceUtils.DEG_TO_KM;
						myCustomer.setDistance(String.valueOf(new Double(distance).intValue())+"KM");
					}else {
						myCustomer.setDistance("##KM");
					}
				}
				
			}
			result = AppMsgResult.result(200,"success",MyCustomerList);
			
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		return result;
	}


	/**
	 *获取律师用户承接的案件列表信息
	 */
	@Override
	public AppMsgResult getPublishCaseByLawId(String userId, String userToken) {
		AppMsgResult result =null;
		AppMsgResult msgResult = checkUserToken(userId, userToken);
		if((boolean)msgResult.getFlag()) {
			List<ZaUserPublishCaseVo> publishCaseList = zaUserPublishCaseMapperVo.getPublishCaseByLawId(userId);
			result = AppMsgResult.result(200,"success",publishCaseList);
		}else {
			result = AppMsgResult.result(538, "用户未登录！",null);
		}
		return result;
	}

	
	
}

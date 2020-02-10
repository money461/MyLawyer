package com.tz.pojo.index.vo;

public class SelectionCriteria {

	/**
	 * 用户获取推荐律师或者咨询律师或者自主寻找律师信息
	 * 
	 * @param sortCode
	 *            推荐律师7 咨询律师3 自主寻找4 推荐委托8 代写文书9 附近 15 （必须传值）
	 * @param lon
	 *            用户当前位置经度 （必须传值）
	 * @param lat
	 *            用户当前位置纬度 （必须传值）
	 * @param userType
	 *            用户类型 （必须传值）
	 * @param isSort
	 *            是否按照距离排序 传 参数1 或null
	 * @param area
	 *            按照区域查询
	 * @param caseId
	 *            按照案件类型查询
	 * @param isTime
	 *            按照时间查询排序 参数1 降序 或 2升序  或者null
	 * @Param isReward 
	 *            按照赏金大小排序 参数1 降序 或 2升序  或者null
	 * @param curPage
	 *            当前页码
	 * @param rows
	 *            每页记录数
	 * @param userId
	 * @param userToken
	 */
	private Integer sortCode;
	
	private Double lon;
	
	private Double lat;
	
	private Integer userType;
	
	private String area;
	
	private Integer caseId;
	
	private Integer isTime;
	
	private Integer isSort;
	
	private Integer isReward;
	
	private Integer curPage;
	
	private Integer rows;
	
	private String userId;
	
	private String userToken;

	public Integer getSortCode() {
		return sortCode;
	}

	public void setSortCode(Integer sortCode) {
		this.sortCode = sortCode;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getCaseId() {
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public Integer getIsTime() {
		return isTime;
	}

	public void setIsTime(Integer isTime) {
		this.isTime = isTime;
	}

	public Integer getIsSort() {
		return isSort;
	}

	public void setIsSort(Integer isSort) {
		this.isSort = isSort;
	}

	public Integer getIsReward() {
		return isReward;
	}

	public void setIsReward(Integer isReward) {
		this.isReward = isReward;
	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	
	
	
	
}

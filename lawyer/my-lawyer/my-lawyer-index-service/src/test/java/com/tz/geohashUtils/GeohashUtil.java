package com.tz.geohashUtils;

import java.util.Map;

import org.junit.Test;

import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.distance.DistanceUtils;
import com.spatial4j.core.io.GeohashUtils;
import com.tz.util.AddressToGpsUtil;

/**
 * 
 * @author Administrator
 *  获取数据库中说有的地理位置信息，生成geohash编码
 *
 */
public class GeohashUtil {
   	/**
	 * 104.705519------------31.504701----------------uxyruzvpbxzx 绵阳市
	 * 104.067076------------30.58102----------------uxvxzzurvrvp 119.36718889284866
	 * 
	 * 
	 * 104.070408------------30.550006----------------uxvxzpvzfxzx 天府三街
	 * 104.067076------------30.58102----------------uxvxzzurvrvp 距离：孵化园
	 * 3.463328568595377 KM
	 * 
	 * 106.484391------------29.531064----------------uxuzzzzzgxvr 重庆市朝天门
	 * 104.067076------------30.58102----------------uxvxzzurvrvp 距离：孵化园
	 * 260.2916355411198 KM
	 * 
	 * 104.069689------------30.585728----------------uxvxzzzzgpgz 楚峰国际中心
	 * 104.067076------------30.58102----------------uxvxzzurvrvp *
	 * 距离：孵化园0.5801947416942145 KM
	 * 
	 * 103.965093------------30.58483----------------uxvxzzzpypyp 双流国际机场
	 * 104.067076------------30.58102----------------uxvxzzurvrvp 9.771728906828983
	 * 
	 * 
	 * 104.072343------------30.663538----------------uxvzczbxbpzp 天府广场
	 * 104.067076------------30.58102----------------uxvxzzurvrvp
	 * 距离：孵化园9.189426550464725 KM
	 * 
	 * 104.402398------------31.13114----------------uxypurvpbpur 德阳市
	 * 104.067076------------30.58102----------------uxvxzzurvrvp 距离：孵化园
	 * 69.0389814715863 KM
	 * 
	 * 104.116583------------30.839504----------------uxvzvzbxuxbz 新都区
	 * 104.067076------------30.58102----------------uxvxzzurvrvp 29.12922330493629
	 */

	@Test
	
	
	
	
	public void method() {
		String userAddress = "四川省成都市双流区";
		Map<String, Double> json = AddressToGpsUtil.getGeocoderLatitude(userAddress);
		//获取地址经纬度
		Double lon  = json.get("lon");
		Double lat = json.get("lat");
		String geoCode = GeohashUtils.encodeLatLon(lon, lat,15);
		System.out.println(lon+"------------"+lat+"----------------"+geoCode);

		String userAddress1 = "四川省成都市高新区天府大道北段1480号高新区孵化园";
		Map<String, Double> json1 = AddressToGpsUtil.getGeocoderLatitude(userAddress1);
		//获取地址经纬度
		Double lon1  = json1.get("lon");
		Double lat1 = json1.get("lat");
		String geoCode1 = GeohashUtils.encodeLatLon(lon1, lat1,15);
		System.out.println(lon1+"------------"+lat1+"----------------"+geoCode1);
		
		SpatialContext geo = SpatialContext.GEO;
		double distance = geo.calcDistance(geo.makePoint(lon, lat), geo.makePoint(lon1, lat1))*DistanceUtils.DEG_TO_KM;
		System.out.println(distance);
		
	}
	
	
}

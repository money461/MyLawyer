package com.tz.comparator;

import java.util.Comparator;

import com.tz.pojo.index.vo.ZaLawyerAuthenticationIndexVo;


/**
 * 自定义的距离比较排序器
 * @author Administrator
 *
 */
public class DistanceComparator implements Comparator<ZaLawyerAuthenticationIndexVo> {

	@Override
	public int compare(ZaLawyerAuthenticationIndexVo o1, ZaLawyerAuthenticationIndexVo o2) {
		return o1.getDistance().compareTo(o2.getDistance());
	}

	
	

	
	
	
}

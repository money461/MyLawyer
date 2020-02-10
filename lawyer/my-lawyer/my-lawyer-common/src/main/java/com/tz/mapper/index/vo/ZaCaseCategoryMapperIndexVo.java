package com.tz.mapper.index.vo;

import java.util.List;

import com.tz.pojo.index.vo.ZaCaseCategoryIndexVo;


public interface ZaCaseCategoryMapperIndexVo {

	//根据父节点查询子节点数据
	List<ZaCaseCategoryIndexVo> getCaseCategoryBypId(Integer i);

}

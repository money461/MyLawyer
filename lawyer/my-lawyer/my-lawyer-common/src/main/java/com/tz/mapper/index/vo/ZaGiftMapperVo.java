package com.tz.mapper.index.vo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.ZaGift;
import com.tz.pojo.index.vo.ZaGiftVo;

public interface ZaGiftMapperVo {

	//获取奖赏礼品
	List<ZaGiftVo> getGift();

	//获取选中的礼品
	ZaGiftVo queryGiftById(@Param("giftId") String giftId);

	//后台条件查询礼品信息
	List<ZaGiftVo> selectGiftList(Map<String, Object> map);

	//插入数据
	Integer insertGiftSelective(ZaGift zagift);

	Integer updateGiftSelective(ZaGift zagift);

	void delGiftById(String id);

}

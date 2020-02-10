package com.tz.mapper.index.vo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.ZaSysNotice;
import com.tz.pojo.index.vo.ZaSysNoticeVo;

public interface ZaSysNoticeMapperVo {

	//将消息数据写入消息表中
	Integer insertSysNotice(ZaSysNotice zaSysNotice);

	//修改通知状态
	Integer updateSysNoticeById(ZaSysNotice new_zaSysNotice);

	//查询通知
	ZaSysNotice querySystNoticeById(@Param("id") Long id);

	//根据用户和消息类型查询
	List<ZaSysNoticeVo> querySysNoticeByUserId(@Param("noticeType") Integer noticeType, @Param("userId") String userId);

	//批量删除通知消息
	void batchDelNoticeById(@Param("idStr") String[] idStr);

}

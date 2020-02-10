package com.tz.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.tz.activemq.Producer;
import com.tz.mapper.index.vo.ZaUserPublishCaseMapperVo;
import com.tz.pojo.ZaUserPublishCase;
import com.tz.res.Constant;

/**
 * 订单定时处理案件是否过期
 * @author QL
 *
 */
@Component
@Configurable
@EnableScheduling
public class PublishCaseOverdueQuartz {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ZaUserPublishCaseMapperVo zaUserPublishCaseMapperVo;
	
	@Autowired
	private Producer producer;
	
	//定时确认收货
	//每天凌晨2点执行一次对8天前创建的已发货的订单执行状态修改为交易成功！
//	@Scheduled(cron = "0/2 * * * * ?")  //2秒执行一次
	@Scheduled(cron = "0 0 2 * * ?")
	public void PublishCaseOverdue(){
		//当前时间
		System.out.println("---------------------开始扫描数据中案件是否过期---------------------------");
		LOG.info("The time is now " + dateFormat ().format (new Date()));
		//只对最近三天的案件进行通知
		Date date = new DateTime().minusDays(3).toDate();
		String format = dateFormat ().format (date);
		//查询案件结束时间小于当前时间的案件，并对该案件用户的
		List<ZaUserPublishCase> list = zaUserPublishCaseMapperVo.queryPublishCaseOverdue(format);
		if(list.size()>0) {
			String jsonString = JSON.toJSONString(list);
			//创建消息目的对象Destination (点对点Queue)
			Destination destination = new ActiveMQQueue(Constant.USERPUBLISHCASEOVERDUE_QUEUE);
			producer.sendMessage(destination, jsonString);
		}
		
	}

	private SimpleDateFormat  dateFormat() {
		 return new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
	}
	
}

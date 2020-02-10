package com.tz.activemq;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;

/**
 * jMS配置
 * @author QL
 *
 */
@Configuration
public class JmsConfig {

	  
    @Bean    
    public ActiveMQConnectionFactory connectionFactory() {    
    	//此链接信息可放入配置文件中  
        return new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");   
    }  
    
    @Bean  
    public JmsMessagingTemplate jmsMessagingTemplate(ActiveMQConnectionFactory connectionFactory){  
    System.out.println("get JmsMessagingTemplate");  
    return new JmsMessagingTemplate(connectionFactory);  
    }  
    
    // topic模式的ListenerContainer
    @Bean(name="jmsListenerContainerTopic")
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ActiveMQConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(activeMQConnectionFactory);
        bean.setConcurrency("3-10");
        return bean;
    }
    
    // queue模式的ListenerContainer
    @Bean(name="jmsListenerContainerQueue")
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ActiveMQConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(activeMQConnectionFactory);
        return bean;
    }
    
    
}

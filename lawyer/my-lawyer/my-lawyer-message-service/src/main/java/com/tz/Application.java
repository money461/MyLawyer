package com.tz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient//激活Eureka中的DiscoveryClient实现(自动化配置,创建DiscoveryClient接口针对Eureka客户端的EurekaDiscoveryClient实例)
public class Application {


	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	 
}

package com.tz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public AccessFilter accessFilter(){
        return new AccessFilter();
    }
}
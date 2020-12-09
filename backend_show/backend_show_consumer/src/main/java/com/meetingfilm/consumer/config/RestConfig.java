package com.meetingfilm.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2020/12/9 9:40 上午
 */
@Configuration
public class RestConfig {


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}

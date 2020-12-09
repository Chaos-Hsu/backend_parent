package com.meetingfilm.hall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2020/12/9 6:51 下午
 */
@Configuration
public class RestConf {


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}

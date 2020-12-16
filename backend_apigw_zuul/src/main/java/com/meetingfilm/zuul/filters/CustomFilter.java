package com.meetingfilm.zuul.filters;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述 : 自定义filter
 * 作者 : 徐起超
 * 时间 : 2020/12/16 6:31 下午
 */
@Configuration
public class CustomFilter {

    @Bean
    public MyFilter initFitler() {
        return new MyFilter();
    }

    @Bean
    public JWTFilter initJWTFitler() {
        return new JWTFilter();
    }

}

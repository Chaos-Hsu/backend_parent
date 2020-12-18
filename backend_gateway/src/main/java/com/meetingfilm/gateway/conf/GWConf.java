package com.meetingfilm.gateway.conf;

import com.meetingfilm.gateway.filters.CustomGatewayFilterFactory;
import com.meetingfilm.gateway.filters.MyGlobalFilter;
import com.meetingfilm.gateway.predicates.XAfterRoutePredicateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述 : 初始化GW配置
 * 作者 : 徐起超
 * 时间 : 2020/12/17 7:47 下午
 */
@Configuration
public class GWConf {

    @Bean
    public XAfterRoutePredicateFactory initAfter() {
        return new XAfterRoutePredicateFactory();
    }

    @Bean
    public MyGlobalFilter initGlobal() {
        return new MyGlobalFilter();
    }

    @Bean
    public CustomGatewayFilterFactory initCustom() {
        return new CustomGatewayFilterFactory();
    }
}

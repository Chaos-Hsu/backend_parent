package com.meetingfilm.consumer.config;

import com.meetingfilm.consumer.ribbon.rule.MyRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public IRule iRule() {
        //return new MyRule();
        return new RoundRobinRule();
    }
}

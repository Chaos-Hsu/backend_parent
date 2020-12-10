package com.meetingfilm.hall.config;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.niws.loadbalancer.NIWSDiscoveryPing;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 负载均衡算法
     *
     * @return
     */
    @Bean
    public IRule iRule() {
        //随机算法
        return new RandomRule();
    }

    /**
     * 存活机制
     *
     * @return
     */
    public IPing iPing() {
        //使用注册中心存活机制(依赖euraka 客户端)
        return new NIWSDiscoveryPing();
    }

}

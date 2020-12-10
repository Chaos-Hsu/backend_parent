package com.meetingfilm.consumer.config;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.niws.loadbalancer.NIWSDiscoveryPing;
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

    /**
     * 负载均衡规则
     *
     * @return
     */
    @Bean
    public IRule iRule() {
        //自定义规则
        //return new MyRule();
        // 随机循环
        //return new RandomRule();
        // 循序循环
        return new RoundRobinRule();
    }

    /**
     * 校验服务是否存活机制
     *
     * @return
     */
    @Bean
    public IPing iPing() {
        //自己校验是否存活   是否安全连接(http,https)  如果/abc存活,服务存活
        //return new PingUrl(false, "/abc");
        //依赖euraka clic 的存活机制
        return new NIWSDiscoveryPing();
    }
}

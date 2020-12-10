package com.meetingfilm.consumer.ribbon.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.Server;

/**
 * 描述 : 自定义ribbon规则
 * 作者 : 徐起超
 * 时间 : 2020/12/10 10:59 上午
 */
public class MyRule extends AbstractLoadBalancerRule {

    @Override
    public Server choose(Object o) {
        return null;
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }
}

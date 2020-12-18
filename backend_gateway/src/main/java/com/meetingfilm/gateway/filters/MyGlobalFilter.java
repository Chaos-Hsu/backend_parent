package com.meetingfilm.gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 描述 : 全局Filter
 * 作者 : 徐起超
 * 时间 : 2020/12/17 8:11 下午
 */
@Slf4j
public class MyGlobalFilter implements GlobalFilter, Ordered {

    /**
     * 全局处理请求时间
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        long beginTime = System.currentTimeMillis();
        log.info("filter start ----> beginTime:{}", beginTime);
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            long endTime = System.currentTimeMillis();
            log.info("filter end ----> endTime{}, cost:[{}] ms", endTime, endTime - beginTime);
        }));
    }

    /**
     * 排序
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}

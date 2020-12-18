package com.meetingfilm.gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * 描述 : 自定义过滤 请求必须携带参数和参数值
 * 作者 : 徐起超
 * 时间 : 2020/12/17 8:20 下午
 * 注意名称后缀必须携带 GatewayFilterFactory
 */
@Slf4j
public class CustomGatewayFilterFactory extends AbstractGatewayFilterFactory<CustomGatewayFilterFactory.Config> {

    private static String PARAM_NAME = "paramName";
    private static String PARAM_VALUE = "paramValue";

    /**
     * 注入配置
     */
    public CustomGatewayFilterFactory() {
        super(Config.class);
    }

    /**
     * 过滤实现
     *
     * @param config
     * @return
     */
    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            Set<String> keys = exchange.getRequest().getQueryParams().keySet();
            for (String key : keys) {
                log.info("key:{} , value:{}", key, exchange.getRequest().getHeaders().get(key));
            }

            List<String> headNames = exchange.getRequest().getQueryParams().get(config.getParamName());

            if (headNames != null && headNames.size() > 0) {
                log.info("PARAM_NAME:{},PARAM_VALUE:{}, headNames:{}", config.getParamName(), config.getParamValue(), headNames);
                if (config.getParamValue().equalsIgnoreCase(headNames.get(0))) {
                    return chain.filter(exchange);
                }
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            } else {
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            }
            return exchange.getResponse().setComplete();
        });
    }

    /**
     * 参数值
     *
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(PARAM_NAME, PARAM_VALUE);
    }


    /**
     * 配置类
     */
    public static class Config {
        private String paramName;
        private String paramValue;

        public String getParamName() {
            return paramName;
        }

        public void setParamName(String paramName) {
            this.paramName = paramName;
        }

        public String getParamValue() {
            return paramValue;
        }

        public void setParamValue(String paramValue) {
            this.paramValue = paramValue;
        }
    }

}

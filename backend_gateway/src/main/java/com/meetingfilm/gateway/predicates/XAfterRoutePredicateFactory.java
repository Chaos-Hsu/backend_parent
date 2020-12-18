package com.meetingfilm.gateway.predicates;


import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.web.server.ServerWebExchange;

import javax.validation.constraints.NotEmpty;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * 描述 : 日期自定义路由
 * 作者 : 徐起超
 * 时间 : 2020/12/17 7:41 下午
 * <p>
 * 注意:一定要以RoutePredicateFactory结尾的方法名才可以匹配到
 */
public class XAfterRoutePredicateFactory extends AbstractRoutePredicateFactory<XAfterRoutePredicateFactory.Config> {

    /**
     * 参数名
     */
    public static final String AFTER_KEY = "datetime";

    /**
     * 传入配置信息
     */
    public XAfterRoutePredicateFactory() {
        super(XAfterRoutePredicateFactory.Config.class);
    }

    /**
     * 配置路由逻辑
     *
     * @param config
     * @return
     */
    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        System.out.println("AfterRoutePredicateFactory -> config : " + config);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        long nowTime = System.currentTimeMillis();
        return (exchange) -> {
            // 解析出配置定义的时间
            long afterTime = 0;
            try {
                afterTime = format.parse(config.getDatetime()).getTime();
            } catch (ParseException e) {
                return false;
            }
            // 如果当前时间大于配置定义时间，则返回true
            return nowTime > afterTime;
        };
    }

    /**
     * 传入参数
     *
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList(AFTER_KEY);
    }

    /**
     * @Description: 用于承载断言所需的参数
     * @Param: 我们这里是判断输入的时间与真实时间之间的先后关系
     */
    public static class Config {
        @NotEmpty
        private String datetime;

        public Config() {
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }
    }

}

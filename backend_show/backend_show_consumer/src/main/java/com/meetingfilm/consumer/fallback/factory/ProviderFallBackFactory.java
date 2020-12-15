package com.meetingfilm.consumer.fallback.factory;

import com.meetingfilm.consumer.dto.UserDto;
import com.meetingfilm.consumer.feign.ProviderApi;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2020/12/15 5:06 下午
 */
@Component
public class ProviderFallBackFactory implements FallbackFactory {


    @Override
    public Object create(Throwable throwable) {
        return new ProviderApi() {
            @Override
            public String test(String msg) {
                return "test 业务降级 工厂方式" + msg;
            }

            @Override
            public String testPost(UserDto json, String id, String headName) {
                return "testPost 业务降级 工厂方式";
            }
        };
    }
}

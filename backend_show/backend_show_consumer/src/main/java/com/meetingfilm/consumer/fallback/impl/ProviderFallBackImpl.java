package com.meetingfilm.consumer.fallback.impl;

import com.meetingfilm.consumer.dto.UserDto;
import com.meetingfilm.consumer.feign.ProviderApi;
import org.springframework.stereotype.Service;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2020/12/15 5:05 下午
 */
@Service
public class ProviderFallBackImpl implements ProviderApi {


    @Override
    public String test(String msg) {
        return "test 业务降级 实现方式" + msg;
    }

    @Override
    public String testPost(UserDto json, String id, String headName) {
        return "testPost 业务降级 实现方式";
    }
}

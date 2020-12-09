package com.meetingfilm.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2020/12/9 9:30 上午
 */
@Slf4j
@RestController
@RequestMapping("/provider")
public class ProviderController {

    @Value("${server.port}")
    private int port;


    @RequestMapping(value = "/test")
    public String test(String msg){
        log.error("port:{},msg:{}", port, msg);
        return "port:" + port + ",msg:" + msg;
    }

}

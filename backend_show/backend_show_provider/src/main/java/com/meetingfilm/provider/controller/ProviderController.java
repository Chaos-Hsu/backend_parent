package com.meetingfilm.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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
    public String test(String msg) {
        log.error("port:{},msg:{}", port, msg);
        return "port:" + port + ",msg:" + msg;
    }

    @RequestMapping(value = "/test/post/{id}", method = RequestMethod.POST)
    public String testPost(@RequestBody String json, @PathVariable("id") String id, @RequestHeader String headName) {
        log.error("port:{},josn:{},id:{},head:{}", port, json, id, headName);
        return "sucess";
    }

}

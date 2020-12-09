package com.meetingfilm.consumer.controller;

import com.meetingfilm.consumer.service.IConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2020/12/9 9:39 上午
 */
@RestController
@RequestMapping("/comsumer")
public class ComsumerController {

    @Autowired
    private IConsumerService consumerService;

    @RequestMapping("/test")
    public String test(String msg) {
        return consumerService.test(msg);
    }

}

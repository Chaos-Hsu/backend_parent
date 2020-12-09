package com.meetingfilm.consumer.service.impl;

import com.meetingfilm.consumer.service.IConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2020/12/9 9:37 上午
 */
@Service
public class ConsumerServiceImpl implements IConsumerService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient eurekaClient;

    @Override
    public String test(String msg) {


        ServiceInstance serviceInstance = eurekaClient.choose("hello-provider");
        String hostName = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        //String hostName = "127.0.0.1";
        //String port = "8201";
        String uri = "/provider/test?msg=" + msg;
        String url = "http://" + hostName + ":" + port + uri;
        //调用生产者
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }
}

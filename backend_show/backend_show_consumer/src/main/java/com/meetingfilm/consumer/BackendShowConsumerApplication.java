package com.meetingfilm.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//可以适配大部分注册中心
@EnableDiscoveryClient
//只能用EureKa
//@EnableEurekaClient
@SpringBootApplication
public class BackendShowConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendShowConsumerApplication.class, args);
    }

}

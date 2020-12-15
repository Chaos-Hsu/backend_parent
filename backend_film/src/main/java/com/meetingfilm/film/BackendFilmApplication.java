package com.meetingfilm.film;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(basePackages = {"com.meetingfilm.film.dao"})
@ComponentScan(basePackages = {"com.meetingfilm"})
public class BackendFilmApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendFilmApplication.class, args);
    }

}

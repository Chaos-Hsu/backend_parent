package com.meetingfilm.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//扫描其他项目内容
@ComponentScan(basePackages = {"com.meetingfilm"})
@MapperScan(basePackages = {"com.meetingfilm.user.dao.mapper"})
public class BackendUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendUserApplication.class, args);
    }

}

package com.meetingfilm.feignConf;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2020/12/15 11:28 上午
 */
@Configuration
public class FeignCustomConf {

    /**
     * feign自带
     *
     * @return
     */
    @Bean
    public Contract getContract() {
        return new Contract.Default();
    }


}

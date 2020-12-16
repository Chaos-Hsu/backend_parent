package com.meetingfilm.eureka.conf;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 描述 : 解决注册中心CSRF防御问题
 * 作者 : 徐起超
 * 时间 : 2020/12/16 9:03 下午
 */
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * @Description: 对eureka注册的URL不进行CSRF防御
     * @Param: [http]
     * @return: void
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(http);
    }
}

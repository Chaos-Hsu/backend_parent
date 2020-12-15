package com.meetingfilm.consumer.feign;

import com.meetingfilm.consumer.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2020/12/14 5:16 下午
 */
//默认 springmvc 语法
@FeignClient(name = "hello-provider", path = "provider")
//@FeignClient(name = "ProviderApi", path = "test", url = "http://127.0.0.1:8202/provider")
//feign语法
//@FeignClient(name = "ProviderApi", path = "test", url = "http://127.0.0.1:8202/provider", configuration = FeignCustomConf.class)
public interface ProviderApi {

    /**
     * feign语法
     *
     * @param msg
     * @return
     */
    //@RequestLine("GET ?msg={msg}")
    //String test(@Param("msg") String msg);


    /**
     * spring MVC 语法
     *
     * @param msg
     * @return
     */
    @RequestMapping("/test")
    String test(@RequestParam("msg") String msg);

    @RequestMapping(value = "/test/post/{id}", method = RequestMethod.POST)
    String testPost(@RequestBody UserDto json, @PathVariable("id") String id, @RequestHeader String headName);
}

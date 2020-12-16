package com.meetingfilm.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 描述 : 自定义filter
 * 作者 : 徐起超
 * 时间 : 2020/12/16 6:25 下午
 */
@Slf4j
public class MyFilter extends ZuulFilter {

    /**
     * 自定义类型
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filter执行顺序
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否开启自定义filter
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //zuul提供的上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        //请求头内容
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String head = headerNames.nextElement();
            log.info("headName:{},value:{}", head, request.getHeader(head));
        }
        return null;
    }
}

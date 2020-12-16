package com.meetingfilm.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletResponse;

/**
 * 描述 : 跨域资源共享
 * 作者 : 徐起超
 * 时间 : 2020/12/16 8:45 下午
 */
public class CorsFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        // 跨域
        HttpServletResponse response = ctx.getResponse();
        response.addHeader("Access-Control-Allow-Origin", "*");//所有域名
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,DELETE,PUT");//允许的请求方式
        response.setHeader("Access-Control-Allow-Headers", "DNT,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Range,Authorization");//请求头
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        return null;
    }
}

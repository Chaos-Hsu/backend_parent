package com.meetingfilm.zuul.filters;

import com.alibaba.fastjson.JSONObject;
import com.meetingfilm.utils.common.vo.BaseResponseVo;
import com.meetingfilm.utils.properties.JwtProperties;
import com.meetingfilm.utils.util.JwtTokenUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述 : 验证
 * 作者 : 徐起超
 * 时间 : 2020/12/16 6:25 下午
 */
@Slf4j
public class JWTFilter extends ZuulFilter {

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
        // JWT工具类
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        JwtProperties jwtProperties = JwtProperties.getJwtProperties();


        //zuul提供的上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        HttpServletResponse response = currentContext.getResponse();

        //跳过登录
        if (request.getServletPath().endsWith("/" + jwtProperties.getAuthPath())) {
            return null;
        }

        //获取认证头
        final String requestHeader = request.getHeader(jwtProperties.getHeader());

        String authToken = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {//前缀+空格+JWT
            //去掉前缀和空格
            authToken = requestHeader.substring(7);

            //验证token是否过期,包含了验证jwt是否正确
            try {
                //校验是否过期
                boolean flag = jwtTokenUtil.isTokenExpired(authToken);
                if (flag) {
                    renderJson(currentContext, response, BaseResponseVo.noLogin());
                }
                //获取userId和签名
                String userId = jwtTokenUtil.getUsernameFromToken(authToken);
                String randomKey = jwtTokenUtil.getMd5KeyFromToken(authToken);
                //验证签名

                //验证用户

                log.info("userId:{},randomKey:{}", userId, randomKey);
            } catch (JwtException e) {
                //有异常就是token解析失败
                renderJson(currentContext, response, BaseResponseVo.noLogin());
            }
        } else {
            //header没有带Bearer字段
            renderJson(currentContext, response, BaseResponseVo.noLogin());
        }
        //放行
        return null;
    }


    /**
     * 渲染json 结果对象
     */
    public static void renderJson(RequestContext ctx, HttpServletResponse response, Object jsonObject) {
        // 设置终止请求
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        ctx.setSendZuulResponse(false);
        ctx.setResponseBody(JSONObject.toJSONString(jsonObject));
    }
}

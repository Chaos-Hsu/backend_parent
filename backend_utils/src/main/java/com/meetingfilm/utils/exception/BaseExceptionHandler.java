package com.meetingfilm.utils.exception;

import com.meetingfilm.utils.common.vo.BaseResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述 : 统一异常处理
 * 作者 : 徐起超
 * 时间 : 2020/12/8 5:54 下午
 */
@Slf4j
@ControllerAdvice
public class BaseExceptionHandler {

    /**
     * 公共业务异常处理
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(CommonServiceException.class)
    @ResponseBody
    public BaseResponseVo serviceExceptionHandler(HttpServletRequest request, CommonServiceException e) {
        log.error("CommonServiceException:" + e.getCode() + ";" + e.getMsg());
        return BaseResponseVo.serviceException(e);
    }

}

package com.meetingfilm.utils.common.vo;

import com.meetingfilm.utils.exception.CommonServiceException;

/**
 * 描述 : 公共请求
 * 作者 : 徐起超
 * 时间 : 2020/12/8 5:22 下午
 */
public abstract class BaseRequestVo {

    /**
     * 公共校验
     *
     * @throws CommonServiceException
     */
    public abstract void checkParam() throws CommonServiceException;


}

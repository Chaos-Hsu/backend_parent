package com.meetingfilm.utils.exception;

import lombok.Data;

/**
 * 描述 : 公共service异常
 * 作者 : 徐起超
 * 时间 : 2020/12/8 5:23 下午
 */
@Data
public class CommonServiceException extends Exception {

    private Integer code;
    private String msg;

    public CommonServiceException(int code, String message) {
        this.code = code;
        this.msg = message;
    }


}

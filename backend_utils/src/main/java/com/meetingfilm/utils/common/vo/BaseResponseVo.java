package com.meetingfilm.utils.common.vo;

import com.meetingfilm.utils.exception.CommonServiceException;
import lombok.Data;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2020/12/6 8:08 下午
 */
@Data
public class BaseResponseVo<T> {

    private Integer code;

    private String msg;

    private T data;

    public BaseResponseVo() {
    }

    /**
     * 成功
     *
     * @return
     */
    public static BaseResponseVo success() {
        BaseResponseVo responseVo = new BaseResponseVo<>();
        responseVo.setCode(200);
        responseVo.setMsg("");
        return responseVo;
    }

    // 成功有参数
    public static <T> BaseResponseVo<T> success(T data) {
        BaseResponseVo response = new BaseResponseVo();
        response.setCode(200);
        response.setMsg("");
        response.setData(data);
        return response;
    }

    // 出现业务异常
    public static BaseResponseVo serviceException(CommonServiceException e) {
        BaseResponseVo response = new BaseResponseVo();
        response.setCode(e.getCode());
        response.setMsg(e.getMsg());
        return response;
    }


}

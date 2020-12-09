package com.meetingfilm.hall.controller.vo;

import com.meetingfilm.utils.common.vo.BaseRequestVo;
import com.meetingfilm.utils.exception.CommonServiceException;
import lombok.Data;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2020/12/9 5:32 下午
 */
@Data
public class HallsAddReqVO extends BaseRequestVo {

    private String cinemaId;
    private String filmId;
    private String hallTypeId;
    private String beginTime;
    private String endTime;
    private String filmPrice;
    private String hallName;

    @Override
    public void checkParam() throws CommonServiceException {

    }
}

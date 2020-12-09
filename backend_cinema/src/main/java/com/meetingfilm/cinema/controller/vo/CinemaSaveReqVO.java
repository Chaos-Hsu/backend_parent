package com.meetingfilm.cinema.controller.vo;

import com.meetingfilm.utils.common.vo.BaseRequestVo;
import com.meetingfilm.utils.exception.CommonServiceException;
import lombok.Data;

/**
 * 描述 : 影院保存
 * 作者 : 徐起超
 * 时间 : 2020/12/9 4:43 下午
 */
@Data
public class CinemaSaveReqVO extends BaseRequestVo {


    private String brandId;
    private String areaId;
    private String hallTypeIds;
    private String cinemaName;
    private String cinemaAddress;
    private String cinemaTele;
    private String cinemaImgAddress;
    private String cinemaPrice;

    @Override
    public void checkParam() throws CommonServiceException {

    }
}

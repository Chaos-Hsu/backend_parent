package com.meetingfilm.cinema.controller.vo;

import lombok.Data;

/**
 * 描述 : 影院响应列表
 * 作者 : 徐起超
 * 时间 : 2020/12/9 4:44 下午
 */
@Data
public class CinemaListRespVO {

    private String brandId;
    private String areaId;
    private String hallTypeIds;
    private String cinemaName;
    private String cinemaAddress;
    private String cinemaTele;
    private String cinemaImgAddress;
    private String cinemaPrice;

}

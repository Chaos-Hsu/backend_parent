package com.meetingfilm.hall.controller.vo;

import lombok.Data;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2020/12/9 5:32 下午
 */
@Data
public class HallsListRespVO {

    private String cinemaName;
    private String hallName;
    private String filmName;
    private String hallTypeName;
    private String beginTime;
    private String endTime;
    private String filmPrice;
}

package com.meetingfilm.film.controller.vo;

import lombok.Data;

/**
 * 描述 : 影片列表响应
 * 作者 : 徐起超
 * 时间 : 2020/12/9 2:00 下午
 */
@Data
public class FilmsListRespVO {

    private String filmStatus;
    private String filmName;
    private String filmEnName;
    private String filmScore;
    private String preSaleNum;
    private String boxOffice;
    private String filmTime;
    private String filmLength;
    private String mainImg;
}

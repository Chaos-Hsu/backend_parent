package com.meetingfilm.film.controller.vo;

import lombok.Data;

/**
 * 描述 : 影片详情响应
 * 作者 : 徐起超
 * 时间 : 2020/12/9 2:00 下午
 */
@Data
public class FilmsInfoRespVO {

    private String filmId;
    private String filmName;
    private String filmLength;
    private String filmCats;
    private String actors;
    private String imgAddress;
    private String subAddress;
}

package com.meetingfilm.film.controller.vo;

import com.meetingfilm.utils.common.vo.BaseRequestVo;
import com.meetingfilm.utils.exception.CommonServiceException;
import lombok.Data;

/**
 * 描述 : 影片添加请求
 * 作者 : 徐起超
 * 时间 : 2020/12/9 2:00 下午
 */
@Data
public class FilmsAddReqVO extends BaseRequestVo {

    private String filmStatus;
    private String filmName;
    private String filmEnName;
    private String mainImgAddress;
    private String filmScore;
    private String filmScorers;
    private String preSaleNum;
    private String boxOffice;
    private String filmTypeId;
    private String filmSourceId;
    private String filmCatIds;
    private String areaId;
    private String dateId;
    private String filmTime;
    private String directorId;
    // actIds与RoleNames是不是能在数量上对应上
    private String actIds;
    private String roleNames;
    private String filmLength;
    private String biography;
    private String filmImgs;

    @Override
    public void checkParam() throws CommonServiceException {
        
    }
}

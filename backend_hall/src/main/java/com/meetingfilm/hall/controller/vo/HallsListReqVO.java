package com.meetingfilm.hall.controller.vo;

import com.meetingfilm.utils.common.vo.BasePageVO;
import lombok.Data;

/**
 * 描述 : 请求
 * 作者 : 徐起超
 * 时间 : 2020/12/9 5:32 下午
 */
@Data
public class HallsListReqVO extends BasePageVO {

    private String cinemaId;

}

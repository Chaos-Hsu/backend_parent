package com.meetingfilm.apis.films;

import com.meetingfilm.apis.films.vo.FilmsInfoRespVO;
import com.meetingfilm.utils.common.vo.BaseResponseVo;
import com.meetingfilm.utils.exception.CommonServiceException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述 : film公共接口
 * 作者 : 徐起超
 * 时间 : 2020/12/15 6:55 下午
 */
public interface BaseFilmsApi {

    /**
     * 影片详情
     *
     * @return
     */
    @RequestMapping("/{filmId}")
    BaseResponseVo<FilmsInfoRespVO> filmsInfo(@PathVariable("filmId") String filmId) throws CommonServiceException;


}

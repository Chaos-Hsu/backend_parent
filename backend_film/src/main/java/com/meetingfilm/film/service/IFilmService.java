package com.meetingfilm.film.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.meetingfilm.apis.films.vo.FilmsInfoRespVO;
import com.meetingfilm.film.controller.vo.ActorsListRespVO;
import com.meetingfilm.film.controller.vo.FilmsAddReqVO;
import com.meetingfilm.film.controller.vo.FilmsListRespVO;
import com.meetingfilm.film.dao.entity.MoocFilmT;
import com.meetingfilm.utils.common.vo.BasePageVO;
import com.meetingfilm.utils.exception.CommonServiceException;

/**
 * <p>
 * 影片主表 服务类
 * </p>
 *
 * @author xqc
 * @since 2020-12-09
 */
public interface IFilmService extends IService<MoocFilmT> {


    /**
     * 获取演员列表
     *
     * @param pageVO
     * @return
     * @throws CommonServiceException
     */
    IPage<ActorsListRespVO> getActorsList(BasePageVO pageVO) throws CommonServiceException;


    /**
     * 获取影片列表
     *
     * @param pageVO
     * @return
     * @throws CommonServiceException
     */
    IPage<FilmsListRespVO> getFilmsList(BasePageVO pageVO) throws CommonServiceException;


    /**
     * 根据影片ID获取详情
     *
     * @param filmId
     * @return
     * @throws CommonServiceException
     */
    FilmsInfoRespVO getFilmInfo(String filmId) throws CommonServiceException;

    /**
     * 保存影片
     *
     * @param reqVO
     * @throws CommonServiceException
     */
    void saveFilm(FilmsAddReqVO reqVO) throws CommonServiceException;
}

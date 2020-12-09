package com.meetingfilm.cinema.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.meetingfilm.cinema.controller.vo.CinemaListReqVO;
import com.meetingfilm.cinema.controller.vo.CinemaListRespVO;
import com.meetingfilm.cinema.controller.vo.CinemaSaveReqVO;
import com.meetingfilm.cinema.dao.entity.MoocCinemaT;
import com.meetingfilm.utils.exception.CommonServiceException;

/**
 * <p>
 * 影院信息表 服务类
 * </p>
 *
 * @author xqc
 * @since 2020-12-09
 */
public interface ICinemaService extends IService<MoocCinemaT> {


    /**
     * 影院列表
     *
     * @param pageVO
     * @return
     * @throws CommonServiceException
     */
    IPage<CinemaListRespVO> findPage(CinemaListReqVO pageVO) throws CommonServiceException;


    /**
     * 影院保存
     *
     * @param saveVO
     * @throws CommonServiceException
     */
    void save(CinemaSaveReqVO saveVO) throws CommonServiceException;


}

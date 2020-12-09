package com.meetingfilm.hall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.meetingfilm.hall.controller.vo.HallsAddReqVO;
import com.meetingfilm.hall.controller.vo.HallsListReqVO;
import com.meetingfilm.hall.controller.vo.HallsListRespVO;
import com.meetingfilm.hall.dao.entity.MoocHallFilmInfoT;
import com.meetingfilm.utils.exception.CommonServiceException;

/**
 * <p>
 * 影厅电影信息表 服务类
 * </p>
 *
 * @author xqc
 * @since 2020-12-09
 */
public interface IHallFilmInfoService extends IService<MoocHallFilmInfoT> {


    /**
     * 热映列表
     *
     * @param pageVO
     * @return
     * @throws CommonServiceException
     */
    IPage<HallsListRespVO> getHallsList(HallsListReqVO pageVO) throws CommonServiceException;

    /**
     * 新增热映
     *
     * @param reqVO
     * @throws CommonServiceException
     */
    void saveHall(HallsAddReqVO reqVO) throws CommonServiceException;
}

package com.meetingfilm.cinema.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meetingfilm.cinema.controller.vo.CinemaListRespVO;
import com.meetingfilm.cinema.dao.entity.MoocCinemaT;

/**
 * <p>
 * 影院信息表 Mapper 接口
 * </p>
 *
 * @author xqc
 * @since 2020-12-09
 */
public interface MoocCinemaTMapper extends BaseMapper<MoocCinemaT> {

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    IPage<CinemaListRespVO> findPage(Page<CinemaListRespVO> page);


}

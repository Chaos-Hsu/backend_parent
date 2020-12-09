package com.meetingfilm.film.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meetingfilm.film.controller.vo.ActorsListRespVO;
import com.meetingfilm.film.dao.entity.MoocActorT;

/**
 * <p>
 * 演员表 Mapper 接口
 * </p>
 *
 * @author xqc
 * @since 2020-12-09
 */
public interface MoocActorTMapper extends BaseMapper<MoocActorT> {

    /**
     * 演员列表
     *
     * @param page
     * @return
     */
    IPage<ActorsListRespVO> findActors(Page<ActorsListRespVO> page);

}

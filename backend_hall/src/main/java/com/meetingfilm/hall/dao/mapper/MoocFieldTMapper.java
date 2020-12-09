package com.meetingfilm.hall.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meetingfilm.hall.controller.vo.HallsListReqVO;
import com.meetingfilm.hall.controller.vo.HallsListRespVO;
import com.meetingfilm.hall.dao.entity.MoocFieldT;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 放映场次表 Mapper 接口
 * </p>
 *
 * @author xqc
 * @since 2020-12-09
 */
public interface MoocFieldTMapper extends BaseMapper<MoocFieldT> {


    /**
     * 根据影片ID获取所有热映
     *
     * @param page
     * @param cinemaId
     * @return
     */
    IPage<HallsListRespVO> findPageByCinemaId(Page<HallsListReqVO> page, @Param("ew") QueryWrapper cinemaId);
}

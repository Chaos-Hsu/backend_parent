package com.meetingfilm.film.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meetingfilm.film.controller.vo.FilmsListRespVO;
import com.meetingfilm.film.dao.entity.MoocFilmT;

/**
 * <p>
 * 影片主表 Mapper 接口
 * </p>
 *
 * @author xqc
 * @since 2020-12-09
 */
public interface MoocFilmTMapper extends BaseMapper<MoocFilmT> {

    IPage<FilmsListRespVO> findFilms(Page<FilmsListRespVO> respPage);

}

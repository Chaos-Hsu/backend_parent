package com.meetingfilm.film.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meetingfilm.film.controller.vo.FilmsInfoRespVO;
import com.meetingfilm.film.controller.vo.FilmsListRespVO;
import com.meetingfilm.film.dao.entity.MoocFilmT;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 根据ID查询
     *
     * @param filmId
     * @return
     */
    FilmsInfoRespVO findById(@Param("filmId") String filmId);

}

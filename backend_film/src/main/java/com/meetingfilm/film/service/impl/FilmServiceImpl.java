package com.meetingfilm.film.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meetingfilm.film.controller.vo.ActorsListRespVO;
import com.meetingfilm.film.controller.vo.FilmsAddReqVO;
import com.meetingfilm.film.controller.vo.FilmsInfoRespVO;
import com.meetingfilm.film.controller.vo.FilmsListRespVO;
import com.meetingfilm.film.dao.entity.MoocFilmT;
import com.meetingfilm.film.dao.mapper.MoocActorTMapper;
import com.meetingfilm.film.dao.mapper.MoocFilmInfoTMapper;
import com.meetingfilm.film.dao.mapper.MoocFilmTMapper;
import com.meetingfilm.film.service.IFilmService;
import com.meetingfilm.utils.common.vo.BasePageVO;
import com.meetingfilm.utils.exception.CommonServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 影片主表 服务实现类
 * </p>
 *
 * @author xqc
 * @since 2020-12-09
 */
@Service
public class FilmServiceImpl extends ServiceImpl<MoocFilmTMapper, MoocFilmT> implements IFilmService {

    @Resource
    private MoocActorTMapper actorMapper;

    @Resource
    private MoocFilmTMapper filmMapper;

    @Resource
    private MoocFilmInfoTMapper filmInfoMapper;

    @Override
    public IPage<ActorsListRespVO> getActorsList(BasePageVO pageVO) throws CommonServiceException {
        return actorMapper.findActors(new Page<>(pageVO.getNowPage(), pageVO.getPageSize()));
    }

    @Override
    public IPage<FilmsListRespVO> getFilmsList(BasePageVO pageVO) throws CommonServiceException {
        return filmMapper.findFilms(new Page<>(pageVO.getNowPage(), pageVO.getPageSize()));
    }

    @Override
    public FilmsInfoRespVO getFilmInfo(String filmId) throws CommonServiceException {
        return null;
    }

    @Override
    public void saveFilm(FilmsAddReqVO reqVO) throws CommonServiceException {

    }
}

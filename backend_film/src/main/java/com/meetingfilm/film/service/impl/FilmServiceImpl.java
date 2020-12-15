package com.meetingfilm.film.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meetingfilm.apis.films.vo.FilmsInfoRespVO;
import com.meetingfilm.film.controller.vo.ActorsListRespVO;
import com.meetingfilm.film.controller.vo.FilmsAddReqVO;
import com.meetingfilm.film.controller.vo.FilmsListRespVO;
import com.meetingfilm.film.dao.entity.MoocFilmActorT;
import com.meetingfilm.film.dao.entity.MoocFilmInfoT;
import com.meetingfilm.film.dao.entity.MoocFilmT;
import com.meetingfilm.film.dao.mapper.MoocActorTMapper;
import com.meetingfilm.film.dao.mapper.MoocFilmActorTMapper;
import com.meetingfilm.film.dao.mapper.MoocFilmInfoTMapper;
import com.meetingfilm.film.dao.mapper.MoocFilmTMapper;
import com.meetingfilm.film.service.IFilmService;
import com.meetingfilm.utils.common.vo.BasePageVO;
import com.meetingfilm.utils.exception.CommonServiceException;
import com.meetingfilm.utils.util.ToolUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    private MoocFilmActorTMapper filmActorMapper;


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
        return filmMapper.findById(filmId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveFilm(FilmsAddReqVO reqVO) throws CommonServiceException {
        try {
            //保存电影主表
            MoocFilmT film = new MoocFilmT();
            film.setFilmName(reqVO.getFilmName());
            film.setFilmType(ToolUtils.str2Int(reqVO.getFilmTypeId()));
            film.setImgAddress(reqVO.getMainImgAddress());
            film.setFilmScore(reqVO.getFilmScore());
            film.setFilmPresalenum(ToolUtils.str2Int(reqVO.getPreSaleNum()));
            film.setFilmBoxOffice(ToolUtils.str2Int(reqVO.getBoxOffice()));
            film.setFilmSource(ToolUtils.str2Int(reqVO.getFilmSourceId()));
            film.setFilmCats(reqVO.getFilmCatIds());
            film.setFilmArea(ToolUtils.str2Int(reqVO.getAreaId()));
            film.setFilmDate(ToolUtils.str2Int(reqVO.getDateId()));
            film.setFilmTime(ToolUtils.str2LocalDateTime(reqVO.getFilmTime() + " 00:00:00"));
            film.setFilmStatus(ToolUtils.str2Int(reqVO.getFilmStatus()));
            filmMapper.insert(film);
            //保存电影子表
            MoocFilmInfoT filmInfo = new MoocFilmInfoT();
            filmInfo.setFilmId(film.getUuid() + "");
            filmInfo.setFilmEnName(reqVO.getFilmEnName());
            filmInfo.setFilmScore(reqVO.getFilmScore());
            filmInfo.setFilmScoreNum(ToolUtils.str2Int(reqVO.getFilmScorers()));
            filmInfo.setFilmLength(ToolUtils.str2Int(reqVO.getFilmLength()));
            filmInfo.setBiography(reqVO.getBiography());
            filmInfo.setDirectorId(ToolUtils.str2Int(reqVO.getDirectorId()));
            filmInfo.setFilmImgs(reqVO.getFilmImgs());
            filmInfoMapper.insert(filmInfo);
            //保存演员表
            String[] actorId = reqVO.getActIds().split("#");
            String[] roleNames = reqVO.getRoleNames().split("#");
            if (actorId.length != roleNames.length) {
                throw new CommonServiceException(500, "演员和角色数量不匹配");
            }
            for (int i = 0; i < actorId.length; i++) {
                MoocFilmActorT filmActor = new MoocFilmActorT();
                filmActor.setFilmId(film.getUuid());
                filmActor.setActorId(ToolUtils.str2Int(actorId[i]));
                filmActor.setRoleName(roleNames[i]);
                filmActorMapper.insert(filmActor);
            }
        } catch (Exception e) {
            throw new CommonServiceException(500, e.getMessage());
        }
    }
}

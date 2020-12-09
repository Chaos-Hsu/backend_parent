package com.meetingfilm.film.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.meetingfilm.film.controller.vo.ActorsListRespVO;
import com.meetingfilm.film.controller.vo.FilmsInfoRespVO;
import com.meetingfilm.film.controller.vo.FilmsListRespVO;
import com.meetingfilm.film.service.IFilmService;
import com.meetingfilm.utils.common.vo.BasePageVO;
import com.meetingfilm.utils.common.vo.BaseResponseVo;
import com.meetingfilm.utils.exception.CommonServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 影片模块
 * </p>
 *
 * @author xqc
 * @since 2020-12-09
 */
@RestController
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private IFilmService filmService;

    /**
     * 影片列表
     *
     * @return
     */
    @PostMapping
    public BaseResponseVo filmsList(BasePageVO pageVO) throws CommonServiceException {
        //校验
        pageVO.checkParam();
        //服务层
        IPage<FilmsListRespVO> respVO = filmService.getFilmsList(pageVO);
        return BaseResponseVo.success(pageResult(respVO, "films"));
    }

    /**
     * 影片详情
     *
     * @return
     */
    @PostMapping("/{filmId}")
    public BaseResponseVo filmsInfo(@PathVariable("filmId") String filmId) throws CommonServiceException {
        FilmsInfoRespVO filmInfo = filmService.getFilmInfo(filmId);
        if (filmInfo == null) {
            throw new CommonServiceException(404, "未找到影片");
        }
        return BaseResponseVo.success(filmInfo);
    }


    /**
     * 演员列表
     *
     * @return
     */
    @PostMapping("/actors")
    public BaseResponseVo actorsList(BasePageVO pageVO) throws CommonServiceException {
        //校验
        pageVO.checkParam();
        //服务层
        IPage<ActorsListRespVO> respVO = filmService.getActorsList(pageVO);
        return BaseResponseVo.success(pageResult(respVO, "actors"));
    }

    /**
     * 分页返回值
     *
     * @param datas
     * @param title
     * @return
     */
    private Map<String, Object> pageResult(IPage page, String title) {
        Map<String, Object> result = new HashMap<>();
        result.put("totalSize", page.getTotal());
        result.put("totalPage", page.getPages());
        result.put("nowPage", page.getCurrent());
        result.put("pageSize", page.getSize());
        result.put(title, page.getRecords());
        return result;
    }
}

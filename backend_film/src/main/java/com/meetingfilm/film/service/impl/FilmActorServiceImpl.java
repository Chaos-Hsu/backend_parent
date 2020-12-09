package com.meetingfilm.film.service.impl;

import com.meetingfilm.film.dao.entity.MoocFilmActorT;
import com.meetingfilm.film.dao.mapper.MoocFilmActorTMapper;
import com.meetingfilm.film.service.IFilmActorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 影片与演员映射表 服务实现类
 * </p>
 *
 * @author xqc
 * @since 2020-12-09
 */
@Service
public class FilmActorServiceImpl extends ServiceImpl<MoocFilmActorTMapper, MoocFilmActorT> implements IFilmActorService {

}

package com.meetingfilm.cinema.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meetingfilm.cinema.controller.vo.CinemaListReqVO;
import com.meetingfilm.cinema.controller.vo.CinemaListRespVO;
import com.meetingfilm.cinema.controller.vo.CinemaSaveReqVO;
import com.meetingfilm.cinema.dao.entity.MoocCinemaT;
import com.meetingfilm.cinema.dao.mapper.MoocCinemaTMapper;
import com.meetingfilm.cinema.service.ICinemaService;
import com.meetingfilm.utils.exception.CommonServiceException;
import com.meetingfilm.utils.util.ToolUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 影院信息表 服务实现类
 * </p>
 *
 * @author xqc
 * @since 2020-12-09
 */
@Service
public class CinemaServiceImpl extends ServiceImpl<MoocCinemaTMapper, MoocCinemaT> implements ICinemaService {


    @Resource
    private MoocCinemaTMapper cinemaMapper;

    @Override
    public IPage<CinemaListRespVO> findPage(CinemaListReqVO pageVO) throws CommonServiceException {
        return cinemaMapper.findPage(new Page<>(pageVO.getNowPage(), pageVO.getPageSize()));
    }

    @Override
    public void save(CinemaSaveReqVO saveVO) throws CommonServiceException {
        try {
            MoocCinemaT cinema = new MoocCinemaT();
            cinema.setCinemaName(saveVO.getCinemaName());
            cinema.setCinemaPhone(saveVO.getCinemaTele());
            cinema.setBrandId(ToolUtils.str2Int(saveVO.getBrandId()));
            cinema.setAreaId(ToolUtils.str2Int(saveVO.getAreaId()));
            cinema.setHallIds(saveVO.getHallTypeIds());
            cinema.setImgAddress(saveVO.getCinemaImgAddress());
            cinema.setCinemaAddress(saveVO.getCinemaAddress());
            cinema.setMinimumPrice(ToolUtils.str2Int(saveVO.getCinemaPrice()));
            cinemaMapper.insert(cinema);
        } catch (Exception e) {
            throw new CommonServiceException(500, e.getMessage());
        }
    }
}

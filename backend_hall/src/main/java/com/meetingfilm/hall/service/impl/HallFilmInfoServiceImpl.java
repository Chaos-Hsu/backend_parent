package com.meetingfilm.hall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meetingfilm.hall.controller.vo.HallsAddReqVO;
import com.meetingfilm.hall.controller.vo.HallsListReqVO;
import com.meetingfilm.hall.controller.vo.HallsListRespVO;
import com.meetingfilm.hall.dao.entity.MoocFieldT;
import com.meetingfilm.hall.dao.entity.MoocHallFilmInfoT;
import com.meetingfilm.hall.dao.mapper.MoocFieldTMapper;
import com.meetingfilm.hall.dao.mapper.MoocHallFilmInfoTMapper;
import com.meetingfilm.hall.service.IHallFilmInfoService;
import com.meetingfilm.utils.exception.CommonServiceException;
import com.meetingfilm.utils.util.ToolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * <p>
 * 影厅电影信息表 服务实现类
 * </p>
 *
 * @author xqc
 * @since 2020-12-09
 */
@Service
public class HallFilmInfoServiceImpl extends ServiceImpl<MoocHallFilmInfoTMapper, MoocHallFilmInfoT> implements IHallFilmInfoService {

    @Resource
    private MoocHallFilmInfoTMapper hallFilmInfoMapper;

    @Resource
    private MoocFieldTMapper fieldMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient eurekaClient;

    @Override
    public IPage<HallsListRespVO> getHallsList(HallsListReqVO pageVO) throws CommonServiceException {
        Page<HallsListReqVO> page = new Page<>(pageVO.getNowPage(), pageVO.getPageSize());
        //查询条件
        QueryWrapper queryWrapper = new QueryWrapper<>();
        String cinemaId = pageVO.getCinemaId();
        if (ToolUtils.strIsNotNul(cinemaId)) {
            queryWrapper.eq("cinema_id", cinemaId);
        }
        return fieldMapper.findPageByCinemaId(page, queryWrapper);
    }

    @Override
    public void saveHall(HallsAddReqVO reqVO) throws CommonServiceException {
        try {
            //影厅列表数据
            MoocFieldT field = new MoocFieldT();
            field.setCinemaId(ToolUtils.str2Int(reqVO.getCinemaId()));
            field.setFilmId(ToolUtils.str2Int(reqVO.getFilmId()));
            field.setBeginTime(reqVO.getBeginTime());
            field.setEndTime(reqVO.getEndTime());
            field.setHallId(ToolUtils.str2Int(reqVO.getHallTypeId()));
            field.setHallName(reqVO.getHallName());
            field.setPrice(ToolUtils.str2Int(reqVO.getFilmPrice()));
            fieldMapper.insert(field);
            //影厅对应影片数据
            MoocHallFilmInfoT hallFilmInfo = getHallFilmInfo(reqVO.getFilmId());
            hallFilmInfoMapper.insert(hallFilmInfo);
        } catch (Exception e) {
            throw new CommonServiceException(500, e.getMessage());
        }
    }


    /**
     * 获取播放厅内容
     *
     * @param filmId
     * @return
     * @throws Exception
     */
    private MoocHallFilmInfoT getHallFilmInfo(String filmId) throws CommonServiceException {
        //调用影片服务
        ServiceInstance choose = eurekaClient.choose("film-serivce");
        String host = choose.getHost();
        int port = choose.getPort();
        String uri = "/films/" + filmId;
        String url = "http://" + host + ":" + port + uri;
        //调用生产者
        JSONObject result = restTemplate.getForObject(url, JSONObject.class);
        if (result.getIntValue("code") != 200) {
            throw new CommonServiceException(500, "数据异常");
        }
        JSONObject data = result.getJSONObject("data");
        if (data == null) {
            throw new CommonServiceException(404, "未找到影片");
        }
        MoocHallFilmInfoT hallFilmInfo = new MoocHallFilmInfoT();
        hallFilmInfo.setFilmId(data.getIntValue("filmId"));
        hallFilmInfo.setFilmName(data.getString("filmName"));
        hallFilmInfo.setFilmLength(data.getString("filmLength"));
        hallFilmInfo.setFilmCats(data.getString("filmCats"));
        hallFilmInfo.setActors(data.getString("actors"));
        hallFilmInfo.setImgAddress(data.getString("imgAddress"));
        return hallFilmInfo;
    }


}

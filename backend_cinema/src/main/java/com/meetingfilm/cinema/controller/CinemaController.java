package com.meetingfilm.cinema.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.meetingfilm.cinema.controller.vo.CinemaListReqVO;
import com.meetingfilm.cinema.controller.vo.CinemaListRespVO;
import com.meetingfilm.cinema.controller.vo.CinemaSaveReqVO;
import com.meetingfilm.cinema.service.ICinemaService;
import com.meetingfilm.utils.common.vo.BaseResponseVo;
import com.meetingfilm.utils.exception.CommonServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 影院信息表
 * </p>
 *
 * @author xqc
 * @since 2020-12-09
 */
@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    /**
     * 影院服务
     */
    @Autowired
    private ICinemaService cinemaService;


    /**
     * 添加影院
     *
     * @return
     */
    @PostMapping("/add")
    public BaseResponseVo add(@RequestBody CinemaSaveReqVO saveReqVO) throws CommonServiceException {
        //参数校验
        saveReqVO.checkParam();
        cinemaService.save(saveReqVO);
        return BaseResponseVo.success();
    }


    /**
     * 查询影院列表
     *
     * @param pageVO
     * @return
     */
    @GetMapping
    public BaseResponseVo getCinemasList(@RequestBody CinemaListReqVO pageVO) throws CommonServiceException {
        //校验参数
        pageVO.checkParam();
        IPage<CinemaListRespVO> resultPage = cinemaService.findPage(pageVO);
        return BaseResponseVo.success(pageResult(resultPage, "cinemas"));
    }

    /**
     * 分页返回值
     *
     * @param page
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

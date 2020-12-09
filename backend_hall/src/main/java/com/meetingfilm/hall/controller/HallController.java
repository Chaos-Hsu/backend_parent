package com.meetingfilm.hall.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.meetingfilm.hall.controller.vo.HallsAddReqVO;
import com.meetingfilm.hall.controller.vo.HallsListReqVO;
import com.meetingfilm.hall.controller.vo.HallsListRespVO;
import com.meetingfilm.hall.service.IHallFilmInfoService;
import com.meetingfilm.utils.common.vo.BaseResponseVo;
import com.meetingfilm.utils.exception.CommonServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 热映
 * </p>
 *
 * @author xqc
 * @since 2020-12-09
 */
@RestController
@RequestMapping("/halls")
public class HallController {

    @Autowired
    private IHallFilmInfoService hallService;


    /**
     * 热映列表
     *
     * @return
     */
    @PostMapping
    public BaseResponseVo filmsList(HallsListReqVO pageVO) throws CommonServiceException {
        //校验
        pageVO.checkParam();
        //服务层
        IPage<HallsListRespVO> respVO = hallService.getHallsList(pageVO);
        return BaseResponseVo.success(pageResult(respVO, "halls"));
    }


    /**
     * 热映新增
     *
     * @return
     */
    @PostMapping("/add")
    public BaseResponseVo hallsAdd(@RequestBody HallsAddReqVO reqVO) throws CommonServiceException {
        reqVO.checkParam();
        hallService.saveHall(reqVO);
        return BaseResponseVo.success();
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

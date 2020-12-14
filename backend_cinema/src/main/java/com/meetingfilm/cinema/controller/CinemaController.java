package com.meetingfilm.cinema.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.meetingfilm.cinema.controller.vo.CinemaListReqVO;
import com.meetingfilm.cinema.controller.vo.CinemaListRespVO;
import com.meetingfilm.cinema.controller.vo.CinemaSaveReqVO;
import com.meetingfilm.cinema.service.ICinemaService;
import com.meetingfilm.utils.common.vo.BaseResponseVo;
import com.meetingfilm.utils.exception.CommonServiceException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
     * 降级处理
     *
     * @param pageVO
     * @return
     * @throws CommonServiceException
     */
    public BaseResponseVo fallbackMethod(@RequestBody CinemaListReqVO pageVO) throws CommonServiceException {
        //Map<String, Object> result = new HashMap<>();
        //result.put("code", 500);
        //result.put("msg", "降级处理");
        //return BaseResponseVo.success(result);
        return BaseResponseVo.success();//保底方案
    }


    /**
     * 查询影院列表
     *
     * @param pageVO
     * @return
     */
    @HystrixCommand(
            fallbackMethod = "fallbackMethod",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),//线程隔离
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),//线程超时时间
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//10个请求熔断器设置
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")//百分之五十错误,熔断器开启
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "1"),//核心线程数
                    @HystrixProperty(name = "maxQueueSize", value = "10"),//队列线程数
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "1000"),//空闲时间
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "8"),//任务请求上线
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1500")
            },
            ignoreExceptions = {CommonServiceException.class}
    )
    @GetMapping
    public BaseResponseVo getCinemasList(@RequestBody CinemaListReqVO pageVO) throws CommonServiceException {

        if (pageVO.getNowPage() > 1000) {
            throw new CommonServiceException(400, "分页太大");
        }
        //try {
        //    if (pageVO.getNowPage() > 1000) {
        //        Thread.sleep(5000L);
        //    }
        //} catch (Exception e) {
        //}

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

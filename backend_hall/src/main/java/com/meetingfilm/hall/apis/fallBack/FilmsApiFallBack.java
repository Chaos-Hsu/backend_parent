package com.meetingfilm.hall.apis.fallBack;

import com.meetingfilm.apis.films.vo.FilmsInfoRespVO;
import com.meetingfilm.hall.apis.FilmsApi;
import com.meetingfilm.utils.common.vo.BaseResponseVo;
import com.meetingfilm.utils.exception.CommonServiceException;
import org.springframework.stereotype.Service;

/**
 * 描述 : 服务降级
 * 作者 : 徐起超
 * 时间 : 2020/12/15 7:11 下午
 */
@Service
public class FilmsApiFallBack implements FilmsApi {

    /**
     * 服务降级
     *
     * @param filmId
     * @return
     * @throws CommonServiceException
     */
    @Override
    public BaseResponseVo<FilmsInfoRespVO> filmsInfo(String filmId) throws CommonServiceException {
        BaseResponseVo success = BaseResponseVo.success();
        success.setMsg("服务降级了");
        return success;
    }
}

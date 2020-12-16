package com.meetingfilm.hall.apis;

import com.meetingfilm.apis.films.BaseFilmsApi;
import com.meetingfilm.hall.apis.fallBack.FilmsApiFallBack;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述 : 影片服务接口
 * 作者 : 徐起超
 * 时间 : 2020/12/15 6:59 下午
 */
@FeignClient(name = "film-service", path = "films", fallback = FilmsApiFallBack.class)
public interface FilmsApi extends BaseFilmsApi {
}

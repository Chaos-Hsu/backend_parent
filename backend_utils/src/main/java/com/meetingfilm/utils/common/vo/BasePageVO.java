package com.meetingfilm.utils.common.vo;

import com.meetingfilm.utils.exception.CommonServiceException;
import lombok.Data;

/**
 * 描述 : 分页请求
 * 作者 : 徐起超
 * 时间 : 2020/12/9 1:55 下午
 */
@Data
public class BasePageVO extends BaseRequestVo{


    private Integer nowPage=1;
    private Integer pageSize=10;


    @Override
    public void checkParam() throws CommonServiceException {
        if (this.nowPage == null || this.nowPage <= 0) {
            throw new CommonServiceException(404, "当前页不能为空");
        }
        if (this.pageSize == null || this.pageSize <= 0) {
            throw new CommonServiceException(404, "页码不能为空");
        }
    }
}

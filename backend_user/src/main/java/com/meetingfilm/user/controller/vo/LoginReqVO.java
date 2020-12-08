package com.meetingfilm.user.controller.vo;

import com.meetingfilm.utils.common.vo.BaseRequestVo;
import com.meetingfilm.utils.exception.CommonServiceException;
import com.meetingfilm.utils.util.ToolUtils;
import lombok.Data;

/**
 * 描述 : 登录请求
 * 作者 : 徐起超
 * 时间 : 2020/12/8 5:43 下午
 */
@Data
public class LoginReqVO extends BaseRequestVo {

    private String userName;
    private String password;


    @Override
    public void checkParam() throws CommonServiceException {
        if (ToolUtils.strIsNull(userName) || ToolUtils.strIsNull(password)) {
            throw new CommonServiceException(404, "用户名或密码为空");
        }
    }
}

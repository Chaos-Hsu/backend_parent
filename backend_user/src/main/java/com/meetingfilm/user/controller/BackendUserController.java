package com.meetingfilm.user.controller;


import com.meetingfilm.user.controller.vo.LoginReqVO;
import com.meetingfilm.user.service.IMoocBackendUserTService;
import com.meetingfilm.utils.common.vo.BaseResponseVo;
import com.meetingfilm.utils.exception.CommonServiceException;
import com.meetingfilm.utils.util.JwtTokenUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户相关内容
 *
 * @author xqc
 * @since 2020-12-07
 */
@RestController
@RequestMapping("/user")
public class BackendUserController {

    /**
     * 业务逻辑
     */
    @Resource
    private IMoocBackendUserTService userService;


    /**
     * 登录
     *
     * @param reqVO
     * @return
     * @throws CommonServiceException
     */
    @PostMapping("/login")
    public BaseResponseVo login(@RequestBody LoginReqVO reqVO) throws CommonServiceException {
        //校验数据
        reqVO.checkParam();
        //校验用户名和密码
        String userId = userService.checkUserLogin(reqVO.getUserName(), reqVO.getPassword());
        //生成TOKEN和盐
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        //盐
        String randomKey = jwtTokenUtil.getRandomKey();
        //TOKEN
        String token = jwtTokenUtil.generateToken(userId, randomKey);
        //结果数据
        Map<String, String> result = new HashMap<>();
        result.put("randomKey", randomKey);
        result.put("token", token);
        return BaseResponseVo.success(result);
    }


}

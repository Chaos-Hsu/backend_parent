package com.meetingfilm.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.meetingfilm.user.dao.entity.MoocBackendUserT;
import com.meetingfilm.utils.exception.CommonServiceException;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author xqc
 * @since 2020-12-07
 */
public interface IMoocBackendUserTService extends IService<MoocBackendUserT> {

    /**
     * 用户校验登录
     *
     * @param username
     * @param password
     * @return
     * @throws CommonServiceException
     */
    String checkUserLogin(String username, String password) throws CommonServiceException;


}

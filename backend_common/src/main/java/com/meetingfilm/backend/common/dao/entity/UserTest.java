package com.meetingfilm.backend.common.dao.entity;

import lombok.Builder;
import lombok.Data;

/**
 * <p>
 * 后台用户表
 * </p>
 *
 * @author xqc
 * @since 2020-12-01
 */
@Data
//@Builder
public class UserTest {


    /**
     * 用户账号
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPwd;

    /**
     * 用户手机号
     */
    private String userPhone;

    public UserTest() {
    }
}

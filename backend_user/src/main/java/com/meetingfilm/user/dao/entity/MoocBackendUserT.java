package com.meetingfilm.user.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 后台用户表
 * </p>
 *
 * @author xqc
 * @since 2020-12-07
 */
@Data
public class MoocBackendUserT extends Model<MoocBackendUserT> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键编号
     */
    @TableId(value = "UUID", type = IdType.AUTO)
    private Integer uuid;

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


    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "MoocBackendUserT{" + ", uuid=" + uuid + ", userName=" + userName + ", userPwd=" + userPwd + ", userPhone=" + userPhone + "}";
    }
}

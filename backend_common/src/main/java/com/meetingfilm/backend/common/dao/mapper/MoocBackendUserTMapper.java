package com.meetingfilm.backend.common.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meetingfilm.backend.common.dao.entity.MoocBackendUserT;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author xqc
 * @since 2020-12-01
 */
public interface MoocBackendUserTMapper extends BaseMapper<MoocBackendUserT> {


    MoocBackendUserT selectOneByUserName(@Param("user_name") String userName);


}

package com.meetingfilm.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meetingfilm.user.dao.entity.MoocBackendUserT;
import com.meetingfilm.user.dao.mapper.MoocBackendUserTMapper;
import com.meetingfilm.user.service.IMoocBackendUserTService;
import com.meetingfilm.utils.exception.CommonServiceException;
import com.meetingfilm.utils.util.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author xqc
 * @since 2020-12-07
 */
@Service
public class MoocBackendUserTServiceImpl extends ServiceImpl<MoocBackendUserTMapper, MoocBackendUserT> implements IMoocBackendUserTService {

    @Resource
    private MoocBackendUserTMapper userMapper;

    @Override
    public String checkUserLogin(String username, String password) throws CommonServiceException {
        //查询语句
        QueryWrapper<MoocBackendUserT> query = new QueryWrapper<>();
        query.eq("user_name", username);
        //用户
        MoocBackendUserT user = null;
        List<MoocBackendUserT> list = userMapper.selectList(query);
        if (!CollectionUtils.isEmpty(list)) {
            user = list.stream().findFirst().get();
        } else {
            throw new CommonServiceException(404, "用户名输入有误");
        }
        //密码校验
        String targetPsw = MD5Util.encrypt(password);
        if (!targetPsw.equals(user.getUserPwd())) {
            throw new CommonServiceException(500, "密码输入有误");
        }
        return user.getUuid().toString();
    }
}

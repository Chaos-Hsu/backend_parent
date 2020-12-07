package com.meetingfilm.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.meetingfilm.user.dao.entity.MoocBackendUserT;
import com.meetingfilm.user.dao.mapper.MoocBackendUserTMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述 : 工具测试
 * 作者 : 徐起超
 * 时间 : 2020/12/1 2:25 下午
 */
@Slf4j
public class UserTest extends BackendUserApplicationTests {

    @Resource
    private MoocBackendUserTMapper backendUser;

    @Test
    public void add() {

    }

    @Test
    public void select() {
        //主键
        //MoocBackendUserT moocBackendUserT = backendUser.selectById("2");
        //System.out.println(moocBackendUserT);
        //列表
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_name", "admin");
        List<MoocBackendUserT> userList = backendUser.selectList(queryWrapper);
        userList.stream().forEach(System.out::println);
    }


}

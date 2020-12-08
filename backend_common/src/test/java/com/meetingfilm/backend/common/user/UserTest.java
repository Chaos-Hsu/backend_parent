package com.meetingfilm.backend.common.user;

import com.meetingfilm.backend.common.BackendCommonApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * 描述 : 工具测试
 * 作者 : 徐起超
 * 时间 : 2020/12/1 2:25 下午
 */
@Slf4j
public class UserTest extends BackendCommonApplicationTests {

    @Resource
    //private MoocBackendUserTMapper backendUser;

    @Test
    public void add() {
        for (int i = 0; i < 5; i++) {
            //MoocBackendUserT user = new MoocBackendUserT();
            //user.setUserName("admin" + i);
            //user.setUserPwd("admin" + i);
            //user.setUserPhone("1310000000" + i);
            //backendUser.insert(user);
        }
    }

    @Test
    public void select() {
        //主键
        //MoocBackendUserT moocBackendUserT = backendUser.selectById("2");
        //System.out.println(moocBackendUserT);
        ////列表
        //QueryWrapper queryWrapper = new QueryWrapper<>();
        //queryWrapper.like("user_name", "admin");
        //List<MoocBackendUserT> userList = backendUser.selectList(queryWrapper);
        //userList.stream().forEach(System.out::print);
    }

    @Test
    public void selectByPage() {
        //Page<MoocBackendUserT> page = new Page<>(1, 2);
        ////列表
        //QueryWrapper queryWrapper = new QueryWrapper<>();
        //queryWrapper.like("user_name", "admin");
        //
        //IPage<MoocBackendUserT> userList = backendUser.selectPage(page, queryWrapper);
        ////List<MoocBackendUserT> userList = backendUser.selectList(queryWrapper);
        //userList.getRecords().stream().forEach(System.out::println);
    }


    @Test
    public void update() {
        //MoocBackendUserT user = new MoocBackendUserT();
        //user.setUuid(2);
        //user.setUserName("admin");
        //user.setUserPwd("xqc");
        //user.setUserPhone("18100000000");
        //backendUser.updateById(user);

        //QueryWrapper queryWrapper = new QueryWrapper();
        //queryWrapper.eq("user_name", "admin000");
        //MoocBackendUserT user = new MoocBackendUserT();
        //user.setUserName("admin4");
        //user.setUserPwd("admin4");
        //user.setUserPhone("18189801238");
        //backendUser.update(user, queryWrapper);
    }

    //@Test
    //public void delete() {
    //    backendUser.deleteById("2");
    //}


    //@Test
    //public void selectUserName() {
    //
    //    MoocBackendUserT userT = backendUser.selectOneByUserName("admin4");
    //    System.out.println(userT);
    //
    //    //com.meetingfilm.backend.common.dao.entity.UserTest.builder().userName("").userPhone("");
    //
    //
    //
    //    //try {
    //        //@Cleanup FileInputStream fileInputStream = new FileInputStream(new File(""));
    //    //} catch (FileNotFoundException e) {
    //       //log.error(e.getMessage());
    //    //}
    //}
}

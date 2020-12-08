package com.meetingfilm.user.utils;

import com.meetingfilm.user.dao.entity.MoocBackendUserT;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2020/12/6 8:39 下午
 */
public class UtilsTest {

    @Test
    public void name() {
        List<MoocBackendUserT> list = new ArrayList<>();
        //测试数据
        for (int i = 0; i < 5; i++) {
            MoocBackendUserT user = new MoocBackendUserT();
            user.setUserName(i + 1 + "");
            user.setUserPwd("a" + i);
            user.setUserPhone("185" + i);
            list.add(user);
        }
        //
        //List<MoocBackendUserT> aList = new ArrayList<>();
        //
        //list.stream().filter(user -> user.getUserName().equals("1"))
        //        .forEach(user -> aList.add(user));
        //
        //
        //aList.forEach(System.out::println);

        //Stream.iterate(1, integer -> integer + 1)
        //        .limit(10).forEach(System.out::print);
        //
        //Stream.generate(Math::random)
        //        .limit(10).forEach(System.out::print);


    }
}

package com.meetingfilm.hystrix;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.Test;

import java.util.concurrent.Future;

/**
 * 描述 : 请求合并
 * 作者 : 徐起超
 * 时间 : 2020/12/14 2:30 下午
 */
public class CommandCollasperTest {


    @Test
    public void name() throws Exception {
        HystrixRequestContext requestContext = HystrixRequestContext.initializeContext();

        //多个服务多次Http请求合并
        //缺点 很少有机会对同一个服务进行多次调用 时间比较近
        CommandCollasper c1 = new CommandCollasper(1);
        CommandCollasper c2 = new CommandCollasper(2);
        CommandCollasper c3 = new CommandCollasper(3);
        CommandCollasper c4 = new CommandCollasper(4);

        //结果 请求足够近  默认10毫秒
        Future<String> q1 = c1.queue();
        Thread.sleep(500);
        Future<String> q2 = c2.queue();
        Thread.sleep(500);
        Future<String> q3 = c3.queue();
        Thread.sleep(500);
        Future<String> q4 = c4.queue();


        System.out.println("r1" + q1.get() + ",r2" + q2.get() + ",r3" + q3.get() + ",r4" + q4.get());


        requestContext.close();
    }
}

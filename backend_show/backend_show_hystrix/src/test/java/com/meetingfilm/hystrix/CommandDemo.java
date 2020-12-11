package com.meetingfilm.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import lombok.Data;

/**
 * 描述 : 异步线程调用
 * 作者 : 徐起超
 * 时间 : 2020/12/10 4:38 下午
 */
@Data
public class CommandDemo extends HystrixCommand {

    private String name;

    public CommandDemo(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("COMMAND_DEMO")));
        this.name = name;
    }

    /**
     * 业务逻辑
     *
     * @return
     * @throws Exception
     */
    @Override
    protected String run() throws Exception {
        String result = "CommandDemo name:" + this.name;
        Thread.sleep(800);
        System.out.println(result + ";thread=" + Thread.currentThread().getName());
        return result;
    }
}

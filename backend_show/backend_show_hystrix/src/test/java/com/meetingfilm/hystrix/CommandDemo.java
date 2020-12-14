package com.meetingfilm.hystrix;

import com.netflix.hystrix.*;
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
        super(
                Setter
                        .withGroupKey(HystrixCommandGroupKey.Factory.asKey("COMMAND_DEMO"))//分组&线程池名称
                        .andCommandKey(HystrixCommandKey.Factory.asKey("COMMAND_KEY"))//
                        .andCommandPropertiesDefaults(
                                HystrixCommandProperties.defaultSetter()
                                        .withRequestCacheEnabled(false)//关闭请求缓存
                                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)//信号量(不重开线程)
                                        .withExecutionIsolationSemaphoreMaxConcurrentRequests(2)//最大信号量
                                        .withFallbackIsolationSemaphoreMaxConcurrentRequests(5)
                                //.withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)//线程(新开线程)
                                .withCircuitBreakerRequestVolumeThreshold(2)//单位时间内两个请求
                                .withCircuitBreakerErrorThresholdPercentage(50)//失败率百分之50开启熔断
                                //.withCircuitBreakerForceOpen(true)//强制开启熔断
                        )
                        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("MY_THREAD_POOL"))
                //线程隔离
                //.andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.defaultSetter()
                //    .withCoreSize(2)//核心线程
                //    .withMaximumSize(3)//最大线程数量
                //    .withAllowMaximumSizeToDivergeFromCoreSize(true)//开启最大线程
                //    .withMaxQueueSize(2)//等待队列数
                //)

        );
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
        //Thread.sleep(800);

        //手动创建异常
        if (this.name.startsWith("xqc")) {
            int i = 1 / 0;
        }

        System.err.println(result + ";thread=" + Thread.currentThread().getName());
        return result;
    }

    /**
     * 请求缓存
     *
     * @return
     */
    @Override
    protected String getCacheKey() {
        return this.name;
    }


    /**
     * 降级处理
     *
     * @return
     */
    @Override
    protected String getFallback() {
        String result = "Fallback name:" + this.name;
        System.err.println(result + ";thread=" + Thread.currentThread().getName());
        return result;
    }
}

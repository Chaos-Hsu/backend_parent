package com.meetingfilm.hystrix;

import org.junit.Test;
import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.Future;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2020/12/10 4:43 下午
 */
public class CommandTest {

    /**
     * 同步(单次处理)
     */
    @Test
    public void executeTest() {
        long startTime = System.currentTimeMillis();
        CommandDemo execute = new CommandDemo("execute");
        //同步执行
        String result = (String) execute.execute();
        long endTime = System.currentTimeMillis();
        System.out.println("同步执行:" + result + ";花费时间=" + (endTime - startTime));
    }

    /**
     * 异步(单次处理)
     *
     * @throws Exception
     */
    @Test
    public void queueTest() throws Exception {
        long startTime = System.currentTimeMillis();
        CommandDemo execute = new CommandDemo("execute");
        //同步执行
        Future<String> queue = execute.queue();
        long endTime = System.currentTimeMillis();
        System.out.println("异步执行;花费时间=" + (endTime - startTime));
        long endTime2 = System.currentTimeMillis();
        System.out.println("异步执行获取;花费时间=" + queue.get() + (endTime2 - startTime));
    }

    /**
     * 同一个对象可以执行多次(多次处理)热处理
     * 特性:
     * <p>
     * 执行步骤:
     * 1.执行commmad的run方法
     * 2.初始化加载/注册Subscriber对象
     * 3.将run方法的结果注入到Subscriber对象的onNext方法
     */
    @Test
    public void observe() {
        long startTime = System.currentTimeMillis();
        CommandDemo execute = new CommandDemo("observe");
        Observable observe = execute.observe();
        //阻塞式
        String single = (String) observe.toBlocking().single();
        long endTime = System.currentTimeMillis();
        System.out.println("Observe阻塞式执行花费时间=" + (endTime - startTime));

        //非阻塞式
        observe.subscribe(new Subscriber() {
            @Override
            public void onCompleted() {
                System.out.println("Observe非阻塞式:onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Observe非阻塞式:onError=" + throwable);
            }

            @Override
            public void onNext(Object result) {
                long endTime = System.currentTimeMillis();
                System.out.println("Observe非阻塞式执行花费时间:" + (endTime - startTime) + ";result=" + result);
            }
        });
    }


    /**
     * 同一个对象不可以执行多次(多次处理)冷处理
     * 特性:每次订阅都需要一个新的command对象
     * <p>
     * 执行步骤:
     * 1.初始化加载/注册Subscriber对象
     * 2.执行commmad的run方法
     * 3.将run方法的结果注入到Subscriber对象的onNext方法
     */
    @Test
    public void toObservable() throws Exception {
        long startTime = System.currentTimeMillis();
        CommandDemo execute = new CommandDemo("toObserve1");
        Observable observe = execute.toObservable();
        //阻塞式
        String single = (String) observe.toBlocking().single();
        long endTime = System.currentTimeMillis();
        System.out.println("toObserve1阻塞式执行花费时间=" + (endTime - startTime));

        //非阻塞式
        long startTime2 = System.currentTimeMillis();
        CommandDemo execute2 = new CommandDemo("toObserve2");
        Observable observe2 = execute2.toObservable();
        observe2.subscribe(new Subscriber() {
            @Override
            public void onCompleted() {
                System.out.println("toObserve2非阻塞式:onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("toObserve2非阻塞式:onError=" + throwable);
            }

            @Override
            public void onNext(Object result) {
                long endTime = System.currentTimeMillis();
                System.out.println("toObserve2非阻塞式执行花费时间:" + (endTime - startTime2) + ";result=" + result);
            }
        });
        Thread.sleep(5000);
    }
}

package com.meetingfilm.hystrix;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
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
        HystrixRequestContext requestContext = HystrixRequestContext.initializeContext();
        long startTime = System.currentTimeMillis();
        CommandDemo execute = new CommandDemo("execute");
        //同步执行
        String result = (String) execute.execute();
        long endTime = System.currentTimeMillis();
        System.out.println("同步执行:" + result + ";花费时间=" + (endTime - startTime));
        requestContext.close();
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


    /**
     * 请求缓存
     */
    @Test
    public void requestCache() {
        //请求上下文
        HystrixRequestContext requestContext = HystrixRequestContext.initializeContext();

        long startTime = System.currentTimeMillis();

        CommandDemo execute1 = new CommandDemo("execute1");
        System.out.println("同步执行:" + execute1.execute() + ";花费时间=" + (System.currentTimeMillis() - startTime));

        CommandDemo execute2 = new CommandDemo("execute2");
        System.out.println("同步执行:" + execute2.execute() + ";花费时间=" + (System.currentTimeMillis() - startTime));

        CommandDemo execute3 = new CommandDemo("execute1");
        System.out.println("同步执行:" + execute3.execute() + ";花费时间=" + (System.currentTimeMillis() - startTime));

        //关闭请求上下文
        requestContext.close();
    }


    /**
     * 线程隔离
     *
     * @throws Exception
     */
    @Test
    public void threadTest() throws Exception {

        CommandDemo execute1 = new CommandDemo("execute1");
        Future<String> queue1 = execute1.queue();

        CommandDemo execute2 = new CommandDemo("execute2");
        Future<String> queue2 = execute2.queue();

        CommandDemo execute3 = new CommandDemo("execute3");
        Future<String> queue3 = execute3.queue();

        CommandDemo execute4 = new CommandDemo("execute4");
        Future<String> queue4 = execute4.queue();

        CommandDemo execute5 = new CommandDemo("execute5");
        Future<String> queue5 = execute5.queue();


        System.out.println(queue1.get() + "," + queue2.get() + "," + queue3.get() + "," + queue4.get() + "," + queue5.get());
    }


    /**
     * 信号量隔离
     *
     * @throws Exception
     */
    @Test
    public void semTest() throws Exception {

        MyThread m1 = new MyThread("m1");
        MyThread m2 = new MyThread("m2");
        MyThread m3 = new MyThread("m3");
        MyThread m4 = new MyThread("m4");
        MyThread m5 = new MyThread("m5");

        m1.start();
        m2.start();
        m3.start();
        m4.start();
        m5.start();

        Thread.sleep(5000L);
    }
}

class MyThread extends Thread {

    private String name;

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        CommandDemo commandDemo = new CommandDemo(this.name);
        commandDemo.execute();
    }
}



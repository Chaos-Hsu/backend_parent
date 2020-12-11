package com.meetingfilm.hystrix;

import org.junit.Test;
import rx.Observable;
import rx.Subscriber;

/**
 * 描述 : 当前线程执行调用
 * 作者 : 徐起超
 * 时间 : 2020/12/10 4:38 下午
 */
public class ObservableCommandTest {


    @Test
    public void observe() throws Exception {
        long startTime = System.currentTimeMillis();
        ObservableCommandDemo execute = new ObservableCommandDemo("observableCommand");
        Observable observe = execute.observe();

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
        Thread.sleep(1000);
    }
}

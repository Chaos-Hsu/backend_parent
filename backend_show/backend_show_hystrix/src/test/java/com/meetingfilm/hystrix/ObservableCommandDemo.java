package com.meetingfilm.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import lombok.Data;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2020/12/10 4:38 下午
 */
@Data
public class ObservableCommandDemo extends HystrixObservableCommand {

    private String name;

    public ObservableCommandDemo(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("OBSERVABLE_COMMAND_DEMO")));
        this.name = name;
    }


    @Override
    protected Observable<String> construct() {
        String result = "ObservableCommandDemo name:" + this.name;
        System.out.println(result + ";thread=" + Thread.currentThread().getName());
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //业务处理
                subscriber.onNext("action1,name=" + name);
                subscriber.onNext("action2,name=" + name);
                subscriber.onNext("action3,name=" + name);
                subscriber.onNext("action4,name=" + name);

                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io());
    }
}

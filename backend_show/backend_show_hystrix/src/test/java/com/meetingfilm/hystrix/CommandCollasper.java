package com.meetingfilm.hystrix;

import com.netflix.hystrix.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 描述 : 请求合并对象
 * 作者 : 徐起超
 * 时间 : 2020/12/11 5:05 下午
 */
public class CommandCollasper extends HystrixCollapser<List<String>, String, Integer> {

    private Integer id;


    public CommandCollasper(Integer id) {
        super(Setter
                .withCollapserKey(HystrixCollapserKey.Factory.asKey("COMMAND_COLLASPER"))
                .andCollapserPropertiesDefaults(
                        HystrixCollapserProperties.defaultSetter().withTimerDelayInMilliseconds(2000)//超时时间
                )
        );
        this.id = id;
    }

    /**
     * 获取请求参数
     *
     * @return
     */
    @Override
    public Integer getRequestArgument() {
        return this.id;
    }

    /**
     * 批量业务处理
     *
     * @param collection
     * @return
     */
    @Override
    protected HystrixCommand<List<String>> createCommand(Collection<CollapsedRequest<String, Integer>> collection) {
        return new BacthCommand(collection);
    }

    /**
     * 批量处理结果与请求业务之间映射关系处理
     *
     * @param strings
     * @param collection
     */
    @Override
    protected void mapResponseToRequests(List<String> strings, Collection<CollapsedRequest<String, Integer>> collection) {

        int counts = 0;

        Iterator<HystrixCollapser.CollapsedRequest<String, Integer>> iterator =
                collection.iterator();

        while (iterator.hasNext()) {
            HystrixCollapser.CollapsedRequest<String, Integer> next = iterator.next();

            String result = strings.get(counts++);
            next.setResponse(result);
        }

    }
}


class BacthCommand extends HystrixCommand<List<String>> {

    private Collection<HystrixCollapser.CollapsedRequest<String, Integer>> collection;

    public BacthCommand(Collection<HystrixCollapser.CollapsedRequest<String, Integer>> collection) {
        super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("BACTH_COMMAND"))
        );
        this.collection = collection;
    }

    @Override
    protected List<String> run() throws Exception {
        System.out.println("currentThread=" + Thread.currentThread().getName());
        //处理结果
        List<String> result = new ArrayList<>();

        Iterator<HystrixCollapser.CollapsedRequest<String, Integer>> iterator =
                collection.iterator();

        while (iterator.hasNext()) {
            HystrixCollapser.CollapsedRequest<String, Integer> next = iterator.next();
            Integer argument = next.getArgument();
            result.add("req" + argument.toString());
        }
        return result;
    }
}

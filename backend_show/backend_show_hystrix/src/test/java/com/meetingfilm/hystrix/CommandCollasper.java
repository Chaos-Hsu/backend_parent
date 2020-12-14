package com.meetingfilm.hystrix;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.Arrays;
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
        super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("COMMAND_COLLASPER")));
        this.id = id;
    }

    /**
     * 获取请求参数
     *
     * @return
     */
    @Override
    public Integer getRequestArgument() {
        return null;
    }

    /**
     * 批量业务处理
     *
     * @param collection
     * @return
     */
    @Override
    protected HystrixCommand<List<String>> createCommand(Collection<CollapsedRequest<String, Integer>> collection) {
        return null;
    }

    /**
     * 批量处理结果与请求业务之间映射关系处理
     *
     * @param strings
     * @param collection
     */
    @Override
    protected void mapResponseToRequests(List<String> strings, Collection<CollapsedRequest<String, Integer>> collection) {

    }
}


class BacthCommand extends HystrixCommand<List<String>> {

    private Collection<HystrixCollapser.CollapsedRequest<String, Integer>> collection;

    public BacthCommand(Collection<HystrixCollapser.CollapsedRequest<String, Integer>> collection) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("BACTH_COMMAND")));
        this.collection = collection;
    }

    @Override
    protected List<String> run() throws Exception {
        System.out.println("currentThread=" + Thread.currentThread().getName());
        //处理结果
        List<String> result = Arrays.asList();

        Iterator<HystrixCollapser.CollapsedRequest<String, Integer>> iterator =
                collection.iterator();

        while (iterator.hasNext()) {


            HystrixCollapser.CollapsedRequest<String, Integer> next = iterator.next();

            Integer argument = next.getArgument();


        }



        return result;
    }
}

package com.meetingfilm.consumer.ribbon;

import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import com.netflix.niws.client.http.RestClient;

import java.net.URI;

/**
 * 描述 : ribbon测试类
 * 作者 : 徐起超
 * 时间 : 2020/12/10 9:56 上午
 */
public class App {


    public static void main(String[] args) throws Exception {
        ConfigurationManager.loadPropertiesFromResources("test.properties");  // 1
        System.err.println(ConfigurationManager.getConfigInstance().getProperty("test-client.ribbon.listOfServers"));
        RestClient client = (RestClient) ClientFactory.getNamedClient("test-client");  // 2
        //基础演示
        HttpRequest request = HttpRequest.newBuilder().uri(new URI("/")).build(); // 3
        for (int i = 0; i < 5; i++) {
            HttpResponse response = client.executeWithLoadBalancer(request); // 4
            System.err.println("Status code for " + response.getRequestedURI() + "  :" + response.getStatus());
        }

        //动态切换服务列表演示
        ZoneAwareLoadBalancer lb = (ZoneAwareLoadBalancer) client.getLoadBalancer();
        System.err.println(lb.getLoadBalancerStats());
        ConfigurationManager.getConfigInstance().setProperty(
                "test-client.ribbon.listOfServers", "www.qq.com:80,www.taobao.com:80"); // 5
        System.err.println("changing servers ...");
        Thread.sleep(3000); // 6
        for (int i = 0; i < 5; i++) {
            HttpResponse response = client.executeWithLoadBalancer(request);
            System.err.println("Status code for " + response.getRequestedURI() + "  : " + response.getStatus());
        }
        System.out.println(lb.getLoadBalancerStats()); // 7

    }


}

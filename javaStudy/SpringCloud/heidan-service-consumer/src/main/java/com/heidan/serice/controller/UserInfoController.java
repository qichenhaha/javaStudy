package com.heidan.serice.controller;

import com.heidan.serice.client.UserInfoClient;
import com.heidan.serice.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Create by heidan on 2020/1/19 16:27
 */
@RestController
/*@DefaultProperties(defaultFallback = "queryUserBuIdFallback")*/ // 使用全局的熔断方法
public class UserInfoController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient; // 拉取包含所有服务信息

    @Autowired
    private UserInfoClient userInfoClient;

    @GetMapping("/user/{id}")
   /* @HystrixCommand(fallbackMethod = "queryUserBuIdFallback")*/
    public UserInfo hello(@PathVariable("id")Integer id){

        // 获取服务信息集合
       /* List<ServiceInstance> instances = discoveryClient.getInstances("service-provider");
        ServiceInstance serviceInstance = instances.get(0);

        return this.restTemplate.getForObject("http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/user/"+id,UserInfo.class);*/

        // 采用负载均衡方式
        /*return this.restTemplate.getForObject("http://service-provider/user/"+id,String.class);*/


        // 采用feign
        UserInfo userInfo = userInfoClient.queryById(id);
        return userInfo;
    }


    // 定义一个熔断方法
    // 如果是全局的 参数设置为空
    public String queryUserBuIdFallback(Integer id){
        return "服务器正忙，请稍后再试！";
    }

}

package com.heidan.serice.client;

import com.heidan.serice.entity.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Create by heidan on 2020/1/20 19:03
 */
@FeignClient(value = "service-provider",fallback = UserInfoClientFallback.class) // 标注该类是一个feign接口
public interface UserInfoClient {

    @GetMapping("user/{id}")
    UserInfo queryById(@PathVariable("id") Integer id);
}

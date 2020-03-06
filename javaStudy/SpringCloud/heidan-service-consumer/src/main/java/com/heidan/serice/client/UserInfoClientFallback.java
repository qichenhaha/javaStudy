package com.heidan.serice.client;

import com.heidan.serice.entity.UserInfo;
import org.springframework.stereotype.Component;

/**
 * Create by heidan on 2020/1/20 19:17
 *
 * 配置feign熔断器配置
 */
@Component
public class UserInfoClientFallback implements UserInfoClient {
    @Override
    public UserInfo queryById(Integer id) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("服务器正忙！请稍好再试！！");
        return userInfo;
    }
}

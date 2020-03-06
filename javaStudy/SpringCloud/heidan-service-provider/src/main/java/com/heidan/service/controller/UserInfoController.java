package com.heidan.service.controller;

import com.heidan.service.entity.UserInfo;
import com.heidan.service.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by heidan on 2020/1/19 14:00
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("{id}")
    public UserInfo queryUserBuId(@PathVariable("id") Integer id){
        System.out.println("8082端口号执行");
        System.out.println("==============================================");
        return userInfoService.selectByPrimaryKey(id);
    }
}

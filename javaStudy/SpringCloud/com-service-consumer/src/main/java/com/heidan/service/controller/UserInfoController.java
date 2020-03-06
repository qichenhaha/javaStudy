package com.heidan.service.controller;

import com.heidan.service.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Create by heidan on 2020/1/19 15:25
 */
@RestController
public class UserInfoController {

    @Autowired
    private RestTemplate restTemplatel;

    @GetMapping("/user")
    public UserInfo hello(@RequestParam("id")Integer id){

        return this.restTemplatel.getForObject("http://localhost:8081/user/"+id,UserInfo.class);
    }

}

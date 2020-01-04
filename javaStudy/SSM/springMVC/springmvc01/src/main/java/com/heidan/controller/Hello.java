package com.heidan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Create by heidan on 2019/12/24 15:17
 */
@Controller
public class Hello {

    @RequestMapping("hello01")
    public String sayHello(){
        System.out.println("Hello StringMVC");
        return "sucess";
    }

    @PostMapping("testjson")
    @ResponseBody
    public String TestJson(@RequestBody String body){
        System.out.println("请求消息体：" + body);
        return body;
    }
}

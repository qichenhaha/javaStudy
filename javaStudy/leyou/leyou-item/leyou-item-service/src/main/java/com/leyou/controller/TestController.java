package com.leyou.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Create by heidan on 2020/2/13 20:00
 */
@Controller
@RequestMapping("/categoru")
public class TestController {

    @GetMapping("list")
    @ResponseBody
    public ResponseEntity<String> list(Integer id){
        System.out.println("请求id为：" + id);
        System.out.println("成功进入该架构！！！");
        return ResponseEntity.ok("请求成功过！");
    }

}

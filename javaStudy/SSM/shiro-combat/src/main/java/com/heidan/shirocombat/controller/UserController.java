package com.heidan.shirocombat.controller;

import com.heidan.shirocombat.service.Userservice;
import com.heidan.shirocombat.vo.req.LoginReqVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by heidan on 2020/1/14 16:27
 */
@RestController
@RequestMapping("/api")
@Api(tags = "用户模块相关接口")
public class UserController {

    @Autowired
    private Userservice userservice;

    @PostMapping("/user/login")
    @ApiOperation("登陆接口")
    public Map<String,Object> login(@RequestBody LoginReqVo loginReqVo){
        Map<String,Object> request = new HashMap<>();
        request.put("code",0);
        request.put("data",userservice.login(loginReqVo));
        return request;
}

    @GetMapping("/user/denc/{id}")
    @ApiOperation("获取用户详情")
    @RequiresRoles("admin")
    public Map<String,Object> detail(@PathVariable("id") @ApiParam("用户id") Integer id){
        System.out.println("成功进入用户详情查询！！！");
        Map<String,Object> request = new HashMap<>();
        request.put("code",0);
        request.put("data",userservice.detail(id));
        return request;
    }

}

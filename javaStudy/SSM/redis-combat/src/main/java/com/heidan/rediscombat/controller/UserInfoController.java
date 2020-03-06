package com.heidan.rediscombat.controller;

import com.heidan.rediscombat.entity.UserInfo;
import com.heidan.rediscombat.service.UserInfoService;
import com.heidan.rediscombat.vo.req.LoginReqVo;
import com.heidan.rediscombat.vo.req.RegisterReqVO;
import com.heidan.rediscombat.vo.resp.LoginRespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Create by heidan on 2020/1/13 15:25
 */
@RestController
@RequestMapping("/api")
@Api(tags = "用户模块")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation("用户登陆接口")
    @PostMapping("/user/login")
    public LoginRespVo login(@RequestBody LoginReqVo loginReqVo) {
        return userInfoService.loginRespVo(loginReqVo);
    }

    @ApiOperation("根据id查询用户信息接口")
    @GetMapping("/user/{id}")
    public UserInfo userInfoById(@PathVariable("id") @ApiParam("用户id") String id) {
        Integer ids = Integer.valueOf(id);
        UserInfo userInfo = userInfoService.selectByPrimaryKey(ids);
        System.out.println(userInfo.toString());
        return userInfoService.selectByPrimaryKey(ids);
    }

    @GetMapping("/code/{phone}")
    @ApiOperation("获取短信验证码")
    public void getCode(@PathVariable("phone") @ApiParam("手机号码") String phone) {
        String code = userInfoService.getCode(phone);
    }

    @PostMapping("/user/register")
    @ApiOperation(value = "注册接口")
    public String register(@RequestBody RegisterReqVO vo) {
        return userInfoService.register(vo);
    }

    @DeleteMapping("/user/delete")
    @ApiOperation("删除信息")
    public String delete(@PathVariable("phone") @ApiParam("手机号码") String phone) {
        return "";
    }

    @PutMapping("/user/update")
    @ApiOperation("修改信息")
    public String update() {
        return "";
    }

}

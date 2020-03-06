package com.heidan.compamyframe.controller;

import com.heidan.compamyframe.constants.Constant;
import com.heidan.compamyframe.exception.code.BaseResponseCode;
import com.heidan.compamyframe.service.UserService;
import com.heidan.compamyframe.utils.DataResult;
import com.heidan.compamyframe.vo.req.LoginReqVo;
import com.heidan.compamyframe.vo.resp.LoginRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Create by heidan on 2020/1/16 10:25
 */
@RestController
@RequestMapping("/api")
@Api(tags = "用户模块相关接口")
public class UserController {


    @Autowired
    private UserService userService;


    @PostMapping("/user/login")
    @ApiOperation("用户登陆接口")
    public DataResult<LoginRespVO> login(@RequestBody LoginReqVo loginReqVo){
        System.out.println(loginReqVo.toString());
        DataResult result = DataResult.success();
        result.setData(userService.login(loginReqVo));
        return result;
    }


    @GetMapping("/user/logout")
    @ApiOperation(value = "用户登出接口")
    public DataResult logout(HttpServletRequest request){
        System.out.println("成功进入用户登出接口");
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        String refreshToken=request.getHeader(Constant.REFRESH_TOKEN);
        userService.logout(accessToken,refreshToken);
        return DataResult.success();
    }


    @GetMapping("/user/unLogin")
    @ApiOperation(value = "引导客户端去登录")
    public DataResult unLogin(HttpServletRequest request){
        System.out.println("引导用户去登陆！！");
        DataResult result= DataResult.getResult(BaseResponseCode.TOKEN_ERROR);
        return result;
    }


    @PostMapping("/users")
    @ApiOperation(value = "分页查询用户接口")
    @RequiresPermissions("sys:user:lists")
    public DataResult pageInfo(){
        DataResult result=DataResult.success();
        result.setData("查询出数据aaa");
        return result;
    }


}

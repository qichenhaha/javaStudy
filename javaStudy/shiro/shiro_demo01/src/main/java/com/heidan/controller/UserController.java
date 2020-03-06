package com.heidan.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by heidan on 2020/1/10 14:16
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 1.传统登陆：
     *  前端发送登陆请求 ==>接口获取部分用户名密码 ==>程序员在接口部分手动控制
     * 2.shiro登陆
     *  前端发送登陆请求 ==> 接口部分获取用户密码 ==> 通过subject.login ==> realm域认证方法
     * 登陆接口
     * @param name 用户名
     * @param password 密码
     * @return 返回登陆结果
     */
    @RequestMapping("login")
    public String login(String name,String password){

        /**
         * 密码进行加密
         *  shiro提供MD5加密方式
         *  Md5Hash:
         *      参数1： 加密内容
         *      参数2： 盐（加密的混淆字符串）（用户登陆的用户名）
         *      参数3： 加密次数
         */
        String passwords = new Md5Hash(password, name, 3).toString();

        System.out.println("加密后的密码：" + passwords);

        // 构造登陆令牌
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name,passwords);
        try {
            // 1.获取subject
            Subject subject = SecurityUtils.getSubject();

            String id = (String) subject.getSession().getId();
            // 2.调用登陆方法
            subject.login(usernamePasswordToken);
            return "登陆成功！" + id;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "用户名密码错误！";
        }
    }


    @RequestMapping("autherror")
    public String autherror(int code){
        return code==1?"未登录":"未授权";
    }


    @RequestMapping("home")
    public String home(){
        return "访问首页成功！";
    }

    @RequiresRoles(value = "role2")
    @RequiresPermissions("user:save")
    @RequestMapping("lisa")
    public String lisa(){
        return "访问lisa成功！";
    }
}

package com.heidan.controller;

import com.heidan.common.MServerResponse;
import com.heidan.dao.UserInfoMapper;
import com.heidan.entity.UserInfo;
import com.heidan.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create by heidan on 2020/1/9 12:06
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserInfoMapper userInfoMapper;

        @RequestMapping("login")
    @ResponseBody
    public MServerResponse login(String phone, String password){
        System.out.println("成功进入登陆！！！");

        UserInfo userInfo = userInfoMapper.selectByPhoneandPassword(phone, password);
        if (userInfo!=null){
            System.out.println("登陆成功！");
            String jwt = JwtUtils.createJwt(userInfo.getId(), userInfo.getUserName(), null);
            System.out.println("返回token:" + jwt);
            return MServerResponse.createBySuccess(jwt);
        }
        return MServerResponse.createByErrorMessage("登陆失败!");
    }

    @RequestMapping("list")
    public MServerResponse lis(HttpServletRequest request){
        Claims user_claims = (Claims) request.getAttribute("user_claims");
        String id = user_claims.getId();
        String subject = user_claims.getSubject();
        Date issuedAt = user_claims.getIssuedAt();
        System.out.println(id);
        System.out.println(subject);
        System.out.println(issuedAt);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeFormat = sdf.format(issuedAt);
        System.out.println(timeFormat);
        return MServerResponse.createBySuccessMessage("成功！");
    }


}

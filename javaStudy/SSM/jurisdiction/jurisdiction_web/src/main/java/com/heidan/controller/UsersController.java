package com.heidan.controller;

import com.heidan.entity.Users;
import com.heidan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Create by heidan on 2019/12/31 14:26
 */
@Controller
@RequestMapping("/user")
public class UsersController {

    @Autowired
    @Qualifier(value = "userService")
    private IUserService iUserService;

    @RequestMapping("/finall")
    @Secured("ROLE_ADMIN")
    @ResponseBody
    public Object finall() {
        List<Users> users = iUserService.finAll();
        System.out.println(users);
        return "";
    }

    @PostMapping("/insert")
    public Object insert(Users users) {
        int i = iUserService.inserUsers(users);
        if (i >= 1) {
            System.out.println("插入成功！");
        } else {
            System.out.println("插入失败！");
        }
        return "";
    }

    @RequestMapping("/finallByid")
    public Object finById(Integer id) {
        Users user = iUserService.finById(id);
        System.out.println("user:" + user);
        return "";
    }
}

package com.heidan.controller;

import com.heidan.dao.UsersMapper;
import com.heidan.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Create by heidan on 2020/1/3 21:31
 */
@Controller
public class UsersController {

    @Autowired
    private UsersMapper usersMapper;

    @ResponseBody
    @GetMapping("/queryUser")
    public List<Users> test(){
        System.out.println("成功进入了！");
        List<Users> users = usersMapper.queryUserList();
        for (Users user : users) {
            System.out.println(user.toString());
        }
        return users;
    }
}

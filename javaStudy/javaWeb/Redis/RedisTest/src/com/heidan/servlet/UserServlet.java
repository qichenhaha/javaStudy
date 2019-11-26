package com.heidan.servlet;

import com.heidan.service.IUserService;
import com.heidan.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by heidan on 2019/11/26 21:33
 */

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private IUserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("成功进入userServlet");
//        List<User> users = userService.listAll();
//        for (User user : users) {
//            System.out.println(user);
//        }

        String jedis = userService.jedis();
        System.out.println("jedis：" + jedis);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

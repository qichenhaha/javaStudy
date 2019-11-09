package com.heidan.servlet;

import com.heidan.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by heidan on 2019/11/10 0:23
 */
@WebServlet("/suessServlet")
public class suessServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取request中共享的对象
        User user = (User) req.getAttribute("user");

        if (user!=null){
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("登陆成功！"+user.getUsername()+"欢迎您");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       this.doGet(req, resp);
    }
}

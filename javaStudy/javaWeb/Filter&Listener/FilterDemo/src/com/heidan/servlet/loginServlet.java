package com.heidan.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Create by heidan on 2019/11/21 21:49
 */
@WebServlet("/loginServlet")
public class loginServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进行登陆");
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");

        String password = req.getParameter("password");

        System.out.println("name:" + name);
        System.out.println("password" + password);
        if (name.equals("李四")  && password.equals("123")){
            System.out.println("登陆成功！");
            HttpSession session = req.getSession();
            session.setAttribute("user",name);
            req.getRequestDispatcher("/scuess.jsp").forward(req,resp);
        }else {
            System.out.println("登陆失败！");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

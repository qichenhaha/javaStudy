package com.heidan.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Create by heidan on 2019/11/18 22:04
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String checkCode = req.getParameter("checkCode");
        HttpSession session = req.getSession();
        String  checkCode_session = (String) session.getAttribute("checkCode_session");
        session.removeAttribute("checkCode_session");
        System.out.println("工具类生成的验证码:" + checkCode_session);
        resp.setContentType("text/html;charset=utf-8");
        if (checkCode_session!=null && checkCode_session.equalsIgnoreCase(checkCode)){
            resp.getWriter().write("验证码正确");
            System.out.println(username);
            System.out.println(password);
            if ("历史".equals(username) && "123".equals(password)){
                resp.getWriter().write("登陆成功！");
            }else {
                resp.getWriter().write("账号或密码错误！");
            }
        }else {

            resp.getWriter().write("验证码错误");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post");
        this.doGet(req, resp);
    }
}

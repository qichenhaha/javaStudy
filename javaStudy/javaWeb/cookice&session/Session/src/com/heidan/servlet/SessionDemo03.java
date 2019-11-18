package com.heidan.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Create by heidan on 2019/11/17 15:17
 */
@WebServlet("/SessionDemo03")
public class SessionDemo03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("SessionDemo03");
        // 1.获取Session对象
        HttpSession session = req.getSession();
        session.setAttribute("msg","Hello Session");
        System.out.println(session.getId());

        //2.忘客户端关闭之后Session 也能相同
        Cookie cookie = new Cookie("JSESSIONID",session.getId());
        cookie.setMaxAge(60*60);
        resp.addCookie(cookie);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

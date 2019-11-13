package com.heidan.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by heidan on 2019/11/13 12:32
 */
@WebServlet("/ResponseDemo01")
public class ResponseDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ResponseDemo01");
        // 访问 /ResponseDemo01，会自动跳转/ResponseDemo02资源
        // 1.设置状态码未302
//        resp.setStatus(302);
        resp.sendRedirect("/Response_war_exploded/ResponseDemo02");
//        resp.setHeader("http://localhost:8080","/Response_war_exploded/ResponseDemo02");
        System.out.println("ResponseDemo01111");
        // 动态获取虚拟目录
            String contextPath = req.getContextPath();
        System.out.println("虚拟目录"+ contextPath);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

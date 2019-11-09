package com.heidan.rquest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by heidan on 2019/11/5 16:35
 */
@WebServlet("/demo04")
public class RequsetDemo04 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.设置流的编码 中文乱码处理
        System.out.println("demo04=======================");
        System.out.println(req.getParameter("name"));
        String name = req.getParameter("name");
        // 存取数据
        req.setAttribute("name",name);
        req.getRequestDispatcher("/demo05").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

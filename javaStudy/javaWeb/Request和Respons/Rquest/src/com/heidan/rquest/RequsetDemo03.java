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
@WebServlet("/demo03")
public class RequsetDemo03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.设置流的编码 中文乱码处理
        req.setCharacterEncoding("UTF-8");
        System.out.println("成功进入Servlet11");
        String name = req.getParameter("userNmae");
        String passWord = req.getParameter("passWord");
        System.out.println(name);
        System.out.println(passWord);
        String[] hub = req.getParameterValues("hbus");
        for (String s : hub) {
            System.out.println(s);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

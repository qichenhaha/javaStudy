package com.heidan.ServletContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by heidan on 2019/11/14 10:38
 */
@WebServlet("/ServletContextDemo01")
public class ServletContextDemo01 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ServletContext 获取方式
        //1. 通过request对象获取
        ServletContext servletContext = req.getServletContext();
        System.out.println(servletContext);
        // 2.通过HttpServlet对象获取
        ServletContext servletContext1 = this.getServletContext();
        System.out.println(servletContext1);

        System.out.println(servletContext==servletContext1);

        String filename = "a.jpg";
        String mimeType = servletContext.getMimeType(filename);
        System.out.println(mimeType);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            this.doGet(req, resp);
    }
}

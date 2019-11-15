package com.heidan.ServletContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by heidan on 2019/11/14 14:03
 */
@WebServlet("/ServletContextDemo04")
public class ServletContextDemo04 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletContextDemo04");

        ServletContext servletContext = this.getServletContext();
        String realPath = servletContext.getRealPath("/a.txt");
        System.out.println(realPath);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

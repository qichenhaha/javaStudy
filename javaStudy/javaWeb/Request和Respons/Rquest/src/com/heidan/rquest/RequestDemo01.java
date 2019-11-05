package com.heidan.rquest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by heidan on 2019/11/5 13:00
 */
@WebServlet("/demo01")
public class RequestDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet......");

        // 获取请求方式
        String method = req.getMethod();
        System.out.println("获取请求方式:"+method);
        // 获取虚拟目录
        String contextPath = req.getContextPath();
        System.out.println("获取虚拟目录:"+contextPath);
        // 获取Servlet路径
        String getServletPath = req.getServletPath();
        System.out.println("获取Servlet路径:"+getServletPath);
        // 获取GET方式的请求参数：
        String getQueryString = req.getQueryString();
        System.out.println("获取GET方式的请求参数:"+getQueryString);
        // 获取请求的urL路径
        String getRequestURL  = req.getRequestURI();
        System.out.println("获取请求的urL路径:"+getRequestURL);
        // 获取完整请求的url
        StringBuffer requestURL = req.getRequestURL();
        System.out.println("获取完整请求的url:"+requestURL);
        // 获取协议即版本
        String getProtocol  = req.getProtocol();
        System.out.println("获取协议即版本:"+getProtocol);
        // 获取客户机的IP地址
        String getRemoteAddr = req.getRemoteAddr();
        System.out.println("获取客户机的IP地址:"+getRemoteAddr);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("dopost.....");



    }
}

package com.heidan.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create by heidan on 2019/11/15 15:22
 *
 * Cookice快速入门
 */
@WebServlet("/cookiceDemo05")
public class CookiceDemo5 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置响应的消息体的数据格式以及编码
        resp.setContentType("text/html;charset=utf-8");
        // 1.获取Cookice
        Cookie[] cookies = req.getCookies();
        // 定义一个类型来判断
        Boolean flag = false;

        if (cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies) {
                // 2.打印cookice
                String name = cookie.getName();
                if ("lastTime".equals(name)){
                    flag = true;

                    String valueca = cookie.getValue();
                    System.out.println("A解码之前:" + valueca);
                    String decodea = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    System.out.println("A解码之后:" + decodea);


                    System.out.println("有该cookie，不是第一次访问");

                    Date date = new Date();
                    // 时间格式转换
                    SimpleDateFormat sdf = new SimpleDateFormat("yyy年MM月dd日 HH:mm:ss");
                    String format = sdf.format(date);
                    // 转换URL编码格式
                    format = URLEncoder.encode(format, "UTF-8");
                    System.out.println("转换后字符串:" + format);
                    cookie.setValue(format);
                    // 设置cookie存活时间
                    cookie.setMaxAge(60 * 60 * 24 * 30);
                    resp.addCookie(cookie);





                    resp.getWriter().write("<h1>欢迎回来，您上次访问的时间为:"+ decodea+"</h1>" );
                    break;
                }
            }
        }

        if (cookies == null || cookies.length ==0 || flag == false){
            System.out.println("没有cookie");
            Date date = new Date();
            // 时间格式转换
            SimpleDateFormat sdf = new SimpleDateFormat("yyy年MM月dd日 HH:mm:ss");
            String format = sdf.format(date);
            System.out.println("转换前字符串:" + format);

            // 转换URL编码格式
            format = URLEncoder.encode(format, "UTF-8");
            System.out.println("转换后字符串:" + format);

            Cookie lastTime = new Cookie("lastTime", format);
            lastTime.setMaxAge(60 * 60 * 24 * 30);
            resp.addCookie(lastTime);
            resp.getWriter().write("<h1>您好欢迎您首次登陆</h1>" );

        }






    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

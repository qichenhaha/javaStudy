package com.heidan.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Create by heidan on 2019/11/13 15:01
 */
@WebServlet("/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CheckCodeServlet");
        int width = 100;
        int height = 50;

        // 1.创建一个对象，在内存中图片（验证码图片）
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        // 2.美化图片
        Graphics graphics = bufferedImage.getGraphics(); // 画笔对象
        graphics.setColor(Color.cyan); // 设置画笔颜色
        graphics.fillRect(0,0,width,height);

        // 2.2 花边框
        graphics.setColor(Color.RED);
        graphics.drawRect(0,0,width-1,height-1);

        // 2.3 写字符串
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqistuvwxyz0123456789";
        // 生成随机角标
        Random ran = new Random();
//        graphics.setColor(Color.RED);
        // 获取字符
        graphics.setColor(Color.black);
        for (int i =1;i<=4;i++){
            int index = ran.nextInt(str.length());
            // 获取字符
            char c = str.charAt(index);
            System.out.println(c);
            graphics.drawString(c+"",width/5*i,height/2);
        }

        graphics.setColor(Color.red);
        // 2.4 画线
        for (int i =0;i<10;i++){
            int x1 = ran.nextInt(width);
            int x2 = ran.nextInt(width);

            int y1 = ran.nextInt(height);
            int y2 = ran.nextInt(height);

            graphics.drawLine(x1,y1,x2,y2);
        }




        //3.输出图片
        ImageIO.write(bufferedImage,"jpg",resp.getOutputStream());


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

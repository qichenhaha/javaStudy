package com.heidan.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Create by heidan on 2019/11/14 19:27
 */
@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filename = req.getParameter("filename");

        //  2.使用字符输入流加载文件进内存
        //  2.1找到文件服务器中的路径
        ServletContext servletContext = this.getServletContext();
        String realPath = servletContext.getRealPath("/img/" + filename);

        // 2.2 用字节流关联
        FileInputStream fileInputStream = new FileInputStream(realPath);


        // 3.设置response响应头
        // 3.1设置响应类型：content-type
        String mimeType = servletContext.getMimeType(filename);//获取文件的mime类型
        resp.setHeader("content-type",mimeType);

        resp.setHeader("content-disposition","attachment;filename="+filename);

        ServletOutputStream sos = resp.getOutputStream();
        byte[] buff = new byte[1024 * 8];
        int len = 0;
        while((len = fileInputStream.read(buff)) != -1){
            sos.write(buff,0,len);
        }

        fileInputStream.close();


        System.out.println("downloadServlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

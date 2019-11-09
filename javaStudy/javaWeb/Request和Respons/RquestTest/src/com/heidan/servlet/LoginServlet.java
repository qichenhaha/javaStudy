package com.heidan.servlet;

import com.heidan.dao.Userdao;
import com.heidan.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Create by heidan on 2019/11/10 0:13
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.设置编码格式
        req.setCharacterEncoding("UTF-8");
        // 2.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 获取所有的请求参数 调用BeanUtils工具类使用
        Map<String, String[]> parameterMap = req.getParameterMap();
        User testUser = new User();
        try {
            BeanUtils.populate(testUser,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // 3.封住User对象
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        // 4.调用UserDao方法
        Userdao userdao = new Userdao();
        User login = userdao.login(testUser);
        // 5.判断
        if (login==null){
            // 登陆失败
            req.getRequestDispatcher("/failServlet").forward(req,resp);
        }else {
            // 登陆成功
            // 存储数据
            req.setAttribute("user",login);
            req.getRequestDispatcher("/suessServlet").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

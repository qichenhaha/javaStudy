package com.heidan.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Create by heidan on 2019/11/21 21:36
 */
@WebFilter("/*")
public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 1.强制装换
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // 2.获取请求的资源路径
        String requestURI = request.getRequestURI();

        // 3.判断是否包含登陆相关资源路径，要注意排除吊掉 css/js/图片/验证码等
        if (requestURI.contains("/longin.jsp") || requestURI.contains("/loginServlet")){
                filterChain.doFilter(servletRequest, servletResponse);
        }else {
            // 不包含，需要验证用户是否登陆
            Object user = request.getSession().getAttribute("user");
            System.out.println("user==>" + user);
            if (user!=null){
                System.out.println("登陆了");
                filterChain.doFilter(servletRequest, servletResponse);
            }else {
                System.out.println("没有登陆");
                request.getRequestDispatcher("/login.jsp").forward(request, servletResponse);
            }
        }

    }

    @Override
    public void destroy() {

    }
}

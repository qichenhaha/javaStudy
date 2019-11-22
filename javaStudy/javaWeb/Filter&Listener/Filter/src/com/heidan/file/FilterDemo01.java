package com.heidan.file;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Create by heidan on 2019/11/21 14:54
 */
@WebFilter(value = "/user/*",dispatcherTypes ={DispatcherType.FORWARD,DispatcherType.REQUEST})  // 访问所有资源之前都会被过滤器拦截
public class FilterDemo01 implements Filter {

    /**
     * 在服务器启动后，会创建Filter对象，然后调用init 方法 。 只执行一次一般用于加载支援
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init...........");
    }

    /**
     * 每一次请求被拦截资源时，会执行，执行多次
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("FilterDemo01 被执行了。。。。");

        // 放行
        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("FilterDemo01..... 返回");
    }

    /**
     *  在服务器关闭后，Filter对象被销毁，如果服务器是正常关闭，则会执行destroy方法，一般用于释放支援
     */
    @Override
    public void destroy() {
        System.out.println("destroy.........");
    }
}

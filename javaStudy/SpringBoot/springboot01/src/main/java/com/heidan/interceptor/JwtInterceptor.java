package com.heidan.interceptor;

import com.heidan.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Create by heidan on 2020/1/9 12:55
 *
 * 自定义拦截器
 *
 * 1.简化获取token数据的代码编写
 *  统一用户权限效验
 * 2.判断用户是否具有当前访问接口的权限
 */

public class JwtInterceptor extends HandlerInterceptorAdapter {

    /**
     * /进入控制器之前执行
     * @param request
     * @param response
     * @param handler
     * @return  boolean true 进行执行  false 拦截
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 通过拦截器获取到token数据
         *  1.通过request数据代码编写(判断是否登陆)
         *  2.从token中解析获取claims
         *  3.讲claims绑定到request域中
         */
        String authorization = request.getHeader("Authorization");
        //System.out.println("请求头信息："+authorization);
        // 判断是否为空，判断token前缀是否携带指定的签名
        if (!StringUtils.isEmpty(authorization)&& authorization.startsWith("heidan")){
            String token = authorization.replace("heidan","");
            Claims claims = JwtUtils.parseJwt(token);
            if (claims!=null){
                request.setAttribute("user_claims",claims);
                return true;
            }
        }
        System.out.println("成功进入拦截器！！！");
        printContent(response,"");
        return false;
    }



    private static void printContent(HttpServletResponse response, String content) {
        try {
            response.reset();
            response.setContentType("application/json");
            response.setHeader("Cache-Control", "no-store");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
            pw.write("token不可用，请重新登陆");
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 进入控制器方法之后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    // 结束之前执行内容
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, X-Requested-With, token");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, OPTIONS, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
    }
}

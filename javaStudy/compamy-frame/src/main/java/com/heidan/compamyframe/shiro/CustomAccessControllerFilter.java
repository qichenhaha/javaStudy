package com.heidan.compamyframe.shiro;

import com.alibaba.fastjson.JSON;
import com.heidan.compamyframe.constants.Constant;
import com.heidan.compamyframe.exception.BusinessException;
import com.heidan.compamyframe.exception.code.BaseResponseCode;
import com.heidan.compamyframe.utils.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Create by heidan on 2020/1/16 16:03
 * token过滤器
 */
@Slf4j
public class CustomAccessControllerFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        log.info(httpServletRequest.getMethod());
        log.info(httpServletRequest.getRequestURL().toString());

        // 1.判断客户端是否携带了accessToken
        try {
            String header = httpServletRequest.getHeader(Constant.ACCESS_TOKEN);

            if (StringUtils.isEmpty(header)){
                System.out.println("没有token拦截跳出异常");
                throw new BusinessException(BaseResponseCode.TOKEN_NOT_NULL);
            }
            System.out.println("有token放行，委托给Realm进行登录");
            CustomUsernamePasswordToken customUsernamePasswordToken = new CustomUsernamePasswordToken(header);
            // 委托给Realm进行登录
            getSubject(servletRequest, servletResponse).login(customUsernamePasswordToken);
        } catch (BusinessException e) {
            customRsponse(e.getCode(),e.getDefaultMessage(),servletResponse);
            return false;
        } catch (AuthenticationException e) {
            System.out.println("认证失败！！！");
                // 认证失败
            // 自定义的异常
            if (e.getCause() instanceof BusinessException){
                BusinessException exception = (BusinessException) e.getCause();
                customRsponse(exception.getCode(),exception.getDefaultMessage(),servletResponse);
            }else {
                // 系统的异常捕获
                customRsponse(BaseResponseCode.SHIRO_AUTHENTICATION_ERROR.getCode(),BaseResponseCode.SHIRO_AUTHENTICATION_ERROR.getMsg(),servletResponse);
            }
            return false;
        }

        return true;
    }


    private void customRsponse(int code, String msg, ServletResponse response){
        // 自定义异常的类，用户返回给客户端相应的JSON格式的信息
        try {
            DataResult result=DataResult.getResult(code,msg);
            response.setContentType("application/json; charset=utf-8");
            response.setCharacterEncoding("UTF-8");

            String userJson = JSON.toJSONString(result);
            OutputStream out = response.getOutputStream();
            out.write(userJson.getBytes("UTF-8"));
            out.flush();
        } catch (IOException e) {
            log.error("eror={}",e);
        }
    }
}

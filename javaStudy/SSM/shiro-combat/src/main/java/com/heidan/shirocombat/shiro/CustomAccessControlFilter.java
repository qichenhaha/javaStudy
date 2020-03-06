package com.heidan.shirocombat.shiro;

import com.alibaba.fastjson.JSON;
import com.heidan.shirocombat.constants.Constant;
import com.heidan.shirocombat.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Create by heidan on 2020/1/14 11:10
 */
@Slf4j
public class CustomAccessControlFilter extends AccessControlFilter {

    /*
    * 是否允许访问，
    * true:允许交给下一个Fileter处理
    * false：回往下执行onAccessDenied
    * */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    /*
    *表示访问拒绝时，是否自己处理
    * 如果返回true表示自己不处理且继续拦截器链执行，
    * 返回false表示自己已经处理了
    * */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info(request.getMethod());
        log.info(request.getRequestURL().toString());
        System.out.println("(1)成功进入进行过滤！！！！");
        // 1.判断客户端是否携带凭证
        try {
            //1判断客户端是否携带凭证
            String sessionId=request.getHeader(Constant.SESSION_ID);
            if(StringUtils.isEmpty(sessionId)){
                throw new BusinessException(4010001,"用户凭证不能为空");
            }else {
                CustomPasswordToken customPasswordToken=new CustomPasswordToken(sessionId);
                getSubject(servletRequest,servletResponse).login(customPasswordToken);
            }
        } catch (BusinessException e) {
            System.out.println("错误错误1！！！！！");
            customResponse(e.getMessageCode(),e.getDetailMessage(),servletResponse);
            return false;
        } catch (AuthenticationException e) {
            System.out.println("错误错误2！！！！！");
            if(e.getCause() instanceof BusinessException){
                BusinessException exception= (BusinessException) e.getCause();
                customResponse(exception.getMessageCode(),exception.getDetailMessage(),servletResponse);
                return false;
            }else {
                customResponse(4000001,"用户认证失败",servletResponse);
                return false;
            }
        }  catch (AuthorizationException e){
            System.out.println("错误错误3！！！！！");
            if(e.getCause() instanceof BusinessException){
                BusinessException exception= (BusinessException) e.getCause();
                customResponse(exception.getMessageCode(),exception.getDetailMessage(),servletResponse);
                return false;
            }else {
                System.out.println("用户没有权限访问！！！");
                customResponse(4030001,"没有访问的权限",servletResponse);
                return false;
            }
        }catch (Exception e){
            System.out.println("错误错误4！！！！！");
            if(e.getCause() instanceof BusinessException){
                BusinessException exception= (BusinessException) e.getCause();
                customResponse(exception.getMessageCode(),exception.getDetailMessage(),servletResponse);
            }else {
                customResponse(5000001,"系统异常",servletResponse);
                return false;
            }
        }
        return true;
    }

    private void customResponse(int code,String msg,ServletResponse response){
        try {
            Map<String,Object> result=new HashMap<>();
            result.put("code",code);
            result.put("msg",msg);
            response.setContentType(MediaType.APPLICATION_JSON_UTF8.toString());
            String resultJson= JSON.toJSONString(result);
            OutputStream out=response.getOutputStream();
            out.write(resultJson.getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

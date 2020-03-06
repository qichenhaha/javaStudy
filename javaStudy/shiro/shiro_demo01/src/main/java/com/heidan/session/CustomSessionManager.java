package com.heidan.session;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * Create by heidan on 2020/1/10 20:54
 *
 * 自定义sessionManager
 */

public class CustomSessionManager extends DefaultWebSessionManager {


    /**
     * 指定sessionId的获取方式
     * 头信息具有sessionid
     *  请求头:Authorization: sessionid
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        // 获取请求头的数据
        String id = WebUtils.toHttp(request).getHeader("Authorization");

        if (StringUtils.isEmpty(id)){
            // 如果没有携带，生成新的sessionId
            return super.getSessionId(request, response);
        }else {
            // 请求头信息
            /*id=id.replaceAll("Bearer ","");*/
            // 返回sessionId
            // 从那获取到的
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,"header");
            // 具体参数
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID,id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID,Boolean.TRUE);
            return id;
        }

    }

}

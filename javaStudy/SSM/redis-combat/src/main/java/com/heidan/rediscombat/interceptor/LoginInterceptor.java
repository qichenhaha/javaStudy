package com.heidan.rediscombat.interceptor;

import com.heidan.rediscombat.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Create by heidan on 2020/1/13 17:56
 */

public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 首先效验是否携带凭证
        String sessionId = request.getHeader("sessionId");
        if (StringUtils.isEmpty(sessionId)) {
            throw new BusinessException(222, "授权凭证为空");
        } else {
            if (!redisTemplate.hasKey(sessionId)) {
                throw new BusinessException(4001002, "授权凭证无效");
            }
        }
        return true;
    }
}

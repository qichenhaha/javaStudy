package com.heidan.shirocombat.shiro;

import com.heidan.shirocombat.exception.BusinessException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @ClassName: CustomHashedCredentialsMatcher
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
public class CustomHashedCredentialsMatcher extends HashedCredentialsMatcher {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        System.out.println("验证token");
        CustomPasswordToken customPasswordToken= (CustomPasswordToken) token;
        String sessionId= (String) customPasswordToken.getPrincipal();
        if(!redisTemplate.hasKey(sessionId)){
            throw new BusinessException(4010001,"用户授权信息无效请重新登录");
        }
        return true;
    }
}

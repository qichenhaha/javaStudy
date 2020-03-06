package com.heidan.compamyframe.shiro;

import com.heidan.compamyframe.constants.Constant;
import com.heidan.compamyframe.exception.BusinessException;
import com.heidan.compamyframe.exception.code.BaseResponseCode;
import com.heidan.compamyframe.utils.JwtTokenUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Create by heidan on 2020/1/16 16:36
 * shiro 验证器
 */

public class CustomHashedCredentialsMatcher extends HashedCredentialsMatcher {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        System.out.println("成功进入shiro验证器进行验证！！！");

        CustomUsernamePasswordToken customUsernamePasswordToken = (CustomUsernamePasswordToken) token;

        /*获取当前token*/
        String accessToken = (String) customUsernamePasswordToken.getPrincipal();

        /*获取token中的用户id*/
        String userId = JwtTokenUtil.getUserId(accessToken);

        /**
         *
         * 判断用户是否被锁定
         */

        if (redisTemplate.hasKey(Constant.ACCOUNT_LOCK_KEY+userId)){
            throw new BusinessException(BaseResponseCode.ACCOUNT_LOCK);
        }

        /**
         * 判断用户是否被删除
         */
        if (redisTemplate.hasKey(Constant.DELETED_USER_KEY+userId)){
            throw new BusinessException(BaseResponseCode.ACCOUNT_HAS_DELETED_ERROR);
        }

        /**
         * 判断是否是主动登出（判断是否在黑名单里面）
         */
        if (redisTemplate.hasKey(Constant.JWT_REFRESH_TOKEN_BLACKLIST+accessToken)){
            throw new BusinessException(BaseResponseCode.TOKEN_ERROR);
        }

        /**
         * 判断token是否通过效验(是否过期)
         */
        if (!JwtTokenUtil.validateToken(accessToken)){
            throw new BusinessException(BaseResponseCode.TOKEN_PAST_DUE);
        }

        /**
         * 判断这个登陆用户是否要主动去刷新
         *
         * 如果key= Constant.JWT_REFRESH_TOKEN_BLACKLIST+userId大于accessToken说明在accesstoken不是重新生成的
         * 这样就要判断它是否刷新过/或者是否新生成的token
         *
         */

        if (redisTemplate.hasKey(Constant.JWT_REFRESH_KEY+userId)&&redisTemplate.getExpire(Constant.JWT_REFRESH_KEY+userId,TimeUnit.MILLISECONDS)>JwtTokenUtil.getRemainingTime(accessToken)){
            /**
             * 是否存在刷新的标识
             */
            if(!redisTemplate.hasKey(Constant.JWT_REFRESH_IDENTIFICATION+accessToken)){
                throw new BusinessException(BaseResponseCode.TOKEN_PAST_DUE);
            }
        }


        return true;
}
}

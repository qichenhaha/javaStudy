package com.heidan.entity;

import lombok.Data;
import org.crazycake.shiro.AuthCachePrincipal;

import java.io.Serializable;

/**
 * Create by heidan on 2020/1/11 11:51
 */

/**
 * AuthCachePrincipal
 *  redis和shiro的插件包，可以讲数据存入redis中
 */
@Data
public class UserInfo implements Serializable, AuthCachePrincipal {
    private String name;

    private String password;

    @Override
    public String getAuthCacheKey() {
        return null;
    }
}

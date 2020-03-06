package com.heidan.compamyframe.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Create by heidan on 2020/1/16 16:01
 */

public class CustomUsernamePasswordToken extends UsernamePasswordToken {

    private String token;

    public CustomUsernamePasswordToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }
}

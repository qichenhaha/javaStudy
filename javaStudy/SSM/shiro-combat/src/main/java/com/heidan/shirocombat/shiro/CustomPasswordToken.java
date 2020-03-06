package com.heidan.shirocombat.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Create by heidan on 2020/1/14 11:01
 */

public class CustomPasswordToken extends UsernamePasswordToken {

    private String token;

    public CustomPasswordToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }
}

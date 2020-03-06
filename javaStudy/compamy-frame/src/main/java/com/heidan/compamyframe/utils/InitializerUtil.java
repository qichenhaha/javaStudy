package com.heidan.compamyframe.utils;

import org.springframework.stereotype.Component;

/**
 * Create by heidan on 2020/1/16 9:17
 */
@Component
public class InitializerUtil {

    private TokenSettings tokenSettings;

    public InitializerUtil(TokenSettings tokenSettings) {
        JwtTokenUtil.setTokenSettings(tokenSettings);
    }
}

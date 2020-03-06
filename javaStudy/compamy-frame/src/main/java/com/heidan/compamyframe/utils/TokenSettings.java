package com.heidan.compamyframe.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * Create by heidan on 2020/1/16 9:09
 */
@Component
@Data
@ConfigurationProperties(prefix = "jwt")
public class TokenSettings {

    // 密钥
    private String secretKey;

    // 过期时间
    private Duration accessTokenExpireTime;

    // 客户端刷新时间
    private Duration refreshTokenExpireTime;

    // app端刷新时间
    private Duration refreshTokenExpireAppTime;

    private String issuer;

}

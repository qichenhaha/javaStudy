package com.heidan.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
public class JwtUtils {
    //签名私钥
    private static String key = "heidan";
    //签名的失效时间
    private static Long ttl = Long.valueOf(360000);

    /**
     *  创建认证token
     *      id:登录用户id
     *      subject：登录用户名
     *      map 在token中添加额外的数据
     *
     */
    public static String createJwt(Integer id, String name, Map<String,Object> map) {
        //1.设置失效时间
        long now = System.currentTimeMillis();//当前毫秒
        long exp = now + ttl;
        //2.创建jwtBuilder
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(String.valueOf(id)) // 主体ID
                .setSubject(name)   // 主体名称
                .setIssuedAt(new Date()) // 签名的签发时间
                .signWith(SignatureAlgorithm.HS256, key); // 签名的加密算法，和一个私钥信息
        //3.根据map设置claims
        if (map!=null){
            for(Map.Entry<String,Object> entry : map.entrySet()) {
                jwtBuilder.claim(entry.getKey(),entry.getValue());
            }
        }
        jwtBuilder.setExpiration(new Date(exp));
        //4.创建token
        String token = jwtBuilder.compact();
        return token;
    }


    /**
     * 解析token字符串获取clamis
     */
    public static Claims parseJwt(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(key) // 指定私钥的信息
                    .parseClaimsJws(token).getBody(); // 进行解析字符串，获取存储的私有的数据
        }catch (Exception e){
        }
        return claims;

    }

}

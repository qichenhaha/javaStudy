package com.heidan.test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create by heidan on 2020/1/8 12:47
 */

public class jwtTest {

    public static void main(String[] args) {
        //String shencheng = shencheng();
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMDAwMDEiLCJzdWIiOiLlsI_nmb0iLCJpYXQiOjE1Nzg0NTk2MDIsImV4cCI6MTU3ODQ1OTY2Miwicm9sZXMiOiJhZG1pbiIsImxvZ28iOiJsb2dvLnBuZyJ9.lie4OSbTYY1qbHis8PMFnYkmIBXUlr6hYtx7-FRq-Ns";
        jiexi(token);
    }



    // 生成token
    public static String shencheng(){
        long now = System.currentTimeMillis();//当前时间
        long exp = now + 1000*60;//过期时间为1分钟
        JwtBuilder builder= Jwts.builder()
                .setId("100001") // id
                .setSubject("小白")   // name
                .setIssuedAt(new Date())    // 时间
                .setExpiration(new Date(exp))   // 过期时间
                .signWith(SignatureAlgorithm.HS256,"heidan") // 签名
                .claim("roles","admin") //自定义claims存储数据
                .claim("logo","logo.png");

        System.out.println( builder.compact() );
        return builder.compact();
    }

    // 解析token
    public static void jiexi(String token){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Claims claims = Jwts.parser().setSigningKey("heidan").parseClaimsJws(token).getBody();
        System.out.println("解析后数据:");
        System.out.println("id:"+claims.getId());
        System.out.println("subject:"+claims.getSubject());
        System.out.println("IssuedAt:"+sdf.format(claims.getIssuedAt()));
    }
}

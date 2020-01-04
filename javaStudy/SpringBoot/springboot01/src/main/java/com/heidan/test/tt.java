package com.heidan.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Create by heidan on 2020/1/3 22:48
 */

public class tt {

    @Test
    public void a(){
        //连接Redis服务器
        Jedis jedis = new Jedis("192.168.229.128",6379);
        jedis.auth("root");
        String av = jedis.get("a1");
//
        System.out.println("缓存中获取数据:"+av);
        //查看服务是否运行正常
        System.out.println("服务正在运行: " + jedis.ping());
    }
}

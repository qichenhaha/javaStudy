package com.heidan.jedis.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;

/**
 * Create by heidan on 2019/11/26 19:59
 */

public class JedisTest {

    /**
     *  Jedis 快速入门
     *
     */
   @Test
   public void a1(){
        // 1.获取连接
        Jedis jedis = new Jedis("127.0.0.1",6379); // 如果使用空参构造，默认值"localhost",6379端口
        // 2.操作
        jedis.set("username","zhangsan");
        // 3.关闭连接
        jedis.close();
    }

    @Test
    public void a2(){
        // 1.获取连接
        Jedis jedis = new Jedis("127.0.0.1",6379); // 如果使用空参构造，默认值"localhost",6379端口
        // 2.操作
        String username = jedis.get("username");
        System.out.println("获取的值为：" + username);
        // 3.关闭连接
        jedis.close();
    }


    @Test
    public void a3(){
        // 1.获取连接
        Jedis jedis = new Jedis("127.0.0.1",6379); // 如果使用空参构造，默认值"localhost",6379端口
        // 2.操作,可以使用setex()  方法存储可以指定过期时间的 key value
        jedis.setex("ac",20,"hehe");
        // 3.关闭连接
        jedis.close();
    }
    
    @Test
    public void a4(){
        // 1.获取连接
        Jedis jedis = new Jedis("127.0.0.1",6379);
        // 2.存储哈希
        jedis.hset("myhash","a","1");
        jedis.hset("myhash","b","2");
        jedis.hset("myhash","c","3");
        // 3.关闭连接
        jedis.close();
    }


    @Test
    public void a5(){
        // 1.获取连接
        Jedis jedis = new Jedis("127.0.0.1",6379);
        // 2.获取哈希
        System.out.println(jedis.hget("myhash","a"));
        System.out.println(jedis.hget("myhash","b"));
        System.out.println(jedis.hget("myhash","c"));
        // 3.获取所有的数据
        System.out.println("=======================================");
        Map<String, String> myhash = jedis.hgetAll("myhash");
        for (String s : myhash.keySet()) {
            System.out.println(s+ "==>"+myhash.get(s));
        }
        // 3.关闭连接
        jedis.close();
    }

    @Test
    public void a6(){
       // 1.创建Jedis连接对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);
        jedisPoolConfig.setMaxIdle(10);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"localhost",6379);
        // 2.获取连接
        Jedis resource = jedisPool.getResource();
        // 3.使用
        String username = resource.get("username");
        System.out.println(username);

        jedisPool.close();

    }

}

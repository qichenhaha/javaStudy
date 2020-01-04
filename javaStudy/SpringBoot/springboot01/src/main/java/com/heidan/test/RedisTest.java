package com.heidan.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.heidan.SpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Create by heidan on 2020/1/3 22:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void test() throws JsonProcessingException {

        // 获取redis中string类型的数据
        String av = (String) redisTemplate.opsForValue().get("bb");
        // 注入redis中String类型
        //redisTemplate.opsForValue().set("bb","321");

        System.out.println(av);

    }
}

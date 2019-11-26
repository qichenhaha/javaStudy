package com.heidan.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heidan.dao.IUserdao;
import com.heidan.dao.impl.UserDaoImpl;
import com.heidan.pojo.User;
import com.heidan.service.IUserService;
import com.heidan.util.JedisPoolUtils;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Create by heidan on 2019/11/26 21:25
 */

public class UserServiceImpl implements IUserService {

    private IUserdao userdao = new UserDaoImpl();

    @Override
    public List<User> listAll() {
        return userdao.listAll();
    }

    @Override
    public String jedis() {
        Jedis jedis = JedisPoolUtils.getJedis();
        String list = jedis.get("list");
        if (list==null){
            System.out.println("当前缓存中没有数据");
            List<User> users = userdao.listAll();
            ObjectMapper mapper = new ObjectMapper();
            try {
                list = mapper.writeValueAsString(users);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            jedis.set("list",list);
            jedis.close();
        }else {
            System.out.println("json中有数据从缓存中读取");
        }

        return list;
    }
}

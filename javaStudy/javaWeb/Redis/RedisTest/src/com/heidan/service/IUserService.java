package com.heidan.service;

import com.heidan.pojo.User;

import java.util.List;

/**
 * Create by heidan on 2019/11/26 21:25
 */

public interface IUserService {
    // 查询所有用户信息
    List<User> listAll();

    //
    String jedis();
}

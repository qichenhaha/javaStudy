package com.heidan.dao;

import com.heidan.pojo.User;

import java.util.List;

/**
 * Create by heidan on 2019/11/26 21:21
 */

public interface IUserdao {

    // 查询所有用户信息
    List<User> listAll();
}

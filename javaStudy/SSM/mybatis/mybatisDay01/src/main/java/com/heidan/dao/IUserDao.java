package com.heidan.dao;

import com.heidan.entity.Account;
import com.heidan.entity.User;

import java.util.List;

/**
 * Create by heidan on 2019/11/30 16:27
 */

public interface IUserDao {
    /**
     * 查询所有
     * @return
     */
    List<User> listAll();

    /**
     * 插入数据
     * @param user
     * @return
     */
    int add(User user);

    int Update(User user);

    int del(Integer id);

    User ById(Integer id);

    List<User> byName(User user);

    List<User> listById(List<Integer> ids);

    List<Account> listAccount();

    // 根据用户id查询账户信息
    List<User> listfindbyId();

    List<Account> acc(Integer id);


}

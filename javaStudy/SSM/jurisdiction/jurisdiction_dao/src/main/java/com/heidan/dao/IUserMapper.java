package com.heidan.dao;

import com.heidan.entity.Users;

import java.util.List;

/**
 * Create by heidan on 2019/12/31 10:42
 */

public interface IUserMapper {
    Users finByUserName(String name);

    List<Users> finAll();

    int inserUsers(Users users);

    Users finById(Integer id);
}

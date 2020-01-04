package com.heidan.service;

import com.heidan.entity.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * Create by heidan on 2019/12/31 10:40
 */

public interface IUserService extends UserDetailsService {
    List<Users> finAll();

    int inserUsers(Users users);

    Users finById(Integer id);
}

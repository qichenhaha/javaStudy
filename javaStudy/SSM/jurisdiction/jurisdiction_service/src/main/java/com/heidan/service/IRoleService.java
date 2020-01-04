package com.heidan.service;

import com.heidan.entity.Role;

import java.util.List;

/**
 * Create by heidan on 2020/1/2 11:31
 */

public interface IRoleService {
    /*更加id查询用户角色*/
    List<Role> finByRole(Integer id);

    /*更加id查询用户可以添加的角色*/
    List<Role> finBynotid(Integer id);
}

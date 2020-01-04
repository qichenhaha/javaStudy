package com.heidan.dao;

import com.heidan.entity.Role;

import java.util.List;

/**
 * Create by heidan on 2019/12/31 11:38
 */

public interface IRoleMapper {
    /*更加id查询用户角色*/
    List<Role> finByRole(Integer id);

    /*更加id查询用户可以添加的角色*/
    List<Role> finBynotid(Integer id);
}

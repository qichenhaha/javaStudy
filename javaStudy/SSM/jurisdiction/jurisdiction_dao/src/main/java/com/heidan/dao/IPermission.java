package com.heidan.dao;

import com.heidan.entity.Permission;

import java.util.List;

/**
 * Create by heidan on 2020/1/2 10:32
 */

public interface IPermission {
    List<Permission> findByRoleId(Integer roleId);
}

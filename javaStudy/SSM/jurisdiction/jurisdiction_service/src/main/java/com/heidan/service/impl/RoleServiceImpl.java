package com.heidan.service.impl;

import com.heidan.dao.IRoleMapper;
import com.heidan.entity.Role;
import com.heidan.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by heidan on 2020/1/2 11:31
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleMapper iRoleMapper;

    @Override
    public List<Role> finByRole(Integer id) {
        return iRoleMapper.finByRole(id);
    }

    @Override
    public List<Role> finBynotid(Integer id) {
        return iRoleMapper.finBynotid(id);
    }
}

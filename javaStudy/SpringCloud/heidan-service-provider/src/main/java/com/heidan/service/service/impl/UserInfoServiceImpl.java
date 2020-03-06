package com.heidan.service.service.impl;

import com.heidan.service.entity.UserInfo;
import com.heidan.service.mapper.UserInfoMapper;
import com.heidan.service.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by heidan on 2020/1/19 12:07
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo selectByPrimaryKey(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }
}

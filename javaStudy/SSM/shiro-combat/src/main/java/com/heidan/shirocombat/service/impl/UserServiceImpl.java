package com.heidan.shirocombat.service.impl;

import com.heidan.shirocombat.entity.UserInfo;
import com.heidan.shirocombat.exception.BusinessException;
import com.heidan.shirocombat.mapper.UserInfoMapper;
import com.heidan.shirocombat.service.Userservice;
import com.heidan.shirocombat.vo.req.LoginReqVo;
import com.heidan.shirocombat.vo.resp.LoginRespVo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Create by heidan on 2020/1/14 16:04
 */
@Service
public class UserServiceImpl implements Userservice {

    @Autowired
    private UserInfoMapper userInfoMapper;


    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public LoginRespVo login(LoginReqVo loginReqVo) {

        UserInfo userByName = userInfoMapper.getUserByName(loginReqVo.getUsername());
        if (userByName == null){
            throw new BusinessException(4000002,"该账户不存在");
        }
        String passwords = new Md5Hash(loginReqVo.getPassword(), loginReqVo.getUsername(), 3).toString();

        if (!userByName.getPassword().equals(passwords)){
            throw new BusinessException(4000002,"密码不匹配");
        }

        LoginRespVo loginRespVos = new LoginRespVo();
        loginRespVos.setId(String.valueOf(userByName.getId()));
        loginRespVos.setSessionId(UUID.randomUUID().toString());
        redisTemplate.opsForValue().set(loginRespVos.getSessionId(),loginRespVos.getId(),3, TimeUnit.MINUTES);
        return loginRespVos;
    }

    @Override
    public UserInfo detail(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }
}

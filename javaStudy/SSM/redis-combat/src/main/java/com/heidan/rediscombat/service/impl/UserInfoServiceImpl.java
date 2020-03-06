package com.heidan.rediscombat.service.impl;

import com.heidan.rediscombat.constants.Constant;
import com.heidan.rediscombat.entity.UserInfo;
import com.heidan.rediscombat.exception.BusinessException;
import com.heidan.rediscombat.mapper.UserInfoMapper;
import com.heidan.rediscombat.service.UserInfoService;
import com.heidan.rediscombat.vo.req.LoginReqVo;
import com.heidan.rediscombat.vo.req.RegisterReqVO;
import com.heidan.rediscombat.vo.resp.LoginRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Create by heidan on 2020/1/13 15:28
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    @Override
    public LoginRespVo loginRespVo(LoginReqVo loginReqVo) {
        UserInfo userByName = userInfoMapper.getUserByName(loginReqVo.getUsername());

        if (userByName == null) {
            throw new BusinessException(123, "登陆失败！没有该账户");
        }

        if (!userByName.getPassword().equals(loginReqVo.getPassword())) {
            throw new BusinessException(123, "登陆失败！账户或密码错误");
        }
        String token = UUID.randomUUID().toString();

        LoginRespVo loginRespVO = new LoginRespVo();
        loginRespVO.setId(String.valueOf(userByName.getId()));
        loginRespVO.setToken(token);
        // 讲返回的token存入redis中
        redisTemplate.opsForValue().set(token, userByName.getId(), 30, TimeUnit.MINUTES);
        return loginRespVO;
    }

    @Override
    public UserInfo selectByPrimaryKey(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public String getCode(String phone) {
        Long count = redisTemplate.opsForHash().increment(Constant.REGISTER_CODE_COUNT_KEY, phone, 1);
        if (count > 5) {
            throw new BusinessException(123, "当日发送次数上限");
        }

        String code = generateCode();
        redisTemplate.opsForValue().set(Constant.REGISTER_CODE_IDENTITY_KEY + phone, code, 5, TimeUnit.MINUTES);

        System.out.println("发送验证码：" + code);

        return code;

    }

    // 注册验证
    @Override
    public String register(RegisterReqVO vo) {

        if (!redisTemplate.hasKey(Constant.REGISTER_CODE_IDENTITY_KEY + vo.getPhone())) {
            throw new BusinessException(123, "验证码失效");
        }

        if (!vo.getCode().equals(redisTemplate.opsForValue().get(Constant.REGISTER_CODE_IDENTITY_KEY + vo.getPhone()))) {
            throw new BusinessException(123, "验证码不正确");
        }
        return "注册成功！";
    }

    // 随机生成验证码
    private String generateCode() {
        Random random = new Random();
        int x = random.nextInt(899999);
        String code = String.valueOf(x + 10000);
        return code;
    }
}

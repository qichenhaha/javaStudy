package com.heidan.compamyframe.service.impl;

import com.heidan.compamyframe.constants.Constant;
import com.heidan.compamyframe.entity.SysUser;
import com.heidan.compamyframe.exception.BusinessException;
import com.heidan.compamyframe.exception.code.BaseResponseCode;
import com.heidan.compamyframe.mapper.SysUserMapper;
import com.heidan.compamyframe.service.UserService;
import com.heidan.compamyframe.utils.JwtTokenUtil;
import com.heidan.compamyframe.vo.req.LoginReqVo;
import com.heidan.compamyframe.vo.resp.LoginRespVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Create by heidan on 2020/1/16 9:41
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public LoginRespVO login(LoginReqVo loginReqVo) {

        System.out.println("成功进入service业务员层！！！");
        SysUser sysUser = sysUserMapper.selectByUserName(loginReqVo.getUsername());

        if (sysUser==null){
            throw new BusinessException(BaseResponseCode.ACCOUNT_ERROR);
        }

        if (sysUser.getStatus()==2){
            throw new BusinessException(BaseResponseCode.ACCOUNT_LOCK);
        }

        String passwords = new Md5Hash(loginReqVo.getPassword(), loginReqVo.getUsername(), 3).toString();

        if (!sysUser.getPassword().equals(passwords)){
            throw new BusinessException(BaseResponseCode.ACCOUNT_PASSWORD_ERROR);
        }

        LoginRespVO loginRespVO = new LoginRespVO();

        loginRespVO.setId(sysUser.getId());
        System.out.println("用户昵称：" + sysUser.getUsername());
        loginRespVO.setUsername(sysUser.getUsername());
        loginRespVO.setPhone(sysUser.getPhone());
        Map<String,Object> claims = new HashMap<>();
        claims.put(Constant.JWT_ROLES_KEY,getRoleByUserId(sysUser.getId()));
        claims.put(Constant.JWT_PERMISSIONS_KEY,getPermissionsByUserId(sysUser.getId()));
        claims.put(Constant.JWT_USER_NAME,sysUser.getUsername());
        String accessToken = JwtTokenUtil.getAccessToken(sysUser.getId(), claims);
        String refreshToken;
        if (loginReqVo.getType().equals("1")){
             refreshToken = JwtTokenUtil.getRefreshToken(sysUser.getId(), claims);
        }else {
            refreshToken = JwtTokenUtil.getRefreshAppToken(sysUser.getId(), claims);
        }
        loginRespVO.setAccessToken(accessToken);
        loginRespVO.setRefreshToken(refreshToken);
        return loginRespVO;
    }

    @Override
    public void logout(String accessToken, String refreshToken) {
        if(StringUtils.isEmpty(accessToken)||StringUtils.isEmpty(refreshToken)){
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        Subject subject = SecurityUtils.getSubject();
        log.info("subject.getPrincipals()={}",subject.getPrincipals());
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        String userId=JwtTokenUtil.getUserId(accessToken);
        System.out.println("存入数据库01");
        redisTemplate.opsForValue().set(Constant.JWT_REFRESH_TOKEN_BLACKLIST+accessToken,userId,JwtTokenUtil.getRemainingTime(accessToken), TimeUnit.MILLISECONDS);

        System.out.println("存入数据库02");
        redisTemplate.opsForValue().set(Constant.JWT_REFRESH_TOKEN_BLACKLIST+refreshToken,userId,JwtTokenUtil.getRemainingTime(refreshToken),TimeUnit.MILLISECONDS);
    }

    // 自定义用户角色
    private List<String> getRoleByUserId(String userId){
        List<String> roles = new ArrayList<>();
        if ("9a26f5f1-cbd2-473d-82db-1d6dcf4598f8".equals(userId)){
            roles.add("admin");
        }else {
            roles.add("test");
        }

        return roles;
    }

    // 自定义用户资源
    private List<String> getPermissionsByUserId(String userId){
        List<String> Permissions = new ArrayList<>();
        if ("9a26f5f1-cbd2-473d-82db-1d6dcf4598f8".equals(userId)){
            Permissions.add("sys:user:list");
            Permissions.add("sys:user:add");
            Permissions.add("sys:user:update");
            Permissions.add("sys:user:detail");
        }else {
            Permissions.add("sys:user:list");
        }

        return Permissions;
    }
}

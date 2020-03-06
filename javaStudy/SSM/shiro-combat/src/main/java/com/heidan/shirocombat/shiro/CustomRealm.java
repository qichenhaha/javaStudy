package com.heidan.shirocombat.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by heidan on 2020/1/14 12:49
 */

public class CustomRealm extends AuthorizingRealm {
    
    @Autowired
    private RedisTemplate redisTemplate;
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("成功进入授权！！！");
        String sessionId= (String)  principalCollection.getPrimaryPrincipal();
        String userId = (String) redisTemplate.opsForValue().get(sessionId);
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        System.out.println("用户角色" +getRolesByUserId(userId));
        info.addRoles(getRolesByUserId(userId));
        info.addStringPermissions(getPermissionByUserId(userId));
        System.out.println("用户资源:" +getPermissionByUserId(userId));
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("成功进入认证！！！");
        CustomPasswordToken customPasswordToken = (CustomPasswordToken) authenticationToken;
        String sessionId = (String) customPasswordToken.getPrincipal();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sessionId,sessionId,CustomRealm.class.getName());
        return info;
    }


    /**
     * 模拟通过数据库获取权限数据
     * @Author:      小霍
     * @UpdateUser:
     * @Version:     0.0.1
     * @param userId
     * @return       java.util.List<java.lang.String>
     * @throws
     */
    private List<String> getPermissionByUserId(String userId) {
        List<String> permissions = new ArrayList<>();
        /**
         * 只有是 admin 用户才拥有所有权限
         */
        if(userId.equals("217")){
            permissions.add("*");
        }else {
            permissions.add("user:edit");
            permissions.add("user:list");
        }
        return permissions;
    }

    /**
     * 模拟通过数据库获取用户角色信息
     * @Author:      小霍
     * @UpdateUser:
     * @Version:     0.0.1
     * @param userId
     * @return       java.util.List<java.lang.String>
     * @throws
     */
    private List<String> getRolesByUserId(String userId) {
        List<String> roles = new ArrayList<>();
        if(userId.equals("217")){
            roles.add("admin");
        }else {
            roles.add("test");
        }

        return roles;
    }
}

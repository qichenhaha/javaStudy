package com.heidan.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by heidan on 2020/1/10 12:03
 * 自定义realm对象
 *      继承AuthorizingRealm
 *          重写方法
 *              doGetAuthorizationInfo：授权
 *                  获取到用户的授权数据（用户的权限数据）
 *               doGetAuthenticationInfo： 认证
 *                  根据用户名密码登陆，讲用户数据保存（安全数据）
 */

public class PermissionRealm extends AuthorizingRealm {
    /**
     * 自定义realm名称
     * @param name
     */
    public void setName(String name){
        super.setName("PermissionRealm");
    }

    // 授权: 授权的主要目的就是主要根据认证的数据获取到用户的权限信息

    /**
     *
     * @param principalCollection 包含了所有已认证的安全数据
     * @return 授权数据
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权");

        // 1.获取到安全数据： username,用户id
        String username = (String) principalCollection.getPrimaryPrincipal();
        // 2.根据id或者名称查询用户
        // 3.查询用户的角色和权限信息
        // 定义一个角色数组
        List<String> roles = new ArrayList<>();
        roles.add("role1");
        roles.add("role2");

        // 定义一个权限数组
        List<String> perms = new ArrayList<>();
        perms.add("user:save");
        perms.add("user:update");

        // 4.构造返回
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(perms);
        info.addRoles(roles);


        return info;
    }


    // 认证：认证的主要目的，比较用户名和密码是否与数据库中的一致
    // 讲安全数据存入shiro进行保管

    /**
     *
     * @param authenticationToken 认证token 登陆构造的usernamepasswordtoken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证");
        // 1. 构造uptoken
        UsernamePasswordToken uptekn = (UsernamePasswordToken) authenticationToken;
        // 2. 获取输入的用户名密码
        String name = uptekn.getUsername();
        String password = new String(uptekn.getPassword());
        // 3. 根据用户名查询数据
        // 4. 比较密码和数据库中的密码是否一致（密码可能需要加密）
        if ("123456".equals(password)){
            // 5. 如果成功向shiro存入安全数据
            // 1.安全数据  2.密码  3.当前realm域的名称
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(name,password,getName());
            return info;
        }else {
            // 6. 失败，抛出异常或返回null
            throw new RuntimeException("用户名或密码错误");
        }
    }
}

package com.heidan.realm;

import com.heidan.entity.UserInfo;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by heidan on 2020/1/10 14:27
 */

public class CustomRealm extends AuthorizingRealm {

    @Override
    public void setName(String name) {
        super.setName("CustomRealm");
    }

    /**
     * 授权方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        // 1.获取到安全数据： username,用户id
        UserInfo user = (UserInfo) principalCollection.getPrimaryPrincipal();
        System.out.println("授权获取信息:"+user.toString());
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

    /**
     * 认证方法
     * @param authenticationToken 传递用户的密码
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 1.获取用户登陆的用户名和密码
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
        // 2.根据用户名查询数据
        String username = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());
        // 3.判断用户是否存在密码是否一致
        // 4.如果一致返回安全数据

        UserInfo userInfo = new UserInfo();
        userInfo.setName("lishi");
        userInfo.setPassword("c9f95f4690c2e6ddd50e3db4fa9246f6");
        /*System.out.println("username:" + username);
        System.out.println("password:" + password);*/
        if (username.equals(userInfo.getName()) && password.equals(userInfo.getPassword())){
            System.out.println("验证成功！");
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userInfo,password,this.getName());
            return info;
        }
        System.out.println("验证失败!");
        // 5.不一致返回null
        return null;
    }

    public static void main(String[] args) {
        String passwords = new Md5Hash("123123", "王五", 3).toString();
        System.out.println(passwords);
    }
}

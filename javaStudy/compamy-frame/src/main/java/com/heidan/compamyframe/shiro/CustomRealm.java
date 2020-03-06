package com.heidan.compamyframe.shiro;

import com.heidan.compamyframe.constants.Constant;
import com.heidan.compamyframe.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Collection;

/**
 * Create by heidan on 2020/1/17 16:21
 */

public class CustomRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof CustomUsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("shiro进行授权");

        String accessToken = (String) principalCollection.getPrimaryPrincipal();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        /**
         * 返回用户的的角色信息给授权器
         */
        Claims claims = JwtTokenUtil.getClaimsFromToken(accessToken);

        if (claims.get(Constant.JWT_ROLES_KEY)!=null){
            info.addRoles((Collection<String>) claims.get(Constant.JWT_ROLES_KEY));
        }
        /**
         * 返回用户的权限资源信息
         */

        if (claims.get(Constant.JWT_PERMISSIONS_KEY)!=null){
            info.addStringPermissions((Collection<String>) claims.get(Constant.JWT_PERMISSIONS_KEY));
        }

        return info;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("shiro进行认证");

        CustomUsernamePasswordToken usernamePasswordToken = (CustomUsernamePasswordToken) authenticationToken;

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(usernamePasswordToken.getPrincipal(),usernamePasswordToken.getCredentials(),CustomRealm.class.getName());

        return simpleAuthenticationInfo;
    }
}

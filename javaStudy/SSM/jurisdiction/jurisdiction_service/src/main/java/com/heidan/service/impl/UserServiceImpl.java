package com.heidan.service.impl;

import com.heidan.dao.IUserMapper;
import com.heidan.entity.Role;
import com.heidan.entity.Users;
import com.heidan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by heidan on 2019/12/31 10:41
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserMapper iUserMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("成功进入！userService");
        Users users = iUserMapper.finByUserName(username);
        System.out.println(users);
        System.out.println(users.getStatus() == "0" ? false : true);
        // {noop} 密码如果是明文的时候，必须要加入这个
        User user = new User(users.getUserName(), users.getPassword(), users.getStatus() == "0" ? false : true, true, true, true, getAuthority(users.getRoles()));
        return user;
    }

    /*权限对象*/
    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> authoritys = new ArrayList();
        for (Role role : roles) {
            authoritys.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
            System.out.println("当前用户身份:===>ROLE_" + role.getRoleName());
        }
        return authoritys;
    }

    @Override
    public List<Users> finAll() {
        return iUserMapper.finAll();
    }

    @Override
    public int inserUsers(Users users) {
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        return iUserMapper.inserUsers(users);
    }

    @Override
    public Users finById(Integer id) {
        return iUserMapper.finById(id);
    }
}
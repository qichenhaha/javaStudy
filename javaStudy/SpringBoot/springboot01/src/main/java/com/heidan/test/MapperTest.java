package com.heidan.test;

import com.heidan.SpringbootApplication;
import com.heidan.dao.UsersMapper;
import com.heidan.entity.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Create by heidan on 2020/1/3 21:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class MapperTest {
    @Autowired
    private UsersMapper userMapper;

    @Test
    public void test() {
        List<Users> users = userMapper.queryUserList();
        System.out.println(users);
    }

}

package com.heidan.test;

import com.heidan.dao.Userdao;
import com.heidan.domain.User;
import org.junit.Test;

/**
 * Create by heidan on 2019/11/9 23:47
 */

public class UserDaoTest {

    @Test
    public void a(){

        User user = new User();
        user.setUsername("黑蛋");
        user.setPassword("1233");

        Userdao dao  = new Userdao();
        User login = dao.login(user);
        System.out.println(login);
    }
}

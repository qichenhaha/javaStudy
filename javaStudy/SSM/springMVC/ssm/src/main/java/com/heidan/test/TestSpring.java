package com.heidan.test;

import com.heidan.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Create by heidan on 2019/12/26 12:14
 */

public class TestSpring {

    @Test
    public void a1() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        AccountService as = (AccountService) ac.getBean("accountServiceImpl");
        as.findAll();
    }

}

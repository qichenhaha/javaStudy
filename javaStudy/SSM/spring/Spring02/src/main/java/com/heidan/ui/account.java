package com.heidan.ui;

import com.heidan.factory.BeanFactory;
import com.heidan.service.IAccountService;

/**
 * Create by heidan on 2019/12/21 14:29
 */

public class account {
    public static void main(String[] args) {
        IAccountService ac = (IAccountService) BeanFactory.getBean("accountService");

        for (int i = 1; i <= 5; i++) {
            ac.saveAccount();
        }

    }
}

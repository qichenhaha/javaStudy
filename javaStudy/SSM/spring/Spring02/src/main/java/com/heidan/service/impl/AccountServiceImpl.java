package com.heidan.service.impl;

import com.heidan.dao.IAccountDao;
import com.heidan.factory.BeanFactory;
import com.heidan.service.IAccountService;

/**
 * Create by heidan on 2019/12/21 14:24
 * 账户业务层实现类
 */

public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao = (IAccountDao) BeanFactory.getBean("accountDao");

    private int i = 1;

    @Override
    public void saveAccount() {

        accountDao.saveAccount();

        System.out.println(i);
        i++;

    }
}

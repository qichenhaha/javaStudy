package com.heidan.service.impl;

import com.heidan.dao.IAccountDao;
import com.heidan.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Create by heidan on 2019/12/21 14:24
 * 账户业务层实现类
 */
@Component(value = "insar")
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountDao accountDao;

    public AccountServiceImpl(){
        System.out.println("AccountServiceImpl对象被创建了");
    }



    @Override
    public void saveAccount() {
        accountDao.saveAccount();

    }
}

package com.heidan.service.impl;

import com.heidan.dao.AccountDao;
import com.heidan.entity.Account;
import com.heidan.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Create by heidan on 2019/12/26 12:04
 */
@Service("accountServiceImpl")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)//只读型事务的配置
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
    public List<Account> findAll() {

        System.out.println("业务层查询所有账户信息");
        List<Account> all = accountDao.findAll();
        System.out.println(all);
        return all;
    }

    @Override
    public int saveAccount(Account account) {
        int i = accountDao.saveAccount(account);
        System.out.println(i);
        System.out.println("业务层保存账户信息");
        int a = 1 / 0;
        System.out.println(a);
        Account ac = new Account();
        ac.setName("v1");
        ac.setMoney(23);
        int c = accountDao.saveAccount(ac);
        return i;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveUpdate() {
        System.out.println("执行");
        accountDao.saveUpdate(500, 23);
        String string = null;
        if (string.equals("")) {
            int i = 0;
        }
        System.out.println("继续执行");
        accountDao.saveUpdate(1500, 24);

    }
}

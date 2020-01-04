package com.heidan.dao.impl;

import com.heidan.dao.IAccountDao;
import org.springframework.stereotype.Repository;

/**
 * Create by heidan on 2019/12/21 14:26
 */
@Repository
public class AccountDaoImpl implements IAccountDao {
    @Override
    public void saveAccount() {
        System.out.println("保存账户了！！！");
    }

    public AccountDaoImpl(){
        System.out.println("AccountDaoImpl对象被创建了");
    }
}

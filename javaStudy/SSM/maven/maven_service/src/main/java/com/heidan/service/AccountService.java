package com.heidan.service;

import com.heidan.entity.Account;

import java.util.List;

/**
 * Create by heidan on 2019/12/26 12:04
 */

public interface AccountService {
    /**
     * 查询所有账户信息
     *
     * @return
     */
    public List<Account> findAll();

    /**
     * 保存账户信息
     *
     * @param account
     */
    public int saveAccount(Account account);

    public void saveUpdate();
}

package com.heidan.dao;

import com.heidan.entity.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Create by heidan on 2019/12/26 12:02
 */
public interface AccountDao {
    /**
     * 查询所有账户信息
     * @return
     */
    public List<Account> findAll();

    /**
     * 保存账户信息
     * @param account
     */
    public int saveAccount(Account account);

    public int saveUpdate(@Param("money") int money, @Param("id") int id);


}

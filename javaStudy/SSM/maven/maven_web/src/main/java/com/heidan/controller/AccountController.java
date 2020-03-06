package com.heidan.controller;

import com.heidan.entity.Account;
import com.heidan.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Create by heidan on 2019/12/26 12:06
 */
@Controller
@RequestMapping("account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("findAll")
    public String findall() {
        System.out.println("表现层查询所有的信息");
        List<Account> all = accountService.findAll();
        for (Account account : all) {
            System.out.println("表现层打印==>" + account);
        }
        return "list";
    }

    @RequestMapping("/save")
    public String save(Account account) {
        System.out.println("成功进入！");
        int i = accountService.saveAccount(account);
        return "list";
    }

    @RequestMapping("update")
    public void update() {
        System.out.println("成功进入更新的");
        accountService.saveUpdate();
    }
}

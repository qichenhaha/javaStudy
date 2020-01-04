package com.heidan.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Create by heidan on 2019/11/30 16:17
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {

    private Integer id;

    private String username;

    private Date birthday;

    private String sex;

    private String address;

    private List<Account> accounts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}

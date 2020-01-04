package com.heidan.entity;

import java.io.Serializable;

/**
 * Create by heidan on 2019/12/29 15:36
 * 会员表
 */

public class Member implements Serializable {

    private int id;

    private String name;

    private String nickName;

    private char phoneNum;

    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public char getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(char phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", phoneNum=" + phoneNum +
                ", email='" + email + '\'' +
                '}';
    }
}

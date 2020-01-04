package com.heidan.entity;

import java.io.Serializable;

/**
 * Create by heidan on 2019/12/29 15:32
 * 旅客表
 */

public class Traveller implements Serializable {

    private int id;

    private String name;

    private char sex;

    private char phoneNum;

    private char credentialsType;

    private String credentialsNum;

    private char travellerType;

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

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public char getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(char phoneNum) {
        this.phoneNum = phoneNum;
    }

    public char getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(char credentialsType) {
        this.credentialsType = credentialsType;
    }

    public String getCredentialsNum() {
        return credentialsNum;
    }

    public void setCredentialsNum(String credentialsNum) {
        this.credentialsNum = credentialsNum;
    }

    public char getTravellerType() {
        return travellerType;
    }

    public void setTravellerType(char travellerType) {
        this.travellerType = travellerType;
    }

    @Override
    public String toString() {
        return "Traveller{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", phoneNum=" + phoneNum +
                ", credentialsType=" + credentialsType +
                ", credentialsNum='" + credentialsNum + '\'' +
                ", travellerType=" + travellerType +
                '}';
    }
}

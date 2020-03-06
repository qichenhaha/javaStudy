package com.heidan.redistemplate.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * Create by heidan on 2020/1/13 13:07
 */
@Data
public class UserInfo implements Serializable {

    private String name;

    private String password;

    private char sex;
}

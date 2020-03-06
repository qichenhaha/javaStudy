package com.heidan.shirocombat.service;

import com.heidan.shirocombat.entity.UserInfo;
import com.heidan.shirocombat.vo.req.LoginReqVo;
import com.heidan.shirocombat.vo.resp.LoginRespVo;

/**
 * Create by heidan on 2020/1/14 16:04
 */

public interface Userservice {

    LoginRespVo login(LoginReqVo loginReqVo);

    UserInfo detail(Integer id);
}

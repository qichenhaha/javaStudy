package com.heidan.compamyframe.service;

import com.heidan.compamyframe.vo.req.LoginReqVo;
import com.heidan.compamyframe.vo.resp.LoginRespVO;

/**
 * Create by heidan on 2020/1/16 9:40
 */

public interface UserService {

    LoginRespVO login(LoginReqVo loginReqVo);

    void logout(String accessToken,String refreshToken);


}

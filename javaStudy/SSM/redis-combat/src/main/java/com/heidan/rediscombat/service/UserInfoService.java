package com.heidan.rediscombat.service;

import com.heidan.rediscombat.entity.UserInfo;
import com.heidan.rediscombat.vo.req.LoginReqVo;
import com.heidan.rediscombat.vo.req.RegisterReqVO;
import com.heidan.rediscombat.vo.resp.LoginRespVo;

/**
 * Create by heidan on 2020/1/13 15:27
 */

public interface UserInfoService {

    LoginRespVo loginRespVo(LoginReqVo loginReqVo);

    UserInfo selectByPrimaryKey(Integer id);

    String getCode(String phone);

    String register(RegisterReqVO vo);

}

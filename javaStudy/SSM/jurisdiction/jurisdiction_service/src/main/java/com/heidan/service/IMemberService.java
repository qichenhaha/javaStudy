package com.heidan.service;

import com.heidan.entity.Member;

/**
 * Create by heidan on 2019/12/30 10:47
 */

public interface IMemberService {

    /**
     * 根据ID查询会员信息
     *
     * @param id
     * @return
     */
    Member findById(Integer id);
}

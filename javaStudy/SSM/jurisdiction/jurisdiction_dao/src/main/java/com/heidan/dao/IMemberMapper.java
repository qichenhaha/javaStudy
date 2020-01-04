package com.heidan.dao;

import com.heidan.entity.Member;

/**
 * Create by heidan on 2019/12/30 10:41
 */

public interface IMemberMapper {

    /**
     * 根据ID查询会员信息
     * @param id
     * @return
     */
    Member findById(Integer id);
}

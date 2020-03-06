package com.heidan.service;

import com.heidan.common.MServerResponse;

/**
 * Create by heidan on 2020/1/6 15:30
 */

public interface IBargainActivityService {

    // 根据id查询砍价表信息
    MServerResponse selectByPrimaryKey(Integer id);

    // 更加id和用户id查询
    MServerResponse selectByidAndUserId(Integer id,Integer userId);

    // 插入砍价信息到砍价表
    MServerResponse insertSelective(Integer id, Integer userId);

    // 帮发起人砍价
    MServerResponse bargian(Integer id,Integer userId);

    // 查询当前用户发起的砍价产品
    MServerResponse selectListByUserId(Integer userId);
}

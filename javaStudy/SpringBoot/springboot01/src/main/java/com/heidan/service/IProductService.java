package com.heidan.service;

import com.heidan.common.MServerResponse;

/**
 * Create by heidan on 2020/1/6 13:56
 */

public interface IProductService {

    MServerResponse selectByPrimaryKey(Integer id);
}

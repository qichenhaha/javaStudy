package com.heidan.service;

import com.heidan.common.MServerResponse;

/**
 * Create by heidan on 2020/1/6 14:46
 */

public interface IBargainProductService {
    MServerResponse selectByPrimaryKey(Integer id);

    MServerResponse listAll();
}

package com.heidan.dao;

import com.heidan.entity.Orders;

/**
 * Create by heidan on 2019/12/30 11:15
 */

public interface IOrderMapper {
    Orders orderById(Integer id);
}

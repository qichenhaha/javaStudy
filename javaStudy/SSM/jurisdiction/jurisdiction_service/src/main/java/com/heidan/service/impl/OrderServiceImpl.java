package com.heidan.service.impl;

import com.heidan.dao.IOrderMapper;
import com.heidan.entity.Orders;
import com.heidan.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by heidan on 2019/12/30 11:25
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderMapper iOrderMapper;

    @Override
    public Orders ordersById(Integer id) {
        return iOrderMapper.orderById(id);
    }
}

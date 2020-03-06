package com.heidan.service.impl;

import com.heidan.common.MServerResponse;
import com.heidan.dao.ProductMapper;
import com.heidan.entity.Product;
import com.heidan.enums.ResultEnum;
import com.heidan.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by heidan on 2020/1/6 13:57
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;


    @Override
    public MServerResponse selectByPrimaryKey(Integer id) {
        Product product = productMapper.selectByPrimaryKey(id);
        if (product==null){
            return MServerResponse.createByErrorCodeMessage(ResultEnum.RESULT_NULL.getCode(),ResultEnum.RESULT_NULL.getMessage());
        }
        return MServerResponse.createBySuccess(product);
    }
}

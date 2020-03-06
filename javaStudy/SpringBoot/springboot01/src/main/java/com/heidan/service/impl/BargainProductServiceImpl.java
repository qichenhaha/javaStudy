package com.heidan.service.impl;

import com.heidan.common.MServerResponse;
import com.heidan.dao.BargainProductMapper;
import com.heidan.entity.BargainProduct;
import com.heidan.enums.ResultEnum;
import com.heidan.service.IBargainProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by heidan on 2020/1/6 14:46
 */
@Service
public class BargainProductServiceImpl implements IBargainProductService {

    @Autowired
    private BargainProductMapper bargainProductMapper;

    @Override
    public MServerResponse selectByPrimaryKey(Integer id) {
        BargainProduct bargainProduct = bargainProductMapper.selectByPrimaryKey(id);
        if (bargainProduct==null){
            return MServerResponse.createByErrorCodeMessage(ResultEnum.RESULT_NULL.getCode(),ResultEnum.RESULT_NULL.getMessage());
        }
        return MServerResponse.createBySuccess(bargainProduct);
    }

    @Override
    public MServerResponse listAll() {
        List<BargainProduct> bargainProducts = bargainProductMapper.listAll();
        if (bargainProducts==null){
            return MServerResponse.createByErrorCodeMessage(ResultEnum.RESULT_NULL.getCode(),ResultEnum.RESULT_NULL.getMessage());
        }
        return MServerResponse.createBySuccess(bargainProducts);
    }
}

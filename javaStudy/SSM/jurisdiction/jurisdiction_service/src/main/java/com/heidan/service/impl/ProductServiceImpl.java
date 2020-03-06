package com.heidan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heidan.dao.IProductMapper;
import com.heidan.entity.Product;
import com.heidan.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Create by heidan on 2019/12/28 12:46
 */
@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductMapper iProductMapper;

    @Override
    public PageInfo<Product> findAll() throws Exception {
        System.out.println("成功进入findAll()==>service层a");
        PageHelper.startPage(1, 2);
        List<Product> products = iProductMapper.finAll();
        PageInfo page = new PageInfo(products);
        return page;
    }

    @Override
    public int insertProduct(Product product) {
        return iProductMapper.InsertProduct(product);
    }

    @Override
    public int ProductDeleteById(Integer id) {
        return iProductMapper.ProductDeleteById(id);
    }

    @Override
    public Product ProductById(Integer id) {
        return iProductMapper.ProductById(id);
    }
}

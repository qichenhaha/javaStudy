package com.heidan.service;

import com.github.pagehelper.PageInfo;
import com.heidan.entity.Product;

/**
 * Create by heidan on 2019/12/28 12:44
 */

public interface IProductService {
    PageInfo<Product> findAll() throws Exception;

    int insertProduct(Product product);

    int ProductDeleteById(Integer id);

    Product ProductById(Integer id);

}

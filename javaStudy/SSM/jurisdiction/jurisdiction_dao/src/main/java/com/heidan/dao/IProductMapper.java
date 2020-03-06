package com.heidan.dao;

import com.heidan.entity.Product;

import java.util.List;

/**
 * Create by heidan on 2019/12/28 12:42
 */

public interface IProductMapper {

    /**
     * 查询所有的产品信息
     *
     * @return 返回所有产品信息
     * @throws Exception
     */
    public List<Product> finAll() throws Exception;

    /**
     * 插入条产品
     *
     * @param product
     * @return
     */
    public int InsertProduct(Product product);

    /**
     * 删除一条产品新增
     *
     * @param id
     * @return
     */
    int ProductDeleteById(Integer id);

    /**
     * 修改产品信息
     *
     * @param product
     * @return
     */
    int ProductUpdate(Product product);

    Product ProductById(Integer id);
}

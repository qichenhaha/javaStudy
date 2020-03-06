package com.heidan.controller;

import com.github.pagehelper.PageInfo;
import com.heidan.entity.Product;
import com.heidan.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Create by heidan on 2019/12/28 13:21
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @ResponseBody
    @RequestMapping("findAll")
    public Object findAll() throws Exception {
        System.out.println("成功进入controller==>findAll方法aaab");
        PageInfo<Product> all = iProductService.findAll();
        int navigatePages = all.getNavigatePages();
        System.out.println(navigatePages);
        for (Product product : all.getList()) {
            System.out.println(product);
        }
        return all;
    }


    /*@PostMapping("insert")
    @ResponseBody
    public Object insert(@RequestBody String body){
        System.out.println("成功进入插入数据接口");
        JSONObject jsonObject = JSON.parseObject(body);
        Product product = new Product();
        product.setProductNum(jsonObject.getString("productNum"));
        product.setProductName(jsonObject.getString("productName"));
        product.setCityName(jsonObject.getString("cityName"));
        product.setDepartureTime(jsonObject.getTimestamp("departureTime"));
        product.setProductPrice(jsonObject.getBigDecimal("productPrice"));
        product.setProductDesc(jsonObject.getString("productDesc"));
        product.setProductStatus(jsonObject.getInteger("productStatus"));
        int i = iProductService.insertProduct(product);
        if (i>=1){
            System.out.println("新增成功！");
        }else {
            System.out.println("新增失败！");
        }
        return "aa";
    }


    @PostMapping("delete")
    @ResponseBody
    public Object delete(@RequestBody String body){
        JSONObject jsonObject = JSON.parseObject(body);
        int id = iProductService.ProductDeleteById(jsonObject.getInteger("id"));
        if (id>=1){
            System.out.println("删除成功！");
        }else {
            System.out.println("删除失败！");
        }
        return "";
    }*/

    @RequestMapping("byid")
    public Object byid(Integer id) {
        System.out.println("成功进入根据Id查询");
        Product product = iProductService.ProductById(id);
        System.out.println("controller返回数据:" + product);
        return "";

    }

}

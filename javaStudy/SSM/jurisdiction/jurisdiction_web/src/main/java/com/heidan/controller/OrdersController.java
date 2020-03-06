package com.heidan.controller;

import com.heidan.entity.Orders;
import com.heidan.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Create by heidan on 2019/12/30 11:26
 */
@Controller
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    private IOrderService iOrderService;

    /*@RequestMapping(value ="ordersbyid",produces = {"application/json;charset=UTF-8"})*/
    @RequestMapping("ordersbyid")
    @ResponseBody
    public Orders ordersById(Integer id) {
        System.out.println("成功进入");
        Orders orders = iOrderService.ordersById(id);
        System.out.println("controller返回查询结果:" + orders);
        /*String string = JSON.toJSONString(orders);*/
        return orders;
    }
}

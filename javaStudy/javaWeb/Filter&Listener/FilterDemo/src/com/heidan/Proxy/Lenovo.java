package com.heidan.Proxy;

/**
 * Create by heidan on 2019/11/24 13:48
 */

public class Lenovo implements SaleComputer {
    @Override
    public String sale(double money) {

        System.out.println("小红花了" + money +"元，买了一个联想电脑");
        return "联想电脑";
    }

    @Override
    public void Show() {
        System.out.println("show......");
    }
}

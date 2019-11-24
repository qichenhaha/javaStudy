package com.heidan.Proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Create by heidan on 2019/11/24 13:50
 */

public class ProxyTest {

    @Test
    public void  a(){
        // 1.创建一个真实对象
        Lenovo lenovo = new Lenovo();



        // 2.动态代理增强lenovo对象

        /**
         *  三个参数
         *     1.类加载器： 真实对象.getClass().getClassLoader()
         *     2.接口数组   真实对象.getClass().getInterfaces()
         *     3.处理器    new InvocationHandler()
         *
         */
        SaleComputer Proxy_lenvov = (SaleComputer) Proxy.newProxyInstance(lenovo.getClass().getClassLoader(), lenovo.getClass().getInterfaces(), new InvocationHandler() {

            /*代理逻辑编写的方法：代理对象调用所有方法都会触发该方法执行*/
                /*参数： 1.proxy：代理对象*/
                /*参数： 2.method：代理对象调用方法，被封装到该对象中*/
                /*参数： 3.args： 代理对象调用方法时，传递的实际参数*/
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("这个方法执行了");

                if (method.getName().equals("sale")){
                    double moery = (double) args[0];
                    moery = moery * 0.85;
                    Object invoke = method.invoke(lenovo, moery);
                    return invoke + "_鼠标垫";
                }else {
                    Object invoke = method.invoke(lenovo, args);
                    return invoke;
                }

            }
        });

        // 2.调用方法
        String sale = Proxy_lenvov.sale(100);
        System.out.println(sale);
    }
}

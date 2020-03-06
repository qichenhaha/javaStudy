package com.heidan.ui;

import com.heidan.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Create by heidan on 2019/12/21 14:29
 */

public class account {

    /*
     *  获取spring的IOC容器，并根据id获取对象
     *   ApplicationContext 三大常用实现类
     *   ClassPathXmlApplicationContext： 它可以加载类路径下的配置文件，要配置文件必须在类路径下，不在的话加载不了
     *   FileSystemXmlApplicationContext: 它可以加载磁盘任意路径下的配置文件（必须有访问权限）
     *   AnnotationConfigApplicationContext：它是用于读取注解创建容器
     * */
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService iAccountService = (IAccountService) ac.getBean("insar");
        iAccountService.saveAccount();
        System.out.println(iAccountService);

    }
}

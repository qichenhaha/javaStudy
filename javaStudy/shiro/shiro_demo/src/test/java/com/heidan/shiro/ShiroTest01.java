package com.heidan.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;
import org.junit.Test;

/**
 * Create by heidan on 2020/1/9 15:08
 */

public class ShiroTest01 {

    /**
     * 测试用户登陆
     *  认证:用户登陆登陆
     *      1.根据配置文件创建SecurityManagerFactory
     *      2.通过工厂获取SecurityManager
     *      3.讲SecurityManager绑定到当前运行环境中
     *      4.从当前运行环境中构造 subject
     *      5.构造shiro登陆的数据
     *      6.主体登陆
     *
     */
    @Test
    public void testLogin(){
        // 1.根据配置文件创建SecurityManagerFactory
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-test01.ini");
        //  2.通过工厂获取SecurityManager
        SecurityManager instance = factory.getInstance();
        // 3.讲SecurityManager绑定到当前运行环境中
        SecurityUtils.setSecurityManager(instance);
        // 4.从当前运行环境中构造 subject
        Subject subject = SecurityUtils.getSubject();
        // 5.构造shiro登陆的数据

        String username = "zhangsan";
        String password = "1234567";

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        subject.login(token);

        System.out.println("是否登陆成功="+subject.isAuthenticated());

    }

}

package com.heidan.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Before;
import org.junit.Test;

/**
 * Create by heidan on 2020/1/9 15:08
 */

public class ShiroTest02 {

    private SecurityManager securityManager;

   @Before
   public void init(){
       // 1.根据配置文件创建SecurityManagerFactory
       Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-test02.ini");
       //  2.通过工厂获取SecurityManager
       securityManager = factory.getInstance();
       // 3.讲SecurityManager绑定到当前运行环境中
       SecurityUtils.setSecurityManager(securityManager);
       // 4.从当前运行环境中构造 subject

   }

    @Test
    public void testLogin(){
        Subject subject = SecurityUtils.getSubject();
        // 5.构造shiro登陆的数据

        String username = "zhangsan";
        String password = "123123";

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        subject.login(token);

        System.out.println(subject.hasRole("role1"));
        System.out.println(subject.isPermitted("user:save"));

        System.out.println("是否登陆成功="+subject.isAuthenticated());

    }

}

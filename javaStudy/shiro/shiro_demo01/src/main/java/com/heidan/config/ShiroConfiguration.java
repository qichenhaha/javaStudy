package com.heidan.config;

import com.heidan.realm.CustomRealm;
import com.heidan.session.CustomSessionManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Create by heidan on 2020/1/10 14:44
 */

public class ShiroConfiguration {

    // 1.创建realm
    @Bean
    public CustomRealm getRealm(){
        return new CustomRealm();
    }

    // 2.创建安全管理器
    @Bean
    public SecurityManager getsecurityManager(CustomRealm customRealm){

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(customRealm);

        // 讲自定义的会话管理器注册到安全管理器中
        securityManager.setSessionManager(defaultWebSessionManager());

        // 讲自定义的redis缓存管理器注册到安全管理器中
        securityManager.setCacheManager(redisCacheManager());

        return securityManager;
    }

    // 3.配置shiro过滤器工厂


    /**
     * 在web程序中，shiro进行权限控制，全部是通过一组过滤器集合进行控制这一组过滤器集合进行指定
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        // 1.创建过滤器工厂
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        // 2.设置安全管理器
        filterFactoryBean.setSecurityManager(securityManager);
        // 3.通用的配置（跳转登陆页面，为授权跳转页面）
        filterFactoryBean.setLoginUrl("/user/autherror?code=1"); // 跳转一个url地址
        filterFactoryBean.setUnauthorizedUrl("/user/autherror?code=2"); // 为授权的url
        // 4.设置过滤器集合
    /**
     * key ：访问连接
     * 支持通配符的形式
     * value：过滤器类型
     * shiro常用过滤器
     * anno ：匿名访问（表明此链接所有人可以访问）
     * authc ：认证后访问（表明此链接需登录认证成功之后可以访问）
     */
        Map<String,String> filterMap = new LinkedHashMap<String,String>();
        // 配置不会被拦截的链接 顺序判断
        filterMap.put("/user/home", "anon");
        filterMap.put("/user/autherror", "anon");
        filterMap.put("/user/login", "anon");
        filterMap.put("/user/**", "authc");
        //5.设置过滤器
        filterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return filterFactoryBean;
    }


    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }


    // 开启对shiro对主键的支持
    @Bean
    public AuthorizationAttributeSourceAdvisor
    authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new
                AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    /**
     * 1.redis的控制器，操作redis
     */
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        return redisManager;
    }


    /**
     *2.sessionDao
     */
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * 3.会话管理器
     */
    public DefaultWebSessionManager defaultWebSessionManager(){
        CustomSessionManager customSessionManager = new CustomSessionManager();
        customSessionManager.setSessionDAO(redisSessionDAO());
        return customSessionManager;
    }


    /**
     * 4.缓存管理器
     */
    public RedisCacheManager redisCacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

}

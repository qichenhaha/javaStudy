package com.heidan.config;

import com.heidan.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Create by heidan on 2020/1/9 13:08
 *
 * 拦截器
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * addInterceptor：需要一个实现HandlerInterceptor接口的拦截器实例
         */
        InterceptorRegistration registration = registry.addInterceptor(new JwtInterceptor());
        /**
         * addPathPatterns：用于设置拦截器的过滤路径规则；addPathPatterns("/**")对所有请求都拦截
         */
        registration.addPathPatterns("/**");
        /**
         * excludePathPatterns：用于设置不需要拦截的过滤规则
         */
        registration.excludePathPatterns("/user/login"); // 指定不拦截URL


    }

}

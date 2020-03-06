package com.heidan.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Create by heidan on 2019/12/23 13:48
 */
@Component("logger")
@Aspect
public class TestLogger {
    private final static Logger log = Logger.getLogger(TestLogger.class);

    @Before("execution(* com.heidan.service.*.*(..))")
    public void before(JoinPoint jp) {
        log.info("用户正在请求," + jp.getTarget() + "控制器中的" + jp.getSignature().getName() + "方法");
        log.info("拿到的参数" + Arrays.toString(jp.getArgs()));
    }

    @AfterReturning(value = "execution(* com.heidan.service.*.*(..))", returning = "result")
    public void afterReturning(JoinPoint jd, Object result) {
        log.info("调用" + jd.getTarget() + "的" + jd.getSignature().getName() + "方法");
        log.info("返回值是" + result);
    }

}

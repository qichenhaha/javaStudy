package com.heidan.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Create by heidan on 2019/12/23 13:48
 */
@Component("logger")
@Aspect
public class TestLogger {
    private final static Logger log = Logger.getLogger(TestLogger.class);

    //  环绕通知
    @Around("execution(* com.heidan.dao.*.*(..))")
    public void around(ProceedingJoinPoint jp) {
        log.info("调用" + jp.getTarget() + "的" + jp.getSignature().getName() + "方法");
        log.info("方法参数:" + Arrays.toString(jp.getArgs()));
        try {
            Object result = jp.proceed();
            log.info("调用" + jp.getTarget() + "的" + jp.getSignature().getName() + "方法" + ",返回值是" + result);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.info("方法:" + jp.getSignature().getName() + "发生异常" + throwable);
        } finally {
            log.info("方法" + jp.getSignature().getName() + "执行结束");
        }

    }
}

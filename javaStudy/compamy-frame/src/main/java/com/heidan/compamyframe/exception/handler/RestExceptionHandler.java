package com.heidan.compamyframe.exception.handler;

import com.heidan.compamyframe.exception.BusinessException;
import com.heidan.compamyframe.exception.code.BaseResponseCode;
import com.heidan.compamyframe.utils.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Create by heidan on 2020/1/15 16:33
 */
@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    public DataResult exception(ArithmeticException e){
        e.printStackTrace();
        log.error("Exception,{}",e.getLocalizedMessage());
        return DataResult.getResult(BaseResponseCode.SYSTEM_ERROR.getCode(),"算数异常");
    }

    @ExceptionHandler(BusinessException.class)
    public DataResult businessException(BusinessException e){
        log.error("Exception,{}",e.getDefaultMessage());
        return DataResult.getResult(e.getCode(),e.getDefaultMessage());
    }

    // 没有权限统一异常处理
    @ExceptionHandler(AuthorizationException.class)
    public DataResult authorizationException(AuthorizationException e){
        log.error("Exception,{}",e.getLocalizedMessage());
        return DataResult.getResult(BaseResponseCode.SHIRO_AUTHORIZATION_EOORO.getCode(),BaseResponseCode.SHIRO_AUTHORIZATION_EOORO.getMsg());
    }


}

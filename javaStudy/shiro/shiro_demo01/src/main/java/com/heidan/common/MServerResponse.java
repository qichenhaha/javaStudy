package com.heidan.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.heidan.enums.ResultEnum;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//保证序列化json的时候，如果是null的对象，key也会消失
public class MServerResponse<T> implements Serializable {

    private int errno;

    private String errmsg;

    private T data;

    private MServerResponse(int errno){
        this.errno=errno;
    }

    private MServerResponse(int errno, T data){
        this.errno=errno;
        this.data=data;
    }

    public MServerResponse(int errno, String errmsg, T data){
        this.errno=errno;
        this.errmsg=errmsg;
        this.data=data;
    }

    private MServerResponse(int errno, String errmsg){
        this.errno=errno;
        this.errmsg=errmsg;
    }

    @JsonIgnore
    //使之不在json序列化结果当中
    public boolean issuccess(){
        return this.errno== ResultEnum.SUCCESS.getCode();
    }

    public int getErrno(){
        return errno;
    }

    public T getData(){
        return data;
    }

    public String getErrmsg(){
        return errmsg;
    }

    public static <T> MServerResponse<T> createBySuccess(){
        return new MServerResponse<T>(ResultEnum.SUCCESS.getCode());
    }

    public static <T> MServerResponse<T> createBySuccessMessage(String msg){
        return new MServerResponse<T>(ResultEnum.SUCCESS.getCode(),msg);
    }

    public static <T> MServerResponse<T> createBySuccess(T data){
        return new MServerResponse<T>(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage(),data);
    }

    public static <T> MServerResponse<T> createBySuccess(String msg, T data){
        return new MServerResponse<T>(ResultEnum.SUCCESS.getCode(),msg,data);
    }

    public static <T> MServerResponse<T> createByErrorMessage(String errorMessage){
        return new MServerResponse<T>(ResultEnum.PARAM_ERROR.getCode(),errorMessage);
    }

    public static <T> MServerResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage){
        return new MServerResponse<T>(errorCode,errorMessage);
    }
}



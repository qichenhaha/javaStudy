package com.heidan.compamyframe.controller;

import com.heidan.compamyframe.exception.BusinessException;
import com.heidan.compamyframe.exception.code.BaseResponseCode;
import com.heidan.compamyframe.utils.DataResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by heidan on 2020/1/15 15:34
 */
@RestController
@RequestMapping("/test")
@Api(tags = "测试接口")
public class TestController {

    @GetMapping("/index")
    @ApiOperation("首页")
    public DataResult testResult(){
        DataResult<String>  result = DataResult.success("测试成功！");
        int l = 1/0;
        return result;
    }

    @GetMapping("/indexs")
    @ApiOperation("副页")
    public DataResult testResults(){
        DataResult<String>  result = DataResult.success("测试成功！");
        if (true){
            throw new BusinessException(BaseResponseCode.ACCOUNT_LOCK);
        }
        return result;
    }
}

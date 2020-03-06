package com.heidan.compamyframe.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Create by heidan on 2020/1/16 9:35
 */
@Data
public class LoginReqVo {

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "登陆类型 1:PC  2:APP")
    private String type;

}

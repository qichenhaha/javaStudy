package com.heidan.compamyframe.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Create by heidan on 2020/1/16 9:37
 */
@Data
public class LoginRespVO {

    @ApiModelProperty(value = "正常的业务token")
    private String accessToken;

    @ApiModelProperty(value = "刷新的token")
    private String refreshToken;

    @ApiModelProperty(value = "用户id")
    private String id;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "用户名")
    private String username;

}

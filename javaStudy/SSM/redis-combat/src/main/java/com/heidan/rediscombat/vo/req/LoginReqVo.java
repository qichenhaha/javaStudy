package com.heidan.rediscombat.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Create by heidan on 2020/1/13 15:24
 */
@Data
public class LoginReqVo {
    @ApiModelProperty("用户昵称")
    private String username;
    @ApiModelProperty("用户密码")
    private String password;
}

package com.heidan.shirocombat.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Create by heidan on 2020/1/14 15:58
 */
@Data
public class LoginReqVo {
    @ApiModelProperty("账户")
    private String username;
    @ApiModelProperty("密码")
    private String password;
}

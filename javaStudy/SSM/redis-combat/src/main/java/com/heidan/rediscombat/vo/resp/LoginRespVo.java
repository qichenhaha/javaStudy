package com.heidan.rediscombat.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Create by heidan on 2020/1/13 15:26
 */
@Data
public class LoginRespVo {
    @ApiModelProperty("用户id")
    private String id;
    @ApiModelProperty("用户密码")
    private String token;
}

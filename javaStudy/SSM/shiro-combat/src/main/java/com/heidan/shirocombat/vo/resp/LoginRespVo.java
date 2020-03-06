package com.heidan.shirocombat.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Create by heidan on 2020/1/14 16:02
 */
@Data
public class LoginRespVo {

    @ApiModelProperty("用户id")
    private String id;

    @ApiModelProperty("凭证")
    private String sessionId;
}

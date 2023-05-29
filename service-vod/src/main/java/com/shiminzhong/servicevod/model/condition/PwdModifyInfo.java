package com.shiminzhong.servicevod.model.condition;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PwdModifyInfo {

    @ApiModelProperty(value = "账号类型")
    private String accountType;

    @ApiModelProperty(value = "账号")
    private String accountNumber;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "验证码")
    private String verifyCoder;

}

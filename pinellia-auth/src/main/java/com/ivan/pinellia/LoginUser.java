package com.ivan.pinellia;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p></p>
 *
 * @author ivan
 * @className LoginUser
 * @since 2020/11/15 20:39
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class LoginUser {

    @ApiModelProperty("客户端ID")
    private String clientId;

    @ApiModelProperty("客户端密钥")
    private String clientSecret;

    @ApiModelProperty("状态")
    private String state;

    @ApiModelProperty("范围")
    private String scope;

    @ApiModelProperty("重定向URI")
    private String redirectUri;

    @ApiModelProperty("返回类型")
    private String responseType;

    @ApiModelProperty("oauth2类型")
    private String grantType;

    @ApiModelProperty("用户账户")
    private String username;

    @ApiModelProperty("用户密码")
    private String password;

}
